package com.study.guestbook.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Guestbook extends BaseEntity {

    // @Id  - primary key,
    // @GeneratedValue(strategy = GenerationType.IDENTITY) - primary key의 값 생성 방식
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;


    // 제목
    @Column(length = 100, nullable = false)
    private String title;

    // 내용
    @Column(length = 1500, nullable = false)
    private String content;

    // 작성자
    @Column(length = 50, nullable = false)
    private String writer;


    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
