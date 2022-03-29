package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;

public class ReadFile {
    //Функция чтения слов файла
    public static String readFile(Path path) {
        StringBuilder builder = new StringBuilder();
        String text = null;
        File file = new File(path.toString());
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(file,  "rw");
            FileChannel channel = randomAccessFile.getChannel()
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());

            channel.read(byteBuffer);
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                builder.append((char) byteBuffer.get());
            }
            text = builder.toString();
        } catch (IOException exception) {
            System.out.println("Какие-то проблемы с файлом ;(");
        }
        return text;
    }
}
