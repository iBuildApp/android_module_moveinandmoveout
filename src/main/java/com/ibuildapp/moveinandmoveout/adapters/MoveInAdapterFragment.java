package com.ibuildapp.moveinandmoveout.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.ibuildapp.moveinandmoveout.MoveInActivity;
import com.ibuildapp.moveinandmoveout.fragments.MoveInFirstFragment;
import com.ibuildapp.moveinandmoveout.fragments.MoveInSecondFragment;
import com.ibuildapp.moveinandmoveout.fragments.details.OnItemChangedListener;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

/**
 * Created by web-developer on 13.04.2017.
 */

public class MoveInAdapterFragment  extends FragmentStatePagerAdapter {
    private SpreadsheetItemMove items;
    private MoveInFirstFragment firstFragment;
    private MoveInSecondFragment secondFragment;
    private static int NUM_ITEMS = 2;
    private OnItemChangedListener listener;

    public MoveInAdapterFragment(FragmentManager fm, SpreadsheetItemMove items, OnItemChangedListener listener) {
        super(fm);
        this.items = items;
        this.listener = listener;
    }

    @Override
    public Fragment getItem(int position) {
       if (position==0) {

       if (firstFragment == null)
           firstFragment = new MoveInFirstFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(MoveInandMoveOutContants.ITEM, items);
        firstFragment.setArguments(bundle);
        return firstFragment;
       }else {
           if (secondFragment == null)
               secondFragment = new MoveInSecondFragment();

           Bundle bundle = new Bundle();
           bundle.putSerializable(MoveInandMoveOutContants.ITEM, items);
           secondFragment.setArguments(bundle);
           return secondFragment;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    public MoveInFirstFragment getFirstFragment() {
        return firstFragment;
    }

    public MoveInSecondFragment getSecondFragment() {
        return secondFragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position!=1)
            return super.instantiateItem(container, position);

        MoveInSecondFragment fragment = (MoveInSecondFragment) super.instantiateItem(container, position);
        fragment.setListener(new OnItemChangedListener() {
            @Override
            public void itemChanged() {
                listener.itemChanged();
            }

            @Override
            public void changesCleared() {
                listener.changesCleared();
            }

            @Override
            public void justLockSwipe() {
                listener.justLockSwipe();
            }

            @Override
            public void justUnlockSwipe() {
                listener.justUnlockSwipe();
            }

            @Override
            public void callPhone(String phone) {
                listener.callPhone(phone);
            }

            @Override
            public void showMap(String address) {
                listener.showMap(address);
            }
        });
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);

        if (position==1) {
            MoveInSecondFragment fragment = (MoveInSecondFragment) object;
            fragment.setListener(null);
        }
    }
}
