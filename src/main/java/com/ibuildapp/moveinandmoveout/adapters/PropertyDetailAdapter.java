package com.ibuildapp.moveinandmoveout.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.ibuildapp.moveinandmoveout.PropertyDetailActivity;
import com.ibuildapp.moveinandmoveout.fragments.PropertyDetailFragment;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;

import java.util.List;


public class PropertyDetailAdapter extends FragmentStatePagerAdapter {
    private List<SpreadsheetItemProp> items;
    private SparseArray<PropertyDetailFragment> registeredFragments = new SparseArray<>();
    private PropertyDetailActivity context;

    public PropertyDetailAdapter(FragmentManager fm, List<SpreadsheetItemProp> items,PropertyDetailActivity context) {
        super(fm);
        this.items = items;
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        PropertyDetailFragment fragment = new PropertyDetailFragment();
        fragment.context=context;
        Bundle bundle = new Bundle();
        bundle.putSerializable(MoveInandMoveOutContants.ITEM, items.get(position));
        fragment.setArguments(bundle);
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
        PropertyDetailFragment fragment = (PropertyDetailFragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }


    public void setItems(List<SpreadsheetItemProp> items) {
        this.items = items;
    }
}
