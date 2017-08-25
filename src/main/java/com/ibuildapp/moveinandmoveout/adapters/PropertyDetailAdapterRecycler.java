package com.ibuildapp.moveinandmoveout.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ibuildapp.moveinandmoveout.PropertyDetailActivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.viewholders.PropertyDetailHolder;

import java.util.List;

/**
 * Created by web-developer on 07.04.2017.
 */

public class PropertyDetailAdapterRecycler extends RecyclerView.Adapter<PropertyDetailHolder>  {
    private List<SpreadsheetItemProp> item;

    private PropertyDetailActivity context;
    public EntityManager manager;
    public PropertyDetailAdapterRecycler(PropertyDetailActivity context, List<SpreadsheetItemProp> item, EntityManager manager) {
        this.context = context;
        this.item=item;
        this.manager=manager;
    }
    @Override
    public PropertyDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.moveinandmoveout_property_detail_item_flatnumber, parent, false);
        PropertyDetailHolder holder = new PropertyDetailHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final PropertyDetailHolder holder, int position) {
        final SpreadsheetItemProp currentItem = item.get(position);
            final String propertyName = currentItem.getPropertyname();
            final String flatnumber = currentItem.getFlatnumber();
            holder.flatnumber.setText(currentItem.getFlatnumber());
            holder.monthlyrent.setText(currentItem.getMonthlyrent());
            holder.status.setText(currentItem.getStatus());
            holder.mainLayoutmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.formDetailsAddressActivity(propertyName, flatnumber);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    public void setItems(List<SpreadsheetItemProp> items) {
        this.item = items;
    }
}