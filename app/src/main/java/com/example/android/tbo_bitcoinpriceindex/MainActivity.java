package com.example.android.tbo_bitcoinpriceindex;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/";
    private static final String API_KEY = "&api_key={5a100b51fd42714654fe58e10f7da21cabf16f8312b82922236e91463ab56332}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Provide CurrentPriceFragment
        CurrentPriceFragment currentPriceFragment = new CurrentPriceFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.current_price_fragment_container, currentPriceFragment)
                .commit();

//        textViewResult = findViewById(R.id.textView);

        Retrofit retrofit = provideRetrofit();

        BitcoinPriceIndexApi bitcoinPriceIndexApi = getBitcoinPriceIndex(retrofit);

        getBitcoinPriceIndex(bitcoinPriceIndexApi);
    }

    public BitcoinPriceIndexApi getBitcoinPriceIndex(Retrofit retrofit) {
        return retrofit.create(BitcoinPriceIndexApi.class);
    }

    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .build();
    }

    private void getBitcoinPriceIndex(BitcoinPriceIndexApi bitcoinPriceIndexApi) {
        Call<BitcoinPrice> call = bitcoinPriceIndexApi.getBitcoinPrices("BTC", "EUR", API_KEY);

        call.enqueue(new Callback<BitcoinPrice>() {
            @Override
            public void onResponse(Call<BitcoinPrice> call, Response<BitcoinPrice> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                } else {
                    BitcoinPrice bitcoinPricesResponse = response.body();
                    textViewResult.append("EUR: " + bitcoinPricesResponse.getTsyms());
                }
            }

            @Override
            public void onFailure(Call<BitcoinPrice> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
