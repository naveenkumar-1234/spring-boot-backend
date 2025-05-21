package com.codelab.backend.request;

import lombok.Data;

@Data
public class TestCaseRequest {
    private String input;
    private String expectedOutput;
}
