package com.phaiecobyte.jaspertopdf.controller.dto;

import lombok.Data;

@Data
public class ScoreCardReq {
    private String subjectName;
    private double totalMarks;
    private double markObtained;
}
