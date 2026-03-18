package org.example.core;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**
     * Читает все строки из файла
     */
    public static String[] readLinesFromFile(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        return lines.toArray(new String[0]);
    }

    /**
     * Записывает строки в файл
     */
    public static void writeLinesToFile(String filename, String[] lines) throws IOException {
        // Создаем директорию, если её нет
        Path path = Paths.get(filename);
        Path parentDir = path.getParent();
        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        Files.write(path, List.of(lines));
    }

    /**
     * Проверяет, существует ли файл
     */
    public static boolean fileExists(String filename) {
        return Files.exists(Paths.get(filename));
    }
}