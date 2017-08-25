package com.ibuildapp.moveinandmoveout.adapters;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import com.ibuildapp.moveinandmoveout.MoveInandMoveOutPlugin;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.database.DBHelper;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.viewholders.MainViewHolder;
import java.util.List;



public class PropertyNameAdapter extends CursorRecyclerViewAdapter<MainViewHolder>  {
    private List<SpreadsheetItemProp> item;
    private List<SpreadsheetItemMove> originalItemsMove;
    private int lastAnimatedPosition = -1;
    private boolean animationsLocked = false;

    private MoveInandMoveOutPlugin context;
    public Integer flagStatus=0;
    public  LinearLayout linearlayout;
    public EntityManager manager;
    public Integer filterflag=0;
    public PropertyNameAdapter(MoveInandMoveOutPlugin context, Cursor cursor, EntityManager manager, Integer flagStatus) {
        super(context,cursor);
        this.context = context;
        this.flagStatus=flagStatus;
        this.manager=manager;
    }
    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
             View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.moveinandmoveout_main_list_item, parent, false);
             MainViewHolder holder = new MainViewHolder(v);
             return holder;
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, Cursor cursor) {
        final SpreadsheetItemProp currentItem = DBHelper.parseDistProp(cursor);
        setStatus(cursor);
        //Если только свободные
        if (flagStatus==2) {
            //Получим новую модель
            for (int i = 0; i < item.size(); i++) {
                if (!item.get(i).getStatus().equals(context.getResources().getString(R.string.moveinandmoveout_available_text))) {
                    item.remove(i);
                    i=i-1;
                }

            }
        }
        //Если только снятые
        if (flagStatus==3) {
            //Получим новую модель
            for (int i = 0; i < item.size(); i++) {
                if (item.get(i).getStatus().equals(context.getResources().getString(R.string.moveinandmoveout_available_text))) {
                    item.remove(i);
                    i=i-1;
                }

            }
        }
        if (item.size()>0){
            holder.mainLayoutmain.setVisibility(View.VISIBLE);
            holder.separator.setVisibility(View.VISIBLE);
        holder.propertyname.setText(item.get(0).getPropertyname());
        holder.flatnumber.setText(item.get(0).getFlatnumber());
        holder.monthlyrent.setText(item.get(0).getMonthlyrent());
        holder.status.setText(item.get(0).getStatus());}
        else {
            holder.mainLayoutmain.setVisibility(View.GONE);
            holder.separator.setVisibility(View.GONE);

        }
        if (item.size()>1) {
            holder.mainLayout2.setVisibility(View.VISIBLE);
            holder.separator2.setVisibility(View.VISIBLE);
            holder.flatnumber2.setText(item.get(1).getFlatnumber());
            holder.monthlyrent2.setText( item.get(1).getMonthlyrent());
            holder.status2.setText(item.get(1).getStatus());
        } else {
            holder.mainLayout2.setVisibility(View.GONE);
            holder.separator2.setVisibility(View.GONE);
        }
        if (item.size()>2) {
            holder.mainLayout3.setVisibility(View.VISIBLE);
            holder.separator3.setVisibility(View.VISIBLE);
            holder.flatnumber3.setText(item.get(2).getFlatnumber());
            holder.monthlyrent3.setText( item.get(2).getMonthlyrent());
            holder.status3.setText(item.get(2).getStatus());
        } else {
            holder.mainLayout3.setVisibility(View.GONE);
            holder.separator3.setVisibility(View.GONE);
        }

        holder.nextscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.launchDetailsActivity(currentItem.getPropertyname());
            }
        });
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.launchDetailsAddressActivity(holder.propertyname.getText().toString(), holder.flatnumber.getText().toString());
            }
        });
        holder.mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.launchDetailsAddressActivity(holder.propertyname.getText().toString(), holder.flatnumber2.getText().toString());
            }
        });
        holder.mainLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.launchDetailsAddressActivity(holder.propertyname.getText().toString(), holder.flatnumber3.getText().toString());
            }
        });
    }

 private void setStatus (Cursor cursor) {
     final SpreadsheetItemProp currentItem = DBHelper.parseDistProp(cursor);
     // Получим все итемы по проперти
     item = manager.getItemsbyProp(currentItem.getPropertyname());
     //Проставим статусы
     for (int i = 0; i < item.size(); i++) {
         //Получим ключи
         String propertyName = item.get(i).getPropertyname();
         String flatnumber = item.get(i).getFlatnumber();
         //Побежали по второй таблице искать нужные данные
         originalItemsMove=manager.getItemsMovebyFlatList(propertyName,flatnumber);
         if (originalItemsMove.size()>0)
         {String dateStr = originalItemsMove.get(0).getDateOut();
             if (!dateStr.equals(""))
             { item.get(i).setStatus(context.getResources().getString(R.string.moveinandmoveout_available_text)); }
             else
             { item.get(i).setStatus(originalItemsMove.get(0).getTenantname()); }
         }
         else {
             //Проставляем статус свободно
             item.get(i).setStatus(context.getResources().getString(R.string.moveinandmoveout_available_text));
         }
     }



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
    public void animateTo(List<SpreadsheetItemProp> models) {
        filterflag=filterflag+1;
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<SpreadsheetItemProp> newModels) {
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
    }
}
