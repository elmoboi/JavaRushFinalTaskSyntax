package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        System.out.println("Выберете режим работы:");
        System.out.println("1 - режим Шифрование Цезарь\n" + "2 - режим Расшифровка (pick mode) \n" + "--------------------");
        if(pickModeMain().equals("1")) {
            System.out.println("Укажите ключ к шифрованию: ");
            Scanner scanner = new Scanner(System.in);
            int key = scanner.nextInt();
            System.out.println("Укажите путь к исходному тексту в виде: \"*.txt\"");
            encryption(readFile(pickFile()), key);
        } else {
            System.out.println("Выберете режим работы:");
            System.out.println("1 - Режим Цезарь \n" + "2 - Режим brute force \n" + "3 - режим Статистического анализа");
            switch (decodingPickMode()) {
                case "1":
                    System.out.println("Режим: расшифровка по Цезарю");
                    System.out.println("Укажите ключ к расшифровке: ");
                    Scanner scanner = new Scanner(System.in);
                    int key = scanner.nextInt();
                    System.out.println("Укажите путь к зашифрованному тексту в виде:  \"*.txt\"");
                    decodingCaesar(readFile(pickFile()), key);
                    break;
                case "2":
                    System.out.println("Режим: brute force");
                    System.out.println("Укажите путь к зашифрованному тексту в виде:  \"*.txt\"");
                    decodingBruteForce(readFile(pickFile()));
                    break;
                case "3":
                    System.out.println("Пока не рабоатет ;(");
                    //TODO: доделать метод
                    break;
            }
        }
    }

    //Функция выбора режима работы программы шифратор/дешифратор
    public static String pickModeMain() {
        String userPick = null;
        try {
            Scanner scanner = new Scanner(System.in);
            userPick = scanner.nextLine();
            switch (userPick) {
                case "1":
                    System.out.println("Выбран - режим Шифрование:\n");
                    break;
                case "2":
                    System.out.println("Выбран - режим Расшифровка:\n");
                    break;
                default:
                    System.out.println("Похоже, для работы нужно выбрать один из режимов!");
                    pickModeMain();
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Похоже нужно выбрать какой-либо из режимов, чтобы продолжить работу");
            pickModeMain();
        }
        return userPick;
    }

    //Функция выбора режима дешифрования
    public static String decodingPickMode() {
        String userPick = null;
        try {
            Scanner scanner = new Scanner(System.in);
            userPick = scanner.nextLine();
            switch (userPick) {
                case "1":
                    System.out.println("Выбран - режим Цезарь:\n");
                    break;
                case "2":
                    System.out.println("Выбран - режим brute force:\n");
                    break;
                case "3":
                    System.out.println("Выбран - режим Статистического анализа");
                    break;
                default:
                    System.out.println("Похоже, для работы нужно выбрать один из режимов дешифратора!");
                    pickModeMain();
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("Похоже нужно выбрать какой-либо из режимов, чтобы продолжить работу");
            pickModeMain();
        }
        return userPick;
    }

    //функция выбора файла
    public static Path pickFile() {
        Scanner scanner = new Scanner(System.in);
        Path path = Path.of(scanner.nextLine());
        return path;
    }

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
            //System.out.println(builder);
        } catch (IOException exception) {
            System.out.println("Какие-то проблемы с файлом ;(");
        }
        return text;
    }

    //Функция шифрования
    public static void encryption(String text, int key) {
        encryptionAlphabet encryptionAlphabet = new encryptionAlphabet();
        encryptionAlphabet.enctyption(text, key);
    }

    //Функция расшифровки по ключу
    public static void decodingCaesar(String text, int key) {
        decodingAlphabet decodingAlphabet = new decodingAlphabet();
        decodingAlphabet.decoding(text, key);
    }

    //Функция расшифровки БрутФорса
    public static void decodingBruteForce(String text) {
        decodingAlphabet decodingAlphabet = new decodingAlphabet();
        System.out.println("Ключ Цезаря: " + decodingAlphabet.decodingBruteForce(text));
    }

    //Функция расшифровки Стат. анализа
    public static void decodingStatisticalAnalysis(String text, int key) {
        //TODO: доделать
    }

}

