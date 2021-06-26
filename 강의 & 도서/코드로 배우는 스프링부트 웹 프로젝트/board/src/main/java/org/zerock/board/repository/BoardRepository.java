package org.zerock.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.board.entity.Board;
import org.zerock.board.repository.search.SearchBoardRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    // 한개의 row(Object) 내에 Objects[]로 반환
    // 내부에 있는 엔티티를 이용할 때는 join에 on을 이용하지 않음
    @Query("select b, w from Board b left join b.member w where b.bno =:bno")
    Object getBoardWithMember(@Param("bno") Long bno);


    @Query("select b, r from Board b left join Reply r on b = r.board WHERE b.bno =:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);


    @Query(value = "select b, w, count(r) " +
            "from Board b " +
            "left join b.member w " +
            "left join Reply r on r.board = b " +
            "GROUP BY b ",
            countQuery = "select count(b) from Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);


    @Query("select b, w, count(r) " +
            " from Board b left join b.member w " +
            " left outer join Reply r on r.board = b " +
            " WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);

}
