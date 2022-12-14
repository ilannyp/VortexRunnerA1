package com.sdm.mgplab1;

import static java.lang.Math.round;

import android.bluetooth.le.ScanSettings;
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

    // Timer for next entity to be spawned
    private float SpawnNewEntityTimer = 0.0f;
    // Max timer for next entity to be spawned
    private float MaxSpawnEntityTimer = 1.f;


    private boolean pattern1Start = true;
    private int _noOfSpike = 0;
    private int _noOfWalls = 0;
    private int _noOfWallsSmall = 0;
    protected static final String TAG = null;



    public void SpawnSpike(int NumberOfSpikes)
    {
        if(_noOfSpike >= NumberOfSpikes) {
            return;
        }
        else if (_noOfSpike < NumberOfSpikes)
        {
            if(SpikeTimer >= 0.2)
            {
                SpikeEntity.Create();
                System.out.println("Spawn");
                _noOfSpike++;
                SpikeTimer = 0;
            }
        }
    }
    public void SpawnWall(int NumberOfWalls, float _dt)
    {
        if(_noOfWalls >= NumberOfWalls)
            return;
        else if (_noOfWalls < NumberOfWalls)
        {
            if(WallTimer >= 0.2)
            {
                TwoBlockWall.Create();
                System.out.println("Wall Spawn");
                _noOfWalls++;
                WallTimer = 0;
            }
        }

    }
    public void SpawnWallSmall(int NumberOfWalls, float _dt)
    {
        if(_noOfWallsSmall >= NumberOfWalls)
            return;
        else if (_noOfWallsSmall < NumberOfWalls)
        {
            if(WallTimer >= 0.2)
            {
                TwoBlockWall.Create();
                System.out.println("Wall Spawn");
                _noOfWallsSmall++;
                WallTimer = 0;
            }
        }
    }

    public void SpawnNewEntity(int index,float _dt)
    {
        SpawnNewEntityTimer+= _dt;
        switch (index)
        {
            case 1:
                SpawnSpike(1);
            case 2:
                SpawnWallSmall(1,_dt);
            case 3:
                SpawnWall(1,_dt);
        }
    }

    public void LevelHC(int _dt)
    {
        timer += _dt;
        if(timer < 700)
        {
            if(SpawnNewEntityTimer > MaxSpawnEntityTimer)
            {
                InvertedSpikeEntity.Create();
                SpawnNewEntityTimer = 0;
            }
        }
        if(timer == 60)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 70)
        {
            SpikeEntity.Create();
        }
        if(timer == 78)
        {
            SpikeEntity.Create();
        }
        if(timer == 90)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 120)
        {
            TwoBlockWall.Create();
        }
        if(timer == 180)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 210)
        {
            SpikeEntity.Create();
        }


        //TODO: To create until timer reaches 2220
        System.out.println(timer);
    }


    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        RenderBackground.Create();
        Floor.Create();
        // Example to include another Renderview for Pause Button
        //Player = SmurfEntity.Create();// For week 7
        //SmurfEntity.Create(); // For week 7
        PauseButtonEntity.Create();
        PlayerEntity.Create();
        SwapGravButtonEntity.Create();
        //TwoBlockWall.CreateNew(200,600);


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
        //timer += _dt;
        WallTimer += _dt;
        SpawnNewEntityTimer+= _dt;
        SpikeTimer+= _dt;
        SpeedUpTimer += _dt;
        SlowDownTimer += _dt;
        EntityManager.Instance.Update(_dt);
       // System.out.println(timer);

        // ToDo: If got time change from hardcode to using patterns
//        if(pattern1Start)
//        {
//            SpawnSpike(1);
//            if(SpawnNewEntityTimer >= MaxSpawnEntityTimer) {
//                // System.out.println("Wall Spawned");
//                SpawnWall(1, _dt);
//                SpawnNewEntityTimer = 0;
//                pattern1Start = false;
//            }
//
//        }
        LevelHC(1);
        //System.out.println(pattern1Start);
//        if(WallTimer)
//        {
//            System.out.println("Wall Created");
//            TwoBlockWall.Create();
//        }
        //System.out.println(WallTimer);

//       if(pattern1Start)
//       {
//            if (i < 3)
//            {
//                SpikeTimer += _dt;
//                if (SpikeTimer >= 0.2)
//                {
//                    SpikeEntity.Create();
//                    System.out.println("Spawn");
//                    i++;
//                    SpikeTimer = 0;
//                }
//            }
//        }
    }
}



