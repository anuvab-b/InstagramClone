package com.example.instagramclone.Share;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.instagramclone.R;
import com.example.instagramclone.Utils.BottomNavigationViewHelper;
import com.example.instagramclone.Utils.Permissions;
import com.example.instagramclone.Utils.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity {

    private static final String TAG = "ShareActivity";

    //constants
    private static final int ACTIVITY_NUM = 2;
    private static final int VERIFY_PERMISSIONS_REQUEST= 1;

    private ViewPager mViewPager;

    private Context mContext = ShareActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        Log.d(TAG,"onCreate : started");

        if(checkPermissionsArray(Permissions.PERMISSIONS)){

            setupViewPager();
        }
        else{
            verifyPermissions(Permissions.PERMISSIONS);
        }
    }

    /**
     * return the current tab number
     * 0 = Gallery Fragment
     * 1 = Photo Fragment
     * @return
     */
    public int getfCurrentTabNumber(){
        return mViewPager.getCurrentItem();
    }

    /**
     * setup viewpager for managing the tabs
     */
    private void setupViewPager(){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GalleryFragment());
        adapter.addFragment(new PhotoFragment());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabsBottom);
        tabLayout.setupWithViewPager(mViewPager);

        tabLayout.getTabAt(0).setText(R.string.gallery);
        tabLayout.getTabAt(1).setText(R.string.photo);
    }
    /**
     * verify all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions){
        Log.d(TAG, "verifyPermissions: verifying permissions");

        ActivityCompat.requestPermissions(
                ShareActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String[] permissions){
        Log.d(TAG, "checkPermissionsArray: Checking permissions array");

        for(int i = 0; i< permissions.length;i++){
            String check = permissions[i];
            if(!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }

    /**
     * Check a single permission if it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission){
        Log.d(TAG, "checkPermissions: checking permission : " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(ShareActivity.this,permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG, "checkPermissions: \n Permission was not granted for : " + permission);
            return false;
        }
        else{
            Log.d(TAG, "checkPermissions: \n Permission was granted for : " + permission);
            return true;
        }

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
}
