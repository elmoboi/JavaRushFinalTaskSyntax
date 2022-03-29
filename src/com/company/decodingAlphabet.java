package com.company;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Objects;

public class decodingAlphabet {

    static StringBuilder decoding(String text, int key) {
        StringBuilder result = new StringBuilder(text.length());
        for(int i = 0; i < text.length(); i++) {
            int c = text.charAt(i);
            if(Character.isUpperCase(c)) {
                c = c-(key % 26);
                if(c < 'A') c = c+26;
            }
            else if(Character.isLowerCase(c)) {
                c = c-(key % 26);
                if(c < 'a') c = c+26;
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

    static int decodingBruteForce(String text) {
        ArrayList<Integer> keyList = new ArrayList<>();
        for (int i = -26; i < 27; i++) {
            keyList.add(i);
        }
        keyList.remove(26);
        int rightKey = 0;
        int count = 0;
        int oldCount = 0;
        for (Integer key : keyList) {
            count = textValidator(decoding(text, key));
            if(oldCount < count) {
                oldCount = count;
                rightKey = key;
            }
        }
        if(rightKey == -26) {
            rightKey = 0;
        }
        return rightKey;
    }

    static int textValidator(StringBuilder text) {
        int count = 0;
        int oldCount = 0;
        validWords[] Validwords = validWords.values();
        String[] words = text.toString().split(" ");
        for (String word : words) {
            for (validWords validword : Validwords) {
                if (Objects.equals(word, validword.toString())) {
                    count++;
                }
            }
        }
        return count;
    }

    //Функция расшифровки по ключу
    public static void decodingCaesar(String text, int key) {
        decoding(text, key);
    }

    //Функция расшифровки БрутФорса
    public static void BruteForce(String text) {
        System.out.println("Ключ Цезаря: " + decodingBruteForce(text));
    }
}
