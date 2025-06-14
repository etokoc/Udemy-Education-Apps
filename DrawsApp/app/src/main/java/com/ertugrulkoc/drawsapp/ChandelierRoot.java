package com.ertugrulkoc.drawsapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public class ChandelierRoot extends View {

    private Paint bodyPaint;
    private Paint bulbPaint;
    private Paint beamPaint;


    private Canvas canvas;
    private Boolean isLightOn = false;

    public ChandelierRoot(Context context) {
        super(context);
        init();
    }

    private void init() {

        bodyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bodyPaint.setColor(0xFF000000); // Siyah avize

        bulbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bulbPaint.setColor(0xFF333333); // Koyu gri ampul

        beamPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        beamPaint.setColor(0xFF000000); // Siyah ışınlar
        beamPaint.setStrokeWidth(8f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        canvasCenter();

        float centerX = getWidth() / 2f;

        float topCircleRadius = drawTopCircle(centerX);

        // gövde
        float bottomY = drawBody(topCircleRadius, centerX);

        // Ampul
        float bulbRadius = drawLamp(centerX, bottomY);

        // Işınlar
        drawLight(bottomY, bulbRadius, centerX);

    }


    private void canvasCenter() {
        canvas.save();
        float canvasCenterX = 0;
        float canvasCenterY = getHeight() / 2f;
        float translateY = canvasCenterY - getHeight() * 0.2f;
        canvas.translate(canvasCenterX, translateY);
    }

    private float drawLamp(float centerX, float bottomY) {
        float bulbRadius = getWidth() * 0.05f;
        canvas.drawCircle(centerX, bottomY + bulbRadius + 10, bulbRadius, bulbPaint);
        return bulbRadius;
    }

    private void drawLight(float bottomY, float bulbRadius, float centerX) {
        // Işınlar
        float beamStartY = bottomY + bulbRadius * 2 + 20;
        canvas.drawLine(centerX, beamStartY, centerX, beamStartY + 60, beamPaint); // Ortadaki

        float offsetX = 40;
        canvas.drawLine(centerX - offsetX, beamStartY + 20,
                centerX - offsetX - 20, beamStartY + 60, beamPaint); // Sol

        canvas.drawLine(centerX + offsetX, beamStartY + 20,
                centerX + offsetX + 20, beamStartY + 60, beamPaint); // Sağ
    }

    private float drawBody(float topCircleRadius, float centerX) {
        float topY = topCircleRadius * 2;
        float bottomY = getHeight() * 0.2f;
        Path trapezoid = new Path();
        trapezoid.moveTo(centerX - getWidth() * 0.1f, topY);
        trapezoid.lineTo(centerX + getWidth() * 0.1f, topY);
        trapezoid.lineTo(centerX + getWidth() * 0.4f, bottomY);
        trapezoid.lineTo(centerX - getWidth() * 0.4f, bottomY);
        trapezoid.close();
        canvas.drawPath(trapezoid, bodyPaint);
        return bottomY;
    }

    private float drawTopCircle(float centerX) {
        // Avize üst parça (yarım daire)
        float topCircleRadius = getWidth() * 0.08f;
        canvas.drawArc(centerX - topCircleRadius, 0,
                centerX + topCircleRadius, topCircleRadius * 2,
                180, 180, true, bodyPaint);
        return topCircleRadius;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            changeLampState();
        }
        return true;
    }

    private void changeLampState() {
        isLightOn = !isLightOn;
        bulbPaint.setColor(isLightOn ? 0xFFFFD700 : 0xFF333333); // Altın rengi veya koyu gri
        beamPaint.setColor(isLightOn ? 0xFFFFD700 : 0xFF000000); // Altın rengi veya siyah
        invalidate(); // Görünümü yeniden çiz
    }
}
