package com.example.project_calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Array;


public class ConfirmDialog extends DialogFragment {
    public interface PLclickListener{
        public void onPLClick(int JokeNum);
    }
    private PLclickListener mlistener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Bundle bundle=getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Confirm")
                .setMessage("text")
                .setPositiveButton("Punchline", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                         handleOK();
                    }
                })
                .setNegativeButton("Cancel", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
        return builder.create();
    }
    public void handleOK() {
        Toast.makeText(getActivity(), "OK was clicked.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mlistener=(PLclickListener)context;
        }
        catch(ClassCastException e){
            throw new ClassCastException(context.toString()+"must implement OKclickListener");
        }
    }
}
