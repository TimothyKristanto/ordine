package com.example.ordine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;

public class LoadingDialogActivity {
    private Activity activity;
    private AlertDialog dialog;

    public LoadingDialogActivity(Activity myActivity){
        activity = myActivity;
    }

    public void startLoading(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.activity_loading_dialog, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    public void stopLoading(){
        dialog.dismiss();
    }
}