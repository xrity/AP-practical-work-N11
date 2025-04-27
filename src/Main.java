import java.io.*;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static String path;

    public static String pathCreate() {
        System.out.println("Write path or 0 to default:");
        String input = sc.nextLine();
        if (input.equals("0")) {
            return "file.txt";
        }
        return input;
    }

    public static boolean writeFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
             BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {

            System.out.println("Enter the number of lines to add:");
            int numberOfLines = sc.nextInt();
            sc.nextLine();

            String[] oldLines = new String[1000]; // обмеження на кількість рядків
            int lineCount = 0;

            String line;
            while ((line = reader.readLine()) != null && lineCount < oldLines.length) {
                oldLines[lineCount++] = line;
            }

            String[] newLines = new String[numberOfLines];
            System.out.println("Write your lines:");
            for (int i = 0; i < numberOfLines; i++) {
                newLines[i] = sc.nextLine();
            }

            for (int i = 0; i < lineCount; i++) {
                writer.write((i + 1) + ". " + oldLines[i]);
                writer.newLine();
            }
            for (int i = 0; i < numberOfLines; i++) {
                writer.write((lineCount + i + 1) + ". " + newLines[i]);
                writer.newLine();
            }

            System.out.println("Done");
            return true;

        } catch (IOException e) {
            System.out.println("Incorrect path");
            return false;
        }
    }

    public static boolean readFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            System.out.println("Reading from " + path);
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                System.out.println(lineNumber + ". " + line);
                lineNumber++;
            }
            System.out.println();
            return true;

        } catch (IOException e) {
            System.out.println("Incorrect path");
            return false;
        }
    }

    public static boolean readFileRange() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            System.out.println("Enter start line:");
            int start = sc.nextInt();
            System.out.println("Enter end line:");
            int end = sc.nextInt();
            sc.nextLine();

            String[] lines = new String[1000];
            int lineCount = 0;
            String line;
            while ((line = reader.readLine()) != null && lineCount < lines.length) {
                lines[lineCount++] = line;
            }

            if (start < 1 || end > lineCount || start > end) {
                System.out.println("Invalid range");
                return false;
            }

            for (int i = start - 1; i <= end - 1; i++) {
                System.out.println((i + 1) + ". " + lines[i]);
            }

            return true;

        } catch (IOException e) {
            System.out.println("Incorrect path");
            return false;
        }
    }

    public static boolean insertLine() {
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
             BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"))) {

            System.out.println("Enter the line number where to insert:");
            int insertLine = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter the text to insert:");
            String textToInsert = sc.nextLine();

            String[] lines = new String[1000];
            int lineCount = 0;
            String line;
            while ((line = reader.readLine()) != null && lineCount < lines.length) {
                lines[lineCount++] = line;
            }

            if (insertLine < 1 || insertLine > lineCount + 1) {
                System.out.println("Invalid line number");
                return false;
            }

            for (int i = 0; i < insertLine - 1; i++) {
                writer.write((i + 1) + ". " + lines[i]);
                writer.newLine();
            }

            writer.write(insertLine + ". " + textToInsert);
            writer.newLine();

            for (int i = insertLine - 1; i < lineCount; i++) {
                writer.write((i + 2) + ". " + lines[i]);
                writer.newLine();
            }

            File original = new File(path);
            File temp = new File("temp.txt");

            if (original.delete()) {
                temp.renameTo(original);
            } else {
                System.out.println("Could not update the file");
                return false;
            }

            System.out.println("Line inserted");
            return true;

        } catch (IOException e) {
            System.out.println("Incorrect path");
            return false;
        }
    }

    public static void main(String[] args) {
        boolean is_working = true;
        path = pathCreate();

        while (is_working) {
            System.out.println("1 - Write to file");
            System.out.println("2 - Read entire file");
            System.out.println("3 - Change file path");
            System.out.println("4 - Read range of lines");
            System.out.println("5 - Insert line");
            System.out.println("6 - Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    writeFile();
                    break;
                case 2:
                    readFile();
                    break;
                case 3:
                    path = pathCreate();
                    break;
                case 4:
                    readFileRange();
                    break;
                case 5:
                    insertLine();
                    break;
                case 6:
                    is_working = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }
    }
}
