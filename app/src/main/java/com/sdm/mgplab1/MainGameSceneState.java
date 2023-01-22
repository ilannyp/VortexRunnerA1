package com.sdm.mgplab1;

import android.app.Activity;
import android.app.GameManager;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.method.Touch;
import android.util.Log;
import android.view.SurfaceView;

import androidx.core.util.SparseIntArrayKt;

import javax.xml.transform.sax.SAXTransformerFactory;

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
    private int _screenwidth = 0;
    private int _screenheight = 0;

    private boolean pattern1Start = true;
    private int _noOfSpike = 0;
    private int _noOfWalls = 0;
    private int _noOfWallsSmall = 0;
    protected static final String TAG = null;

    public void Reset()
    {
        wanGoBack = false;
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
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            //SmurfEntityTest.Create();
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
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }
        if(timer == 98)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
          //  TwoBlockWall.Create();
        }
        if(timer == 157)
        {
            //CoinEntity.Create();
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }
        if(timer == 170)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
        }
        if(timer == 182)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }
        if(timer == 195)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
        }
        if(timer == 208)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }
        if(timer == 223)
        {
            SpikeEntity.Create();
        }

//        if(timer == 243)
//        {
//            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
//        }
        if(timer == 273)
        {
            SpikeEntity.Create();
        }

        if(timer == 320)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }

        if(timer == 333)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
        }
        if(timer == 340)
        {
            SpikeEntity.Create();
        }
        if(timer == 350)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
        }

        if(timer == 367)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
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
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }
        if(timer == 570)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }


        if(timer == 660)
        {
            //        xPos = _view.getWidth() ;
            //        yPos = _view.getHeight() * 1/9;
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
        }

        if(timer == 720)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
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
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
        }
        if(timer == 757)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +180);
        }
        if(timer == 806) //157 - 98
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
        }
        if(timer == 819) // 170 - 157
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +180);
        }
        if(timer == 831) // 182 - 170
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
        }
        if(timer == 844) // 195 - 182
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +180);
        }
        if(timer == 857) //208 - 195
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
        }
        if(timer == 873) //223 - 208
        {
            InvertedSpikeEntity.Create();
        }
        if(timer == 913)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +180);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +360);
        }

        if(timer == 963)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -180);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -360);
        }

        if(timer == 1003)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +180);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 +360);
        }

        if(timer == 1013)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }

        if(timer == 1183)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }
        if(timer == 1189)
        {
            SpikeEntity.Create();
        }
        if(timer == 1213)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
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

        if(timer == 1400)
        {
            StateManager.Instance.ChangeState("WinState");
        }

    }


    @Override
    public String GetName() {
        return "MainGame";
    }

    @Override
    public void OnEnter(SurfaceView _view)
    {

        wanGoBack = false;
        _screenwidth = _view.getWidth();
        _screenheight = _view.getHeight();
        GameSystem.Instance.SetIsPaused(false);
        GameSystem.Instance.SetIsDead(false);
        RenderBackground.Create();
        RenderTextEntity.Create();
        Floor.Create();
        PauseButtonEntity.Create();
        PlayerEntity.Create();
        //CoinEntity.Create();
        SwapGravButtonEntity.Create();
        BackToMainMenuButtonEntity.Create();
        CoinEntity.CreateNew(_screenwidth,_screenheight * 6/9);

        AudioManager.Instance.PlayAudio(R.raw.musicbackground,0.1f);

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

        String scoreText = String.format("Score : %d", GameSystem.Instance.GetIntFromSave("Score"));

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(64);

        _canvas.drawText(scoreText, 10, 220, paint);
    }

    @Override
    public void Update(float _dt) {

        //if(AudioManager.Instance.IsPlaying()
        if(wanGoBack)
        {
           // StateManager.Instance.Start("Levelselect");
            StateManager.Instance.ChangeState("Levelselect");

        }
        //timer += _dt;
        GamePage.Instance.Update(_dt);
        EntityManager.Instance.Update(_dt);
        if (GameSystem.Instance.GetIsPaused() && GameSystem.Instance.GetIsDead())
            return;

        WallTimer += _dt;
        SpawnNewEntityTimer+= _dt;
        SpikeTimer+= _dt;
        SpeedUpTimer += _dt;
        SlowDownTimer += _dt;

        // ToDo: If got time change from hardcode to using patterns
        LevelHC(1);
    }

    @Override
    public void SetEnd() {

    }

    @Override
    public void SetWinScreen() {

    }
}



