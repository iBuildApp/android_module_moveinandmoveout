package com.ibuildapp.moveinandmoveout.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import com.ibuildapp.moveinandmoveout.fragments.MoveOutFirstFragment;
import com.ibuildapp.moveinandmoveout.fragments.MoveOutSecondFragment;
import com.ibuildapp.moveinandmoveout.fragments.details.OnItemChangedListener;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

public class MoveOutAdapterFragment extends FragmentStatePagerAdapter {
    private SpreadsheetItemMove items;
    private MoveOutFirstFragment firstFragmentMoveOut;
    private MoveOutSecondFragment secondFragmentMoveOut;
    private OnItemChangedListener listener;
    private static int NUM_ITEMS = 2;

    public MoveOutAdapterFragment(FragmentManager fm, SpreadsheetItemMove items,  OnItemChangedListener listener) {
        super(fm);
        this.items = items;
        this.listener = listener;
    }
    @Override
    public Fragment getItem(int position) {
        if (position==0) {

            if (firstFragmentMoveOut == null)
                firstFragmentMoveOut = new MoveOutFirstFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable(MoveInandMoveOutContants.ITEM, items);
            firstFragmentMoveOut.setArguments(bundle);
            return firstFragmentMoveOut;
        }else {
            if (secondFragmentMoveOut == null)
                secondFragmentMoveOut = new MoveOutSecondFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable(MoveInandMoveOutContants.ITEM, items);
            secondFragmentMoveOut.setArguments(bundle);
            return secondFragmentMoveOut;
        }

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    public MoveOutFirstFragment getFirstFragment() {
        return firstFragmentMoveOut;
    }

    public MoveOutSecondFragment getSecondFragment() {
        return secondFragmentMoveOut;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position!=1)
            return super.instantiateItem(container, position);

        MoveOutSecondFragment fragment = (MoveOutSecondFragment) super.instantiateItem(container, position);
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
            MoveOutSecondFragment fragment = (MoveOutSecondFragment) object;
            fragment.setListener(null);
        }
    }
}



