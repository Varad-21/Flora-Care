package com.example.skincure;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textViewName;
    public TextView textViewPrevention;
    public TextView textViewPrecautions;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewPrevention = itemView.findViewById(R.id.textViewPrevention);
        textViewPrecautions = itemView.findViewById(R.id.textViewPrecautions);
    }

    public void setName(String name) {
        textViewName.setText(name);
    }

    public void setPrevention(String prevention) {
        textViewPrevention.setText(prevention);
    }

    public void setPrecautions(String precautions) {
        textViewPrecautions.setText(precautions);
    }
}
