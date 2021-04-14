package ru.techpark.myapplication.Number;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.techpark.myapplication.R;

public class NumberAdapter extends RecyclerView.Adapter<NumberViewHolder> {

    private ArrayList<Number> numberList;
    private OnNumberClickListener onNumberClickListener;


    public NumberAdapter(int size, OnNumberClickListener listener) {
        numberList = new ArrayList<Number>();
        for (int i = 1; i <= size; ++i) {
            numberList.add(new Number(i));
        }

        this.onNumberClickListener = listener;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View numberView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_one_item, parent, false);
        return new NumberViewHolder(numberView, onNumberClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        holder.bind(numberList.get(position));
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }
}
