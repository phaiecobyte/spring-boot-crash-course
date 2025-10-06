package com.phaiecobyte.jaspertopdf.model;

import jakarta.persistence.*;
import lombok.Data;
import org.eclipse.jdt.internal.compiler.ast.NullAnnotationMatching;

@Data
@Entity
public class ScoreCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectName;
    private double totalMarks;
    private double markObtained;

    @ManyToOne
    @JoinColumn(name = "roll_number", nullable = false)
    private Student student;
}
