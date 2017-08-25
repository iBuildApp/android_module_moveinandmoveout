package com.ibuildapp.moveinandmoveout.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbuilder.sdk.android.Widget;
import com.ibuildapp.moveinandmoveout.AddressDetailAtivity;
import com.ibuildapp.moveinandmoveout.PropertyDetailActivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.adapters.PropertyDetailAdapterRecycler;
import com.ibuildapp.moveinandmoveout.adapters.PropertyNameAdapter;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by web-developer on 07.04.2017.
 */

public class PropertyDetailFragment extends Fragment {
    private SpreadsheetItemProp item;
    private TextView propertynameValue;
    private TextView addressValue;
    private RecyclerView mainList;
    private LinearLayout unit;
    private TextView unit_text;
    public EntityManager manager;
    public  String propName;
    public PropertyDetailActivity context;
    private List<SpreadsheetItemProp> propertyDetailItem;
    public FragmentActivity actfragment;
    private List<SpreadsheetItemMove> originalItemsMove;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_property_detail_item, container, false);
        actfragment = getActivity();
        Bundle bundle = getArguments();
        item = (SpreadsheetItemProp) bundle.getSerializable(MoveInandMoveOutContants.ITEM);
        propertynameValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_property_detail_propertyname);
        addressValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_property_detail_address);
        mainList = (RecyclerView)fragment.findViewById(R.id.moveinandmoveout_property_details_recyclerview);
        mainList.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        unit = (LinearLayout)fragment.findViewById(R.id.moveinandmoveout_property_detail_UNIT);
        unit_text = (TextView)fragment.findViewById(R.id.moveinandmoveout_property_detail_UNIT_text);
        manager =   EntityManager.getInstance();
        propName = item.getPropertyname();
        initColorScheme();
        initData();
        return fragment;
    }

    private void initColorScheme() {
        propertynameValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        addressValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        unit.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        unit_text.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
    }

    public void update(final SpreadsheetItemProp item) {
        this.item = item;
        initData();
    }

    private void initData() {
        propertynameValue.setText(item.getPropertyname());
        addressValue.setText(item.getAddress());

    }
    private void setContainer() {
        Schedulers.io().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                propertyDetailItem = manager.getItemsbyProp(propName);
                //Проставим статусы
                for (int i = 0; i < propertyDetailItem.size(); i++) {
                    //Получим ключи
                    String flatnumber = propertyDetailItem.get(i).getFlatnumber();
                    String propName = propertyDetailItem.get(i).getPropertyname();

                        //берем из второй таблицы последнюю актуальную запись
                        originalItemsMove = EntityManager.getInstance().getItemsMovebyFlatList(propName, flatnumber);
                        if (originalItemsMove.size() > 0) {
                            String dateStr = originalItemsMove.get(0).getDateOut();
                            if (!dateStr.equals("")) {
                                propertyDetailItem.get(i).setStatus(getResources().getString(R.string.moveinandmoveout_available_text));
                            } else {
                                propertyDetailItem.get(i).setStatus(originalItemsMove.get(0).getTenantname());
                            }
                        } else {
                            //Проставляем статус свободно
                            propertyDetailItem.get(i).setStatus(getResources().getString(R.string.moveinandmoveout_available_text));
                        }
                }

                AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
                    @Override
                    public void call() {
                        final PropertyDetailAdapterRecycler adapter = new PropertyDetailAdapterRecycler(context,propertyDetailItem, manager);
                        mainList.setAdapter(adapter);
                    }
                });
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setContainer();
    }


}
