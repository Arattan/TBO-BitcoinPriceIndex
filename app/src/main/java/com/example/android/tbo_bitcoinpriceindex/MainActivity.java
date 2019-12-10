package com.example.android.tbo_bitcoinpriceindex;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Provide CurrentPriceFragment
        CurrentPriceFragment currentPriceFragment = new CurrentPriceFragment();

        //
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.current_price_fragment_container, currentPriceFragment)
                .commit();

        textViewResult = findViewById(R.id.current_price_view);


    }


}
