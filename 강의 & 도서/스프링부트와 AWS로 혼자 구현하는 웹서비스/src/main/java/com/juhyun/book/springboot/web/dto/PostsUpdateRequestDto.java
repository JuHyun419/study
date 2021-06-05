package com.juhyun.book.springboot.web.dto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PostsUpdateRequestDto {
    private String title;
    private String content;
}
