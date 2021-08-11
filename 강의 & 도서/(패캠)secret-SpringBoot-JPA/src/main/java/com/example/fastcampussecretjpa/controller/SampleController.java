package com.example.fastcampussecretjpa.controller;

import com.example.fastcampussecretjpa.entity.Notice;
import com.example.fastcampussecretjpa.entity.NoticeRequestDto;
import com.example.fastcampussecretjpa.error.ResponseError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Log4j2
public class SampleController {

    @PostMapping("/api/notice")
    public ResponseEntity<Object> addNotice(
            @RequestBody @Valid NoticeRequestDto dto,
            Errors errors) {

        if (errors.hasErrors()) {
            List<ResponseError> responseErrors = new ArrayList<>();
            errors.getAllErrors().forEach(error ->
                    responseErrors.add(ResponseError.of((FieldError) error)));

            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }

        Notice notice = Notice.builder()
                .title(dto.getTitle())
                .contents(dto.getContents())
                .hits(0)
                .likes(0)
                .regDate(LocalDateTime.now())
                .build();
        log.info("notice: {}", notice);

        return ResponseEntity.ok().build();
    }
}
