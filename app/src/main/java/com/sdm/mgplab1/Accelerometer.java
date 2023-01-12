package com.sdm.mgplab1;
import android.app.Activity;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

public class Accelerometer implements SensorEventListener {

    private Sensor sensor;
    private float[] values = {0,0,0};
    private long lastTime = System.currentTimeMillis();

    public void SensorMove(){
        // temp var
        float tempX, tempY;

        //bX// bX and bY are variables used for moving the object
        //// values [1] – sensor values for x axis
        //// values [0] – sensor values for y axis
        // tempX = bX + (values[1] * ((System.currentTimeMillis() - lastTime)/1000 ));
        // tempY = bY + (values[0] * ((System.currentTimeMillis() - lastTime)/1000 ));
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        values = sensorEvent.values;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


}
