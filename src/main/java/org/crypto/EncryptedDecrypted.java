package org.crypto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptedDecrypted {

    private final Scanner scanner = new Scanner(System.in);
    private final CaesarCipher cesarCipher = new CaesarCipher();

    public void encryptDecode(Boolean encrypt) throws IOException {

        System.out.println("Введите путь к файлу:");
        String path = scanner.nextLine();

        System.out.println("Введите ключ:");
        int key = Integer.parseInt(scanner.nextLine());
        String suffix = encrypt ? "_enc" : "_dec";
        Path newPath = PathHelper.buildFileName(path, suffix);

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String message = encrypt ? cesarCipher.encrypt(string, key) : cesarCipher.decode(string, key);

                writer.write(message + System.lineSeparator());
            }
        }
        System.out.println("Содержимое файла " + newPath.getFileName() +
                System.lineSeparator());
    }
}
