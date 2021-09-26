package com.pdf.pdfassembler;

import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

public class UnlockPDF {
    public void unlock(String password) throws Exception {
        PdfReader reader;
        if (password == null) {
            reader = new PdfReader(lockedFile);
        } else {
            // reader = new PdfReader(lockedFile, password.getBytes());
        }
        PdfEncryptor.encrypt(reader, new FileOutputStream(unlockedFile), null,
                null, PdfWriter.ALLOW_ASSEMBLY | PdfWriter.ALLOW_COPY
                        | PdfWriter.ALLOW_DEGRADED_PRINTING | PdfWriter.ALLOW_FILL_IN
                        | PdfWriter.ALLOW_MODIFY_ANNOTATIONS | PdfWriter.ALLOW_MODIFY_CONTENTS
                        | PdfWriter.ALLOW_PRINTING | PdfWriter.ALLOW_SCREENREADERS, false);

    }
}
