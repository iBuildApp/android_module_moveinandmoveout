package com.ibuildapp.moveinandmoveout.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibuildapp.moveinandmoveout.R;

/**
 * Created by web-developer on 04.04.2017.
 */

public class PropertyDetailHolder extends RecyclerView.ViewHolder {
        public LinearLayout mainLayoutmain;
        public TextView flatnumber;
        public TextView monthlyrent;
        public  TextView status;
        public PropertyDetailHolder(View itemView) {
            super(itemView);
            mainLayoutmain = (LinearLayout)itemView.findViewById(R.id.moveinandmoveout_property_detail_mainlayout);
            flatnumber = (TextView)itemView.findViewById(R.id.moveinandmoveout_property_detail_flatnumber);
            monthlyrent = (TextView)itemView.findViewById(R.id.moveinandmoveout_property_detail_monthlyrent);
            status = (TextView)itemView.findViewById(R.id.moveinandmoveout_property_detail_status);

        }
    }


