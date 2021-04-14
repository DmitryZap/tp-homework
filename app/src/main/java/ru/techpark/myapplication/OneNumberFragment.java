package ru.techpark.myapplication;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ru.techpark.myapplication.Number.Number;

public class OneNumberFragment extends Fragment {

    public final static String TAG = "ru.techpark.myapplication.OneNumberFragment";

    private Number number;
    private TextView item_number;

    public OneNumberFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one_item, container, false);

        item_number = (TextView) view.findViewById(R.id.item_number);
        item_number.setText(number.getNumber().toString());
        item_number.setTextColor(number.getColor());
        return view;
    }


    public void setNumber(Number number) {
        this.number = number;
    }

}