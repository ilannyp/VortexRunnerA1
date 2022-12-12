package com.sdm.mgplab1;

// Created by TanSiewLan2021

public interface Collidable {
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);

    void SetPosX(float _newX);
    void SetPosY(float _newY);

}

