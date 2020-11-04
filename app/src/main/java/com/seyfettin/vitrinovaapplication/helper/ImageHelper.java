package com.seyfettin.vitrinovaapplication.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageHelper {
    private Bitmap bitmap;

    public ImageHelper(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageHelper setResize(int newWidth, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();

        if(scaleWidth<scaleHeight){
            matrix.postScale(scaleHeight, scaleHeight);
        }else {
            matrix.postScale(scaleWidth, scaleWidth);
        }

        bitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        return this;
    }

    public ImageHelper setGrayScale() {
        Bitmap mBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(createGreyMatrix());
        bitmap = getImageWithFilter(bitmap, mBitmap, filter);
        return this;
    }

    public Bitmap get() {
        return bitmap;
    }

    private Bitmap getImageWithFilter(Bitmap bitmap, Bitmap toBitmap, ColorMatrixColorFilter filter) {
        Canvas canvas = new Canvas(toBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return toBitmap;
    }

    private ColorMatrix createGreyMatrix() {
        return new ColorMatrix(new float[]{
                0.2989f, 0.5870f, 0.1140f, 0, 0,
                0.2989f, 0.5870f, 0.1140f, 0, 0,
                0.2989f, 0.5870f, 0.1140f, 0, 0,
                0, 0, 0, 1, 0
        });
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new java.net.URL(src);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
