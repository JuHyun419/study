package org.zerock.mreview.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieImageRepository movieImageRepository;

    @Test
    @DisplayName("")
    void insertMovies() {
        /* given */
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    Movie movie = Movie.builder()
                            .title("Movie...." + i)
                            .build();
                    movieRepository.save(movie);

                    int count = (int) (Math.random() * 5) + 1; // 1 ~ 4

                    for (int j = 0; j < count; j++) {
                        MovieImage movieImage = MovieImage.builder()
                                .uuid(UUID.randomUUID().toString())
                                .movie(movie)
                                .imgName("test" + j + ".jpg")
                                .build();
                        movieImageRepository.save(movieImage);
                    }

                });

        /* when */

        /* then */
    }

    @Test
    @DisplayName("")
    void testListPage() {
        /* given */
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

        /* when */
        Page<Object[]> result = movieRepository.getListPage(pageable);

        /* then */
        result.stream().forEach(objects -> System.out.println(Arrays.toString(objects)));
    }

    @Test
    @DisplayName("")
    void testGetMovieWithAll() {
        /* given */
        Long mno = 87L;
        List<Object[]> result = movieRepository.getMovieWithAll(mno);
        System.out.println(result);

        /* when */

        /* then */
        result.forEach(objects -> System.out.println(Arrays.toString(objects)));
    }

}