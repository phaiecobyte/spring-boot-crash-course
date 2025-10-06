package com.phaiecobyte.jaspertopdf.service;

import com.phaiecobyte.jaspertopdf.model.ScoreCard;
import com.phaiecobyte.jaspertopdf.repository.ScoreCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreCardService {
    private final ScoreCardRepository repository;

    public List<ScoreCard> getAllScoreCard(){
        return repository.findAll();
    }

    public List<ScoreCard> getScoreCardByStudentId(Long rollNumber){
        return repository.findByStudentRollNumber(rollNumber);
    }
}
