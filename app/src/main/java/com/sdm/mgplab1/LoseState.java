package com.sdm.mgplab1;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.ImageButton;

public class LoseState extends Activity implements OnClickListener, StateBase {
    protected boolean _active = true;
    private ImageButton btn_leave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.losescreen);

          btn_leave = (ImageButton)findViewById(R.id.btn_leave);
          btn_leave.setOnClickListener(this); //Set Listener to this button --> Start Button
//
//        btn_back = (Button)findViewById(R.id.btn_back);
//        btn_back.setOnClickListener(this); //Set Listener to this button --> Back Button
//
//        btn_option = (Button)findViewById(R.id.btn_option);
//        btn_option.setOnClickListener(this); //Set Listener to this button --> Back Button
//
        StateManager.Instance.AddState(new LoseState());
    }
//   public boolean onTouchEvent(MotionEvent event){
//        if(event.getAction() == MotionEvent.ACTION_DOWN){
//            LoseState.this.finish();
//            Intent intent = new Intent(LoseState.this, Mainmenu.class);
//            startActivity(intent);
//        }
//        return true;
//    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v==btn_leave){
            LoseState.this.finish();
            intent.setClass(LoseState.this,Mainmenu.class);
        }
        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "LoseState";
    }

    @Override
    public void OnEnter(SurfaceView _view) {

    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {

    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void SetEnd() {

    }

    @Override
    public void SetWinScreen() {

    }
}
