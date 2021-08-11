package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // FETCH => attributePaths에 명시한 속성은 EAGER 처리, 나머지는 LAZY
    // LOAD => attributePaths에 명시한 속성은 EAGER 처리, 나머지는 엔티티 명시된 방식으로 처리
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);


    @Modifying
    @Query("delete from Review r where r.member = :member")
    void deleteByMember(Member member);
}
