package classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

    // Method for removing assets from portfolio (Removing asset or selling shares)
    public void removeAsset(Asset asset){
        assetList.remove(asset);
    }

    // Method to show invested assets (Asset name + price per unit + total invested + purchase date)
    public void investedAssets(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("---------- Invested Assets ----------");
        // For each asset in the portfolio, print all asset info
        for (Asset asset: assetList) {
            System.out.println(
                    " | Asset code/name: " + asset.getAssetName() +
                    " | Price per Unit: U$" + asset.getPricePerUnit() +
                    " | Total invested: U$" + String.format("%.2f", asset.getTotalInvested()) +
                    " | Purchase date: " + sdf.format(asset.getPurchaseDate()));
        }
    }

    // Method to show invested assets (investedAssets()) + total amount of shares + total amount invested
    public void portfolioSummary(){
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
        System.out.println("You have a total amount of " + totalAmount + " assets");
        System.out.println("You have a total invested of U$" + totalInvested);
    }
}
