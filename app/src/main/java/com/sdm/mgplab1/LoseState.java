package com.sdm.mgplab1;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.w3c.dom.Text;

import java.util.Arrays;

public class LoseState extends Activity implements OnClickListener, StateBase {
    protected boolean _active = true;
    private ImageButton btn_leave;
    private TextView scoreText;
    private  TextView highScoreText;
    String GetHighScoreString;
    String GetHighestScoreString;
    private int GetHighScore = 0;
    private int GetHighestScore = 0;
    protected static final String TAG = null;

    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private static final String EMAIL = "email";

    private Button btn_sharescore;

    private LoginButton btn_fbLogin;

    private ShareDialog share_dialog;
    private int PICK_IMAGE_REQUEST = 1;
    ProfilePictureView profile_pic;



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



        setContentView(R.layout.losescreen);

        btn_leave = (ImageButton)findViewById(R.id.btn_leave);
        btn_leave.setOnClickListener(this); //Set Listener to this button --> Start Button

        scoreText = (TextView)findViewById(R.id.ScoreText);
        scoreText.setText(GetHighScoreString);

        highScoreText = (TextView)findViewById(R.id.HighScoreText);
        highScoreText.setText(GetHighestScoreString);

     //btn_fbLogin = (LoginButton) findViewById(R.id.login_button);
     //btn_fbLogin.setReadPermissions(Arrays.asList(EMAIL));
     //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));


     //share_dialog = new ShareDialog(this);
     //callbackManager = CallbackManager.Factory.create();

     //loginManager = LoginManager.getInstance();

     //loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
     //    @Override
     //    public void onSuccess(LoginResult loginResult) {
     //        profile_pic.setProfileId(Profile.getCurrentProfile().getId());

     //        AccessToken accessToken = AccessToken.getCurrentAccessToken();
     //        loginResult.getAccessToken().getUserId();
     //    }

     //    @Override
     //    public void onCancel() {
     //        System.out.println("Login attempt cancelled.");
     //    }

     //    @Override
     //    public void onError(FacebookException e) {
     //        System.out.println("Login attempt failed.");
     //    }
     //});

     //if(BuildConfig.DEBUG)
     //{
     //    FacebookSdk.setIsDebugEnabled(true);
     //    FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
     //}

        //StateManager.Instance.AddState(new LoseState());
    }

    public void ShareScore(){
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);

        if(ShareDialog.canShow(SharePhotoContent.class)){
            System.out.println("photo Shown");
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .setCaption("Thank you for playing Vortex Runner. Your final score is" + highScoreText)
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            share_dialog.show(content);
        }
    }

    public void sharePhotos(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v==btn_leave){
            LoseState.this.finish();
            Levelselect.level.finish();     // end the activity
            intent.setClass(LoseState.this,Mainmenu.class);
            startActivity(intent);
        }

    }

    @Override
    public String GetName() {
        return "LoseState";
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
