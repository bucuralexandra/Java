package com.nagarro.remotelearning.week15.p3;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Main {

    private static final String INITIAL = "Initial size = ";
    private static final String COMPRESSED = "Size after compression = ";
    private static final String COMPRESS_RATIO = "Compression ratio = ";
    private static final String FILE_PATH = "compress.txt";
    private static final String COMPRESSED_FILE_PATH = "compress.txt.zip";

    public static void main(String[] args) throws IOException {

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        long initialSize = FileChannel.open(Paths.get(FILE_PATH)).size();
        FileCompressor.compress(file);

        try {
            Path filePathCompressed = Paths.get(COMPRESSED_FILE_PATH);
            long sizeAfterCompression = Files.size(filePathCompressed);

            System.out.println(INITIAL + initialSize);
            System.out.println(COMPRESSED + sizeAfterCompression);
            System.out.println(COMPRESS_RATIO +
                    new DecimalFormat("##.###").format(initialSize / (double) sizeAfterCompression) + "%");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
