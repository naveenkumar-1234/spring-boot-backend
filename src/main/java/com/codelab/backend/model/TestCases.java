package com.codelab.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor  // Needed by JPA
public class TestCases {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String input;
    private String expectedOutput;

    // Parameterized constructor for convenience
    public TestCases(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }
}
