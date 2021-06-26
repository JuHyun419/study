package org.zerock.guestbook.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.guestbook.dto.GuestbookDto;
import org.zerock.guestbook.dto.PageRequestDto;
import org.zerock.guestbook.dto.PageResponseDto;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.Objects;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Transactional
    public Long register(GuestbookDto dto) {
        Guestbook entity = GuestbookDto.dtoToEntity(dto);
        guestbookRepository.save(entity);

        return entity.getGno();
    }

    @Transactional
    public PageResponseDto<GuestbookDto, Guestbook> getList(PageRequestDto requestDto) {
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
        // 검색 조건 처리
        BooleanBuilder booleanBuilder = getSearch(requestDto);
        Page<Guestbook> result = guestbookRepository.findAll(booleanBuilder, pageable);
        Function<Guestbook, GuestbookDto> fn = (GuestbookDto::entityToDto);

        return new PageResponseDto<>(result, fn);
    }

    @Transactional
    public GuestbookDto read(Long gno) {
        Guestbook guestbook = guestbookRepository.findById(gno).orElseThrow(NullPointerException::new);

        return GuestbookDto.entityToDto(guestbook);
    }

    @Transactional
    public void remove(Long gno) {
        guestbookRepository.deleteById(gno);
    }

    @Transactional
    public void modify(GuestbookDto dto) {
        guestbookRepository.findById(dto.getGno())
                .ifPresent(entity -> {
                    entity.changeContent(dto.getContent());
                    entity.changeTitle(dto.getTitle());
                    guestbookRepository.save(entity);
        });
    }

    @Transactional
    public BooleanBuilder getSearch(PageRequestDto requestDto) {
        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;
        BooleanExpression expression = qGuestbook.gno.gt(0L); // gno > 0

        booleanBuilder.and(expression);

        // 검색 조건이 없는 경우
        if (isNotExistSearchWord(type)) {
            return booleanBuilder;
        }

        // 검색 조건 작성
        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) { // title
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }

        if (type.contains("c")) { // content
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }

        if (type.contains("w")) { // writer
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
    }

    private boolean isNotExistSearchWord(String type) {
        return Objects.isNull(type) || type.trim().length() == 0;
    }

}
