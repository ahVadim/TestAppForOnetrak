package com.example.vadim.testappforonetrak.ui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.vadim.testappforonetrak.R;

public class SettingsDialogFragment extends DialogFragment {
    public interface SettingsDialogListener {
        public void onDialogPositiveClick(String value);
    }

    public static SettingsDialogFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SettingsDialogFragment fragment = new SettingsDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    SettingsDialogListener mSettingsDialogListener;
    private EditText mEditText;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSettingsDialogListener = (SettingsDialogListener)getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.hint_aim_change);
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_fragment,null);
        mEditText = view.findViewById(R.id.et_settings);
        builder.setView(view);
        builder.setPositiveButton(R.string.hint_save, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mSettingsDialogListener.onDialogPositiveClick(mEditText.getText().toString());
            }
        })
                .setNegativeButton(R.string.hint_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();
    }


}
