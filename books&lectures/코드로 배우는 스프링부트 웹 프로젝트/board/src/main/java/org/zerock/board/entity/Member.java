package org.zerock.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    private String email;

    @Column
    private String password;

    @Column
    private String name;
}
