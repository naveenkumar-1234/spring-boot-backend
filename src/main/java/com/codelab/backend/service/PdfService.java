package com.codelab.backend.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    public PdfService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePdf(Map<String, Object> data) throws Exception {
        Context context = new Context();
        context.setVariables(data);

        String html = templateEngine.process("pdf_template", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        ITextRenderer renderer = new ITextRenderer();

        renderer.getSharedContext().setPrint(true);
        renderer.getSharedContext().setInteractive(false);
        renderer.setDocumentFromString(html);

        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }


}
