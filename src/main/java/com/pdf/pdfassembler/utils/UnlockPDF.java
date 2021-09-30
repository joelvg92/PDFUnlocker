package com.pdf.pdfassembler.utils;

import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UnlockPDF {
    public InputStream unlock(String password, MultipartFile file) throws Exception {
        PdfReader.unethicalreading = true;
        PdfReader reader;
        FIleUtils fIleUtils = new FIleUtils();
        if (password == null) {
            reader = new PdfReader(file.getBytes());
        } else {
             reader = new PdfReader(file.getBytes(), password.getBytes());
        }
        Path source = Paths.get(this.getClass().getResource("/").getPath());
        Path pdfFolder = Paths.get(source.toAbsolutePath() + "/pdfs/");
        if(!Files.exists(pdfFolder)){
            Files.createDirectories(pdfFolder);
        }
        String unlockedFile = pdfFolder.toString() + '/' + file.getOriginalFilename();
        PdfEncryptor.encrypt(reader, new FileOutputStream(unlockedFile), null,
                null, PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_COPY
                        | PdfWriter.ALLOW_DEGRADED_PRINTING | PdfWriter.ALLOW_FILL_IN
                        | PdfWriter.ALLOW_MODIFY_ANNOTATIONS | PdfWriter.ALLOW_MODIFY_CONTENTS
                        | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS, false);
        File pdfFile = new File(unlockedFile);
        InputStream inputStream = new BufferedInputStream(new FileInputStream(pdfFile));
        //InputStream inputStream = fIleUtils.getFileFromResourceAsStream(unlockedFile);
        FIleUtils.deleteFIle(unlockedFile);
        return inputStream;
    }

}
