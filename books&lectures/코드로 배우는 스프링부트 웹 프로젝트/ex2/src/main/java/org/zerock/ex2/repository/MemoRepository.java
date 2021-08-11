package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.ex2.entity.Memo;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    void deleteMemoByMnoLessThan(Long num);

    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getListDesc();

    @Query(value = "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno",
    countQuery = "select count(m) from Memo m where m.mno > :mno")
    Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);
}
