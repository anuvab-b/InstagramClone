package com.example.instagramclone.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.instagramclone.R;

public class ConfirmPasswordDialog extends DialogFragment {

    private static final String TAG = "ConfirmPasswordDialog";
    TextView mPassword;

    public interface OnConfirmPasswordListener{
        public void onConfirmPassword(String password);
    }
    OnConfirmPasswordListener mOnConfirmPasswordListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_confirm_password,container,false);
        Log.d(TAG, "onCreateView: started");

        mPassword = view.findViewById(R.id.confirm_password);
        TextView cancelDialog = view.findViewById(R.id.dialogCancel);
        cancelDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Closing the dialog");
                getDialog().dismiss();
            }
        });
        TextView confirmDialog = view.findViewById(R.id.dialogConfirm);
        confirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capture the password and confirming");
                String password = mPassword.getText().toString();
                if(!password.equals("")){
                    mOnConfirmPasswordListener.onConfirmPassword(password);
                    getDialog().dismiss();
                }
                else{
                    Toast.makeText(getActivity(),"You must enter a password",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try{
            mOnConfirmPasswordListener = (OnConfirmPasswordListener)getTargetFragment();
        }
        catch (ClassCastException e)
        {
            Log.d(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
    }
}
