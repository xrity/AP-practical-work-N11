import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        try {
            System.out.println("Write to " + path);
            String text = sc.nextLine();
            String old_text = new String();



            FileWriter writer = new FileWriter(path);
            FileReader reader = new FileReader(path);


            int c;
            int i = 0;
            while ((c = reader.read()) != -1) {
                old_text = old_text.substring(0, i) + (char) c + old_text.substring(i + 1);
            }
            System.out.println();
            reader.close();


            writer.write(old_text + text);
            writer.close();

            System.out.println("Done");
            return true;

        } catch (IOException e) {
            System.out.println("Incorrect path");
            return false;
        }
    }

    public static boolean readFile() {
        try {
            System.out.println("Reading from " + path);
            FileReader reader = new FileReader(path);

            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
            reader.close();

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
            System.out.println("1 - Write");
            System.out.println("2 - Read");
            System.out.println("3 - Change file");
            System.out.println("4 - Exit");

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
                    is_working = false;
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }
    }
}
