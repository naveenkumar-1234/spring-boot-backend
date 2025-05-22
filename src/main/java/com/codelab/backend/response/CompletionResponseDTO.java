package com.codelab.backend.response;

import lombok.Data;

import java.util.Date;

@Data
public class CompletionResponseDTO {
    private Long id;
    private Long studentId;
    private Long experimentId;
    private Date completionDate;
    private Boolean isCompleted;

    // Constructor without builder
    public CompletionResponseDTO(Long id, Long studentId, Long experimentId, Date completionDate, Boolean isCompleted) {
        this.id = id;
        this.studentId = studentId;
        this.experimentId = experimentId;
        this.completionDate = completionDate;
        this.isCompleted = isCompleted;
    }
}
