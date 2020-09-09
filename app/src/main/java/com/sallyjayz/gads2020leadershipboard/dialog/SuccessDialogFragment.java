package com.sallyjayz.gads2020leadershipboard.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.sallyjayz.gads2020leadershipboard.R;

public class SuccessDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View successPrompt = layoutInflater.inflate(R.layout.dialog_successful, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();

        alertDialog.setView(successPrompt);
        alertDialog.show();

        return alertDialog;
    }
}
