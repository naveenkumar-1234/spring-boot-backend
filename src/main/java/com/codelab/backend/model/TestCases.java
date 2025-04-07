package com.codelab.backend.model;

public class TestCases {
    private String input;
    private String expected;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public TestCases(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    public TestCases() {
    }
}
