package org.zerock.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GuestbookRepositoryTest {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    @DisplayName("더미 데이터를 삽입한다.")
    void insert_Dummies() {
        IntStream.rangeClosed(1, 300)
                .forEach(i -> {
                    Guestbook guestbook = Guestbook.builder()
                            .title("Title ... " + i)
                            .content("Content ... " + i)
                            .writer("user" + (i % 10))
                            .build();
                    System.out.println(guestbookRepository.save(guestbook));
                });
    }

    @Test
    @DisplayName("Guestbook의 Title, Content 수정 테스트")
    void update_Test() {
        /* given */
        final Long id = 250L;
        final String updateTitle = "Change Title!";
        final String updateContent = "Change Content!!";
        final Guestbook guestbook = guestbookRepository.findById(id).orElseThrow(NullPointerException::new);

        /* when */
        guestbook.changeTitle(updateTitle);
        guestbook.changeContent(updateContent);
        guestbookRepository.save(guestbook);
        final Guestbook updatedGuestbook = guestbookRepository.findById(id).orElseThrow(NullPointerException::new);

        /* then */
        assertThat(updatedGuestbook.getGno()).isEqualTo(250L);
        assertThat(updatedGuestbook.getTitle()).isEqualTo(updateTitle);
        assertThat(updatedGuestbook.getContent()).isEqualTo(updateContent);
    }

    @Test
    @DisplayName("Querydsl로 title 검색 조건을 처리한다.")
    void test_Querydsl1() {
        /* given */
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = "20";
        // BooleanBuilder -> Where 문에 들어가는 조건들을 넣어주는 컨테이너
        BooleanBuilder builder = new BooleanBuilder();

        /* when */
        BooleanExpression expression = qGuestbook.title.contains(keyword);
        builder.and(expression);
        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        /* then */
        result.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("Querydsl로 다중 항목 검색 조건을 처리한다.")
    void test_Querydsl2() {
        /* given */
        Pageable pageable = PageRequest.of(0, 50, Sort.by("gno").ascending());
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String titleKeyword = "10";
        String contentKeyword = "20";
        BooleanBuilder builder = new BooleanBuilder();

        /* when */
        BooleanExpression exTitle = qGuestbook.title.contains(titleKeyword);
        BooleanExpression exContent = qGuestbook.content.contains(contentKeyword);
        BooleanExpression exAll = exTitle.or(exContent);
        builder.and(exAll);
        builder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        /* then */
        result.stream().forEach(System.out::println);
    }

}