package com.seyfettin.vitrinovaapplication.helper;

import android.content.res.Resources;

public class DisplayUtils {
    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
    public static int screenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
}
