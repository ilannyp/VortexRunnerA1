package com.sdm.mgplab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

public class SmurfEntityTest implements EntityBase , Collidable{

    private Bitmap bmp = null;
    private Sprite spritesheet=null;//using Sprite Class
    private boolean isDone = false;
    private boolean isInit = false;
    private int xPos = 0, yPos = 0;
    private boolean hasTouched = false;
    protected static final String TAG = null;
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
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.walltile);

        spritesheet = new Sprite(bmp, 1,1,1);
        xPos = _view.getWidth() ;
        yPos = _view.getHeight() * 8/9;

        isInit = true;

    }
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;
        spritesheet.Update(_dt);
        AddForceTowardsLeft(40);
        //Log.v(TAG,"x: " + xPos + " y " + yPos);

//        //addon codes provide on slides from week 6 -- Slide no.7
        if(TouchManager.Instance.HasTouch()){
            //Check collision here
            float imgRad = spritesheet.GetWidth() * .5f;
//            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRad)){
//                //Collided
//                hasTouched = true;
//
//                xPos = TouchManager.Instance.GetPosX();
//                yPos = TouchManager.Instance.GetPosY();
//
//            }
        }
        if(xPos <  -2)
        {
            //_bStatus = false;
            isDone = true;
        }
    }
    public void Render(Canvas _canvas) {
        if(_bStatus)
        {
            Bitmap bmp = ResourceManager.Instance.GetBitmap(R.drawable.walltile);
            spritesheet = new Sprite(bmp, 1,1,1);
            spritesheet.Render(_canvas, xPos,yPos);
            //System.out.println(spritesheet.GetHeight());
        }
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

    public static SmurfEntityTest Create(){
        SmurfEntityTest result = new SmurfEntityTest();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }

    @Override
    public String GetType() {
        return "SmurfEntityTest";
    }

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
        //Log.v(TAG,"SmurfEnityTest colliding with"+ _other);
        if(_other.GetType()== "PlayerEntity")
        {
            _other.SetPosX(_other.GetPosX() - 40);
        }
    }

    @Override
    public void OnBoxHit(Collidable _other) {
        //Log.v(TAG, "OnBoxHit ");  //not workin well
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
        return spritesheet.GetHeight();
    }

    @Override
    public int GetWidth() {
        return spritesheet.GetWidth();
    }

    @Override
    public void SetStatus(boolean bStatus) {_bStatus = bStatus;}

    @Override
    public boolean GetStatus(){return _bStatus;}

    public float AddForceTowardsLeft(int amount){
        xPos -= amount;
        return amount;
    }
}
