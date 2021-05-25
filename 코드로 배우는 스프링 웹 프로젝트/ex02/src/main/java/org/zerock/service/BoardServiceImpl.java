package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service	// 비즈니스 영역을 담당하는 객체임을 표시.
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	
	@Override
	public void register(BoardVO board) {
		log.info("register....." + board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get................");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify..........." + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove.............." + bno);
		return mapper.delete(bno) == 1;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.................");
//		
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with Criteria: " + cri);
		
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count ...");
		
		return mapper.getTotalCount(cri);
	}

}
