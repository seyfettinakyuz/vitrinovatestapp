package com.seyfettin.vitrinovaapplication.databinding;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.seyfettin.vitrinovaapplication.R;
import com.seyfettin.vitrinovaapplication.helper.textdrawable.TextDrawable;
import com.squareup.picasso.Picasso;

final class ImageBindingAdapter {
    private ImageBindingAdapter() {

    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.ic_place_holder)
                .into(view);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter(value = "imageUrlShop")
    public static void loadImageByShop(ImageView view, String imageUrl) {

        if(imageUrl.length() == 1){
            view.post(() -> {
                TextDrawable drawable = TextDrawable.builder()
                        .buildRect(imageUrl, Color.parseColor("#fa3c1a"));
                view.setImageBitmap(convertToBitmap(drawable,view.getWidth(),view.getHeight()));
            });
        }else {
            Picasso.get()
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_place_holder)
                    .into(view);
        }

    }

    public static Bitmap convertToBitmap(Drawable drawable, int widthPixels, int heightPixels) {
        Bitmap mutableBitmap = Bitmap.createBitmap(widthPixels, heightPixels, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mutableBitmap);
        drawable.setBounds(0, 0, widthPixels, heightPixels);
        drawable.draw(canvas);

        return mutableBitmap;
    }

}
