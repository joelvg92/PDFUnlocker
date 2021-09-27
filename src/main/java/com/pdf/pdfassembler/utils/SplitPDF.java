package com.pdf.pdfassembler.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

import java.io.FileOutputStream;

public class SplitPDF {

    public static void splitPdf(PdfReader pdfReader){
        try {
            int n = pdfReader.getNumberOfPages();
            System.out.println("Number of pages : " + n);
            int i = 0;
            while (i < n) {
                String outFile = "output-" +i + "-.pdf";
                System.out.println("Writing " + outFile);
                Document document = new Document(pdfReader.getPageSizeWithRotation(1));
                PdfCopy writer = new PdfCopy(document, new FileOutputStream(outFile));
                document.open();
                PdfImportedPage page = writer.getImportedPage(pdfReader, ++i);
                writer.addPage(page);
                document.close();
                writer.close();
            }
        }catch (Exception e){

        }

    }
}
