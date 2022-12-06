package com.sdm.mgplab1;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;


public class SmurfEntity implements EntityBase{
//public class SmurfEntity implements EntityBase, Collidable {
// Comment for now and use if code from Slide no 7 is type in

    private Bitmap bmp = null;
    private Sprite spritesheet = null; // using Sprite class

    private boolean isDone = false;
    private float xPos = 0, yPos = 0;

    private boolean isInit = false;

    public boolean IsDone() {
        return isDone;
    }

    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(),
                R.drawable.smurf_sprite);
        spritesheet = new Sprite(bmp, 4,4, 16);

        //spritesheet = new Sprite(BitmapFactory.decodeResource(_view.getResources(),
        //                R.drawable.smurf_sprite), 4,4, 16);

        isInit = true;
    }
    public void Update(float _dt) {

        spritesheet.Update(_dt);

        // addon codes provide on slides from Week 6 -- Slide no.7
    }

    public void Render(Canvas _canvas) {
        spritesheet.Render(_canvas, xPos+100, yPos+100);
    }

    public boolean IsInit(){

        return bmp != null;
    }

    public void SetRenderLayer(int _newLayer){ return;}

    public int GetRenderLayer(){return LayerConstants.RENDERSMURF_LAYER;}

    public ENTITY_TYPE GetEntityType(){

        return ENTITY_TYPE.ENT_SMURF;
    }

    public static SmurfEntity Create(){
        SmurfEntity result = new SmurfEntity();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_SMURF);
        return result;
    }
}
