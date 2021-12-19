package com.nagarro.remotelearning.week15.p2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileManager {

    private static final String EXCEPTION_CREATING_FILE = "Error when creating the file";
    private static final int[] array = {1, 4, 67, 9};

    public void writeData(File file) {
        try (FileWriter writer = new FileWriter(file)) {
            if (!file.exists()) {
                file.createNewFile();
            }
            for (int i = 0; i < array.length; i++) {
                writer.write(array[i] + System.getProperty("line.separator"));
            }

        } catch (FileNotFoundException e) {
            System.out.println(EXCEPTION_CREATING_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try (RandomAccessFile randomAccessFile = new RandomAccessFile("integers.txt", "r");) {
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(20);
            channel.position(6);
            int bytesRead = channel.read(buf);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.clear();
                bytesRead = channel.read(buf);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something wrong while reading");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
