package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("")
    void insertMovieReviews() {
        /* given */
        IntStream.rangeClosed(1, 200)
                .forEach(i -> {
                    // movie 번호
                    Long mno = (long) (Math.random() * 100) + 1;
                    Movie movie = Movie.builder().mno(mno).build();

                    // Member 번호
                    Long mid = ((long)(Math.random() * 100) + 1);
                    Member member = Member.builder().mid(mid).build();

                    // 랜덤 평점
                    int score = (int) (Math.random() * 5) + 1;

                    Review movieReview = Review.builder()
                            .movie(movie)
                            .member(member)
                            .grade(score)
                            .text("이 영화에 대한 느낌...." + i)
                            .build();

                    reviewRepository.save(movieReview);
                });

        /* when */


        /* then */
    }

    @Test
    @DisplayName("")
    void testGetMovieReviews() {
        /* given */
        Movie movie = Movie.builder().mno(87L).build();

        /* when */
        List<Review> reviewList = reviewRepository.findByMovie(movie);

        /* then */
        // Review(LAZY), Member 객체를 한 번에 조회할 수 없어서 오류 발생
        // @EntityGraph - 엔티티의 특정 속성을 같이 로딩하도록 표시하는 어노테이션
        reviewList.forEach(review -> {
            System.out.print(review.getReviewnum() + "\t");
            System.out.print(review.getGrade() + "\t");
            System.out.print(review.getText() + "\t");
            System.out.print(review.getMember().getEmail() + "\t");
            System.out.print(review.getMember().getNickname());
            System.out.println("----------------------------------");
        });
    }
}