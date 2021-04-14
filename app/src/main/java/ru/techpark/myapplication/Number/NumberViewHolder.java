package ru.techpark.myapplication.Number;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.techpark.myapplication.R;

public class NumberViewHolder extends RecyclerView.ViewHolder {
    private final TextView numberTextView;
    private Number number;

    public NumberViewHolder(@NonNull View itemView, OnNumberClickListener onNumberClickListener) {
        super(itemView);

        this.numberTextView = itemView.findViewById(R.id.item_number);
        this.numberTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNumberClickListener.onNumberClick(number);
                }
            }
        );
    }

    void bind(Number number) {
        this.number = number;
        this.numberTextView.setText(number.getNumber().toString());
        this.numberTextView.setTextColor(number.getColor());
    }
}
