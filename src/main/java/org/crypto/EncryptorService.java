package org.crypto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptorService {

    private final Scanner scanner;
    private final CaesarCipher cesarCipher = new CaesarCipher();

    public EncryptorService(Scanner scanner) {
        this.scanner = scanner;
    }
    public void encryptDecode(Boolean encrypt) throws IOException {

        System.out.println("Введите путь к файлу:");
        String path = scanner.nextLine();

        System.out.println("Введите ключ:");
        int key = Integer.parseInt(scanner.nextLine());
        String end = encrypt ? "_enc" : "_dec";
        Path newPath = PathHelper.buildFileName(path, end);

        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(newPath)) {
            while (reader.ready()) {
                String string = reader.readLine();
                String message = encrypt ? cesarCipher.encrypt(string, key) : cesarCipher.decode(string, key);

                writer.write(message + System.lineSeparator());
            }
        }
        System.out.println("Содержимое файла " + path + " сохранено в " + newPath.getFileName());
    }
}
