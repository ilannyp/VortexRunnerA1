package com.sdm.mgplab1;

import android.text.method.Touch;
import android.util.Log;
import android.view.MotionEvent;

// Created by TanSiewLan2021

// Manages the touch events

public class TouchManager {
    public final static TouchManager Instance = new TouchManager();
    protected static final String TAG = null;
    private TouchManager(){

    }

    public enum TouchState{
        NONE,
        DOWN,
        SWIPEUP,
        MOVE
    }

    private int posX, posY;
    private int StoreX, StoreY;
    private TouchState status = TouchState.NONE; //Set to default as NONE

    public boolean HasTouch(){  // Check for a touch status on screen
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }

    public boolean IsDown(){
        return status == TouchState.DOWN;
    }

    public boolean IsUp(){
        return status == TouchState.NONE;
    }


    public boolean IsSwipedUp(){
        return status == TouchState.SWIPEUP;
    }


    public int GetPosX(){
        return posX;
    }

    public int GetPosY(){
        return posY;
    }

    public void Update(int _posX, int _posY, int _motionEventStatus){
        posX = _posX;
        posY = _posY;



        switch (_motionEventStatus){
            case MotionEvent.ACTION_DOWN:
                status = TouchState.DOWN;
                StoreX = _posX;
                StoreY = _posY;
                break;

            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;

            case MotionEvent.ACTION_UP:
                status = TouchState.NONE;
                break;
        }


    }
}

