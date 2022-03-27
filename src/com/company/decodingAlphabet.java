package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class decodingAlphabet {
    StringBuilder decoding(String text, int key) {
        encryptionAlphabet encryptionAlphabet = new encryptionAlphabet();
        StringBuilder result = encryptionAlphabet.enctyption(text, 26 - (key % 26));
        File file = new File("encryptedFile.txt");
        try(RandomAccessFile randomAccessFile = new RandomAccessFile("encryptedFile.txt", "rw");
            FileChannel channel = randomAccessFile.getChannel()) {
            file.createNewFile();
            String encryptedText = result.toString();
            ByteBuffer byteBuffer = ByteBuffer.allocate(encryptedText.getBytes().length);
            byteBuffer.put(encryptedText.getBytes());
            byteBuffer.flip();
            channel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
