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
        xPos = _view.getWidth() / 2 + 50;
        yPos = _view.getHeight() / 2 + 20;

        isInit = true;

    }
    public void Update(float _dt) {

        spritesheet.Update(_dt);
        AddForceTowardsLeft(5);
        //Log.v(TAG,"x: " + xPos + " y " + yPos);

//        //addon codes provide on slides from week 6 -- Slide no.7
        if(TouchManager.Instance.HasTouch()){
            //Check collision here
            float imgRad = spritesheet.GetWidth() * .5f;
            if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(),TouchManager.Instance.GetPosY(),0.0f,xPos,yPos,imgRad)){
                //Collided
                hasTouched = true;

                xPos = TouchManager.Instance.GetPosX();
                yPos = TouchManager.Instance.GetPosY();

            }
        }
    }
    public void Render(Canvas _canvas) {
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
        _other.SetPosX(_other.GetPosX() - 5);

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
    public void SetStatus(boolean bStatus) {_bStatus = bStatus;}

    @Override
    public boolean GetStatus(){return _bStatus;}

    public float AddForceTowardsLeft(int amount){
        xPos -= amount;
        return amount;
    }
}
