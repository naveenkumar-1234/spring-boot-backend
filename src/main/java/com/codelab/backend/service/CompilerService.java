package com.codelab.backend.service;

public class CompilerService {
    private final PdfService pdfService;


    public CompilerService(PdfService pdfService) {
        this.pdfService = pdfService;
    }
}
