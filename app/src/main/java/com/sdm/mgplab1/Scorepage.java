package com.sdm.mgplab1;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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

import java.util.Arrays;

public class Scorepage extends Activity implements View.OnClickListener {
    public static Scorepage Instance = null;
    public static final String SHARED_PREF_ID = "GameSaveFile";
    SharedPreferences sharedPreferences = null;

    private Button btn_sharescore;
    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private static final String EMAIL = "email";
    private Button btn_back;
    private LoginButton btn_fbLogin;
    private int highscore;
    private ShareDialog share_Dialog;
    private int PICK_IMAGE_REQUEST = 1;
    //Share DIalog shareDialog
    ProfilePictureView profile_pic;
    public Scorepage() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Instance = this;
        super.onCreate(savedInstanceState);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.scorepage);
        sharedPreferences = getSharedPreferences("GameSaveFile",0);
        FacebookSdk.setApplicationId("1205845453377928");
        FacebookSdk.isInitialized();

        if(BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        setContentView((R.layout.scorepage));

        TextView scoreText;
        scoreText = (TextView) findViewById(R.id.scoreText);
        profile_pic= (ProfilePictureView) findViewById(R.id.picture);
        highscore = GetIntFromSave("HighestScore");
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
        btn_sharescore = (Button) findViewById(R.id.btn_sharescore);
        btn_sharescore.setOnClickListener(this);
        btn_fbLogin = (LoginButton) findViewById(R.id.fb_login_button);
        btn_fbLogin.setReadPermissions(Arrays.asList(EMAIL));
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
        callbackManager = CallbackManager.Factory.create();
        // If you are using in a fragment, call loginButton.setFragment(this);
        loginManager = LoginManager.getInstance();

        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                scoreText.setText(String.format(profile.getName() + "'s highscore is " + highscore));
                profile_pic.setProfileId(Profile.getCurrentProfile().getId());
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                loginResult.getAccessToken().getUserId();
            }

            @Override
            public void onCancel() {System.out.println("Login attempt cancelled.");}

            @Override
            public void onError(FacebookException e) {
                System.out.println("Login attempt failed.");
            }
        });
    }

    public void shareScore() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        if(ShareDialog.canShow(SharePhotoContent.class)) {
            System.out.println("photoShown");
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .setCaption("Thank you for playing MGP2021. Your final score is " + highscore)
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();

            share_Dialog.show(content);
            System.out.println("Done");
        }
    }
    /*
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            _active = false;
        }
        return true;
    }
    */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
    public int GetIntFromSave(String _key)
    {
        return sharedPreferences.getInt(_key, 10); //<---- 10 is temp number that I place for test use
    }

    @Override
    public void onClick(View v) {
        if (v == btn_sharescore)
        {
            shareScore();
        }
        else if (v == btn_back)
        {
            StateManager.Instance.ChangeState("Mainmenu");
            Intent intent = new Intent();
            intent.setClass(this,Mainmenu.class);
            startActivity(intent);
        }
    }
    //End here with the 3 methods for the android life cycle of an activity class
}