package com.sdm.mgplab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

public class SmurfEntity implements EntityBase , Collidable{

    private Bitmap bmp = null;
    private Sprite spritesheet=null;//using Sprite Class
    private boolean isDone = false;
    private boolean isInit = false;
    private int xPos = 0, yPos = 0;
    private boolean hasTouched = false;
    protected static final String TAG = null;
    private int store_xPos = 0, store_yPos = 0;
    public static int gravity = 9;
    public static int JumpForce = 130;
    public static int floor = 569;
    private boolean jump = false;
   private boolean _bStatus = true;


    public boolean IsDone() {
        return isDone;
    }

    //@Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    //@Override
    public void Init(SurfaceView _view){
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.smurf_sprite);

        spritesheet = new Sprite(bmp, 4,4,16);
        xPos = 296;
        yPos = floor;

        isInit = true;

    }
    public void Update(float _dt) {
        spritesheet.Update(_dt);
        StorePositionForRollback();
        ApplyGravity();
        CheckGround();
        CheckJump(_dt);

//        //addon codes provide on slides from week 6 -- Slide no.7
        if(TouchManager.Instance.HasTouch()){
            //Check collision here
            float imgRad = spritesheet.GetWidth() * .5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRad)){
                //Collided
                hasTouched = true;
                //Log.v(TAG,"Touch SmurfEntity");
                //xPos = TouchManager.Instance.GetPosX();
                //yPos = TouchManager.Instance.GetPosY();
                if(yPos == floor)
                {
                    jump = true;

                }
            }

        }


    }
    public void Render(Canvas _canvas) {
        if(_bStatus)
            spritesheet.Render(_canvas, xPos,yPos);
    }
    public boolean IsInit() {
        return bmp != null;
    }

    public int GetRenderLayer() {
        return LayerConstants.RENDERSMURF_LAYER;
    }

    public void SetRenderLayer(int _newLayer) {
        return;
    }
    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_SMURF;
    }

    public static SmurfEntity Create(){
        SmurfEntity result = new SmurfEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }

    @Override
    public String GetType() {
        return "SmurfEntity";
    }

    @Override
    public void SetStatus(boolean bStatus) {_bStatus = bStatus;}

    @Override
    public boolean GetStatus(){return _bStatus;}

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {

        return 55.0f;
    }

    @Override
    public void OnHit(Collidable _other) {
        //RollbackPosition();

    }

    @Override
    public void SetPosX(float _newX) {
        xPos = (int) _newX;
    }

    @Override
    public void SetPosY(float _newY) {
        yPos = (int) _newY;
    }

    public void StorePositionForRollback(){
        store_xPos = xPos;
        store_yPos = yPos;
    }

    public void RollbackPosition(){
        xPos = store_xPos;
        yPos = store_yPos;
    }
    public void RollbackPositionX(){
        xPos = store_xPos;

    }
    public void RollbackPositionY(){
        yPos = store_yPos;
    }

    public void ApplyGravity(){
        yPos += gravity;
        //Log.v(TAG,"yPos" + yPos);
    }
    public void CheckGround(){
        if(yPos >= floor)
        {
            yPos = floor;
            //vely = 0.0f;
        }
    }
    public void CheckJump(float _dt){
        //if(yPos == floor)
        //{
            if (jump){
                yPos -= (JumpForce * _dt) + 20;
                if(yPos <= 430)
                {
                    Log.v(TAG,"jump" + yPos);
                    jump = false;
                }
           // }
        }
    }

}
