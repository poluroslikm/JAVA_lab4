package org.example.core;

import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    /**
     * Преобразует массив строк в список чисел Double
     */
    public List<Double> parseStringsToDoubles(String[] lines) {
        List<Double> numbers = new ArrayList<>();

        for (String line : lines) {
            String trimmedLine = line.trim();
            if (trimmedLine.isEmpty()) {
                continue; // пропускаем пустые строки
            }

            try {
                numbers.add(Double.parseDouble(trimmedLine));
            } catch (NumberFormatException e) {
                System.err.println("Ошибка парсинга числа: '" + trimmedLine + "' (пропущено)");
            }
        }

        return numbers;
    }

    /**
     * ВАРИАНТ 13: Переставляет элементы так, чтобы положительные и отрицательные
     * числа чередовались. Если одного знака больше, оставляет их в конце.
     * Пример: { -1, 2, -3, 4, 5, 6, -7, 8, 9 } → { -1, 2, -3, 4, -7, 5, 6, 8, 9 }
     */
    public List<Double> processData(List<Double> input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }

        List<Double> positives = new ArrayList<>();
        List<Double> negatives = new ArrayList<>();

        // Разделяем положительные и отрицательные числа
        for (Double num : input) {
            if (num >= 0) {
                positives.add(num);
            } else {
                negatives.add(num);
            }
        }

        List<Double> result = new ArrayList<>();
        int posIndex = 0;
        int negIndex = 0;

        // Чередуем числа, пока есть и положительные, и отрицательные
        while (posIndex < positives.size() && negIndex < negatives.size()) {
            result.add(negatives.get(negIndex++)); // сначала отрицательное
            result.add(positives.get(posIndex++)); // потом положительное
        }

        // Добавляем оставшиеся числа (если есть)
        while (negIndex < negatives.size()) {
            result.add(negatives.get(negIndex++));
        }

        while (posIndex < positives.size()) {
            result.add(positives.get(posIndex++));
        }

        return result;
    }

    /**
     * Преобразует список чисел обратно в массив строк
     */
    public String[] convertDoublesToStrings(List<Double> numbers) {
        String[] result = new String[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            // Форматируем число: если это целое число, выводим без .0
            Double num = numbers.get(i);
            if (num == Math.floor(num)) {
                result[i] = String.valueOf(num.intValue());
            } else {
                result[i] = String.valueOf(num);
            }
        }
        return result;
    }

    /**
     * Основной метод конвейерной обработки
     * 1) Преобразует строки в числа
     * 2) Выполняет обработку по варианту
     * 3) Преобразует результаты обратно в строки
     */
    public String[] processPipeline(String[] inputLines) {
        // Шаг 1: парсинг строк в числа
        List<Double> numbers = parseStringsToDoubles(inputLines);

        // Шаг 2: обработка данных по варианту
        List<Double> processedNumbers = processData(numbers);

        // Шаг 3: преобразование обратно в строки
        return convertDoublesToStrings(processedNumbers);
    }
}