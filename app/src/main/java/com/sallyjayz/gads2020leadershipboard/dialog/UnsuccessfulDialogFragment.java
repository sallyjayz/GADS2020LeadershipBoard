package com.sallyjayz.gads2020leadershipboard.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sallyjayz.gads2020leadershipboard.R;

public class UnsuccessfulDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View unsuccessPrompt = layoutInflater.inflate(R.layout.dialog_unsuccessful, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();

        alertDialog.setView(unsuccessPrompt);
        alertDialog.show();

        return alertDialog;
    }
}
