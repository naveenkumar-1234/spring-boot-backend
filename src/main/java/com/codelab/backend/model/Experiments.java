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
    @NotNull(message = "Emperiment Number   is Required")
    @Column(nullable = false)
    private Integer experimentNo;
    @NotNull(message = "Expriment Name is Required")
    @Column(nullable = false)
    private String experimentName;

    @NotNull(message = "Expriment description is Required")
    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "experiment_id")
    private List<TestCases> testCasesList;

    private String constains;
    @NotNull(message = "Subject Code is Required")
    @Column(nullable = false)
    private String subjectCode;
    private Date completedDate;
}
