package com.ibuildapp.moveinandmoveout.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.ibuildapp.moveinandmoveout.AddressDetailAtivity;
import com.ibuildapp.moveinandmoveout.fragments.AddressDetailFragment;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

import java.util.List;

/**
 * Created by web-developer on 04.04.2017.
 */

public class AddressDetailAdapter extends FragmentStatePagerAdapter {
        private List<SpreadsheetItemProp> items;
        private SparseArray<AddressDetailFragment> registeredFragments = new SparseArray<>();
        public AddressDetailAtivity context;

        public AddressDetailAdapter(FragmentManager fm, List<SpreadsheetItemProp> items, AddressDetailAtivity context) {
                super(fm);
                this.items = items;
                this.context=context;
                }

        @Override
        public Fragment getItem(int position) {
                AddressDetailFragment fragment = new AddressDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable(MoveInandMoveOutContants.ITEM, items.get(position));
                fragment.setArguments(bundle);
                fragment.setContext(context);
                registeredFragments.put(position, fragment);
                return fragment;
                }

        @Override
        public int getCount() {
                return items.size();
                }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
                registeredFragments.remove(position);
                }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
                AddressDetailFragment fragment = (AddressDetailFragment) super.instantiateItem(container, position);
                registeredFragments.put(position, fragment);
                return fragment;
                }

        public void setItems(List<SpreadsheetItemProp> items) {
                this.items = items;
                }
}


