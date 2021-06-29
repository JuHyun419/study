package org.zerock.mreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.dto.MovieDto;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;
import org.zerock.mreview.repository.MovieImageRepository;
import org.zerock.mreview.repository.MovieRepository;

import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    private final MovieImageRepository movieImageRepository;

    @Transactional
    public Long register(MovieDto movieDto) {
        Map<String, Object> entity = MovieDto.dtoToEntity(movieDto);

        Movie movie = (Movie) entity.get("movie");
        List<MovieImage> movieImages = (List<MovieImage>) entity.get("imgList");

        // Movie, MovieImage 등록
        movieRepository.save(movie);
        movieImages.forEach(movieImageRepository::save);

        return null;
    }
}
