package org.zerock.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    void insertReply() {
        IntStream.rangeClosed(1, 300)
                .forEach(i -> {
                    long bno = (long) (Math.random() * 100) + 1;
                    Board board = Board.builder().bno(bno).build();
                    Reply reply = Reply.builder()
                            .text("Reply..." + i)
                            .board(board)
                            .replyer("guest")
                            .build();

                    replyRepository.save(reply);
                });
    }

    @Test
    @DisplayName("")
    void readReply1() {
        /* given */
        Optional<Reply> result = replyRepository.findById(1L);

        /* when */
        Reply reply = result.get();

        /* then */
        System.out.println(reply);
        System.out.println(reply.getBoard());
    }

    @Test
    @DisplayName("")
    void testListByBoard() {
        /* given */
        Board board = Board.builder()
                .bno(97L)
                .build();

        /* when */
        List<Reply> replyList = replyRepository.getRepliesByBoardOrderByRno(board);

        /* then */
        replyList.forEach(System.out::println);
    }
}