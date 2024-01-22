package com.example.paging_example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  // bu  anatatsiya frontentga null ga teng bolgan qiymatlarni berib yubormaydi.
public class StudentDTO {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private LocalDateTime createdDate;
}
