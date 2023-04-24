package org.crypto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("""
                    Выберете действие, введя его номер:\s
                    1. Зашифровать текст в файле(Шифр Цезаря).\s
                    2. Расшифровать текст в файле(Шифр Цезаря).\s
                    3. Подобрать ключ к зашифрованному тексу в файле (brute force).\s
                    4. Расшифровать текст в файле методом статического перебора.\s
                    5. Выхода из программы""");

            String answer = reader.readLine();

            switch (answer) {
                case ("1") -> new EncryptedDecrypted().encryptDecode(true);
                case ("2") -> new EncryptedDecrypted().encryptDecode(false);
                case ("3") -> {
                    return;
                }
            }
        }
    }
}