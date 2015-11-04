package menasoft.lejarapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.Locale;

import menasoft.lejarapp.fragments.BalanceFragment;
import menasoft.lejarapp.fragments.HistoryFragment;
import menasoft.lejarapp.fragments.RandomFragment;
import menasoft.lejarapp.R;

/**
 * Created by Rmena on 10/28/2015.
 */
public class SwipeAdapter extends FragmentStatePagerAdapter {




    public SwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        Log.i("SwipeAdapter","Count ###: " + i);
        switch(i){
            case 1:
                fragment = new BalanceFragment();
                break;
            case 2:
                fragment = new HistoryFragment();
                break;
            default:
                fragment = new RandomFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale l = Locale.getDefault();
        switch (position) {
            case 0:
                return "RANDOM";
            case 1:
                return "BALANCE";
            case 2:
                return "MY HISTORY";
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
