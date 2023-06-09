package org.crypto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Bruteforce {

    private final Scanner scanner;
    private final CaesarCipher caesarCipher = new CaesarCipher();
    private static final int MAX_LENGTH = 28;
    public Bruteforce(Scanner scanner) {
        this.scanner = scanner;
    }

    public void bruteforce() throws IOException {

        System.out.println("Введите путь к файлу, для его расшифровки:");
        String path = scanner.nextLine();

        Path bfPath = PathHelper.buildFileName(path, "_bf");

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(bfPath)) {

            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>();

            while (reader.ready()) {
                String string = reader.readLine();
                builder.append(string).append(System.lineSeparator());
                list.add(string);
            }
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String decrypt = caesarCipher.decode(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    for (String string : list) {
                        String encrypt = caesarCipher.decode(string, i);
                        writer.write(encrypt + System.lineSeparator());
                    }
                    System.out.println("Содержимое файла расшифровано, методом перебора ключей. Ключ расшифровки K = " + i);
                    break;
                }
            }
        }
    }

    private boolean isValidateText(String text) {

        boolean isValidate = false;

        for (String word : text.split(" ")) {
            if (word.length() > MAX_LENGTH) {
                return false;
            }
        }

        if (text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")) {
//            substring.matches("[.,:!?]+\s");
            isValidate = true;
        }
        while (isValidate) {
            int indexStart = new Random().nextInt(text.length() / 2);
            String substring = text.substring(indexStart, indexStart + (int) Math.sqrt(text.length()));
            System.out.println(substring + System.lineSeparator() + "Понятен ли вам этот текст? Y/N");

            String answer = scanner.nextLine();

            if (answer.equalsIgnoreCase("Y")) {
                return true;
            } else if (answer.equalsIgnoreCase("N")) {
                isValidate = false;
            } else {
                System.out.println("выберете только между Y/N");
            }
        }
        return false;
    }
}