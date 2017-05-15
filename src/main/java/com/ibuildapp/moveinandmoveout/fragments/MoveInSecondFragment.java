package com.ibuildapp.moveinandmoveout.fragments;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ibuildapp.moveinandmoveout.MoveInActivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.fragments.details.OnItemChangedListener;
import com.ibuildapp.moveinandmoveout.fragments.details.SignatureHolder;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.view.DetailsScrollView;

/**
 * Created by web-developer on 13.04.2017.
 */

public class MoveInSecondFragment extends Fragment {
    SpreadsheetItemMove data;
    SpreadsheetItemMove cloneData;
    private DetailsScrollView root;
    public String propName;
    public MoveInActivity context;
    private TextView signatureTitle;
    private SignatureHolder signatureHolder;
    private OnItemChangedListener listener;
    private Button goodFirst;
    private Button fairFirst;
    private Button poorFirst;
    private Button goodSecond;
    private Button fairSecond;
    private Button poorSecond;
    private Button goodThird;
    private Button fairThird;
    private Button poorThird;
    private Button goodFourth;
    private Button fairFourth;
    private Button poorFourth;
    private Button goodFifth;
    private Button fairFifth;
    private Button poorFifth;
    private Button goodSix;
    private Button fairSix;
    private Button poorSix;
    private Button goodSeven;
    private Button fairSeven;
    private Button poorSeven;
    private Button goodEight;
    private Button fairEight;
    private Button poorEight;
    private Button goodNine;
    private Button fairNine;
    private Button poorNine;
    private Button goodTen;
    private Button fairTen;
    private Button poorTen;
    private Button goodEleven;
    private Button fairEleven;
    private Button poorEleven;
    private Button goodTwelve;
    private Button fairTwelve;
    private Button poorTwelve;

    private LinearLayout livingRoom;
    private TextView livingRoomText;
    private LinearLayout diningRoom;
    private TextView diningRoomText;
    private LinearLayout hallway;
    private TextView hallwayText;
    private LinearLayout kitchen;
    private TextView kitchenText;

    public FragmentActivity fragAct;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_movein_second, container, false);
        Bundle bundleMoveInSecond = getArguments();
        fragAct = getActivity();
        data = (SpreadsheetItemMove) bundleMoveInSecond.getSerializable(MoveInandMoveOutContants.ITEM);
        cloneData = SpreadsheetItemMove.newInstanceSecond(data);
        //Проставим значения по умолчанию
        cloneData.setLrDoorsLlocksIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setLrWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setLrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setDrWindowScreensIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setDrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.sethCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.sethWwallsIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.sethLightsSwitchesIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkRefrigeratorIn(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkSinkIn(getResources().getString(R.string.moveinandmoveout_good));

        root = (DetailsScrollView)fragment.findViewById(R.id.moveinandmoveout_movein_second_main);
        root.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        livingRoom = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout1) ;
        livingRoom.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        livingRoomText = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout1_text) ;
        livingRoomText.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        diningRoom = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout2) ;
        diningRoom.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        diningRoomText = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout2_text) ;
        diningRoomText.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        hallway = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout3) ;
        hallway.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        hallwayText = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout3_text) ;
        hallwayText.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        kitchen = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout4) ;
        kitchen.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        kitchenText = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout4_text) ;
        kitchenText.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        goodFirst = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first);
        goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );

                cloneData.setLrDoorsLlocksIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairFirst = (Button)fragment.findViewById(R.id.moveinandmoveout_button_second);
        fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrDoorsLlocksIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorFirst = (Button)fragment.findViewById(R.id.moveinandmoveout_button_third);
        poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );

                cloneData.setLrDoorsLlocksIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodSecond = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_2);
        goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairSecond = (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_2);
        fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorSecond = (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_2);
        poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodThird = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_3);
        goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairThird = (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_3);
        fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorThird = (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_3);
        poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setLrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodFourth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_4);
        goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setDrWindowScreensIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairFourth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_4);
        fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setDrWindowScreensIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorFourth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_4);
        poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setDrWindowScreensIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodFifth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_5);
        goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setDrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairFifth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_5);
        fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setDrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorFifth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_5);
        poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setDrCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodSix = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_6);
        goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairSix= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_6);
        fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorSix= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_6);
        poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethCarpetFlooringIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodSeven = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_7);
        goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethWwallsIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairSeven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_7);
        fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethWwallsIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorSeven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_7);
        poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethWwallsIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodEight = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_8);
        goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethLightsSwitchesIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairEight= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_8);
        fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethLightsSwitchesIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorEight= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_8);
        poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.sethLightsSwitchesIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodNine = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_9);
        goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairNine= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_9);
        fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorNine= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_9);
        poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkWindowsScreensIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodTen = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_10);
        goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkFlooringIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairTen= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_10);
        fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkFlooringIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorTen= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_10);
        poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkFlooringIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodEleven = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_11);
        goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkRefrigeratorIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairEleven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_11);
        fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkRefrigeratorIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorEleven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_11);
        poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkRefrigeratorIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodTwelve = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_12);
        goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        goodTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkSinkIn(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairTwelve= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_12);
        fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        fairTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

                setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkSinkIn(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorTwelve= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_12);
        poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
        poorTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
                poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor5(),StaticData.getXmlParsedData().getColorSkin().getColor5() );
                cloneData.setkSinkIn(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        signatureTitle = (TextView) fragment.findViewById(R.id.moveinandmoveout_edit_signature_title);
        signatureHolder = new SignatureHolder(fragment);
        signatureHolder.initData(cloneData);
        signatureHolder.setListener(new SignatureHolder.SignatureHolderListener() {
            @Override
            public void lockPressed() {
                root.setScrollEnabled(false);
                listener.justLockSwipe();
            }

            @Override
            public void unlockPressed(byte[] newSignature) {
                root.setScrollEnabled(true);
                listener.justUnlockSwipe();
                cloneData.setSignature(newSignature);
                itemChanged();
            }

            @Override
            public void justUnlock() {
                root.setScrollEnabled(true);
                listener.justUnlockSwipe();
            }
        });
        signatureHolder.initColorScheme();
        bundleMoveInSecond.putSerializable(MoveInandMoveOutContants.ITEMMOVESECOND, cloneData);
        return fragment;
    }

    private void itemChanged() {
        if (listener == null)
            return;

        if (!cloneData.cloneEquals(data))
            listener.itemChanged();
        else
            listener.changesCleared();
    }

    public OnItemChangedListener getListener() {
        return listener;
    }

    public void setListener(OnItemChangedListener listener) {
        this.listener = listener;
    }


    public SpreadsheetItemMove getCloneData() {
        return cloneData;
    }

    private void setDrawable( Button button, int background , int  frame ) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA });
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(5);
        drawable.setColor(background);
        drawable.setStroke(3,frame, 0, 5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackground(drawable);
        }
    }
}