package com.company;

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.util.Arrays;
import java.util.stream.IntStream;

//TODO: ЭТОТ КЛАСС ПИСАЛ НЕ Я, НАШЕЛ, РАЗОБРАЛ, ИСПОЛЬЗОВАЛ :) САМ НЕ ДОУМАЛ...
public class CaesarCipher {

    static final char LETTER_A = 'a';
    static final char LETTER_Z = 'z';
    static final int ALPHABET_SIZE = LETTER_Z - LETTER_A + 1;
    static final double[] ENGLISH_LETTERS_PROBABILITIES = {0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035, 0.074, 0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003, 0.077, 0.063, 0.093, 0.027, 0.013, 0.016, 0.005, 0.019, 0.001};

    public static int breakCipher(String message) {
        return probableOffset(chiSquares(message));
    }

    private static double[] chiSquares(String message) {
        double[] expectedLettersFrequencies = expectedLettersFrequencies(message.length());

        double[] chiSquares = new double[ALPHABET_SIZE];

        for (int offset = 0; offset < chiSquares.length; offset++) {
            String decipheredMessage = String.valueOf(decodingAlphabet.decoding(message, offset));
            long[] lettersFrequencies = observedLettersFrequencies(decipheredMessage);
            double chiSquare = new ChiSquareTest().chiSquare(expectedLettersFrequencies, lettersFrequencies);
            chiSquares[offset] = chiSquare;
        }

        return chiSquares;
    }

    private static long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed(LETTER_A, LETTER_Z)
                .mapToLong(letter -> countLetter((char) letter, message))
                .toArray();
    }

    private static long countLetter(char letter, String message) {
        return message.chars()
                .filter(character -> character == letter)
                .count();
    }

    private static double[] expectedLettersFrequencies(int messageLength) {
        return Arrays.stream(ENGLISH_LETTERS_PROBABILITIES)
                .map(probability -> probability * messageLength)
                .toArray();
    }

    private static int probableOffset(double[] chiSquares) {
        int probableOffset = 0;
        System.out.println("Ключ - самый нименьший Chi-Square для offset'a \n");
        for (int offset = 0; offset < chiSquares.length; offset++) {
            System.out.printf("Chi-Square for offset %d: %.2f%n", offset, chiSquares[offset]);
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }

        return probableOffset;
    }
}