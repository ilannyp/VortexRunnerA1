package com.sdm.mgplab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class CoinEntity implements EntityBase , Collidable{

    private Bitmap bmp = null;
    private Sprite spritesheet=null;//using Sprite Class
    private boolean isDone = false;
    private boolean isInit = false;
    private int xPos = 0, yPos = 0;
    private boolean hasTouched = false;
    protected static final String TAG = null;
    private boolean _bStatus = true;

    public void Init(SurfaceView _view) {
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.flystar);
        spritesheet = new Sprite(bmp, 1,5,16);
        xPos = _view.getWidth();
        yPos = _view.getHeight()* 6/9;

        isInit = true;

    }

    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;
        spritesheet.Update(_dt);
        AddForceTowardsLeft(40);
        if(xPos < -2) { isDone = true;}
    }


    public float AddForceTowardsLeft(int amount){
        xPos -= amount;
        return amount;
    }


    public void Render(Canvas _canvas) {
        if(_bStatus)
        {
            //System.out.println(xPos);
            Bitmap bmp = ResourceManager.Instance.GetBitmap(R.drawable.flystar);
            spritesheet = new Sprite(bmp, 1,5,16);
            spritesheet.Render(_canvas, xPos,yPos);
        }
    }


    public boolean IsInit() {return bmp != null;}


    public String GetType() {return "Coin";}

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

    }

    @Override
    public void OnBoxHit(Collidable _other) {
        if(_other.GetType() == "PlayerEntity"){
            SetStatus(false);
        }
    }

    @Override
    public void SetPosX(float _newX) {
        xPos = (int) _newX;

    }

    @Override
    public void SetPosY(float _newY) {
        xPos = (int) _newY;
    }

    @Override
    public int GetHeight() {
        return spritesheet.GetHeight();
    }

    @Override
    public int GetWidth() {
        return spritesheet.GetWidth();
    }


    public boolean IsDone() {
        return isDone;
    }


    public void SetIsDone(boolean _isDone) {isDone = _isDone;}

    public int GetRenderLayer() {return LayerConstants.RENDERPLAYER_LAYER;}

    public void SetRenderLayer(int _newLayer) {return;}

    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_COIN;
    }

    public static CoinEntity Create(){
        CoinEntity result = new CoinEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_COIN);
        return result;
    }

}
