package com.codelab.backend.controller;


import com.codelab.backend.service.PdfService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    private static List<byte[]> pdfFiles=new ArrayList<>();
    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }


    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePdf(@RequestBody Map<String, Object> jsonData) throws Exception {
        byte[] pdf = pdfService.generatePdf(jsonData);
        pdfFiles.add(pdf);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/getpdf")
    public ResponseEntity<byte[]> getPdf() throws Exception {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfFiles.get(0));
    }

}
