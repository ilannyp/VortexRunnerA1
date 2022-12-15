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
    protected static boolean swapGrav = false;
    public boolean _bStatus = true;



    private double xPos = 0, yPos = 0;
    private static double velx;
    private static double vely;
    public double Gravity = 11.8;
    public static double JumpVel = 55;  // original = 45, changing to 55 to test
    public static boolean Falling = false;
    public boolean Jumpable = false;

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

        // TODO: Uncomment Irfan's JumpVel value
        //JumpVel = ScreenHeight * 0.075  ;
        //JumpVel = ScreenHeight * 0.065;

        xPos = ScreenWidth / 6;
        //yPos = 296;
        yPos = ScreenHeight * 8/9;
        isInit = true;

    }
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;
        xPos+=velx;
        yPos+=vely;

        CheckJump();

        if(!swapGrav)
        {
            if(yPos+vely < ScreenHeight - (ScreenHeight * 0.1))
            {
                yPos+=vely;
            }
            else
            {
                Falling  = false;
                vely = 0;
            }
        }
        else
        {
            if(yPos+vely > ScreenHeight - ScreenHeight * 0.85)
            {
                yPos+= vely;
            }
            else
            {
                Falling  = false;
                vely = 0;
            }
        }

        //gravity
        //if swapGrav true -> vel -
        //if swapGrav false -> vel +
        //Log.v(TAG,"isFalling: "+Falling);
        //Log.v(TAG,"isGravSwap: "+swapGrav);
        //Log.v(TAG,"vely: "+vely);

        if(!swapGrav)
        {
            if(vely < Gravity && Falling)
            {
                vely += 9.8;
            }else if(!Falling && vely > 0)
            {
                vely = 0;
            }
        }
        else
        {
            if(vely > -Gravity && Falling)
            {
                vely -= 9.8;
            }else if(!Falling && vely > 0)
            {
                vely = 0;
            }
        }

        //if swapGrav true -> (yPos+vely > 50(for now))
        //if swapGrav false ->(yPos+vely < 633)

        if(GameSystem.Instance.GetIsWon())
        {
            xPos += 40;
        }

    }

    private void CheckJump() {
        if(TouchManager.Instance.HasTouch()){
            float imgRad = spritesheet.GetWidth() * 2.5f;
            if (Collision.SphereToSphere((float) TouchManager.Instance.GetPosX(), (float) TouchManager.Instance.GetPosY(),0.0f, (float) xPos, (float) yPos,imgRad)){
                if (vely == 0)
                {
                    Log.v(TAG,"can jump");
                    if(!swapGrav)
                    {
                        vely = -JumpVel;
                    }
                    else
                    {
                        vely = JumpVel;

                    }
                    Log.v(TAG, "vely" + (vely));
                    Log.v(TAG, "posY" + (yPos));

                }
            }
        }

    }


    public void Render(Canvas _canvas) {
        //if(_bStatus)
        {
            _canvas.drawBitmap(sbmp, (int) (xPos - sbmp.getWidth()*0.5f), (int) (yPos - sbmp.getHeight()*0.5f), null);
            //spritesheet.Render(_canvas, (int)xPos,(int)yPos); //If want to animate our player
        }
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
    public void SetStatus(boolean bStatus) {
            _bStatus = bStatus;
    }

    @Override
    public boolean GetStatus() {
        return _bStatus;
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
    public void OnBoxHit(Collidable _other) {
        Falling  = false;
        //Collision Detection
        if(vely > 0)
        {
            vely = 0;
            yPos = (_other.GetPosY()- (this.GetHeight() * 1.1));
        }
        if(vely < 0 ){
            Falling = true;
            yPos -= (velx+1);
            vely = -1*vely;
        }







    }

    @Override
    public void SetPosX(float _newX) {
        xPos = (int) _newX;
    }

    @Override
    public void SetPosY(float _newY) {
        yPos = (int) _newY;
    }

    @Override
    public int GetHeight() {
        return sbmp.getHeight();
    }

    @Override
    public int GetWidth() {
        return sbmp.getWidth();
    }

    public static double GetVelY(){
        return vely;
    }
    public static void SetVelY(double _vely){
        vely = _vely;
    }


}
