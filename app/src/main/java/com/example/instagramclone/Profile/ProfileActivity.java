package com.example.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.GridImageAdapter;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;

    private Context mContext = ProfileActivity.this;

    private ProgressBar mProgressBar;
    private ImageView profilePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG,"onCreate : started");

        setupBottomNavigationView();
        setupToolBar();
        setupActivityWidgets();
        setProfileImage();

        tempGridSetup();
    }

    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://www.appliedis.com/wp-content/uploads/2013/11/android1.png");
        imgURLs.add("https://previews.agefotostock.com/previewimage/medibigoff/b110f54e1d5c11031ef1a5de9c161fde/l30-499844.jpg");
        imgURLs.add("https://www.appliedis.com/wp-content/uploads/2013/11/android1.png");
        imgURLs.add("https://previews.agefotostock.com/previewimage/medibigoff/b110f54e1d5c11031ef1a5de9c161fde/l30-499844.jpg");
        imgURLs.add("https://www.appliedis.com/wp-content/uploads/2013/11/android1.png");
        imgURLs.add("https://previews.agefotostock.com/previewimage/medibigoff/b110f54e1d5c11031ef1a5de9c161fde/l30-499844.jpg");
        imgURLs.add("https://www.appliedis.com/wp-content/uploads/2013/11/android1.png");
        imgURLs.add("https://previews.agefotostock.com/previewimage/medibigoff/b110f54e1d5c11031ef1a5de9c161fde/l30-499844.jpg");

        setUpImageGrid(imgURLs);

    }

    private void setUpImageGrid(ArrayList<String> imgURLs){
        GridView gridView = findViewById(R.id.gridView);

        GridImageAdapter adapter =new GridImageAdapter(mContext,R.layout.layout_grid_imageview,"",imgURLs);
        gridView.setAdapter(adapter);
    }

    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile photo");

        String imgURL="https://www.appliedis.com/wp-content/uploads/2013/11/android1.png";
        UniversalImageLoader.setImage(imgURL,profilePhoto,mProgressBar,"");

    }
    private void setupActivityWidgets(){
        mProgressBar = findViewById(R.id.profileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto=findViewById(R.id.profile_photo);
    }

    //Responsible for setting up Profile Toolbar
    private void setupToolBar(){
        Toolbar toolbar = findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);
        /*toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG,"onMenuItemClick: clicked Menu Item : " + item);

                switch (item.getItemId()){
                    case R.id.profileMenu :
                    Log.d(TAG,"onMenuItemClick : Navigating to Profile Preferences");
                }

                return false;
            }
        });*/
        ImageView profileMenu = findViewById(R.id.profileMenu);
        profileMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick : Navigating to Account Settings");
                Intent intent = new Intent(mContext,AccountSettingsActivity.class);
                startActivity(intent);
            }
        });
    }
    //Bottom Navigation View Setup
    private void setupBottomNavigationView(){
        Log.d("TAG","setupBottomNavigationView : setting up BottomNavigationView ");
        BottomNavigationViewEx bottomNavigationViewEx=findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(mContext,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    /*//Always remember to implement this method
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile_menu,menu);
        return true;
    }*/
}
