package org.zerock.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.board.dto.BoardDto;
import org.zerock.board.dto.PageRequestDto;
import org.zerock.board.dto.PageResponseDto;
import org.zerock.board.entity.Board;
import org.zerock.board.entity.Member;
import org.zerock.board.repository.BoardRepository;
import org.zerock.board.repository.ReplyRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public Long register(BoardDto dto) {
        Board board = BoardDto.dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBno();
    }

    public PageResponseDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
        Function<Object[], BoardDto> fn = (en ->
                BoardDto.entityToDto((Board) en[0], (Member) en[1], (Long) en[2]));

//        Page<Object[]> result = boardRepository.getBoardWithReplyCount(
//                pageRequestDto.getPageable(Sort.by("bno").descending())
//        );

        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDto.getType(),
                pageRequestDto.getKeyword(),
                pageRequestDto.getPageable(Sort.by("bno").descending())
        );

        return new PageResponseDto<>(result, fn);
    }

    public BoardDto get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;
        return BoardDto.entityToDto((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Transactional
    public void deleteWithReplies(Long bno) {
        // 1) 해당 게시물의 FK로 참조하고 있는 모든 댓글 삭제
        replyRepository.deleteByBno(bno);

        // 2) 해당 게시물 삭제
        boardRepository.deleteById(bno);
    }

    public void update(BoardDto boardDto) {
        boardRepository.findById(boardDto.getBno())
                .ifPresent(board -> {
                    board.changeTitle(boardDto.getTitle());
                    board.changeContent(boardDto.getContent());
                    boardRepository.save(board);
                });
    }
}
