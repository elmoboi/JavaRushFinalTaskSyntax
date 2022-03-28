package com.company;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Objects;

public class decodingAlphabet {

    StringBuilder decoding(String text, int key) {
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
//        StringBuilder result = new StringBuilder();
//        for (char chars : text.toCharArray()) {
//            if (chars != ' ') {
//                int rightAlfhabetPosition = chars-'a';
//                int newAlphabetPosition = (rightAlfhabetPosition + key) % 26;
//                char newChar = (char) ('a' + newAlphabetPosition);
//                result.append(newChar);
//            } else {
//                result.append(char);
//            }
//        }
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

    int decodingBruteForce(String text) {
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

    int textValidator(StringBuilder text) {
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

    int decodingStatAnalysis(String text) {
        double[] lettersProbability = {0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035, 0.074,
                0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003,
                0.077, 0.063, 0.093, 0.027, 0.013, 0.016, 0.005, 0.019, 0.001};
        return 0;
    }

}
