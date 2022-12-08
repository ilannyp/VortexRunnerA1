package com.sdm.mgplab1;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


public class SmurfEntity implements EntityBase{
//public class SmurfEntity implements EntityBase, Collidable {
// Comment for now and use if code from Slide no 7 is type in

    private Bitmap bmp = null;
    private Sprite spritesheet=null;//using Sprite Class
    private boolean isDone = false;
    private boolean isInit = false;
    private int xPos = 0, yPos = 0;
    private boolean hasTouched = false;

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
        xPos = _view.getWidth() / 2;
        yPos = _view.getHeight() / 2;

        isInit = true;

    }
    public void Update(float _dt) {
        spritesheet.Update(_dt);

        //addon codes provide on slides from week 6 -- Slide no.7
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

    public static SmurfEntity Create(){
        SmurfEntity result = new SmurfEntity();
        EntityManager.Instance.AddEntity(result, ENTITY_TYPE.ENT_SMURF);
        return result;
    }
}
