package com.ertugrulkoc.drawsapp;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;


public class SwitchWithDrawLayout extends FrameLayout {

    private Paint backgroundPaint;
    private Paint blackPaint;
    private boolean isOn = false;

    public SwitchWithDrawLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {

        backgroundPaint = new Paint();
        blackPaint = new Paint();
        backgroundPaint.setColor(Color.GRAY);
        blackPaint.setColor(Color.BLACK);
        blackPaint.setTextSize(100f);
        Switch sw = new Switch(context);
        sw.setChecked(false);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isOn = isChecked;
            backgroundPaint.setColor(isOn ? Color.WHITE : Color.GRAY);
            invalidate();
        });
        addView(sw);
        sw.setTranslationX(0);
        sw.setTranslationY(0);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);
        canvas.drawText("Lamba", getWidth() / 2f, getHeight() / 2f, blackPaint);
        super.dispatchDraw(canvas);
    }
}
