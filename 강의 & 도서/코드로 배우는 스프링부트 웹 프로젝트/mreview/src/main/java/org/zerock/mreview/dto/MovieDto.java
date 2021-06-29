package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private Long mno;
    private String title;

    @Builder.Default
    private List<MovieImageDto> imageDtoList = new ArrayList<>();


    // MovieDto, MovieImageDto 두 객체 모두 처리
    public static Map<String, Object> dtoToEntity(MovieDto dto) {
        Map<String, Object> entity = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(dto.getMno())
                .title(dto.getTitle())
                .build();

        entity.put("movie", movie);

        List<MovieImageDto> imageDtoList = dto.getImageDtoList();

        if (imageDtoList.size() > 0) {
            List<MovieImage> movieImages = imageDtoList.stream().map(movieImageDto -> {
                return MovieImage.builder()
                        .path(movieImageDto.getPath())
                        .imgName(movieImageDto.getImgName())
                        .uuid(movieImageDto.getUuid())
                        .movie(movie)
                        .build();
            }).collect(Collectors.toList());

            entity.put("imgList", movieImages);
        }
        return entity;
    }
}
