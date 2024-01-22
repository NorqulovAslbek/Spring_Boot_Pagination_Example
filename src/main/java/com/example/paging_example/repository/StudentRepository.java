package com.example.paging_example.repository;

import com.example.paging_example.entity.StudentEntity;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository  extends JpaRepository<StudentEntity,Integer> {

    Page<StudentEntity> findByAge(Integer age, Pageable pageable);
}
