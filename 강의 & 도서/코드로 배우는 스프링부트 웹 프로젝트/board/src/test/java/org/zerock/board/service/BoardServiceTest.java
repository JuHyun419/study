package org.zerock.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.board.dto.BoardDto;
import org.zerock.board.dto.PageRequestDto;
import org.zerock.board.dto.PageResponseDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void testRegister() {
        /* given */
        BoardDto dto = BoardDto.builder()
                .title("Test..")
                .content("Test Content..")
                .writerEmail("user55@aaa.com")
                .build();

        /* when */
        Long bno = boardService.register(dto);

        /* then */
    }

    @Test
    void testList() {
        /* given */
        PageRequestDto pageRequestDto = new PageRequestDto();

        /* when */
        PageResponseDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);

        /* then */
        for (BoardDto boardDto : result.getDtoList()) {
            System.out.println(boardDto);
        }
    }

    @Test
    void testGet() {
        /* given */
        Long bno = 100L;

        /* when */
        BoardDto boardDto = boardService.get(bno);

        /* then */
        assertThat(boardDto.getBno()).isEqualTo(bno);
    }

    @Test
    void testDelete() {
        /* given */
        Long bno = 3L;

        /* when */
        boardService.deleteWithReplies(bno);

        /* then */
        assertThatThrownBy(() -> boardService.get(bno))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testUpdate() {
        /* given */
        Long bno = 5L;
        BoardDto boardDto = BoardDto.builder()
                .bno(bno)
                .title("Update Title....")
                .content("Update Content..")
                .build();

        /* when */
        boardService.update(boardDto);
        BoardDto newBoard = boardService.get(bno);

        /* then */
        assertThat(newBoard.getTitle()).isEqualTo("Update Title....");
        assertThat(newBoard.getContent()).isEqualTo("Update Content..");
    }
}