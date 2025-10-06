package com.phaiecobyte.jaspertopdf.service;

import com.phaiecobyte.jaspertopdf.controller.dto.ScoreCardReq;
import com.phaiecobyte.jaspertopdf.model.ScoreCard;
import com.phaiecobyte.jaspertopdf.model.Student;
import com.phaiecobyte.jaspertopdf.repository.ScoreCardRepository;
import com.phaiecobyte.jaspertopdf.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository repository;
    private final ScoreCardRepository scoreCardRepository;

    public List<Student> getAllStudent(){
        return repository.findAll();
    }

    public Student getStudentById(Long rollNumber){
        return repository.findById(rollNumber).orElseThrow(() -> new RuntimeException("Student not found with id" + rollNumber));
    }

    @Transactional
    public ScoreCard addStudentScore(Long studentId, ScoreCardReq score){
        Student student = repository.findById(studentId).orElseThrow(()->new EntityNotFoundException("Student is not found"));

        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setSubjectName(score.getSubjectName());
        scoreCard.setTotalMarks(score.getTotalMarks());
        scoreCard.setMarkObtained(score.getMarkObtained());

        scoreCard.setStudent(student);

        return scoreCardRepository.save(scoreCard);
    }

    @Transactional
    public Student createStudent(Student student){
        return repository.save(student);
    }
}
