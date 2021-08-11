package com.juhyun.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter

// JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우, 필드들(createdDate, modifiedDate)도 칼럼으로 인식
@MappedSuperclass   

// BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate // Entity가 생성되어 저장될때 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 Entity의 값을 변경할 때 시간 자동 저장
    private LocalDateTime modifiedDate;
}
