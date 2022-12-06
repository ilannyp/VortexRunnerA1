package com.sdm.mgplab1;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.slider.Slider;

public class Option extends Activity implements View.OnClickListener , StateBase{

    //define buttons
    private Button btn_back;
    private Slider slider_volume;

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

        StateManager.Instance.AddState(new Option());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View view) {
        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (view == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
        }
        startActivity(intent);
    }

    @Override
    public void Render(Canvas _canvas) {

    }
    @Override
    public void OnEnter(SurfaceView _view) {

    }
    @Override
    public void OnExit() {

    }
    @Override
    public void Update(float _dt) {

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


}
