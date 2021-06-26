package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.zerock.board.dto.ReplyDto;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Reply;
import org.zerock.board.repository.ReplyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    public Long register(ReplyDto replyDto) {
        Reply reply = ReplyDto.dtoToEntity(replyDto);
        replyRepository.save(reply);

        return reply.getRno();
    }

    public List<ReplyDto> getList(Long bno) {
        Board board = Board.builder().bno(bno).build();
        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(board);

        return result.stream()
                .map(ReplyDto::entityToDto)
                .collect(Collectors.toList());
    }

    public void modify(ReplyDto replyDto) {
        Reply reply = ReplyDto.dtoToEntity(replyDto);

        replyRepository.save(reply);
    }

    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
