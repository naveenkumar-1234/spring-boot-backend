package com.codelab.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeRequest {

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
