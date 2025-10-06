package com.phaiecobyte.jaspertopdf.controller.api;

import com.phaiecobyte.jaspertopdf.controller.dto.ScoreCardReq;
import com.phaiecobyte.jaspertopdf.model.ScoreCard;
import com.phaiecobyte.jaspertopdf.model.Student;
import com.phaiecobyte.jaspertopdf.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class ApiStudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Object> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @PostMapping("/addStudent")
    public ResponseEntity<Object> createStudent(@RequestBody Student req){
        return ResponseEntity.ok(studentService.createStudent(req));
    }

    @PostMapping("/{studentId}/addScore")
    public ResponseEntity<Object> addStudentScore(
            @PathVariable Long studentId,
            @RequestBody ScoreCardReq req){
        return ResponseEntity.ok(studentService.addStudentScore(studentId,req));
    }
}
