package com.sdm.mgplab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.google.android.material.slider.Slider;

public class Option extends Activity implements View.OnClickListener , StateBase{

    //define buttons
    private Button btn_back;
    private Slider slider_volume;
    private ImageButton imgbtn_mute;
    public static boolean bMuted;
    public static float fSliderVal;
    protected static final String TAG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.option);//Set content view based on the xml in layout folder

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this); //Set Listener to this button --> Back Button

        imgbtn_mute = (ImageButton)findViewById(R.id.MuteImageBtn) ;
        imgbtn_mute.setOnClickListener(this);

        slider_volume = (Slider) findViewById(R.id.VolSlider);


        slider_volume.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                AudioManager.Instance.SetMasterVolume(slider.getValue());
                if(slider.getValue() == 0.0f)
                {
                    bMuted = true;
                }
                else
                {
                    bMuted = false;
                }
            }
        });

        if(bMuted)
            imgbtn_mute.setBackgroundResource(R.drawable.mute);
        else
            imgbtn_mute.setBackgroundResource(R.drawable.unmute);

        slider_volume.setValue(fSliderVal);
        StateManager.Instance.AddState(new Option());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View view) {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        if(view == imgbtn_mute)
        {
            bMuted = !bMuted;
            Log.v(TAG,"mute status" + bMuted);

            if(bMuted)
            {
                imgbtn_mute.setBackgroundResource(R.drawable.mute);
                AudioManager.Instance.SetMasterVolume(0.0f);
                slider_volume.setValue(0.0f);
            }else
            {
                imgbtn_mute.setBackgroundResource(R.drawable.unmute);
                AudioManager.Instance.SetMasterVolume(1.0f);
                slider_volume.setValue(1.0f);


            }
        }


        if (view == btn_back)
        {
            if(GameSystem.Instance.CheckSharedPref())
            {
                //if(GameSystem.Instance.CheckSavedPrefs("MasterVolumeSaved"))
                //{
                    GameSystem.Instance.SaveEditBegin();
                    GameSystem.Instance.SetFloatInSave("MasterVolumeSaved", AudioManager.Instance.GetMasterVolume());//set high score in "HighScore"
                    GameSystem.Instance.SaveEditEnd();
                //}
            }
            Intent intent = new Intent();

            Option.this.finish();
            intent.setClass(this, Mainmenu.class);
            startActivity(intent);

        }


    }

    @Override
    public void Render(Canvas _canvas) {

    }
    @Override
    public void OnEnter(SurfaceView _view) {
        slider_volume.setValue(AudioManager.Instance.GetMasterVolume());
        if(bMuted)
        {
            imgbtn_mute.setBackgroundResource(R.drawable.mute);
            slider_volume.setValue(0f);

        }else
        {
            imgbtn_mute.setBackgroundResource(R.drawable.unmute);
        }



    }
    @Override
    public void OnExit() {

    }
    public void Update(float _dt) {

        if(slider_volume.getValue() == 0)
        {
            bMuted = true;
        }
        else
        {
            bMuted = false;
        }


        if(bMuted)
        {
            imgbtn_mute.setBackgroundResource(R.drawable.mute);

        }else
        {
            imgbtn_mute.setBackgroundResource(R.drawable.unmute);
        }


    }

    @Override
    public void SetEnd() {

    }

    @Override
    public void SetWinScreen() {

    }

    @Override
    public String GetName() { return "Option";}

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void SetSlider(float _val){
        slider_volume.setValue(_val);
    }
}
