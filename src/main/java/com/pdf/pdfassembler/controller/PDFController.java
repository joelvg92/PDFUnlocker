package com.pdf.pdfassembler.controller;

import com.pdf.pdfassembler.utils.UnlockPDF;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;

@RestController
public class PDFController {

    @PostMapping("/processpdf")
    public ResponseEntity<UploadFileResponse> processPDF(@RequestParam("files") MultipartFile[] files) {
        UnlockPDF unlockPDF = new UnlockPDF();
        try {
            for (MultipartFile file : Arrays.asList(files)) {
                unlockPDF.unlock(null, file);
            }
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body(new UploadFileResponse(files[0].getName(),files[0].getContentType()));
    }

    @PostMapping("/unlockpdf")
    public ResponseEntity<InputStreamResource> unlockPDF(@RequestParam("file") MultipartFile file) {
        UnlockPDF unlockPDF = new UnlockPDF();
        try {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf")).body(new InputStreamResource(unlockPDF.unlock(null, file)));
        }catch (Exception e){
            System.err.println(e.getLocalizedMessage());
        }
        return null;
    }
}
