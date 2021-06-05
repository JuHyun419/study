package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("제목 등록");
		board.setContent("내용 등록");
		board.setWriter("newbie");
		
		service.register(board);
		log.info("testRegister ... board.getBno(): " + board.getBno());
	}
	
	@Test
	public void testGetList() {
//		service.getList().forEach(board -> log.info(board));
		
		service.getList(new Criteria(2, 10)).forEach(board -> log.info(board));
	}
	
	@Test
	public void testGet() {
		log.info(service.get(13L));
	}
	
	@Test
	public void testDelete() {
		log.info("delete.........." + service.remove(14L));
	}
	
	@Test
	public void testUpdate() {
		BoardVO board = service.get(15L);
		
		if(board == null) {
			return;
		}
		board.setTitle("제목 수정합니다...");
		board.setContent("내용 수정합니다...");
		log.info("modify........." + service.modify(board));
	}

}
