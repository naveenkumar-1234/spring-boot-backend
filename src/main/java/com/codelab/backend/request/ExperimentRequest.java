package com.codelab.backend.request;

import com.codelab.backend.model.TestCases;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ExperimentRequest {
    @NotNull(message = "Experiment number is required")
    private Integer experimentNo;

    @NotNull(message = "Experiment name is required")
    private String experimentName;

    @NotNull(message = "Experiment description is required")
    private String description;

    private List<TestCaseRequest> testCasesList;

    private String constains;

    @NotNull(message = "Subject code is required")
    private String subjectCode;

    private Date completedDate;
}
