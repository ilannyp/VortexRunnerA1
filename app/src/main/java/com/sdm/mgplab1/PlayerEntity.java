package com.sdm.mgplab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;

import kotlin.jvm.internal.PropertyReference0Impl;

public class PlayerEntity implements EntityBase , Collidable{

    private Bitmap bmp = null;
    private Bitmap sbmp = null;
    private Sprite spritesheet=null;//If want to animate our player
    private int ScreenWidth, ScreenHeight;
    private boolean isDone = false;
    private boolean isInit = false;
    protected static final String TAG = null;





    private double xPos = 0, yPos = 0;
    private double velx, vely;
    public double Gravity = 12.8;
    public double JumpVel = 100;

    public boolean IsDone() {
        return isDone;
    }

    //@Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    //@Override
    public void Init(SurfaceView _view){
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.playerbox);
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;
        sbmp = Bitmap.createScaledBitmap(bmp, (int)(ScreenWidth)/12,
                (int) (ScreenHeight)/7,true);
        spritesheet = new Sprite(bmp, 1,1,16);    //If want to animated our player




        xPos = 296;
        yPos = 296;

        isInit = true;

    }
    public void Update(float _dt) {
        xPos+=velx;
        if(vely < Gravity)
        {
            vely += 15.8;
        }

        if(yPos+vely < 633)
        {
            yPos+=vely;
        }
        else
        {
            vely = 0;
        }




       if(TouchManager.Instance.HasTouch()){
            float imgRad = spritesheet.GetWidth() * .5f;
             if (Collision.SphereToSphere((float) TouchManager.Instance.GetPosX(), (float) TouchManager.Instance.GetPosY(),0.0f, (float) xPos, (float) yPos,imgRad)){
                //Collided
            //hasTouched = true;
//            //Log.v(TAG,"Touch SmurfEntity");
             //xPos = TouchManager.Instance.GetPosX();
             //yPos = TouchManager.Instance.GetPosY();
                 if (vely == 0)
                 {
                     vely = -JumpVel;
                 }
//            if(yPos == floor)
//            {
//                jump = true;
//
//            }
            }
        }



    }
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(sbmp, (int) (xPos - sbmp.getWidth()*0.5f), (int) (yPos - sbmp.getHeight()*0.5f), null);
        //spritesheet.Render(_canvas, (int)xPos,(int)yPos); //If want to animate our player
    }
    public boolean IsInit() {
        return bmp != null;
    }

    public int GetRenderLayer() {
        return LayerConstants.RENDERPLAYER_LAYER;
    }

    public void SetRenderLayer(int _newLayer) {
        return;
    }
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_PLAYER;
    }

    public static PlayerEntity Create(){
        PlayerEntity result = new PlayerEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_PLAYER);
        return result;
    }

    @Override
    public String GetType() {
        return "PlayerEntity";
    }

    @Override
    public float GetPosX() {
        return (float)xPos;
    }

    @Override
    public float GetPosY() {
        return (float)yPos;
    }

    @Override
    public float GetRadius() {

        return 55.0f;
    }

    @Override
    public void OnHit(Collidable _other) {


    }

    @Override
    public void SetPosX(float _newX) {
        xPos = (int) _newX;
    }

    @Override
    public void SetPosY(float _newY) {
        yPos = (int) _newY;
    }




}
