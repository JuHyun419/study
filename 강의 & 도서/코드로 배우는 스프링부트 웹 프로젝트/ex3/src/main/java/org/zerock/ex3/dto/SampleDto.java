package org.zerock.ex3.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SampleDto {
    private Long sno;
    private String first;
    private String last;
    private LocalDateTime regTime;
}
