package com.hackingbuzz.viewpager2withdots.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackingbuzz.viewpager2withdots.R;


public class SecondFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.second_fragment, container, false);


        return v;
    }

    public static SecondFragment newInstance() {   // returning fragment object to main activity where we are adding it to array list...

        SecondFragment f = new SecondFragment();
        Bundle b = new Bundle();     // Bundle helps to send object ...  setting bundle to fragment and then returing fragment
        f.setArguments(b);

        return f;
    }


}