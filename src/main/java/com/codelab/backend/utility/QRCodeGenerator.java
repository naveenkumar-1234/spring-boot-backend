package com.codelab.backend.utility;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class QRCodeGenerator {

    public static byte[] generateBarCodeImage(String text , int width , int height) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE,width,height);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.CODE_128, width, height);
        ByteArrayOutputStream png = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",png);
        return png.toByteArray();
    }

    public static Path saveQRCodeToFile(String text, int width, int height, String filePath) throws Exception {
        byte[] qrBytes = generateBarCodeImage(text, width, height);
        Path path = Path.of(filePath);
        Files.write(path, qrBytes);
        return path;
    }
}
