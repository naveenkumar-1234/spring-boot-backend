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

    public CodeResponse(String pdfBase64, String status, String output) {
        this.pdfBase64 = pdfBase64;
        this.status = status;
        this.output = output;
    }

    public  CodeResponse(String status , String output){
        this.status = status;
        this.output = output;
    }

    public String getPdfBase64() {
        return pdfBase64;
    }

    public void setPdfBase64(String pdfBase64) {
        this.pdfBase64 = pdfBase64;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
