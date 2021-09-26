package com.pdf.pdfassembler.controller;

import com.pdf.pdfassembler.utils.UnlockPDF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PDFController {

    @PostMapping("/processpdf")
    public ResponseEntity<UploadFileResponse> processPDF(@RequestParam("files") MultipartFile[] files) throws Exception {
        UnlockPDF unlockPDF = new UnlockPDF();
        unlockPDF.unlock(null,files[0]);
        return ResponseEntity.ok().body(new UploadFileResponse(files[0].getName(),files[0].getContentType()));
    }
}
