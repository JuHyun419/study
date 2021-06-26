package org.zerock.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyDto {

    private Long rno;

    private String text;

    private String replyer;

    private Long bno;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    public static Reply dtoToEntity(ReplyDto dto) {
        Board board = Board.builder().
                bno(dto.getBno())
                .build();

        return Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
    }

    public static ReplyDto entityToDto(Reply entity) {
        return ReplyDto.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyer(entity.getReplyer())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    }
}
