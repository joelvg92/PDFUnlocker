package com.pdf.pdfassembler.utils;

import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class UnlockPDF {
    public InputStream unlock(String password, MultipartFile file) throws Exception {
        PdfReader.unethicalreading = true;
        PdfReader reader;
        if (password == null) {
            reader = new PdfReader(file.getBytes());
        } else {
             reader = new PdfReader(file.getBytes(), password.getBytes());
        }
        String unlockedFile ="pdfs/"+file.getOriginalFilename();
        PdfEncryptor.encrypt(reader, new FileOutputStream(unlockedFile), null,
                null, PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_COPY
                        | PdfWriter.ALLOW_DEGRADED_PRINTING | PdfWriter.ALLOW_FILL_IN
                        | PdfWriter.ALLOW_MODIFY_ANNOTATIONS | PdfWriter.ALLOW_MODIFY_CONTENTS
                        | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS, false);
        File pdfFile = new File(unlockedFile);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(pdfFile));
        FIleUtils.deleteFIle(unlockedFile);
        return inputStream;
    }

}
