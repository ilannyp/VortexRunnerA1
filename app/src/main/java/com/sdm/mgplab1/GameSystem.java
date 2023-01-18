package com.sdm.mgplab1;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.SurfaceView;

// Created by TanSiewLan2021

public class GameSystem {
    public final static GameSystem Instance = new GameSystem();
    public static final String SHARED_PREF_ID = "GameSaveFile";

    // Game stuff
    private boolean isPaused = false;
    private boolean isWon = false;
    SharedPreferences sharedPref = null;
    SharedPreferences.Editor editor = null;

    int score = 0;

    // Singleton Pattern : Blocks others from creating
    private GameSystem() {
    }

    public void Update(float _deltaTime) {
    }

    public void Init(SurfaceView _view) {
        //Get our shared pref (save file)
        sharedPref = GamePage.Instance.getSharedPreferences(SHARED_PREF_ID, 0);


        // We will add all of our states into the state manager here!
        StateManager.Instance.AddState(new Mainmenu());
        StateManager.Instance.AddState(new MainGameSceneState());
        StateManager.Instance.AddState(new Levelselect());
        StateManager.Instance.AddState(new LoseState());
    }

    public void SetIsPaused(boolean _newIsPaused) {
        isPaused = _newIsPaused;
    }

    public boolean GetIsPaused() {
        return isPaused;
    }

    public void SetIsWon(boolean _newIsWon) {
        isWon = _newIsWon;
    }

    public boolean GetIsWon() {
        return isWon;
    }

    public void SaveEditBegin() {
        //Safety check, only allow if not already editing
        if (editor != null)
            return;

        //Start the editing
        editor = sharedPref.edit();
    }

    public void SaveEditEnd()
    {
        //check if has editor
        if(editor == null)
            return;

        editor.commit();
        editor = null;//Safety to ensure other functions will fail once commit done
    }

    public void SetIntInSave(String _key, int _value){
        if(editor == null)
            return;

        editor.putInt(_key,_value);

    }

    public int GetIntFromSave(String _key)
    {
        return sharedPref.getInt(_key,1);


    }

}
