package classes;

import java.util.Date;

public class Asset {
    private String assetName;
    private Double amount;
    private Double pricePerUnit;
    private Date purchaseDate;

    // Default constructor
    public Asset(){
    }

    // Constructor with arguments
    public Asset(String assetName, Double amount, Double pricePerUnit, Date purchaseDate) {
        this.assetName = assetName;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Double getTotalInvested(){
        return pricePerUnit * amount;
    }

}
