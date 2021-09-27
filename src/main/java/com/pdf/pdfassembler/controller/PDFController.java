package com.pdf.pdfassembler.controller;

import com.pdf.pdfassembler.utils.UnlockPDF;
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
            //
        }
        return ResponseEntity.ok().body(new UploadFileResponse(files[0].getName(),files[0].getContentType()));
    }
}
