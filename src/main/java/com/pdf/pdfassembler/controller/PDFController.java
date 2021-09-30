package com.pdf.pdfassembler.controller;

import com.pdf.pdfassembler.model.UnlockedPDF;
import com.pdf.pdfassembler.utils.UnlockPDF;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@RestController
public class PDFController {


    @PostMapping("/base64/unlockpdf")
    public ResponseEntity<UnlockedPDF> unlockPDFBase64(@RequestBody Map<String, Object> payload) {
        String pdfContent = (String) payload.get("pdf");
        byte[] decoder = Base64.getDecoder().decode(pdfContent);
        InputStream targetStream = new ByteArrayInputStream(decoder);
        UnlockPDF unlockPDF = new UnlockPDF();
        try {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/json")).body((unlockPDF.unlock(null, targetStream)));
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }


    @PostMapping("/mpf/unlockpdf")
    public ResponseEntity<InputStreamResource> unlockPDF(@RequestParam("file") MultipartFile file) {
        UnlockPDF unlockPDF = new UnlockPDF();
        try {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf")).body(new InputStreamResource(unlockPDF.unlock(null, file)));
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }

    @GetMapping(value="/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() {

        return "Hello there!";
    }
}
