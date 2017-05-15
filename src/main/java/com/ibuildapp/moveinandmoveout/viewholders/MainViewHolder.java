package com.ibuildapp.moveinandmoveout.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibuildapp.moveinandmoveout.R;

/**
 * Created by web-developer on 17.03.2017.
 */

public class MainViewHolder extends RecyclerView.ViewHolder{
    public LinearLayout mainLayout;
    public LinearLayout mainLayoutmain;
    public LinearLayout mainLayoutprop;
    public LinearLayout mainLayout2;
    public LinearLayout mainLayout3;
    public TextView propertyname;
    public TextView address;
    public TextView flatnumber;
    public TextView monthlyrent;
    public  TextView status;
    public TextView flatnumber2;
    public TextView monthlyrent2;
    public  TextView status2;
    public TextView flatnumber3;
    public TextView monthlyrent3;
    public  TextView status3;
    public  ImageView nextscreen;
    public View separator;
    public View separator2;
    public View separator3;
    public MainViewHolder(View itemView) {
        super(itemView);
        mainLayoutmain=(LinearLayout)itemView.findViewById(R.id.linerlayoutmain);
        mainLayoutprop=(LinearLayout)itemView.findViewById(R.id.linerlayoutprop);
        propertyname = (TextView) itemView.findViewById(R.id.moveinandmoveout_item_propertyname);
        mainLayout=(LinearLayout)itemView.findViewById(R.id.moveinandmoveout_layout_flatnumber);
        flatnumber = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_flatnumber);
        monthlyrent = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_monthlyrent);
        status = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_status);
        mainLayout2=(LinearLayout)itemView.findViewById(R.id.moveinandmoveout_layout_flatnumber2);
        flatnumber2 = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_flatnumber2);
        monthlyrent2 = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_monthlyrent2);
        status2 = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_status2);
        mainLayout3=(LinearLayout)itemView.findViewById(R.id.moveinandmoveout_layout_flatnumber3);
        flatnumber3 = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_flatnumber3);
        monthlyrent3 = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_monthlyrent3);
        status3 = (TextView)itemView.findViewById(R.id.moveinandmoveout_item_status3);
        nextscreen = (ImageView)itemView.findViewById(R.id.moveinandmoveout_next_screen);
        separator = itemView.findViewById(R.id.moveinandmoveout_main_separator);
        separator2 = itemView.findViewById(R.id.moveinandmoveout_main_separator2);
        separator3 = itemView.findViewById(R.id.moveinandmoveout_main_separator3);

    }
}
