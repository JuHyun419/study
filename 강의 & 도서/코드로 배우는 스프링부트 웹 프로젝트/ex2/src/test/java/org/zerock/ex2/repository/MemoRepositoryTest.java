package org.zerock.ex2.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.ex2.entity.Memo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    void testClass() {
        System.out.println(memoRepository.getClass().getSimpleName());
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    void insert_Dummy_Datas() {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    Memo memo = Memo.builder()
                            .memoText("Sample... " + i)
                            .build();
                    memoRepository.save(memo);
                });
    }

    @Test
    void select() {
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    @Test
    void update() {
        // given
        final Long id = 100L;
        Memo memo = Memo.builder().mno(id).memoText("Update Test").build();

        // when
        Memo update = memoRepository.save(memo);

        // then
        assertThat(update.getMno()).isEqualTo(id);
        assertThat(update.getMemoText()).isEqualTo("Update Test");
    }

    @Test
    void delete() {
        // given
        Long id = 100L;

        // when
        memoRepository.deleteById(id);

        // then
        assertThat(memoRepository.findById(id)).isEmpty();
    }

    @Test
    void page_Default() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Memo> result = memoRepository.findAll(pageable);
        final int pages = result.getTotalPages();

        // then
        System.out.println(result);
        assertThat(pages).isEqualTo(10);
    }

    @Test
    void page_method() {
        // given
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<Memo> result = memoRepository.findAll(pageable);

        // then
        System.out.println(result);
        System.out.println("=================================");
        System.out.println("Total Pages: " + result.getTotalPages()); // 전체 개수
        System.out.println("Page Number: " + result.getNumber());     // 현재 페이지 번호 0부터 시작
        System.out.println("Page Size: " + result.getSize());         // 페이지당 데이터 개수
        System.out.println("has next page? " + result.hasNext());     // 다음 페이지 존재 여부
        System.out.println("first page? " + result.isFirst());         // 시작 페이지(0) 여부
    }

    @Test
    @DisplayName("페이징 처리를 정렬과 함께 한다.")
    void page_with_sort() {
        // given
        Sort sort = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort);

        // when
        Page<Memo> result = memoRepository.findAll(pageable);

        // then
        result.get().forEach(System.out::println);
    }

    @Test
    @DisplayName("Between 데이터로 조회한다")
    void queryMethod() {
        final Long from = 70L;
        final Long to = 80L;
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(from, to);

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    @DisplayName("Between 데이터를 Pageable 객체와 함께 조회한다")
    void queryMethod_With_Pageable() {
        final Long from = 10L;
        final Long to = 50L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(from, to, pageable);

        result.get().forEach(System.out::println);
    }

    @Commit
    @Transactional
    @Test
    @DisplayName("num 이하의 데이터를 모두 삭제한다")
    void delete_Query_Method() {
        memoRepository.deleteMemoByMnoLessThan(10L);
    }

    @Test
    @DisplayName("")
    void test() {
        // given


        // when


        // then
    }
}