package com.sdm.mgplab1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MainMenuLeaveConfirmDialogFragment extends DialogFragment {
    public static boolean IsShown = false;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        IsShown = true;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Leave The Game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        // User triggered leave
                        //GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
                        Mainmenu.leaveGame = true;
                        System.exit(0);
                        IsShown = false;
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        //User cancelled leave
                        IsShown = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
