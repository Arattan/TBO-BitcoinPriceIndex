package com.example.android.tbo_bitcoinpriceindex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BitcoinPriceIndexApi {

    @GET("price")
    Call<BitcoinPrice> getBitcoinPrices(
            @Query("fsym") String fsym,
            @Query("tsyms") String tsyms,
            @Query("api_key") String api_key);
}
