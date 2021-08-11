package org.zerock.guestbook.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDto<Dto, E> {

    private List<Dto> dtoList;
    private int totalPage; // 총 페이지 번호
    private int page;      // 현재 페이지 번호
    private int size;      // 목록 사이즈
    private int start;     // 시작 페이지 번호
    private int end;       // 끝 페이지 번호
    private boolean prev;  // 다음 페이지 존재 여부
    private boolean next;  // 이전 페이지 존재 여부
    private List<Integer> pageList; // 페이지 번호 목록

    public PageResponseDto(Page<E> result, Function<E, Dto> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;   // 0부터 시작하므로 + 1
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = Math.min(totalPage, tempEnd);
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}
