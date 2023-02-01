package com.sdm.mgplab1;

import android.app.Activity;
import android.graphics.Canvas;
import android.media.FaceDetector;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.content.Intent;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;

// Created by TanSiewLan2021

public class Mainmenu extends Activity implements OnClickListener, StateBase {  //Using StateBase class

    //Define buttons
    private Button btn_start;
    private Button btn_back;
    private Button btn_option;
    public static boolean leaveGame;

    private CallbackManager callbackManager;
    private LoginManager loginManager;
    private static final String EMAIL = "email";

    private LoginButton btn_fbLogin;

    private ShareDialog share_dialog;
    private int PICK_IMAGE_REQUEST = 1;
    ProfilePictureView profile_pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.setApplicationId("1205845453377928");
        FacebookSdk.isInitialized();

        if(BuildConfig.DEBUG)
        {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        btn_fbLogin = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        profile_pic = (ProfilePictureView) findViewById(R.id.picture);
        loginManager = LoginManager.getInstance();


        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                profile_pic.setProfileId(profile.getId());
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                loginResult.getAccessToken().getUserId();

                Intent intent = new Intent();
                intent.setClass(Mainmenu.this,Scorepage.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {
                System.out.println("Login attempt cancelled.");
            }

            @Override
            public void onError(FacebookException e) {
                System.out.println("Login attempt failed.");
            }
        });



        leaveGame = false;
        // Hide Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Hide Top Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.mainmenu);

        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this); //Set Listener to this button --> Start Button

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this); //Set Listener to this button --> Back Button

        btn_option = (Button)findViewById(R.id.btn_option);
        btn_option.setOnClickListener(this); //Set Listener to this button --> Back Button


       // FacebookSdk.setApplicationId("1205845453377928");
        //FacebookSdk.isInitialized();




        StateManager.Instance.AddState(new Mainmenu());
    }

    @Override
    //Invoke a callback event in the view
    public void onClick(View v)
    {

        // Intent = action to be performed.
        // Intent is an object provides runtime binding.
        // new instance of this object intent

        Intent intent = new Intent();

        if (v == btn_start)
        {

            // intent --> to set to another class which another page or screen that we are launching.

            Mainmenu.this.finish();
            intent.setClass(this, Levelselect.class);
            StateManager.Instance.ChangeState("Levelselect"); // Default is like a loading page
            //AudioManager.Instance.PlayAudio(R.raw.buttonsfx2,0.1f);
            startActivity(intent);

        }
        else if (v == btn_option)
        {
            Mainmenu.this.finish();
            // intent --> to set to another class which another page or screen that we are launching.
            intent.setClass(this, Option.class);
            StateManager.Instance.ChangeState("Option"); // Default is like a loading page
            //AudioManager.Instance.PlayAudio(R.raw.buttonsfx2,0.1f);
            startActivity(intent);
        }

        else if (v == btn_back)
        {
            MainMenuLeaveConfirmDialogFragment leavingGame = new MainMenuLeaveConfirmDialogFragment();
            leavingGame.show(this.getFragmentManager(),"LeaveConfirm");

//           Mainmenu.this.finish();
//           System.exit(0);
           //AudioManager.Instance.PlayAudio(R.raw.buttonsfx2,0.1f);
        }
        else if (v == btn_fbLogin)
        {
            LoginManager.getInstance().logInWithReadPermissions(Mainmenu.this, Arrays.asList("public_profile"));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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
    public void SetEnd() {

    }

    @Override
    public void SetWinScreen() {

    }

    @Override
    public String GetName() {
        return "Mainmenu";
    }

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
