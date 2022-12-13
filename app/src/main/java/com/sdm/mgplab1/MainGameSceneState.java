package com.sdm.mgplab1;

import android.graphics.Canvas;
import android.text.method.Touch;
import android.util.Log;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class MainGameSceneState implements StateBase {
    private float timer = 0.0f;
    protected static final String TAG = null;
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
        SmurfEntity.Create(); // For week 7
        SmurfEntityTest.Create();
        SpikeEntity.Create();
        PauseButtonEntity.Create();
        PlayerEntity.Create();

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

        EntityManager.Instance.Update(_dt);


//        if (TouchManager.Instance.IsDown()) {
//
//            //Example of touch on screen in the main game to trigger back to Main menu
//            StateManager.Instance.ChangeState("Mainmenu");
//        }
    }
}



