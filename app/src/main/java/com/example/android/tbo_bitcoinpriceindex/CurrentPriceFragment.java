package com.example.android.tbo_bitcoinpriceindex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CurrentPriceFragment extends Fragment {

    public CurrentPriceFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_price_current, container, false);
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
