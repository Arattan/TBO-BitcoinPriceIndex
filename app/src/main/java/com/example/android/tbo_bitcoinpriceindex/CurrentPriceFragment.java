package com.example.android.tbo_bitcoinpriceindex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CurrentPriceFragment extends Fragment {

    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/";
    private static final String API_KEY =
            "&api_key={5a100b51fd42714654fe58e10f7da21cabf16f8312b82922236e91463ab56332}";
    private TextView textViewResult;


    public CurrentPriceFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_price_current, container, false);
        TextView textViewResult = (TextView)rootView.findViewById(R.id.current_price_view);

        Retrofit retrofit = provideRetrofit();
        BitcoinPriceIndexApi bitcoinPriceIndexApi = provideApiService(retrofit);
        getBitcoinPriceIndex(bitcoinPriceIndexApi);


//        textViewResult.setText("HI");
        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public BitcoinPriceIndexApi provideApiService(Retrofit retrofit) {
        return retrofit.create(BitcoinPriceIndexApi.class);
    }

    private void getBitcoinPriceIndex(BitcoinPriceIndexApi bitcoinPriceIndexApi) {

        Call<BitcoinPrice> call = bitcoinPriceIndexApi.getBitcoinPrices(
                "BTC", "EUR", API_KEY);

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
