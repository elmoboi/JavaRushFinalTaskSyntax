package com.company;

import java.util.Scanner;

import static com.company.CaesarCipher.breakCipher;
import static com.company.DecodingAlphabet.BruteForce;

public class Runner {
    public static void main(String[] args) {
        CaesarCipher caesarCipher = new CaesarCipher();
        Messages messages = new Messages();
        System.out.println(messages.messagePickMode);
        System.out.println(messages.messagePickMain);
        if (Pick.pickModeMain().equals("1")) {
            System.out.println(messages.messageEncryptKey);
            Scanner scanner = new Scanner(System.in);
            int key = scanner.nextInt();
            System.out.println(messages.messagePathWhite);
            EncryptionAlphabet.encryption(ReadFile.readFile(Pick.pickFile()), key);
        } else {
            System.out.println(messages.messagePickMode);
            System.out.println(messages.messagePickDecodingMode);
            switch (Pick.decodingPickMode()) {
                case "1":
                    System.out.println(messages.messageModeCaesar);
                    System.out.println(messages.messageDecryptKey);
                    Scanner scanner = new Scanner(System.in);
                    int key = scanner.nextInt();
                    System.out.println(messages.messgaeEncryptedPath);
                    DecodingAlphabet.decodingCaesar(ReadFile.readFile(Pick.pickFile()), key);
                    break;
                case "2":
                    System.out.println(messages.messageModeBruteForce);
                    System.out.println(messages.messgaeEncryptedPath);
                    BruteForce(ReadFile.readFile(Pick.pickFile()));
                    break;
                case "3":
                    System.out.println(messages.messageAnalyticPicked);
                    System.out.println(messages.messgaeEncryptedPath);
                    breakCipher(ReadFile.readFile(Pick.pickFile()));
                    break;
            }
        }
    }
}

