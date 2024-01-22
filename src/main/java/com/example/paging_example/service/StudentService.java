package com.example.paging_example.service;

import com.example.paging_example.dto.StudentDTO;
import com.example.paging_example.entity.StudentEntity;
import com.example.paging_example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO create(StudentDTO dto) {
        dto.setCreatedDate(LocalDateTime.now());
        StudentEntity studentEntity = toEntity(dto);
        StudentEntity save = studentRepository.save(studentEntity);
        return toDTO(save);
    }

    public PageImpl<StudentDTO> studentPagination(Integer page, Integer size) {
        /**
         *  Pageable pageable= PageRequest.of(page-1,size);  --> bu bizga pagalab olish uchun ishlatiladi
         *  PageRequest.of() -methodi ichiga  page - 1 ni berib yuborishni sababi dasturlashda hamma narsa 0 - dan bosh
         *  lanadi ammo user 0 - page ni tanlamidi u har har doim u 1 - chi page ni ko'radi va 1 ni bossa
         *  bizga 1 - page keladi uni biz 0 ga aylantirib of ga berishimz uchun page -1 qilib qo'ydik.
         *  Bu erda siza 1 - page da nechta malumot kelishi sizeni 30 va page ni 1 kiritsak 1 - page dagi 30 ta malumot
         *  larni korsatadi agar page ni 2 kiritsan  boshidagi 30 tani tashab keyingi 30 kinoni ro'yhatini
         *  chiqarib beradi.
         */
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<StudentEntity> all = studentRepository.findAll(pageable);
        List<StudentEntity> content = all.getContent();
        long totalElements = all.getTotalElements();
        List<StudentDTO> list = new LinkedList<>();
        for (StudentEntity entity : content) {
            list.add(toDTO(entity));
        }
        return new PageImpl<>(list, pageable, totalElements);

    }
    public PageImpl<StudentDTO> studentByAgePagination(Integer page,Integer size,Integer age){
        /**
         * Sort bizga eng oxirida qo'shilgan studentni birinchi
         * chiqarib yani eng oxiridan boshlab chiqarb berish uchun ishlatidi
         */
        Sort sort=Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable=PageRequest.of(page-1,size,sort);
        Page<StudentEntity> byAge = studentRepository.findByAge(age, pageable);
        long totalElements = byAge.getTotalElements();
        List<StudentEntity> content = byAge.getContent();
        List<StudentDTO> list=new LinkedList<>();
        for (StudentEntity entity : content) {
            list.add(toDTO(entity));
        }
        return new PageImpl<>(list,pageable,totalElements);
    }


    public StudentEntity toEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    public StudentDTO toDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setAge(entity.getAge());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
