package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FoodItemAdapter extends ArrayAdapter<FoodItem> {

    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
    }

    public FoodItemAdapter(Context context, ArrayList<FoodItem> foodList) {
        super(context, 0, foodList);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        FoodItem foodItem = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.foodItemTextView);
        textView.setText(foodItem.toString());

        Button deleteButton = convertView.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(position);
                }
            }
        });

        return convertView;
    }
}