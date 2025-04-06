package com.codelab.backend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CodeResponse {
    private String pdfBase64;
    private String status;
    private String output;

    public  CodeResponse(String status , String output){
        this.status = status;
        this.output = output;
    }
}
