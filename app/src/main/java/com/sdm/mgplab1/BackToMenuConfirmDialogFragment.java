package com.sdm.mgplab1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

public class BackToMenuConfirmDialogFragment extends DialogFragment {
    public static boolean IsShown = false;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        IsShown = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Confirm Back To Menu?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        // User triggered pause
                        //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                        MainGameSceneState.wanGoBack = true;
                        IsShown = false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //User cancelled pause
                        IsShown = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}
