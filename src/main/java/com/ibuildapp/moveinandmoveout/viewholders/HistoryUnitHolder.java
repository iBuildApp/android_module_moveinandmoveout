package com.ibuildapp.moveinandmoveout.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ibuildapp.moveinandmoveout.R;

/**
 * Created by web-developer on 25.04.2017.
 */

public class HistoryUnitHolder extends RecyclerView.ViewHolder {
    public LinearLayout mainLayoutmain;
    public TextView moveIn;
    public TextView moveOut;
    public  TextView tenantName;
    public HistoryUnitHolder(View itemView) {
        super(itemView);
        mainLayoutmain = (LinearLayout)itemView.findViewById(R.id.moveinandmoveout_unti_history_item);
        moveIn = (TextView)itemView.findViewById(R.id.moveinandmoveout_unti_history_dateIn);
        moveOut = (TextView)itemView.findViewById(R.id.moveinandmoveout_unti_history_dateOut);
        tenantName = (TextView)itemView.findViewById(R.id.moveinandmoveout_unti_history_tenantName);

    }
}
