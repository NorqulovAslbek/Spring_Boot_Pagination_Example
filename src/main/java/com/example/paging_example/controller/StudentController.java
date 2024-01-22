package com.example.paging_example.controller;


import com.example.paging_example.dto.StudentDTO;
import com.example.paging_example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping()
    public ResponseEntity<StudentDTO> create(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.create(dto));
    }

    /**
     * Bu method bizga studentni page lab yani bitta page da nechta malumot kerak bo'lsa shunchadan
     * olishimiz uchun ishlatida qaysi page ni kiritsak osha page dan nechtada malumot olishimizniham tanlashimiz
     * kerak. Misol uchun googledan  nimanidir qidiradigan bo'lsak , biz qidirgan narsaga oid 1000 lab malumotlar
     * chiqib keladi lekn google bizga faqat 10 ta yoki 15 tasini korsatib beradi va biz osha page ni eng pastiga tushadigan
     * bo'lsak  1,2,3,4 ...........100  manashunaqa korinishda battinlar boladi agar biz shulardan bittasini bossak
     * misol uchun 3 shunda bizga google 3 - page dagi  10 ta malumotni chiqarib beradi. Bizham pastdagi methodni
     * huddi shunaqa qilib yozdik agar user page va shu page da nechta malumot (yani page va size ni) kelishini kiritmasa
     * bizni method default 1-page dan 10 ta student malumotni olib keladi.
     *
     * @param page Integer
     * @param size Integer
     * @return
     */

    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<StudentDTO>> studentPagination(@RequestParam(defaultValue = "1") Integer page,
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(studentService.studentPagination(page, size));
    }

    @GetMapping("/paginationByAge")
    public ResponseEntity<PageImpl<StudentDTO>> studentByAgePagination(@RequestParam(defaultValue = "1") Integer page,
                                                                       @RequestParam(defaultValue = "10") Integer size,
                                                                       @RequestParam Integer age) {
     return ResponseEntity.ok(studentService.studentByAgePagination(page,size,age));
    }


}
