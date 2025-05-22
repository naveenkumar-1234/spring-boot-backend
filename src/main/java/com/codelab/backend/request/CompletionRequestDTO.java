package com.codelab.backend.request;

import lombok.Data;

@Data
public class CompletionRequestDTO {
    private Long studentId;
    private Long experimentId;
}
