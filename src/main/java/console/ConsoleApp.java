import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Запрос имени входного файла
        System.out.print("Введите имя входного файла (например, input01.txt): ");
        String inputFileName = scanner.nextLine();

        // Запрос имени выходного файла
        System.out.print("Введите имя выходного файла (например, output.txt): ");
        String outputFileName = scanner.nextLine();

        // Чтение данных из файла
        List<Integer> numbers = readNumbersFromFile(inputFileName);
        if (numbers == null) {
            System.out.println("Ошибка при чтении файла. Программа завершена.");
            return;
        }

        // Обработка данных (перестановка чисел)
        processVariant13(numbers);

        // Вывод результатов в консоль
        System.out.println("Результаты обработки:");
        for (Integer num : numbers) {
            System.out.println(num);
        }

        // Запись результатов в файл
        writeNumbersToFile(outputFileName, numbers);
        System.out.println("Результаты сохранены в файле: " + outputFileName);
    }

    // Метод для чтения чисел из файла
    private static List<Integer> readNumbersFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    numbers.add(Integer.parseInt(line.trim()));
                } catch (NumberFormatException e) {
                    System.err.println("Пропущена строка с некорректным числом: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return null;
        }
        return numbers;
    }

    // Метод для записи чисел в файл
    private static void writeNumbersToFile(String filename, List<Integer> numbers) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Integer num : numbers) {
                writer.write(num.toString());
                writer.newLine(); // Добавляем перенос строки
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    // Реализация метода по варианту 13 (перестановка чисел)
    public static void processVariant13(List<Integer> list) {
        if (list.isEmpty()) return;

        int positiveIndex = 0; // Индекс для положительных чисел
        int negativeIndex = 0; // Индекс для отрицательных чисел

        // Разделяем список на положительные и отрицательные числа
        List<Integer> positives = new ArrayList<>();
        List<Integer> negatives = new ArrayList<>();

        for (Integer num : list) {
            if (num >= 0) positives.add(num);
            else negatives.add(num);
        }

        // Чередуем положительные и отрицательные числа
        List<Integer> result = new ArrayList<>();
        int minSize = Math.min(positives.size(), negatives.size());

        for (int i = 0; i < minSize; i++) {
            result.add(negatives.get(i));
            result.add(positives.get(i));
        }

        // Добавляем оставшиеся числа (если одного знака больше)
        if (positives.size() > minSize) {
            for (int i = minSize; i < positives.size(); i++) {
                result.add(positives.get(i));
            }
        }
        if (negatives.size() > minSize) {
            for (int i = minSize; i < negatives.size(); i++) {
                result.add(negatives.get(i));
            }
        }

        // Обновляем исходный список
        list.clear();
        list.addAll(result);
    }
}
