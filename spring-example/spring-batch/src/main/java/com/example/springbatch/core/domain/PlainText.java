package com.example.springbatch.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@DynamicUpdate // 일부 컬럼의 값만 변경되었을 때, 변경된 값들에 대해서만 변경 쿼리가 실행
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plain_text")
public class PlainText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String text;
}
