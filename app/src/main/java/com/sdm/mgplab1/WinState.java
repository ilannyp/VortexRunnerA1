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
import android.widget.TextView;

public class WinState extends Activity implements OnClickListener, StateBase {
    protected boolean _active = true;
    private ImageButton btn_leave;
    private TextView scoreText;
    private  TextView highScoreText;
    String GetHighScoreString;
    String GetHighestScoreString;
    private int GetHighScore = 0;
    private int GetHighestScore = 0;
    protected static final String TAG = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GetHighScore = GameSystem.Instance.GetIntFromSave("Score");
        GetHighScoreString = String.format("SCORE : %d", GameSystem.Instance.GetIntFromSave("Score"));

        if( GameSystem.Instance.GetIntFromSave("HighestScore") < GetHighScore)
        {
            //store new highest score
            GameSystem.Instance.SaveEditBegin();
            GameSystem.Instance.SetIntInSave("HighestScore", GetHighScore);//set high score in "HighScore"
            GameSystem.Instance.SaveEditEnd();

        }

        GetHighestScoreString = String.format("HIGHSCORE : %d", GameSystem.Instance.GetIntFromSave("HighestScore"));
        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.winscreen);

        btn_leave = (ImageButton)findViewById(R.id.btn_leave);
        btn_leave.setOnClickListener(this); //Set Listener to this button --> Start Button

        scoreText = (TextView)findViewById(R.id.ScoreText);
        scoreText.setText(GetHighScoreString);

        highScoreText = (TextView)findViewById(R.id.HighScoreText);
        highScoreText.setText(GetHighestScoreString);

        //StateManager.Instance.AddState(new WinState());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v==btn_leave){
            WinState.this.finish();
            Levelselect.level.finish();     // end the activity
            intent.setClass(WinState.this,Mainmenu.class);
        }
        startActivity(intent);
    }

    @Override
    public String GetName() {
        return "WinState";
    }

    @Override
    public void OnEnter(SurfaceView _view) {
        GetHighScore = GameSystem.Instance.GetIntFromSave("HighScore");
        GetHighScoreString = String.format("SCORE : %d", GameSystem.Instance.GetIntFromSave("HighScore"));

        if(GetHighestScore < GetHighScore)
            GetHighestScore = GetHighScore;

        GetHighestScoreString = String.format("HIGHSCORE : %d", GetHighestScore);
    }

    @Override
    public void OnExit() {

    }

    @Override
    public void Render(Canvas _canvas) {
        EntityManager.Instance.Render(_canvas);
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
