package org.zerock.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long bno;
    private String title;
    private String content;
    private String writerEmail; // 작성자 이메일(ID)
    private String writerName;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount;

    public static Board dtoToEntity(BoardDto dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(member)
                .build();
    }

    public static BoardDto entityToDto(Board board, Member member, Long replyCount) {
        return BoardDto.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())
                .build();
    }
}
