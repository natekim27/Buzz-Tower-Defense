package com.example.towerdefense.util;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

public class DrawableHelper {

    public static GradientDrawable buildCircle() {
        GradientDrawable shape = new GradientDrawable();

        shape.setShape(GradientDrawable.OVAL);
        shape.setColor(Color.parseColor("#99808080"));
        shape.setStroke(5, Color.parseColor("#FF808080"));

        return shape;
    }
}
