package com.sdm.mgplab1;

import android.app.GameManager;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
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
    private boolean gameWon = false;
    public static boolean wanGoBack;
    // Timer for next entity to be spawned
    private float SpawnNewEntityTimer = 0.0f;
    // Max timer for next entity to be spawned
    private float MaxSpawnEntityTimer = 1.f;


    private boolean pattern1Start = true;
    private int _noOfSpike = 0;
    private int _noOfWalls = 0;
    private int _noOfWallsSmall = 0;
    protected static final String TAG = null;

    public void Reset()
    {
        wanGoBack = false;
        EntityManager.Instance.Clean();
      timer = 0.0f;
      WallTimer = 0.0f;
      SpikeTimer = 0.0f;
      SpeedUpTimer = 0.0f;
      SlowDownTimer = 0.0f;
      gameWon = false;
      // Timer for next entity to be spawned
        SpawnNewEntityTimer = 0.0f;
        // Max timer for next entity to be spawned
        MaxSpawnEntityTimer = 1.f;
       pattern1Start = true;
        _noOfSpike = 0;
        _noOfWalls = 0;
        _noOfWallsSmall = 0;
        final String TAG = null;
    }


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

        if(timer == 63)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 70)
        {
            SpikeEntity.Create();
        }
        if(timer == 77)
        {
            SpikeEntity.Create();
        }
        if(timer == 86)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 98)
        {
            TwoBlockWall.Create();
        }
        if(timer == 157)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 170)
        {
            TwoBlockWall.Create();
        }
        if(timer == 182)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 195)
        {
            TwoBlockWall.Create();
        }
        if(timer == 208)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 223)
        {
            SpikeEntity.Create();
        }

//        if(timer == 243)
//        {
//            SmurfEntityTest.Create();
//        }
        if(timer == 273)
        {
            SpikeEntity.Create();
        }

        if(timer == 320)
        {
            SmurfEntityTest.Create();
        }

        if(timer == 333)
        {
            TwoBlockWall.Create();
        }
        if(timer == 340)
        {
            SpikeEntity.Create();
        }
        if(timer == 350)
        {
            TwoBlockWall.Create();
        }

        if(timer == 367)
        {
            TwoBlockWall.Create();
        }

        if(timer == 400)
        {
            SpikeEntity.Create();
        }

        if(timer == 440)
        {
            SpikeEntity.Create();
        }

        if(timer == 480)
        {
            SpikeEntity.Create();
        }

        if(timer == 530)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 570)
        {
            SmurfEntityTest.Create();
        }


        if(timer == 660)
        {
            InvertedOneBlockWall.Create();
        }

        if(timer == 720)
        {
            InvertedOneBlockWall.Create();
        }
        if(timer == 727)
        {
            InvertedSpikeEntity.Create();
        }
        if(timer == 734)
        {
            InvertedSpikeEntity.Create();
        }
        if(timer == 745)
        {
            InvertedOneBlockWall.Create();
        }
        if(timer == 767)
        {
            InvertedTwoBlockWall.Create();
        }
        if(timer == 806) //157 - 98
        {
            InvertedOneBlockWall.Create();
        }
        if(timer == 819) // 170 - 157
        {
            InvertedTwoBlockWall.Create();
        }
        if(timer == 831) // 182 - 170
        {
            InvertedOneBlockWall.Create();
        }
        if(timer == 844) // 195 - 182
        {
            InvertedTwoBlockWall.Create();
        }
        if(timer == 857) //208 - 195
        {
            InvertedOneBlockWall.Create();
        }
        if(timer == 873) //223 - 208
        {
            InvertedSpikeEntity.Create();
        }
        if(timer == 913)
        {
            InvertedTwoBlockWall.Create();
        }

        if(timer == 963)
        {
            TwoBlockWall.Create();
        }

        if(timer == 1003)
        {
            InvertedTwoBlockWall.Create();
        }

        if(timer == 1013)
        {
            SmurfEntityTest.Create();
        }

        if(timer == 1183)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 1189)
        {
            SpikeEntity.Create();
        }
        if(timer == 1213)
        {
            SmurfEntityTest.Create();
        }
        if(timer == 1219)
        {
            SpikeEntity.Create();
        }


        if(timer < 300)
        {
            if(SpawnNewEntityTimer > MaxSpawnEntityTimer)
            {
                InvertedSpikeEntity.Create();
                SpawnNewEntityTimer = 0;
            }
        }

        if(timer > 600 && timer <  890)
        {
            if(SpawnNewEntityTimer > MaxSpawnEntityTimer)
            {
                SpikeEntity.Create();
                SpawnNewEntityTimer = 0.95f;
            }
        }

        if(timer > 1080 && timer <  1155)
        {
            if(SpawnNewEntityTimer > MaxSpawnEntityTimer)
            {
                SpikeEntity.Create();
                SpawnNewEntityTimer = 0.95f;
            }
        }
        if(timer > 1170 && timer <  1275)
        {
            if(SpawnNewEntityTimer > MaxSpawnEntityTimer)
            {
                InvertedSpikeEntity.Create();
                SpawnNewEntityTimer = 0.95f;
            }
        }

        if(timer == 1328)
        {
            GameSystem.Instance.SetIsWon(true);

        }

        // Percentage = 1350 / 100

        if(timer == 1500)
        {
            StateManager.Instance.ChangeState("Levelselect");

        }
        //TODO: To create until timer reaches 2220
        //System.out.println(timer);
    }


    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {
        wanGoBack = false;
        GameSystem.Instance.SetIsPaused(false);
        RenderBackground.Create();
        RenderTextEntity.Create();
        Floor.Create();
        // Example to include another Renderview for Pause Button
        //Player = SmurfEntity.Create();// For week 7
        //SmurfEntity.Create(); // For week 7
        PauseButtonEntity.Create();
        PlayerEntity.Create();
        SwapGravButtonEntity.Create();
        BackToMainMenuButtonEntity.Create();
       // TwoBlockWall.CreateNew(1,3);
        //InvertedOneBlockWall.Create();

    }

    @Override
    public void OnExit() {
        Reset();
        EntityManager.Instance.Clean();
        GamePage.Instance.finish();
        GameSystem.Instance.SetIsWon(false);

    }

    @Override
    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    @Override
    public void Update(float _dt) {
        if(wanGoBack)
        {
            StateManager.Instance.ChangeState("Levelselect");
        }
        //timer += _dt;

        EntityManager.Instance.Update(_dt);
        if (GameSystem.Instance.GetIsPaused())
            return;

        WallTimer += _dt;
        SpawnNewEntityTimer+= _dt;
        SpikeTimer+= _dt;
        SpeedUpTimer += _dt;
        SlowDownTimer += _dt;

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

    @Override
    public void SetEnd() {

    }
}



