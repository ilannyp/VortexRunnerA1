package com.sdm.mgplab1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class RenderTextEntity implements EntityBase {

    // Paint object
    Paint paint = new Paint();
    private int red = 0, green = 0, blue = 0;

    //Our own font type to print text on screen
    Typeface myfont;

    private boolean isDone = false;
    private int xPos = 0, yPos = 0;

    private boolean isInit = false;

    public boolean IsDone() {
        return isDone;
    }

    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    public void Init(SurfaceView _view) {

    }

    public void Update(float _dt) {

    }

    public void Render(Canvas _canvas) {

    }

    public boolean IsInit(){
        return true; // Not loading any images hence not the same as Smurf entity
    }

    public void SetRenderLayer(int _newLayer){
        return;
    }

    public int GetRenderLayer(){
        return LayerConstants.RENDERTEXT_LAYER;
    }

    public ENTITY_TYPE GetEntityType(){

        return ENTITY_TYPE.ENT_TEXT;
    }

    public static RenderTextEntity Create(){
        RenderTextEntity result = new RenderTextEntity();
        EntityManager.Instance.AddEntity(result,ENTITY_TYPE.ENT_TEXT);
        return result;
    }
}
