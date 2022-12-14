package com.sdm.mgplab1;

import android.graphics.Canvas;
import android.text.method.Touch;
import android.util.Log;
import android.view.SurfaceView;

import androidx.core.util.SparseIntArrayKt;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    private float WallTimer = 0.0f;
    private float SpikeTimer = 0.0f;
    private float SpeedUpTimer = 0.0f;
    private float SlowDownTimer = 0.0f;


    protected static final String TAG = null;
    @Override
    public String GetName() {
        return "MainGame";
    }
    public SmurfEntity Player;
    public SpikeEntity Spike;

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        Floor.Create();
        // Example to include another Renderview for Pause Button
        //Player = SmurfEntity.Create();// For week 7
        //SmurfEntity.Create(); // For week 7
        SmurfEntityTest.Create();
        Spike = SpikeEntity.Create();
        PauseButtonEntity.Create();
        PlayerEntity.Create();
        SwapGravButtonEntity.Create();



    }

    @Override
    public void OnExit() {
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    @Override
    public void Update(float _dt) {
        timer += _dt;
        WallTimer += _dt;
        SpikeTimer += _dt;
        SpeedUpTimer += _dt;
        SlowDownTimer += _dt;
        EntityManager.Instance.Update(_dt);
       // System.out.println(timer);
        if(SpikeTimer >= 12)
        {
            SpikeEntity.Create();
            System.out.println("Spawn");
            SpikeTimer = 0;
        }


    }
}



