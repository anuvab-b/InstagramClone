package com.example.instagramclone.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.instagramclone.R;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";

    private ImageView mProfilePhoto;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editprofile,container,false);
        mProfilePhoto = view.findViewById(R.id.profile_photo);

        setProfileImage();

        //back arrow for navigating back to ProfileActivity
        ImageView backArrow = view.findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating back to Profile Activity");
                getActivity().finish();

            }
        });
        return view;
    }
    private void setProfileImage(){
        Log.d(TAG,"setProfileImage : setting profile image");
        String imgURL = "https://www.appliedis.com/wp-content/uploads/2013/11/android1.png";
        UniversalImageLoader.setImage(imgURL,mProfilePhoto,null,"");
    }
}
