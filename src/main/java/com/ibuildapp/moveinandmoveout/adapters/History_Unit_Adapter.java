package com.ibuildapp.moveinandmoveout.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.ibuildapp.moveinandmoveout.MoveinandMoveoutHistory;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.viewholders.HistoryUnitHolder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by web-developer on 25.04.2017.
 */

public class History_Unit_Adapter extends RecyclerView.Adapter<HistoryUnitHolder>  {
    private List<SpreadsheetItemMove> item;
    private int lastAnimatedPosition = -1;
    private boolean animationsLocked = false;
    public String dateFormat="";
    public MoveinandMoveoutHistory context;

    public History_Unit_Adapter(List<SpreadsheetItemMove> item, MoveinandMoveoutHistory context) {
        this.item=item;
        this.context=context;
    }

    @Override
    public HistoryUnitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.moveinandmoveout_unit_history_main_item, parent, false);
        HistoryUnitHolder holder = new HistoryUnitHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final HistoryUnitHolder holder, int position) {
           runEnterAnimation(holder.itemView, position);
        final SpreadsheetItemMove currentItem = item.get(position);
            holder.moveIn.setText(excelDateStringToJavaDate(currentItem.getDateIn()));
            holder.moveOut.setText(excelDateStringToJavaDate(currentItem.getDateOut()));
            holder.tenantName.setText(currentItem.getTenantname());

        holder.mainLayoutmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.formHistoryDetail(currentItem.getPropertyname(), currentItem.getFlatnumber(), currentItem.getDateIn(), currentItem.getRowId());
            }
        });
    }

    public  String  excelDateStringToJavaDate(String dateExcel){
        if (!dateExcel.equals("")) {
            Long dateInLong = Long.parseLong(dateExcel);
            Date javaDateIn = com.ibuildapp.moveinandmoveout.utils.DateUtils.excelDateToJavaDate(dateInLong);
            Calendar calendar = new GregorianCalendar();
            //dateIn
            calendar.setTime(javaDateIn);
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            mMonth = mMonth + 1;
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            dateFormat = mMonth + "/" + mDay + "/" + mYear;
        } else dateFormat="";
        return dateFormat;
    }

    @Override
    public int getItemCount() {
        return item.size();
    }


    private void runEnterAnimation(View view, int position) {
        if (animationsLocked) return;

        if (android.os.Build.VERSION.SDK_INT <  12)
            return;

        int height = view.getContext().getResources().getDisplayMetrics().heightPixels;

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;

            view.setTranslationY(height);

            view.animate()
                    .translationY(0)
                    .setStartDelay(MoveInandMoveOutContants.ANIMATION_DURATION + 100 * (position))
                    .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                    .setInterpolator(new DecelerateInterpolator(2.f))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            animationsLocked = true;
                        }
                    })
                    .start();
        }
    }
 /*   public void animateTo(List<SpreadsheetItemProp> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }*/


  /*  private void applyAndAnimateRemovals(List<SpreadsheetItemProp> newModels) {
        for (int i = item.size() - 1; i >= 0; i--) {
            final SpreadsheetItemProp model = item.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<SpreadsheetItemProp> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final SpreadsheetItemProp model = newModels.get(i);
            if (!item.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<SpreadsheetItemProp> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final SpreadsheetItemProp model = newModels.get(toPosition);
            final int fromPosition = item.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public SpreadsheetItemProp removeItem(int position) {
        final SpreadsheetItemProp model = item.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, SpreadsheetItemProp model) {
        item.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final SpreadsheetItemProp model = item.remove(fromPosition);
        item.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void setItems(List<SpreadsheetItemProp> items) {
        this.item = items;
    }*/
}