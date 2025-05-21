package com.codelab.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Department is required")
    @Column(name = "department", nullable = false)
    private String department;
    @NotBlank
    @Column(nullable = false,unique = true)
    private String registerNumber;
    @NotNull
    @Column(nullable = false)
    private Integer year;
    @NotNull
    @Column(nullable = false)
    private Integer semester;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "student_id"))

    private List<String> subjectCodes;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users user;
}