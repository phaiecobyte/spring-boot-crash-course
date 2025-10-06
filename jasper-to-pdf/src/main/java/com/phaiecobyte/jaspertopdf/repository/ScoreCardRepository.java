package com.phaiecobyte.jaspertopdf.repository;

import com.phaiecobyte.jaspertopdf.model.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard,Long> {
    List<ScoreCard> findByStudentRollNumber(Long rollNumber);
}
