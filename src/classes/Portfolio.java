package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Portfolio {
    private String ownerName;

    private List<Asset> assetList = new ArrayList<>();

    // Default constructor
    public Portfolio(){
    }

    // Constructor with arguments
    public Portfolio(String ownerName) {
        this.ownerName = ownerName;
    }

    // Getters and Setters
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    // Method for adding assets to portfolio (Adding assets)
    public void addAsset(Asset asset){
        assetList.add(asset);
    }

    // Method for removing assets from portfolio (Removing asset)
    public void removeAsset(Asset asset){
        assetList.remove(asset);
    }

    // Method to sell assets/shares
    public boolean sellAsset(String assetName){
        Scanner sc = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        boolean success = false;

        for (Asset asset : assetList) {
            names.add(asset.getAssetName());
        }

        if (names.contains(assetName)) {
            for (Asset asset : assetList) {
                if (asset.getAssetName().equals(assetName)) {
                    // Request the amount to be sold
                    System.out.print("Enter the amount you want to sell: ");
                    int amount = sc.nextInt();

                    // Error if amount < 0 or amount > invested amount
                    if (amount < 1 || amount > asset.getAmount()){
                        System.out.println("Invalid amount");
                    } else{
                        // If only part of the total amount is going to be sold, set new amount
                        asset.setAmount(asset.getAmount() - amount);
                        success = true;

                        // If all shares of the assets are sold, remove the asset from the portfolio
                        if (asset.getAmount() == 0) {
                            assetList.remove(asset);
                        }
                    }
                }
            }
        } else {
            System.out.println("\nAsset not found");
        }
        return success;

    }

    // Method to show invested assets (Asset name + price per unit + total invested + purchase date)
    public void investedAssets(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (assetList.size() != 0) {
            System.out.println("---------- Invested Assets ----------");
            // For each asset in the portfolio, print all asset info
            for (Asset asset: assetList) {
                System.out.println(
                        "Asset code/name: " + asset.getAssetName() +
                                " | Price per Unit: U$" + asset.getPricePerUnit() +
                                " | Amount: " + asset.getAmount() +
                                " | Total invested: U$" + String.format("%.2f", asset.getTotalInvested()) +
                                " | Purchase date: " + sdf.format(asset.getPurchaseDate()));
            }
        } else {
            System.out.println("\nYou don't have any invested assets yet");
        }
    }

    // Method to show invested assets (investedAssets()) + total amount of shares + total amount invested
    public void Summary(){
        if (assetList.size() != 0) {
            // Call investedAssets method
            investedAssets();

            /* For each asset in the portfolio, add the amount and amount invested in the variables totalAmount and
             totalInvested; */
            int totalAmount = 0;
            double totalInvested = 0;
            for (Asset asset : assetList) {
                totalAmount += asset.getAmount();
                totalInvested += asset.getTotalInvested();
            }

            System.out.println("---------- Summary ----------");
            System.out.println("You have a total amount of " + totalAmount + " shares");
            System.out.println("You have a total invested of U$" + totalInvested);

        } else {
            System.out.println("\nThe portfolio is empty.");
        }

    }
}
