package com.sdm.mgplab1;

// Created by TanSiewLan2021

public class Collision {

    public static boolean SphereToSphere(float x1, float y1, float radius1, float x2, float y2, float radius2)
    {
        float xVec = x2 - x1;
        float yVec = y2 - y1;

        float distSquared = xVec * xVec + yVec * yVec;

        float rSquared = radius1 + radius2;
        rSquared *= rSquared;

        if (distSquared > rSquared)
            return false;

        return true;
    }

    public static boolean AABB(float x1, float y1, float width1, float height1,
                               float x2, float y2, float width2, float height2){

        float x1min = x1 - (width1 * 0.5f);
        float y1min = y1 - (height1 * 0.5f);
        float x2min = x2 - (width2 * 0.5f);
        float y2min = y2 - (height2 * 0.5f);

        float x1max = x1 + (width1 * 0.5f);
        float y1max = y1 + (height1 * 0.5f);
        float x2max = x2 + (width2 * 0.5f);
        float y2max = y2 + (height2 * 0.5f);

        if(x2min <= x1max &&
           x2max >= x1min &&
           y2min <= y1max &&
           y2max >= y1min)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public static boolean CollideWithSide(float x1, float y1, float width1, float height1,
                               float x2, float y2, float width2, float height2) {
        float x1min = x1 - (width1 * 0.5f);
        float y1min = y1 - (height1 * 0.5f);
        float x2min = x2 - (width2 * 0.5f);
        float y2min = y2 - (height2 * 0.5f);
        //System.out.println(y1min);
        //System.out.println(y2min);
        float x1max = x1 + (width1 * 0.5f);
        float y1max = y1 + (height1 * 0.5f);
        float x2max = x2 + (width2 * 0.5f);
        float y2max = y2 + (height2 * 0.5f);

        if(y2min <= y1max && y2max >= y1min)
        {
            return true;
        }
        else
        {
            return false;
        }

    }



}
