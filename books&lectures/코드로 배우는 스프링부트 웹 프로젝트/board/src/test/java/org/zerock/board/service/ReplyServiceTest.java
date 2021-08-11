package org.zerock.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.ReplyDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;

    @Test
    @DisplayName("")
    void testGetList() {
        /* given */
        Long bno = 100L;

        /* when */
        List<ReplyDto> replyDtoList = replyService.getList(bno);

        /* then */
        assertThat(replyDtoList.size()).isNotEqualTo(0);
        replyDtoList.forEach(System.out::println);
    }
}