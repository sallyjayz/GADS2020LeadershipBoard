package com.sallyjayz.gads2020leadershipboard.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.sallyjayz.gads2020leadershipboard.R;

public class ConfirmDialogFragment extends DialogFragment implements View.OnClickListener {

    private ConfirmDialogListener mConfirmDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View confirmPrompt = layoutInflater.inflate(R.layout.dialog_confirmation, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();

        ImageView cancelImage = confirmPrompt.findViewById(R.id.cancel_image);
        Button yesButton = confirmPrompt.findViewById(R.id.yes_button);

        cancelImage.setOnClickListener(this);
        yesButton.setOnClickListener(this);

        alertDialog.setView(confirmPrompt);
        alertDialog.show();

        return alertDialog;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.cancel_image:
                ConfirmDialogFragment.this.getDialog().cancel();
                return;
            case R.id.yes_button:
                mConfirmDialogListener.submitProject();
                dismiss();
                return;
        }

    }

    /*public interface ConfirmDialogListener {

        void submitProject();

    }*/

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            mConfirmDialogListener = (ConfirmDialogListener) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ConfirmDialogListener");
        }
    }
}
