package com.example.adesina.currencyexchange;

/**
 * Created by adesina on 11/2/2017.
 */

public class CurrencyList {

    private String currency;
    private String btc;
    private String eth;
    private String rate;

    public String getCurrency() {
        return currency;
    }

    public String getBTC() {
        return btc;
    }

    public String getEth() {
        return eth;
    }

    public String getRate() {
        return rate;
    }


    public CurrencyList(String currency, String btc, String eth, String rate) {
        this.currency = currency;
        this.btc = btc;
        this.eth = eth;
        this.rate = rate;

    }

}
