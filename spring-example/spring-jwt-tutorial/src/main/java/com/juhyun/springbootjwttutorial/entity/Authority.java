package com.juhyun.springbootjwttutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority {

    @Id
    @Column(name = "authority_name", length = 50)
    private String authorityName;
}