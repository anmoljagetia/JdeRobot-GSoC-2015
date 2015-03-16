package com.example.anmoljagetia.myapplication;

/**
 * Created by Anmol Jagetia on 15-03-2015.
 */
public class Item {
    public String sku;
    public String amount;
    public String currency;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Item() {

    }

}
