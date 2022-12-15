package com.sdm.mgplab1;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

public class SwapGravButtonEntity implements EntityBase {

    private Bitmap bmp, bmp1 = null;
    private Bitmap sbmp, sbmp1 = null;

    int ScreenWidth, ScreenHeight;

    private boolean Paused = false;
    private boolean Swiped = false;
    private float buttonDelay = 0;

    private boolean isDone = false;
    private int xPos, yPos;

    private boolean isInit = false;

    protected static final String TAG = null;

    public boolean IsDone() {
        return isDone;
    }

    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.invertbutton);
        bmp1 = BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.invertbutton1);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        // Scale is hardcode number -- Adjust your own 00
        sbmp = Bitmap.createScaledBitmap(bmp, (int)(ScreenWidth)/12,
                (int) (ScreenHeight)/7,true);

        sbmp1 = Bitmap.createScaledBitmap(bmp1, (int)(ScreenWidth)/12,
                (int) (ScreenHeight)/7,true);

        xPos = ScreenWidth - 150;
        yPos = ScreenHeight * 8/9;
        isInit = true;
    }

    public void Update(float _dt) {
        //All entity needs to have this
        buttonDelay += _dt;
        if (TouchManager.Instance.HasTouch()){
            if (TouchManager.Instance.IsDown() && !Paused) {
                float imgRadius = sbmp.getHeight() * 1.5f;

                if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                        TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius)
                        && buttonDelay >= 0.25) {

                    if(PlayerEntity.GetVelY() == 0)
                    {
                        Swiped = !Swiped;
                        PlayerEntity.swapGrav = !PlayerEntity.swapGrav;

                    }

                }

            }

        }
        else
            Paused = false;
    }

    public void Render(Canvas _canvas) {
        if(!Swiped)
        {
            _canvas.drawBitmap(sbmp, xPos - sbmp.getWidth()*0.5f, yPos - sbmp.getHeight()*0.5f, null);
        }
        else
        {
            _canvas.drawBitmap(sbmp, xPos - sbmp1.getWidth()*0.5f, yPos - sbmp1.getHeight()*0.5f, null);
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

    public static SwapGravButtonEntity Create(){
        SwapGravButtonEntity result = new SwapGravButtonEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PAUSE);
        return result;
    }
}

