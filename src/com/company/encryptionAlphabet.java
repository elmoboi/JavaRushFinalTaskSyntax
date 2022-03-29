package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Locale;

public class encryptionAlphabet {
    static StringBuilder enctyption(String text, int key) {
        StringBuilder result = new StringBuilder(text.length());
        for(int i = 0; i < text.length(); i++) {
            int c = text.charAt(i);
            if(Character.isUpperCase(c)) {
                c = c+(key % 26);
                if(c > 'Z') c = c-26;
            }
            else if(Character.isLowerCase(c)) {
                c = c+(key % 26);
                if(c > 'z') c = c-26;
            }
            result.append((char)c);
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

    //Функция шифрования
    public static void encryption(String text, int key) {
        enctyption(text, key);
    }
}
