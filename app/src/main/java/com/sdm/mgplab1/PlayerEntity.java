package com.sdm.mgplab1;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceView;


public class PlayerEntity implements EntityBase , Collidable{

    private Bitmap bmp = null;
    private Bitmap sbmp = null;
    private Bitmap sbmp2 = null;
    private Sprite spritesheet=null;//If want to animate our player
    private int ScreenWidth, ScreenHeight;
    private boolean isDone = false;
    private boolean isInit = false;
    protected static final String TAG = null;
    protected static boolean swapGrav = false;
    public boolean _bStatus = true;  // Player Status
    private int deathTimer = 0;     // Time before switching to lose state
    private int uponDeathCounter = 0; //For one-time uses when player dies. i.e death audio

    private double xPos = 0, yPos = 0;
    private static double velx;
    private static double vely;
    public double Gravity = 11.8;
    public static double JumpVel = 55;  // original = 45, changing to 55 to test
    public static boolean Falling = false;
    private Vibrator _vibrate;


    public boolean IsDone() {
        return isDone;
    }

    //@Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    //@Override
    public void Init(SurfaceView _view){
        swapGrav = false;

        _vibrate = (Vibrator)_view.getContext().getSystemService (_view.getContext().VIBRATOR_SERVICE);

        GameSystem.Instance.SaveEditBegin();
        GameSystem.Instance.SetIntInSave("Score",0);
        GameSystem.Instance.SaveEditEnd();

        bmp = BitmapFactory.decodeResource(_view.getResources(),R.drawable.playerbox);

        DisplayMetrics metrics = _view.getResources().getDisplayMetrics();
        ScreenWidth = metrics.widthPixels;
        ScreenHeight = metrics.heightPixels;

        sbmp = Bitmap.createScaledBitmap(bmp, (int)(ScreenWidth)/12,
                (int) (ScreenHeight)/7,true);

        //If want to animated our player
        spritesheet = new Sprite(BitmapFactory.decodeResource(_view.getResources(),R.drawable.deathanim),
                3, 3, 7);

        xPos = ScreenWidth / 6;
        yPos = ScreenHeight * 8/9;
        isInit = true;
        TouchManager.Instance.setTouchState(TouchManager.TouchState.NONE);

    }
    public void Update(float _dt) {
        if (GameSystem.Instance.GetIsPaused())
            return;
        spritesheet.Update(_dt);
        xPos+=velx;
        yPos+=vely;

        //System.out.println(spritesheet.GetHeight());
        int testScore = GameSystem.Instance.GetIntFromSave("Score");
        if (!GameSystem.Instance.GetIsDead())
            ++testScore;
        GameSystem.Instance.SaveEditBegin();
        GameSystem.Instance.SetIntInSave("Score", testScore);
        GameSystem.Instance.SaveEditEnd();

        String showScore = String.format("%d",GameSystem.Instance.GetIntFromSave("Score"));

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
        if(xPos < 0)
        {

            int score = GameSystem.Instance.GetIntFromSave("Score");
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("HighScore", score);//set high score in "HighScore"
            GameSystem.Instance.SaveEditEnd();

            Log.v(TAG, "score: " + score);
            _bStatus = false;

        }
        if(!_bStatus)
        {
            if(uponDeathCounter == 0)
            {
                AudioManager.Instance.PlayAudio(R.raw.soundfeelsbadman, 0.9f);
                if(Build.VERSION.SDK_INT >= 26)
                    _vibrate.vibrate(VibrationEffect.createOneShot(150,10));
                else
                {
                    long pattern[] = {0,50,0};
                    _vibrate.vibrate(pattern, 1);
                }
            }
            uponDeathCounter = 1;
            GameSystem.Instance.SetIsDead(true);
            deathTimer += 1;
            //System.out.println(deathTimer);
            if(deathTimer > 60)
            {
                GamePage.Instance.SetEnd();
                AudioManager.Instance.StopAudio(R.raw.musicbackground);
            }
        }

        if(xPos > ScreenWidth)
        {
            GamePage.Instance.SetWinScreen();
        }


    }

    private void CheckJump() {

        if(TouchManager.Instance.HasTouch()){
            float imgRad = spritesheet.GetWidth() * 2.5f;
            if (Collision.SphereToSphere((float) TouchManager.Instance.GetPosX(), (float) TouchManager.Instance.GetPosY(),0.0f, (float) xPos, (float) yPos,imgRad) && _bStatus){
                if (vely == 0)
                {
                    Log.v(TAG,"can jump");
                    AudioManager.Instance.PlayAudio(R.raw.jump,1.0f);
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
        if(_bStatus)
        {
            //drawHitbox(_canvas);
            _canvas.drawBitmap(sbmp, (int) (xPos - sbmp.getWidth()*0.5f), (int) (yPos - sbmp.getHeight()*0.5f), null);
             //If want to animate our player
        }
        else
        {
            spritesheet.Render(_canvas, (int) (xPos - sbmp.getWidth()*0.5f), (int) (yPos - sbmp.getHeight()*0.5f));

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

        return 65.0f;
    }

    @Override
    public void OnHit(Collidable _other) {


    }

    @Override
    public void OnBoxHit(Collidable _other) {
        if(_other.GetType() == "Coin")
        {
            return;
        }
        Falling  = false;
        //Collision Detection

    if(!swapGrav)
    {
      if(vely > 0)
      {
          vely = 0;
          if(_other.GetType() == "SmurfEntityTest")
          {
              yPos = (_other.GetPosY()- (this.GetHeight() * 1.1));
          }
          else if(_other.GetType() == "Wall")
          {
              yPos = (_other.GetPosY()- (this.GetHeight() * 1.6));
          }
      }
      if(vely < 0 ){
          Falling = true;
          yPos -= (velx+1);
          vely = -1*vely;
      }
    }
    else
    {
        if(vely < 0)
        {
            vely = 0;
            if(_other.GetType() == "SmurfEntityTest")
            {
                yPos = (_other.GetPosY()+ (this.GetHeight() * 1.1));
            }
            else if(_other.GetType() == "Wall")
            {
                yPos = (_other.GetPosY() + (this.GetHeight() * 1.6));
            }
        }
        if(vely < 0 ){
            Falling = true;
            yPos += (velx+1);
            vely = +1*vely;
        }
    }

    }

    @Override
    public void OnCoinHit(Collidable _other) {

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
