package com.pdf.pdfassembler.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class FIleUtils {

    public static void deleteFIle(String filename){
        Path path = FileSystems.getDefault().getPath(filename);
        try {
            Files.deleteIfExists(path);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

}
