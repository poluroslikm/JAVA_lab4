package gui;

import core.DataProcessor;
import core.FileUtils;
import models.InputArgs;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApp {

    public static void main(String[] args) {
        System.out.println("Программа обработки данных");

        Scanner scanner = new Scanner(System.in);

        try {
            InputArgs inputArgs = getInputFromUser(scanner);

            if (!FileUtils.fileExists(inputArgs.getInputFile())) {
                System.err.println("Входной файл не существует");
                return;
            }

            String[] inputLines = FileUtils.readLinesFromFile(inputArgs.getInputFile());

            System.out.println("\nИсходные данные:");
            for (int i = 0; i < inputLines.length; i++) {
                System.out.println(inputLines[i]);
            }

            DataProcessor processor = new DataProcessor();
            String[] resultLines = processor.processPipeline(inputLines);

            System.out.println("\nРезультат:");
            for (int i = 0; i < resultLines.length; i++) {
                System.out.println(resultLines[i]);
            }

            FileUtils.writeLinesToFile(inputArgs.getOutputFile(), resultLines);
            System.out.println("Результат сохранен в: " + inputArgs.getOutputFile());


        } catch (IOException e) {
            System.err.println("\nОшибка ввода: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("\nОшибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static InputArgs getInputFromUser(Scanner scanner) {
        System.out.println("\nВведите параметры:");

        System.out.print("Входной файл (например: input01.txt): ");
        String inputFile = "test/" + scanner.nextLine().trim();

        System.out.print("Выходной файл (например: result01.txt): ");
        String outputFile = "output/" + scanner.nextLine().trim();

        return new InputArgs(inputFile, outputFile);
    }
}