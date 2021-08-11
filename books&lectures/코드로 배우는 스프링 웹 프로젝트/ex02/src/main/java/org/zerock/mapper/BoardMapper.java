package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	//@Select("SELECT * FROM tbl_board WHERE bno > 0")
	public List<BoardVO> getList();
	
	// INSERT만 실행
	public void insert(BoardVO board);
	
	// INSERT 실행 후 생성된 PK값도 알아야 하는 경우
	public void insertSelectKey(BoardVO board);
	
	// READ(SELECT) 처리
	public BoardVO read(Long bno);
	
	// DELETE 처리
	public int delete(Long bno);
	
	// UPDATE 처리
	public int update(BoardVO board);
	
	// 페이징 처리
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	// 전체 데이터 갯수 조회
	public int getTotalCount(Criteria cri);
	
}
