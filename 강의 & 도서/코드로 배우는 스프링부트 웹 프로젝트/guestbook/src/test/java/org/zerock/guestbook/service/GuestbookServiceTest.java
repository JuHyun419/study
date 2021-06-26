package org.zerock.guestbook.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.dto.PageResponseDto;
import org.zerock.guestbook.entity.Guestbook;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookServiceTest {

    @Autowired
    private GuestbookService guestbookService;

    @Test
    @DisplayName("게시물 등록 테스트")
    void guestbook_register() {
        /* given & when */
        GuestbookDto dto = GuestbookDto.builder()
                .title("Sample Title..")
                .content("Sample Content..")
                .writer("JuHyun00")
                .build();

        /* then */
        System.out.println(guestbookService.register(dto));
    }

    @Test
    @DisplayName("Entity 객체가 Dto 객체로 변환이 된다.")
    void entity_to_Dto() {
        /* given & when */
        PageRequestDto pageRequestDto = PageRequestDto.builder().page(1).size(10).build();
        PageResponseDto<GuestbookDto, Guestbook> responseDto = guestbookService.getList(pageRequestDto);

        /* then */
        for (GuestbookDto guestbookDto : responseDto.getDtoList()) {
            System.out.println(guestbookDto);
        }
    }

    @Test
    @DisplayName("페이징 처리 테스트")
    void paging() {
        /* given */
        PageRequestDto pageRequestDto = PageRequestDto.builder().page(1).size(10).build();

        /* when */
        PageResponseDto<GuestbookDto, Guestbook> responseDto = guestbookService.getList(pageRequestDto);

        /* then */
        assertThat(responseDto.isPrev()).isFalse();
        assertThat(responseDto.isNext()).isTrue();
        assertThat(responseDto.getTotalPage()).isEqualTo(31);
        responseDto.getPageList().forEach(System.out::println);
    }

    @Test
    @DisplayName("1페이지부터 시작하는 페이징 처리는 이전 링크가 존재하지 않는다.")
    void sholud_Not_Exist_Prev_When_First_Page() {
        /* given */
        PageRequestDto pageRequestDto = PageRequestDto.builder().page(1).size(10).build();

        /* when */
        PageResponseDto<GuestbookDto, Guestbook> responseDto = guestbookService.getList(pageRequestDto);

        /* then */
        assertThat(responseDto.isPrev()).isEqualTo(false);
    }

    @Test
    @DisplayName("키워드가 존재하지 않는 데이터는 검색이 되지 않는다.")
    void test_Not_Search() {
        /* given */
        PageRequestDto requestDto = PageRequestDto.builder().page(1)
                .size(10)
                .type("tc")     // title, content
                .keyword("한글") // 검색 키워드
                .build();

        /* when */
        PageResponseDto<GuestbookDto, Guestbook> responseDto = guestbookService.getList(requestDto);

        /* then */
        assertThat(responseDto.getDtoList().size()).isEqualTo(0);
        responseDto.getPageList().forEach(System.out::println);
    }

    @Test
    @DisplayName("키워드가 존재하는 데이터는 검색이 정상적으로 처리된다.")
    void test_Ok_Search() {
        /* given */
        PageRequestDto requestDto = PageRequestDto.builder().page(1)
                .size(10)
                .type("tw")     // title, content
                .keyword("JuHyun") // 검색 키워드
                .build();

        /* when */
        PageResponseDto<GuestbookDto, Guestbook> responseDto = guestbookService.getList(requestDto);

        /* then */
        assertThat(responseDto.getDtoList().size()).isNotEqualTo(0);
        responseDto.getPageList().forEach(System.out::println);
    }
}