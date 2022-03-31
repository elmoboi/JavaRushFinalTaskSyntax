package com.company;

import java.nio.file.Path;
import java.util.Scanner;

    public class Pick {
        //Функция выбора режима работы программы шифратор/дешифратор
        public static String pickModeMain() {
            Messages messages = new Messages();
            String userPick = null;
            try {
                Scanner scanner = new Scanner(System.in);
                userPick = scanner.nextLine();
                switch (userPick) {
                    case "1":
                        System.out.println(messages.messageEncryptPicked);
                        break;
                    case "2":
                        System.out.println(messages.messageDecryptPicked);
                        break;
                    default:
                        System.out.println(messages.errorMessage1);
                        pickModeMain();
                        break;
                }
        } catch (NullPointerException e) {
            System.out.println(messages.ErrorMessage2);
            pickModeMain();
        }
        return userPick;
    }

        //Функция выбора режима дешифрования
    public static String decodingPickMode() {
        Messages messages = new Messages();
        String userPick = null;
            try {
                Scanner scanner = new Scanner(System.in);
                userPick = scanner.nextLine();
                switch (userPick) {
                    case "1":
                        System.out.println(messages.messageCaesarPicked);
                        break;
                    case "2":
                        System.out.println(messages.messageBrureForcePicked);
                        break;
                    case "3":
                        System.out.println(messages.messageStysticPicked);
                        break;
                    default:
                        System.out.println(messages.errorMessage3);
                        pickModeMain();
                        break;
                }
            } catch (NullPointerException e) {
                System.out.println(messages.ErrorMessage2);
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
}
