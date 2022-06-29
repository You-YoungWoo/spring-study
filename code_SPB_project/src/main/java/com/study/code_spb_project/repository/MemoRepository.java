package com.study.code_spb_project.repository;

import com.study.code_spb_project.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long>{

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    /*
        원하는 구간의 데이터 찾기
        PageRequest : 정렬을 위한 인자
     */
    Page<Memo> findByMnoBetween(Long from, Long to, PageRequest pageRequest);

    void deleteByMnoLessThan(Long num);

}
