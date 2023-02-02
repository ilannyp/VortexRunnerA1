package com.sdm.mgplab1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.text.method.Touch;
import android.util.Log;
import android.view.SurfaceView;

import androidx.core.util.SparseIntArrayKt;

import javax.xml.transform.sax.SAXTransformerFactory;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    private boolean gameWon = false;
    public static boolean wanGoBack;
    // Timer for next entity to be spawned
    private float SpawnNewEntityTimer = 0.0f;
    // Max timer for next entity to be spawned
    private float MaxSpawnEntityTimer = 1.f;
    private int _screenwidth = 0;
    private int _screenheight = 0;


    protected static final String TAG = null;


    public void Reset() {
        wanGoBack = false;
      timer = 0.0f;
      gameWon = false;
      // Timer for next entity to be spawned
        SpawnNewEntityTimer = 0.0f;
        // Max timer for next entity to be spawned
        MaxSpawnEntityTimer = 1.f;

        final String TAG = null;
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
            CoinEntity.CreateNew(_screenwidth,_screenheight * 4/9);
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -210);
        }
        if(timer == 208)
        {
            CoinEntity.CreateNew(_screenwidth,_screenheight * 6/9);
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
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -210);
        }
        if(timer == 340)
        {
            CoinEntity.CreateNew(_screenwidth,_screenheight * 3/9);
            SpikeEntity.Create();
        }
        if(timer == 350)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -210);
        }

        if(timer == 367)
        {
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -210);
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
        if(timer == 530)
            CoinEntity.CreateNew(_screenwidth,_screenheight * 6/9);
        if(timer == 570)
        {
            CoinEntity.CreateNew(_screenwidth,_screenheight * 6/9);
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
        }


        if(timer == 660)
        {
            //        xPos = _view.getWidth() ;
            //        yPos = _view.getHeight() * 1/9;
            CoinEntity.CreateNew(_screenwidth,_screenheight * 3/9);
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
            CoinEntity.CreateNew(_screenwidth,_screenheight * 4/9);
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
        }
        if(timer == 873) //223 - 208
        {
            InvertedSpikeEntity.Create();
        }
        if(timer == 913)
        {
            CoinEntity.CreateNew(_screenwidth,_screenheight * 6/9);
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 1/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +180);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 1/9 +360);
        }

        if(timer == 963)
        {
            CoinEntity.CreateNew(_screenwidth,_screenheight * 4/9);
            SmurfEntityTest.CreateNew(_screenwidth,_screenheight * 8/9);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -210);
            SmurfEntityTest.CreateNew(_screenwidth, _screenheight * 8/9 -360);
        }

        if(timer == 1003)
        {
            CoinEntity.CreateNew(_screenwidth,_screenheight * 6/9);
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
        if (GameSystem.Instance.GetIsPaused() || GameSystem.Instance.GetIsDead())
            return;

        SpawnNewEntityTimer+= _dt;

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



