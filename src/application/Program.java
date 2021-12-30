package application;

import classes.Asset;
import classes.Portfolio;

import java.io.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
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
                System.out.println("Enter the portfolio name: ");
                String name = sc.nextLine();

                // Create file path with the name entered
                String userPath =
                        "C:\\Users\\" +
                                System.getProperty("user.name") +
                                "\\Desktop" +
                                "\\Portfolios\\" + name + ".txt";

                // Create a .txt file where all info will be stored (Portfolio)
                File f = new File(userPath);
                // If there is already a .txt with the name entered, inform error. Else, create the file
                if (f.exists()) {
                    System.out.println("Error creating portfolio: Portfolio '" + name + "' already exists");
                } else {
                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
                        // Inform sucess
                        System.out.println("Portfolio created successfully!");
                    } catch (IOException e) {
                        System.out.println("Error creating portfolio: " + e.getMessage());
                    }
                }
            }


            // Load portfolio option
            if (option == 2) {
                // Request the portfolio name
                System.out.println("Enter the portfolio name: ");
                String name = sc.nextLine();

                // Create file path with the name entered and create a file with it
                String portfolioPath =
                        "C:\\Users\\" +
                                System.getProperty("user.name") +
                                "\\Desktop" +
                                "\\Portfolios\\" + name + ".txt";
                File f = new File(portfolioPath);

                // Check if the portfolio .txt file exists
                if (!f.exists()) {
                    System.out.println("Error: Portfolio '" + name + "' does nos exists");
                } else {
                    // Inform success loading portfolio
                    System.out.println("\nPortfolio '" + name + "' loaded successfully!");
                    Portfolio portfolio = new Portfolio();

                    try (BufferedReader br = new BufferedReader(new FileReader(f))){


                        String itemStr = br.readLine();
                        while (itemStr != null) {
                            String[] fields = itemStr.split(",");

                            String assetName = fields[0];
                            double amount = Double.parseDouble(fields[1]);
                            double pricePerUnit = Double.parseDouble(fields[2]);
                            String purchaseDateStr = fields[3];

                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date purchaseDate = sdf.parse(purchaseDateStr);

                            Asset asset = new Asset(assetName, amount, pricePerUnit, purchaseDate);
                            portfolio.addAsset(asset);

                            itemStr = br.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    int operation = menu("Add asset", "Sell asset", "View invested assets", "Portfolio overview");

                    // Add asset option
                    if (operation == 1) {
                        // Request asset name
                        System.out.print("Enter asset name: ");
                        String assetName = sc.nextLine();

                        // Request asset amount
                        System.out.print("Enter amount: ");
                        int amount = sc.nextInt();

                        // Request price per asset unit/share
                        System.out.print("Enter price per unit: U$");
                        double pricePerUnit = sc.nextDouble();

                        // Add purchase date
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date purchaseDate = new Date();
                        String purchaseDateStr = sdf.format(purchaseDate);


                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))){
                                bw.newLine();
                                bw.write(assetName + "," + amount + "," + String.format("%.2f", pricePerUnit) + "," + purchaseDateStr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    // Sell asset option
                    if (operation == 2) {
                        System.out.print("Enter asset name: ");
                        String sellAssetName = sc.nextLine();

                        portfolio.sellAsset(sellAssetName);
                    }

                    // View invested assets option
                    if (operation == 3) {
                        portfolio.investedAssets();
                    }

                    // Portfolio overview
                    if (operation == 4) {
                        portfolio.Summary();
                    }
                }
            }

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
