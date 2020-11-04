package com.seyfettin.vitrinovaapplication.databinding;

import android.graphics.Paint;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

final class TextViewBindingAdapter {
    private TextViewBindingAdapter() {

    }

    @BindingAdapter(value = "strikeThrough")
    public static void strikeThrough(TextView textView, Boolean strikeThrough) {
        if (strikeThrough) {
            textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            textView.setPaintFlags(textView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
