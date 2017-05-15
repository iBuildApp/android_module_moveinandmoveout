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
import com.ibuildapp.moveinandmoveout.MoveOutActivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.fragments.details.OnItemChangedListener;
import com.ibuildapp.moveinandmoveout.fragments.details.SignatureHolder;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.view.DetailsScrollView;
import android.graphics.Color;

/**
 * Created by web-developer on 17.04.2017.
 */

public class MoveOutSecondFragment extends Fragment {
    SpreadsheetItemMove data;
    SpreadsheetItemMove cloneData;
    private DetailsScrollView root;
    public String propName;
    public MoveOutActivity context;
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

    private TextView firstMovein;
    private TextView secondMovein;
    private TextView thirdMovein;
    private TextView fourthMovein;
    private TextView fiveMovein;
    private TextView sixtMovein;
    private TextView sevenMovein;
    private TextView eightMovein;
    private TextView nineMovein;
    private TextView tenMovein;
    private TextView elevenMovein;
    private TextView twelveMovein;

    private LinearLayout livingRoomOut;
    private TextView livingRoomTextOut;
    private LinearLayout diningRoomOut;
    private TextView diningRoomTextOut;
    private LinearLayout hallwayOut;
    private TextView hallwayTextOut;
    private LinearLayout kitchenOut;
    private TextView kitchenTextOut;
    public FragmentActivity fragAct;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_moveout_second, container, false);
        Bundle bundleMoveInSecond = getArguments();
        fragAct = getActivity();
        data = (SpreadsheetItemMove) bundleMoveInSecond.getSerializable(MoveInandMoveOutContants.ITEM);
        firstMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data);
        firstMovein.setText(data.getLrDoorsLlocksIn().substring(0,1).toUpperCase()+data.getLrDoorsLlocksIn().substring(1));
      //  firstMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        setDrawableText(firstMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        secondMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data2);
        secondMovein.setText(data.getLrWindowsScreensIn().substring(0,1).toUpperCase()+data.getLrWindowsScreensIn().substring(1));
     //   secondMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(secondMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        thirdMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data3);
        thirdMovein.setText(data.getLrCarpetFlooringIn().substring(0,1).toUpperCase()+data.getLrCarpetFlooringIn().substring(1));
      //  thirdMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(thirdMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        fourthMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data4);
        fourthMovein.setText(data.getDrWindowScreensIn().substring(0,1).toUpperCase()+data.getDrWindowScreensIn().substring(1));
     //   fourthMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(fourthMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        fiveMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data5);
        fiveMovein.setText(data.getDrCarpetFlooringIn().substring(0,1).toUpperCase()+data.getDrCarpetFlooringIn().substring(1));
     //   fiveMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(fiveMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        sixtMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data6);
        sixtMovein.setText(data.gethCarpetFlooringIn().substring(0,1).toUpperCase()+data.gethCarpetFlooringIn().substring(1));
     //   sixtMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(sixtMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        sevenMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data7);
        sevenMovein.setText(data.gethWwallsIn().substring(0,1).toUpperCase()+data.gethWwallsIn().substring(1));
      //  sevenMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(sevenMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        eightMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data8);
        eightMovein.setText(data.gethLightsSwitchesIn().substring(0,1).toUpperCase()+data.gethLightsSwitchesIn().substring(1));
      //  eightMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(eightMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        nineMovein = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data9);
        nineMovein.setText(data.getkWindowsScreensIn().substring(0,1).toUpperCase()+data.getkWindowsScreensIn().substring(1));
      //  nineMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(nineMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        tenMovein= (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data10);
        tenMovein.setText(data.getkFlooringIn().substring(0,1).toUpperCase()+data.getkFlooringIn().substring(1));
     //   tenMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(tenMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        elevenMovein= (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data11);
        elevenMovein.setText(data.getkRefrigeratorIn().substring(0,1).toUpperCase()+data.getkRefrigeratorIn().substring(1));
     //   elevenMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(elevenMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        twelveMovein= (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_secondfragment_movein_data12);
        twelveMovein.setText(data.getkSinkIn().substring(0,1).toUpperCase()+data.getkSinkIn().substring(1));
     //   twelveMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawableText(twelveMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2());

        cloneData = SpreadsheetItemMove.newInstance(data);
        cloneData.setLrDoorsLocksOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setLrWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setLrCFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setDrWindowScreensOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setDrCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.sethCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.sethWallsOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.sethLightsSwitchesOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkRefrigeratorOut(getResources().getString(R.string.moveinandmoveout_good));
        cloneData.setkSinkOut(getResources().getString(R.string.moveinandmoveout_good));

        root = (DetailsScrollView)fragment.findViewById(R.id.moveinandmoveout_moveout_second_main);
        root.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        livingRoomOut = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout1_out) ;
        livingRoomOut.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        livingRoomTextOut = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout1_text_out) ;
        livingRoomTextOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        diningRoomOut = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout2_out) ;
        diningRoomOut.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        diningRoomTextOut = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout2_text_out) ;
        diningRoomTextOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        hallwayOut = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout3_out) ;
        hallwayOut.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        hallwayTextOut = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout3_text_out) ;
        hallwayTextOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        kitchenOut = (LinearLayout)fragment.findViewById(R.id.moveinsecondfragmentlayout4_out) ;
        kitchenOut.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        kitchenTextOut = (TextView)fragment.findViewById(R.id.moveinsecondfragmentlayout4_text_out) ;
        kitchenTextOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        goodFirst = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_moveout);
        goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrDoorsLocksOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairFirst = (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_moveout);
        fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrDoorsLocksOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorFirst = (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_moveout);
        poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorFirst.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFirst, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFirst, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrDoorsLocksOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodSecond = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_2_moveout);
        goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairSecond = (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_2_moveout);
        fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorSecond = (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_2_moveout);
        poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorSecond.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSecond, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSecond, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodThird = (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_3_moveout);
        goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrCFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairThird = (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_3_moveout);
        fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrCFlooringOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorThird = (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_3_moveout);
        poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorThird.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairThird, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorThird, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setLrCFlooringOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodFourth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_4_moveout);
        goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setDrWindowScreensOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairFourth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_4_moveout);
        fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setDrWindowScreensOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorFourth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_4_moveout);
        poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorFourth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFourth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFourth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setDrWindowScreensOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodFifth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_5_moveout);
        goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setDrCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairFifth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_5_moveout);
        fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setDrCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorFifth= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_5_moveout);
        poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorFifth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorFifth.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairFifth, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorFifth, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setDrCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodSix= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_6_moveout);
        goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairSix= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_6_moveout);
        fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorSix= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_6_moveout);
        poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorSix.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSix, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSix, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethCarpetFlooringOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodSeven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_7_moveout);
        goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethWallsOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairSeven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_7_moveout);
        fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethWallsOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorSeven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_7_moveout);
        poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorSeven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairSeven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorSeven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethWallsOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodEight= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_8_moveout);
        goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethLightsSwitchesOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairEight= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_8_moveout);
        fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethLightsSwitchesOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorEight= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_8_moveout);
        poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorEight.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairEight, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorEight, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.sethLightsSwitchesOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodNine= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_9_moveout);
        goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairNine= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_9_moveout);
        fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorNine= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_9_moveout);
        poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorNine.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairNine, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorNine, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkWindowsScreensOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodTen= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_10_moveout);
        goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkFlooringOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairTen= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_10_moveout);
        fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkFlooringOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorTen= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_10_moveout);
        poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorTen.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairTen, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorTen, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkFlooringOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodEleven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_11_moveout);
        goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkRefrigeratorOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairEleven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_11_moveout);
        fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkRefrigeratorOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorEleven= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_11_moveout);
        poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorEleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorEleven.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairEleven, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorEleven, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkRefrigeratorOut(getResources().getString(R.string.moveinandmoveout_poor));
            }
        });
        goodTwelve= (Button)fragment.findViewById(R.id.moveinandmoveout_button_first_12_moveout);
        goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        goodTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkSinkOut(getResources().getString(R.string.moveinandmoveout_good));
            }
        });
        fairTwelve= (Button)fragment.findViewById(R.id.moveinandmoveout_button_second_12_moveout);
        fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        fairTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
                poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());

                setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkSinkOut(getResources().getString(R.string.moveinandmoveout_fair));
            }
        });
        poorTwelve= (Button)fragment.findViewById(R.id.moveinandmoveout_button_third_12_moveout);
        poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
        setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
        poorTwelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goodTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                fairTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor2());
                poorTwelve.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

                setDrawable(goodTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(fairTwelve, StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                setDrawable(poorTwelve, StaticData.getXmlParsedData().getColorSkin().getColor2(),StaticData.getXmlParsedData().getColorSkin().getColor2() );
                cloneData.setkSinkOut(getResources().getString(R.string.moveinandmoveout_poor));
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
        //    drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setCornerRadius(5);
        drawable.setColor(background);
        drawable.setStroke(3,frame, 0, 5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            button.setBackground(drawable);
        }
    }

    private void setDrawableText( TextView textV, int background , int  frame ) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA });
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(5);
        drawable.setColor(background);
        int colorFrame = Color.parseColor("#A9A9A9"); //#808080
        drawable.setStroke(3, colorFrame, 0, 5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textV.setBackground(drawable);
        }
    }
}