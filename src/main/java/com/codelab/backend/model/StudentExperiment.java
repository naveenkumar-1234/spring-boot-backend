package com.codelab.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"student_id", "experiment_id"})
})
public class StudentExperiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "experiment_id", nullable = false)
    private Long experimentId;

    @Column(nullable = false)
    private Date completionDate;

    private Boolean isCompleted = false;

    // Custom constructor
    public StudentExperiment(Long studentId, Long experimentId, Date completionDate, Boolean isCompleted) {
        this.studentId = studentId;
        this.experimentId = experimentId;
        this.completionDate = completionDate;
        this.isCompleted = isCompleted;
    }
}
