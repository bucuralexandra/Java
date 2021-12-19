package com.nagarro.remotelearning.week15.p3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileCompressor {


    public static void compress(File file) throws IOException {
        try {
            String zipFileName = file.getName().concat(".zip");

            FileOutputStream fileOutputStream = new FileOutputStream(zipFileName);
            ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));

            byte[] bytes = Files.readAllBytes(Paths.get(file.getName()));
            zipOutputStream.write(bytes, 0, bytes.length);
            zipOutputStream.closeEntry();
            zipOutputStream.close();

        } catch (FileNotFoundException ex) {
            System.err.format("The file %s does not exist", file.getName());
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }
}
