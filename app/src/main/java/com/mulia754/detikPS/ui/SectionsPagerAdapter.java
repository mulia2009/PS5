package com.mulia754.detikPS.ui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.mulia754.detikPS.R;
import com.mulia754.detikPS.swipe.DummyFragment;
import com.mulia754.detikPS.swipe.LoginFragment;
import com.mulia754.detikPS.swipe.SignupFragment;
import com.mulia754.detikPS.swipe.Terkini;
import com.mulia754.detikPS.swipe.aadepan_militer;
import com.mulia754.detikPS.swipe.aadepan_olahraga;
import com.mulia754.detikPS.swipe.aadepan_persit;
import com.mulia754.detikPS.swipe.aadepan_politik;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3,R.string.tab_text_4,R.string.tab_text_5,R.string.tab_text_6};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch(position) {
            case 0: fragment = new Terkini();
                break;
            case 1: fragment = new aadepan_militer();
                break;
            case 2: fragment = new aadepan_persit();
                break;
            case 3: fragment = new aadepan_politik();
                break;
            case 4: fragment = new aadepan_olahraga();
                break;
            case 5: fragment = new DummyFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 6;
    }
}