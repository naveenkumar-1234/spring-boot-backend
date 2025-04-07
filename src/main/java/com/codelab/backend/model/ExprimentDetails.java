package com.codelab.backend.model;

import java.util.List;

public class ExprimentDetails {
    private Integer exprimentNo;
    private String exprimentName;
    private String description;
    private List<TestCases> testCasesList;

    public Integer getExprimentNo() {
        return exprimentNo;
    }

    public void setExprimentNo(Integer exprimentNo) {
        this.exprimentNo = exprimentNo;
    }

    public String getExprimentName() {
        return exprimentName;
    }

    public void setExprimentName(String exprimentName) {
        this.exprimentName = exprimentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TestCases> getTestCasesList() {
        return testCasesList;
    }

    public void setTestCasesList(List<TestCases> testCasesList) {
        this.testCasesList = testCasesList;
    }

    public ExprimentDetails() {
    }

    public ExprimentDetails(Integer exprimentNo, String exprimentName, String description, List<TestCases> testCasesList) {
        this.exprimentNo = exprimentNo;
        this.exprimentName = exprimentName;
        this.description = description;
        this.testCasesList = testCasesList;
    }
}
