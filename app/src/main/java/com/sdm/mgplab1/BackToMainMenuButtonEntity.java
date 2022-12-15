package com.sdm.mgplab1;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

public class BackToMainMenuButtonEntity implements EntityBase {

    private Bitmap bmp, bmp1 = null;
    private Bitmap sbmp, sbmp1 = null;

    int ScreenWidth, ScreenHeight;

    private boolean Paused = false;
    private float buttonDelay = 0;
protected static final String TAG = null;
    private boolean isDone = false;
    private int xPos, yPos;

    private boolean isInit = false;

    public boolean IsDone() {
        return isDone;
    }

    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.pause);
        bmp1 = BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.pause1);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        // Scale is hardcode number -- Adjust your own 00
        sbmp = Bitmap.createScaledBitmap(bmp, (int)(ScreenWidth)/12,
                (int) (ScreenHeight)/7,true);

        sbmp1 = Bitmap.createScaledBitmap(bmp1, (int)(ScreenWidth)/12,
                (int) (ScreenHeight)/7,true);

        xPos = (_view.getWidth() / 2);
        yPos = 150;

        isInit = true;
    }

    public void Update(float _dt) {


        //if pause, update
        if(GameSystem.Instance.GetIsPaused())
        {

            buttonDelay += _dt;
            if (TouchManager.Instance.HasTouch()){
                if (TouchManager.Instance.IsDown() && !Paused) {
                    float imgRadius = sbmp.getHeight() * 0.5f;

                    if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                            TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius)
                            && buttonDelay >= 0.25) {
                        MainGameSceneState.wanGoBack = true;



                        //change state back to level or mainmenu idk



                    }
                }
            }
        }
    }

    public void Render(Canvas _canvas) {
        if(GameSystem.Instance.GetIsPaused())
        {
            _canvas.drawBitmap(sbmp, xPos - sbmp.getWidth()*0.5f, yPos - sbmp.getHeight()*0.5f, null);
        }
    }

    public boolean IsInit(){
        return isInit;
    }

    public void SetRenderLayer(int _newLayer){
        return;
    }

    public int GetRenderLayer(){
        return LayerConstants.RENDERPAUSE_LAYER;
    }

    public ENTITY_TYPE GetEntityType(){

        return ENTITY_TYPE.ENT_PAUSE;
    }

    public static BackToMainMenuButtonEntity Create(){
        BackToMainMenuButtonEntity result = new BackToMainMenuButtonEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }
}

