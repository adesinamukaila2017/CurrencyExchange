package com.example.adesina.currencyexchange;

/**
 * Created by adesina on 11/2/2017.
 */

public class CurrencyList {

    private String currency;
    private Double btc;
    private Double eth;


    public String getCurrency() {
        return currency;
    }

    public Double getBTC() {
        return btc;
    }

    public Double getEth() {
        return eth;
    }



    public CurrencyList(String currency, Double btc, Double eth) {
        this.currency = currency;
        this.btc = btc;
        this.eth = eth;


    }

}
