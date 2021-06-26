package org.zerock.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("연관관계를 가진 두 엔티티에 CASCADE 속성을 설정하면 정상적으로 save()메소드가 수행된다.")
    void insertBoard() {
        IntStream.rangeClosed(401, 450)
                .forEach(i -> {
                    Member member = Member.builder().email("user" + i + "@aaa.com").build();
                    Board board = Board.builder()
                            .title("Title..." + i)
                            .content("Content..." + i)
                            .member(member)
                            .build();

                    boardRepository.save(board);
                });
    }

    @Transactional
    @Test
    void testRead1() {
        /* given */
        Optional<Board> result = boardRepository.findById(100L);

        /* when */
        Board board = result.get();

        /* then */
        System.out.println(board);
        System.out.println(board.getMember());
    }

    @Test
    void testReadWithMember() {
        /* given */
        Object result = boardRepository.getBoardWithMember(100L);

        /* when */
        Object[] arr = (Object[]) result;

        /* then */
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void testGetBoardWithReply() {
        /* given */
        List<Object[]> result = boardRepository.getBoardWithReply(100L);

        /* when & then */
        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }
    }

    @Test
    void testWithReplyCount() {
        /* given */
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        /* when */
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        /* then */
        result.get().forEach(row -> System.out.println(Arrays.toString(row)));
    }

    @Test
    void testRead3() {
        /* given & when */
        Object result = boardRepository.getBoardByBno(100L);
        Object[] arr = (Object[]) result;

        /* then */
        System.out.println(Arrays.toString(arr));
    }

    @Test
    void testSearch1() {
        /* given */


        /* when */


        /* then */
        boardRepository.search1();
    }

    @Test
    void testSearch2() {
        /* given */


        /* when */


        /* then */
        boardRepository.search2();
    }

    @Test
    void testSearch3() {
        /* given */


        /* when */


        /* then */
        boardRepository.search3();
    }

    @Test
    void testSearchPage() {
        /* given */
        Pageable pageable =
                PageRequest.of(0, 10,
                        Sort.by("bno")
                                .descending()
                                .and(Sort.by("title").ascending()));

        /* when */
        Page<Object[]> result = boardRepository.searchPage("tw", "1", pageable);

        /* then */
    }

}