package com.example.fastcampussecretjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserNoticeCount {
    private long id;
    private String email;
    private String userName;
    private long noticeCount;
}
