package com.codelab.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Experiments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Experiment number is required")
    @Column(nullable = false)
    private Integer experimentNo;

    @NotNull(message = "Experiment name is required")
    @Column(nullable = false, unique = true)
    private String experimentName;

    @NotNull(message = "Experiment description is required")
    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "experiment_id") // This joins TestCases to this experiment
    private List<TestCases> testCasesList;

    private String constraints;

    @NotNull(message = "Subject code is required")
    @Column(nullable = false)
    private String subjectCode;

    private Date completedDate;
}
