package com.ibuildapp.moveinandmoveout.fragments;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ibuildapp.moveinandmoveout.AddressDetailAtivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemProp;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;


public class AddressDetailFragment extends Fragment  {
    private SpreadsheetItemProp item;
    private View propertynameLayout;
    private TextView propertynameTitle;
    private TextView propertynameValue;

    private View flatnumberLayout;
    private TextView flatnumberTitle;
    private TextView flatnumberValue;

    private View addressLayout;
    private TextView addressTitle;
    private TextView addressValue;

    private View monthlyrentLayout;
    private TextView monthlyrentTitle;
    private TextView monthlyrentValue;

    private View tenantLayout;
    private TextView tenantTitle;
    private TextView tenantValue;

    private Button moveinandmoveout;
    private Button moveinandmoveout_history;

    private Button map;


    @Override
    public AddressDetailAtivity getContext() {
        return context;
    }

    public void setContext(AddressDetailAtivity context) {
        this.context = context;
    }

    private AddressDetailAtivity context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_address_detail_item, container, false);
        Bundle bundle = getArguments();
        item = (SpreadsheetItemProp) bundle.getSerializable(MoveInandMoveOutContants.ITEM);

        propertynameLayout = fragment.findViewById(R.id.moveinandmoveout_adress_details_item_propertyname_layout);
        propertynameTitle = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_proprtyname_title);
        propertynameValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_proprtyname_title_value);

        flatnumberLayout = fragment.findViewById(R.id.moveinandmoveout_adress_details_item_flatnumber_layout);
        flatnumberTitle = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_flatnumber_title);
        flatnumberValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_flatnumber__title_value);

        addressLayout = fragment.findViewById(R.id.moveinandmoveout_adress_details_item_address_layout);
        addressTitle = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_address_title);
        addressValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_address_title_value);

        monthlyrentLayout = fragment.findViewById(R.id.moveinandmoveout_adress_details_item_monthlyrent_layout);
        monthlyrentTitle = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_monthlyrent_title);
        monthlyrentValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_monthlyrent_title_value);

        tenantLayout = fragment.findViewById(R.id.moveinandmoveout_adress_details_item_tenant_layout);
        tenantTitle = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_tenant_title);
        tenantValue = (TextView) fragment.findViewById(R.id.moveinandmoveout_adress_details_item_tenant_title_value);

        //Кнопки
        moveinandmoveout = (Button)fragment.findViewById(R.id.moveinandmoveout_button);
        moveinandmoveout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (item.getStatus().equals(getResources().getString(R.string.moveinandmoveout_available_text))) {
                    context.formMoveInActivity(propertynameValue.getText().toString(), flatnumberValue.getText().toString());
                } else
                {
                    context.formMoveOutActivity(propertynameValue.getText().toString(), flatnumberValue.getText().toString(), tenantValue.getText().toString());
                }
            }
        });
        moveinandmoveout_history = (Button)fragment.findViewById(R.id.moveinandmoveout_button_history);
        moveinandmoveout_history.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                context.formMoveHistory(propertynameValue.getText().toString(), flatnumberValue.getText().toString(), tenantValue.getText().toString());
            }
        });
        //Карта
        map = (Button) fragment.findViewById(R.id.moveinandmoveout_edit_address_map_text);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              context.formMap(addressValue.getText().toString());
            }
        });
        initColorScheme();
        initData();
        return fragment;
    }

    private void initColorScheme() {
        propertynameTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        propertynameValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());

        flatnumberTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        flatnumberValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());

        addressTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        addressValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());

        monthlyrentTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        monthlyrentValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());


        tenantTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        tenantValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());

        moveinandmoveout.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());
        moveinandmoveout_history.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        map.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());


    }

    private void setDrawable( int background , int  frame ) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA });
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(5);
        drawable.setColor(background);
        drawable.setStroke(3,frame, 0, 5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            moveinandmoveout.setBackground(drawable);
        }
    }

    public void update(final SpreadsheetItemProp item) {
        this.item = item;
        initData();
    }

    private void initData() {
        if (!TextUtils.isEmpty(item.getPropertyname()))
            propertynameLayout.setVisibility(View.VISIBLE);
        else propertynameLayout.setVisibility(View.GONE);

        propertynameValue.setText(item.getPropertyname());

        if (!TextUtils.isEmpty(item.getFlatnumber()))
            flatnumberLayout.setVisibility(View.VISIBLE);
        else flatnumberLayout.setVisibility(View.GONE);

        flatnumberValue.setText(item.getFlatnumber());

        if (!TextUtils.isEmpty(item.getAddress()))
            addressLayout.setVisibility(View.VISIBLE);
        else addressLayout.setVisibility(View.GONE);

        addressValue.setText(item.getAddress());

        if (!TextUtils.isEmpty(item.getMonthlyrent()))
            monthlyrentLayout.setVisibility(View.VISIBLE);
        else monthlyrentLayout.setVisibility(View.GONE);

        monthlyrentValue.setText(item.getMonthlyrent());

        if (!TextUtils.isEmpty(item.getStatus()))
            tenantLayout.setVisibility(View.VISIBLE);
        else tenantLayout.setVisibility(View.GONE);

        tenantValue.setText(item.getStatus());
        if (item.getStatus().equals(getResources().getString(R.string.moveinandmoveout_available_text))) {
            moveinandmoveout.setText(getResources().getString(R.string.moveinandmoveout_movein));
        } else {
            moveinandmoveout.setText(getResources().getString(R.string.moveinandmoveout_moveOut));
        }


    }

}
