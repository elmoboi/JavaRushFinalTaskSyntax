package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class encryptionAlphabet {
    StringBuilder enctyption(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + key) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
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
