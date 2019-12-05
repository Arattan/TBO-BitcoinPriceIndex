package com.example.android.tbo_bitcoinpriceindex;

import com.squareup.moshi.Json;

import java.util.Date;

public class BitcoinPrice {
    private Date time;
    @Json(name = "bpi") private double price;


    public Date getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }
}
