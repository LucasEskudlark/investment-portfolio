package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Create directory where the files will be created (User Desktop)
        String directoryPath =
                "C:\\Users\\" +
                        System.getProperty("user.name") +
                        "\\Desktop" +
                        "\\Portfolios";
        new File(directoryPath).mkdirs();


        while (true) {
            int option = menu("Create portfolio", "Load portfolio", "Exit");

            // Option 1 - User wants to create a portfolio
            if (option == 1) {
                // Request portfolio owner's name
                System.out.println("Enter your name: ");
                String name = sc.nextLine();

                // Create file path with the name entered
                String userPath =
                        "C:\\Users\\" +
                                System.getProperty("user.name") +
                                "\\Desktop" +
                                "\\Portfolios\\" + name + ".txt";

                // Create a .txt file where all info will be stored (Portfolio)
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(userPath))) {
                    // Inform sucess
                    System.out.println("Portfolio created successfully!");
                } catch (IOException e) {
                    System.out.println("Error creating portfolio: " + e.getMessage());
                }


            }


            // Load portfolio option


            // Exit option
            if (option == 3) {
                break;
            }


        }


    sc.close();
    }

    /* Interactive menu method
     * It receives String options and creates a menu requesting the option chosen by the user*/
    public static int menu(String... options) {
        Scanner sc = new Scanner(System.in);
        int c = 1;

        System.out.println();
        System.out.println("------- Options -------");
        for (String option: options) {
            System.out.println("[" + c + "] " + option);
            c++;
        }
        System.out.print("Enter your option: ");

        return sc.nextInt();
    }
}
