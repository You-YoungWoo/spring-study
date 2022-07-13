package com.study.guestbook.dto;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// JPA를 이용하는 Repository에서는 페이지 처리 결과를 Page<Entity> 타입으로 변환하므로
// 서비스 계층에서 이를 처리하기 위해서는 별도의 클래스를 만듦
// 1) Page<Entity>의 엔티티 객체들을 DTO 객체로 변환해서 자료 구조로 담고,
// 2) 화면 출력에 필요한 페이지 정보들을 구성

public class PageResultDTO<DTO, EN> {  // 다양한 곳에서 사용할 수 있도록 제네릭 타입을 이용

    private List<DTO> dtoList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
