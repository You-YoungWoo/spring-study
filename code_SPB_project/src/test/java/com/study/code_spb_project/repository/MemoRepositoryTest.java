package com.study.code_spb_project.repository;

import com.study.code_spb_project.entity.Memo;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println("memoRepository DI Test : " + memoRepository.getClass().getName());
    }


    @Test
    @Transactional
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..."+i).build();
            memoRepository.save(memo);
        });
    }


    @Test
    public void testSelectFindById() {

        // 데이터베이스에 존재하는 mno
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("===============================================");

        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }

    }

    @Transactional
    @Test
    public void testSelectGetOne() {

        // 데이터베이스에 존재하는 mno
        Long mno = 100L;

        @Deprecated
        Memo result = memoRepository.getOne(mno);

        System.out.println("===============================================");

        System.out.println(result);

    }

    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(99L).memoText("Update Text 99").build();

        System.out.println("testUpdate : " + memoRepository.save(memo));
    }

    @Test
    public void testDelete() {
        Long mno = 100L;

        try {
            memoRepository.deleteById(mno);
        } catch (DataAccessException e) {
            System.out.println("DB에 접근에 실패했습니다.");
        }
    }

    @Test
    public void testPageDefault() {
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println("testPageDefault :  " + result);

        System.out.println("===============================================");

        System.out.println("Total Pages : " + result.getTotalPages()); // 총 몇 페이지
        System.out.println("Total Count : " + result.getTotalElements()); // 전체 개수
        System.out.println("Page Number : " + result.getNumber()); // 현재 페이지 번호 0부터 시작
        System.out.println("Page Size : " + result.getSize()); // 페이지당 데이터 개수
        System.out.println("has next page? : " + result.hasNext()); // 다음 페이지 존재 여부
        System.out.println("first page? : " + result.isFirst()); // 시작 페이지(0) 여부

        System.out.println("===============================================");

        for(Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();

        PageRequest pageRequest = PageRequest.of(0, 10, sort1);

        Page<Memo> result = memoRepository.findAll(pageRequest);

        result.get().forEach(System.out::println);
    }

    @Test
    public void testSortAll() {
        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2); // and를 이용한 연결

        PageRequest pageRequest = PageRequest.of(0, 10, sortAll);

        Page<Memo> result = memoRepository.findAll(pageRequest);

        result.get().forEach(System.out::println);
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);

        for(Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageRequest);

        result.get().forEach(System.out::println);
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteByMnoLessThan(10L);
    }
}
