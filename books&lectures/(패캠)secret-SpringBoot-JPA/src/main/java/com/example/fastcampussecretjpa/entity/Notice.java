package com.example.fastcampussecretjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Notice {

    private String title;

    private String contents;

    private int hits;

    private int likes;

    private LocalDateTime regDate;

}
