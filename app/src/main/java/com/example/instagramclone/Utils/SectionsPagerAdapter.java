package com.example.instagramclone.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

//Class that stores Fragments for tabs

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "SectionsPagerAdapter";

    private final List<Fragment> mFragmnentList = new ArrayList<>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmnentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmnentList.size();
    }
    public void addFragment(Fragment fragment){
        mFragmnentList.add(fragment);
    }
}
