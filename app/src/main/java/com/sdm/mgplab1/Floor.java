package com.sdm.mgplab1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class Floor implements EntityBase , Collidable{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos = 0, yPos = 0;

    int ScreenWidth, ScreenHeight;
    private Bitmap scaledbmp = null;  // will be a scaled version of the bmp based on ScreenWidth and height



    public boolean IsDone() {
        return isDone;
    }


    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }


    public void Init(SurfaceView _view) {   // indicate what image to use
        //load the image
        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.floorbg);

        // Get Screensize
        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        scaledbmp = Bitmap.createScaledBitmap(bmp,ScreenWidth,ScreenHeight,true);
    }


    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;
        xPos -=_dt * 600; //500 is just a variable. Can be edited. To deal with speed, how fast the screen scrolls.
        if (xPos < - ScreenWidth){
            xPos = 0;
        }

    }


    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(scaledbmp,xPos,yPos,null);
        _canvas.drawBitmap(scaledbmp,xPos+ScreenWidth,yPos,null);
    }


    public boolean IsInit() {
        return bmp != null;
    }


    public int GetRenderLayer() {
        return LayerConstants.FLOORCEILING_LAYER;
    }

    public void SetRenderLayer(int _newLayer) {
        return;
    }

    public ENTITY_TYPE GetEntityType() {
        return ENTITY_TYPE.ENT_DEFAULT;
    }

    public static Floor Create() {
        Floor result = new Floor();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_DEFAULT);
        return result;
    }

    @Override
    public String GetType() {
        return "Floor";
    }

    @Override
    public void SetStatus(boolean bStatus) {

    }

    @Override
    public boolean GetStatus() {
        return false;
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
        return scaledbmp.getHeight();
    }

    @Override
    public int GetWidth() {
        return scaledbmp.getWidth();
    }
}