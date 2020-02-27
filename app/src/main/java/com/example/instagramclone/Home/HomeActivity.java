package com.example.instagramclone.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.instagramclone.Login.LoginActivity;
import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.SectionsPagerAdapter;
import com.example.instagramclone.Utils.UniversalImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private Context mContext = HomeActivity.this;

    private static final int ACTIVITY_NUM = 0;

    //firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG,"onCreate : starting");
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        initImageLoader();
        setupBottomNavigationView();
        setupViewPager();

    }

    private void initImageLoader(){
        UniversalImageLoader universalImageLoader=new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    //Responsible for adding the 3 tabs - Camera,Phone & Messages
    private void setupViewPager(){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());//Index 0
        adapter.addFragment(new HomeFragment());//Index 1
        adapter.addFragment(new MessagesFragment());//Index 2
        ViewPager viewPager = findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);

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
    /*
    * ------------------------------Firebase----------------------------------------
    * */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    //Change UI according to user data.
    public void  updateUI(FirebaseUser account){
        //checks to see if @param account is logged in
        if(account != null){
            Log.d(TAG, "updateUI: Currently Signed In");
            //Toast.makeText(this,"You have signed in successfully",Toast.LENGTH_LONG).show();
            //startActivity(new Intent(this,AnotherActivity.class));
        }else {
            Log.d(TAG, "updateUI: Not Signed In");
            /*Toast.makeText(this,"Sign in failed", Toast.LENGTH_LONG).show();*/
            startActivity(new Intent(mContext, LoginActivity.class));
        }
    }
}
