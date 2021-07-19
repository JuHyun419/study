## M:N(다대다) 관계와 파일 업로드 처리
  - 영화(Movie), 회원(Member) 다대다 관계
  - 대표적인 M:N(다대다) 관계
    - 학생과 수업: 한 명의 학생은 여러 수업에 참여하고, 하나의 수업은 여러 학생이 수강
    - 상품과 상품 카테고리: 하나의 상품은 여러 카테고리에 속하고, 하나의 카테고리는 여러 상품을 가지고 있다.
    - 상품과 회원: 하나의 상품은 여러 회원이 구매할 수 있고, 한 명의 회원은 여러 상품을 구매할 수 있다.
  - M:N(다대다)를 해결하기 위해 실제 테이블 설계에서는 매핑(mapping) 테이블을 사용(연결 테이블)
    - 영화 -> 리뷰 <- 회원
  - 매핑 테이블의 특징
    - 매핑 테이블의 작성 이전에 다른 테이블들이 먼저 존재해야 함
    - 매핑 테이블은 주로 '명사'가 아닌 '동사'나 '히스토리'에 대한 데이터를 보관하는 용도
    - 매핑 테이블은 중간에서 양쪽의 PK를 참조하는 형태로 사용됨
  - JPA에서 M:N을 처리하는 방식은 크게 두 가지
    - @ManyToMany를 이용해 처리
    - 별도의 엔티티를 설계하고, @ManyToOne을 이용해서 처리하는 방식
  - 실무에서 가능하면 '단방향 참조'(위 두 번째 방식)을 위주로 프로젝트를 진행함
    - JPA의 실행에서 가장 중요한 것이 현재 메모리상의 엔티티 객체들의 상태와 DB의 상태를 동기화 시키는 점
    - 하나의 객체를 수정하는 경우 다른 객체의 상태를 매번 일치하도록 변경하는 작업은 그다지 간단하지 않음
    
### Entity 설계
  - M:N(다대다) 관계 처리 시 매핑 테이블의 설계는 마지막 단계에서 처리
  - '명사'에 해당하는 클래스를 먼저 설계
  - 매핑 테이블은 주로 '동사'나 '히스토리'를 의미하는 테이블
  - '회원이 영화에 대해서 평점을 준다' -> '평점을 준다'는 행위가 매핑 테이블이 필요한 부분
  - 100명 회원 생성 -> 100개의 영화, 영화 이미지 생성 -> 200개의 영화 리뷰(매핑 테이블) 생성

### 영화 목록 화면
  - 영화, 영화 이미지, 리뷰 수, 평점 평균 출력
  - @EntityGraph: 엔티티의 특정 속성을 같이 로딩하도록 표시하는 어노테이션
    - attributePaths: 로딩 설정을 변경하고 싶은 속성의 이름을 배열로 명시
    - type: @EntityGraph를 어떤 방식으로 적용할 것인지 설정
    - FATCH 속성값은 attributePaths에 명시한 속성은 EAGER로 처리하고, 나머지는 LAZY로 처리
    - LOAD 속성값은 attributePaths에 명시한 속성은 EAGER로 처리하고, 나머지는 엔티티 클래스에 명시되거나 기본 방식으로 처리

```java
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // FETCH => attributePaths에 명시한 속성은 EAGER 처리, 나머지는 LAZY
    // LOAD => attributePaths에 명시한 속성은 EAGER 처리, 나머지는 엔티티 명시된 방식으로 처리
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
```
    
<br>

## 파일 업로드 처리
  - 서블릿 기반으로 처리
  - application.properties 파일에 multipart 관련 추가(파일 처리)

```java        
# 파일 업로드
spring.servlet.multipart.enabled=true
spring.servlet.multipart.location=/Users/juhyun/Desktop/study/files
spring.servlet.multipart.max-request-size=30MB
spring.servlet.multipart.max-file-size=10MB
        
# 파일 업로드 경로
org.zerock.upload.path=/Users/juhyun/Desktop/study/files
```


```java
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.mreview.dto.UploadResultDto;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @Value("${org.zerock.upload.path}")
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDto>> uploadFile(MultipartFile[] uploadFiles) {
        Assert.notNull(uploadFiles, "Files must not be null!");

        List<UploadResultDto> resultDtoList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {
            // 이미지 파일만 업로드
            if (!isImageFile(uploadFile.getContentType())) {
                log.warn("This file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            final String originalName = uploadFile.getOriginalFilename();
            Assert.notNull(originalName, "file is not exist");
            final String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            // 날짜 폴더 생성
            final String folderPath = makeFolder(); // /Users/juhyun/Desktop/study/files/2021/06/28

            // UUID
            final String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_"를 이용해서 구분 => /Users/juhyun/Desktop/study/files/2021/06/28 + uuid_fileName
            final String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(saveName);

            try {
                // 원본 파일 저장
                uploadFile.transferTo(savePath);

                // 썸네일 파일명(s_)
                String thumbnail = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
                File thumbnailFile = new File(thumbnail);

                // 썸네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

                resultDtoList.add(new UploadResultDto(fileName, uuid, folderPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(resultDtoList, HttpStatus.OK);
    }

    private boolean isImageFile(String contentType) {
        return contentType.startsWith("image") || contentType.startsWith("images");
    }

    private String makeFolder() {
        // 2021/06/28
        final String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        final String folderPath = now.replace("//", File.separator);
        // uploadPathFolder path : /Users/juhyun/Desktop/study/files /2021/06/28
        final File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        ResponseEntity<byte[]> result;

        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("decode fileName: " + srcFileName);
            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            // MIME 타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));

            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/remove")
    public ResponseEntity<Boolean> remove(String fileName) {
        String srcFileName;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

```

#### 파일 저장 단계에서는 다음 사항들을 고려해야 함
  - 업로드된 확장자가 이미지만 가능하도록 검사
    - MultipartFile에서 제공하는 getContentType()을 이용해서 처리
  - 동일한 이름의 파일이 업로드 된다면 기존 파일을 덮어쓰는 문제
    - 시간 추가 or UUID 이용
  - 업로드된 파일을 저장하는 폴더의 용량
    - 년/월/일 폴더를 따로 생성해서 파일 저장
    

#### 썸네일(Thumbnail) 처리
  - 업로드된 파일을 저장하고 썸네일 라이브러리(net.coobird Thumbnailator) 를 활용해서 썸네일 파일 생성
  - 썸네일 파일은 맨 앞 's_'를 붙여서 일반 파일과 구분
  - Dto에 썸네일의 Url을 받아오는 메소드 추가 --> <img> 태그로 처리

<br>

## 영화 / 리뷰 프로젝트 적용하기
  - 영화의 등록, 수정은 파일 업로드 기능
  - 회원(Member)은 기존 회원들이 존재한다고 가정하고 데이터베이스에 존재하는 회원 이용
  - 회원(Member)은 특정 영화 조회 페이지에서 평점과 자신의 감상을 리뷰(Review)로 기록할 수 있음
  - 조회 화면에서 회원은 자신이 기록한 리뷰의 내용을 수정/삭제할 수 있음
