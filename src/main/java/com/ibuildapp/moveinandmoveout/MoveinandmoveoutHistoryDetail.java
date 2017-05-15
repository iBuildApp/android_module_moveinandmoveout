package com.ibuildapp.moveinandmoveout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.appbuilder.sdk.android.AppBuilderModuleMainAppCompat;
import com.appbuilder.sdk.android.Widget;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.fragments.details.SignatureHolder;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import com.ibuildapp.moveinandmoveout.view.DetailsScrollView;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class MoveinandmoveoutHistoryDetail extends AppBuilderModuleMainAppCompat {
    String propName;
    String flatNumber;
    String dateIn;
    String title;
    String dateFormat;
    SpreadsheetItemMove data;
    SpreadsheetItemMove dataSign;

    private TextView signatureTitle;
    private SignatureHolder signatureHolder;

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

    private TextView firstMoveOut;
    private TextView secondMoveOut;
    private TextView thirdMoveOut;
    private TextView fourthMoveOut;
    private TextView fiveMoveOut;
    private TextView sixtMoveOut;
    private TextView sevenMoveOut;
    private TextView eightMoveOut;
    private TextView nineMoveOut;
    private TextView tenMoveOut;
    private TextView elevenMoveOut;
    private TextView twelveMoveOut;

    private DetailsScrollView rootView;
    private LinearLayout topPanelMain;

    @Override
    public void create() {
        setContentView(R.layout.moveinandmoveout_unit_history_main_item_detail);
        initContent();
        setTopBarBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        setTopBarTitle(getString(R.string.moveinandmoveout_historydetail));
        setTopBarLeftButtonTextAndColor(getResources().getString(R.string.moveinandmoveout_back),
                ContextCompat.getColor(this, android.R.color.black),
                true, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
        setTopBarTitleColor(ContextCompat.getColor(this, android.R.color.black));

        rootView = (DetailsScrollView)findViewById(R.id.moveinandmoveout_unit_history_detail);
        rootView.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        topPanelMain = (LinearLayout)findViewById(R.id.moveinandmoveout_unit_history_toppanel_detail);
        topPanelMain.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor5());

       //Первый ряд
        firstMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn);
        firstMovein.setText(data.getLrDoorsLlocksIn().substring(0,1).toUpperCase()+data.getLrDoorsLlocksIn().substring(1));
        firstMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(firstMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        firstMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut);
        if (!data.getLrDoorsLocksOut().equals("")) {
            firstMoveOut.setText(data.getLrDoorsLocksOut().substring(0, 1).toUpperCase() + data.getLrDoorsLocksOut().substring(1));
        } else  {
            firstMoveOut.setText("");
        }
        firstMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(firstMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());
        //Второй ряд
        secondMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn2);
        secondMovein.setText(data.getLrWindowsScreensIn().substring(0,1).toUpperCase()+data.getLrWindowsScreensIn().substring(1));
        secondMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(secondMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        secondMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut2);
        if (!data.getLrWindowsScreensOut().equals("")) {
            secondMoveOut.setText(data.getLrWindowsScreensOut().substring(0, 1).toUpperCase() + data.getLrWindowsScreensOut().substring(1));
        } else {
            secondMoveOut.setText("");
        }
        secondMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(secondMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());
        //Третий
        thirdMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn3);
        thirdMovein.setText(data.getLrCarpetFlooringIn().substring(0,1).toUpperCase()+data.getLrCarpetFlooringIn().substring(1));
        thirdMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(thirdMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        thirdMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut3);
        if (!data.getLrCFlooringOut().equals("")) {
            thirdMoveOut.setText(data.getLrCFlooringOut().substring(0, 1).toUpperCase() + data.getLrCFlooringOut().substring(1));
        } else  {
            thirdMoveOut.setText("");
        }
        thirdMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(thirdMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());
        //Четвертый
        fourthMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn4);
        fourthMovein.setText(data.getDrWindowScreensIn().substring(0,1).toUpperCase()+data.getDrWindowScreensIn().substring(1));
        fourthMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(fourthMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        fourthMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut4);
        if (!data.getDrWindowScreensOut().equals("")) {
            fourthMoveOut.setText(data.getDrWindowScreensOut().substring(0, 1).toUpperCase() + data.getDrWindowScreensOut().substring(1));
        } else {
            fourthMoveOut.setText("");
        }
        fourthMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(fourthMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Пятый
        fiveMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn5);
        fiveMovein.setText(data.getDrCarpetFlooringIn().substring(0,1).toUpperCase()+data.getDrCarpetFlooringIn().substring(1));
        fiveMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(fiveMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        fiveMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut5);
        if (!data.getDrCarpetFlooringOut().equals("")) {
            fiveMoveOut.setText(data.getDrCarpetFlooringOut().substring(0, 1).toUpperCase() + data.getDrCarpetFlooringOut().substring(1));
        } else {
            fiveMoveOut.setText("");
        }
        fiveMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(fiveMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Шестой
        sixtMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn6);
        sixtMovein.setText(data.gethCarpetFlooringIn().substring(0,1).toUpperCase()+data.gethCarpetFlooringIn().substring(1));
        sixtMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(sixtMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        sixtMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut6);
        if (!data.gethCarpetFlooringOut().equals("")) {
            sixtMoveOut.setText(data.gethCarpetFlooringOut().substring(0, 1).toUpperCase() + data.gethCarpetFlooringOut().substring(1));
        } else
        {
            sixtMoveOut.setText("");
        }
        sixtMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(sixtMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Седьмой
        sevenMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn7);
        sevenMovein.setText(data.gethWwallsIn().substring(0,1).toUpperCase()+data.gethWwallsIn().substring(1));
        sevenMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(sevenMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        sevenMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut7);
        if (!data.gethWallsOut().equals("")) {
            sevenMoveOut.setText(data.gethWallsOut().substring(0, 1).toUpperCase() + data.gethWallsOut().substring(1));
        } else
        {
            sevenMoveOut.setText("");
        }
        sevenMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(sevenMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Восьмой
        eightMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn8);
        eightMovein.setText(data.gethLightsSwitchesIn().substring(0,1).toUpperCase()+data.gethLightsSwitchesIn().substring(1));
        eightMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(eightMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        eightMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut8);
        if (!data.gethLightsSwitchesOut().equals("")) {
            eightMoveOut.setText(data.gethLightsSwitchesOut().substring(0, 1).toUpperCase() + data.gethLightsSwitchesOut().substring(1));
        } else {
            eightMoveOut.setText("");
        }
        eightMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(eightMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Девятый
        nineMovein = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn9);
        nineMovein.setText(data.getkWindowsScreensIn().substring(0,1).toUpperCase()+data.getkWindowsScreensIn().substring(1));
        nineMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(nineMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        nineMoveOut = (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut9);
        if (!data.getkWindowsScreensOut().equals("")) {
            nineMoveOut.setText(data.getkWindowsScreensOut().substring(0, 1).toUpperCase() + data.getkWindowsScreensOut().substring(1));
        } else {
            nineMoveOut.setText("");
        }
        nineMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(nineMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Десятый
        tenMovein= (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn10);
        tenMovein.setText(data.getkFlooringIn().substring(0,1).toUpperCase()+data.getkWindowsScreensIn().substring(1));
        tenMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(tenMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        tenMoveOut= (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut10);
        if (!data.getkFlooringOut().equals("")) {
            tenMoveOut.setText(data.getkFlooringOut().substring(0, 1).toUpperCase() + data.getkFlooringOut().substring(1));
        } else {
            tenMoveOut.setText("");
        }
        tenMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(tenMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        //Одиннадцатый
        elevenMovein= (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn11);
        elevenMovein.setText(data.getkRefrigeratorIn().substring(0,1).toUpperCase()+data.getkRefrigeratorIn().substring(1));
        elevenMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(elevenMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        elevenMoveOut= (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut11);
        if (!data.getkRefrigeratorOut().equals("")) {
            elevenMoveOut.setText(data.getkRefrigeratorOut().substring(0, 1).toUpperCase() + data.getkRefrigeratorOut().substring(1));
        } else {
            elevenMoveOut.setText("");
        }
        elevenMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(elevenMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());


        //Двенадцатый
        twelveMovein= (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveIn12);
        twelveMovein.setText(data.getkSinkIn().substring(0,1).toUpperCase()+data.getkSinkIn().substring(1));
        twelveMovein.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(twelveMovein,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());

        twelveMoveOut= (TextView)findViewById(R.id.moveinandmoveout_history_detail_moveOut12);
        if (!data.getkSinkOut().equals("")) {
            twelveMoveOut.setText(data.getkSinkOut().substring(0, 1).toUpperCase() + data.getkSinkOut().substring(1));
        } else {
            twelveMoveOut.setText("");
        }
        twelveMoveOut.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor5());
        setDrawableText(twelveMoveOut,StaticData.getXmlParsedData().getColorSkin().getColor1(),StaticData.getXmlParsedData().getColorSkin().getColor5());
        signatureTitle = (TextView)findViewById(R.id.moveinandmoveout_edit_signature_title);
        signatureHolder = new SignatureHolder(rootView);
        signatureHolder.initData(data);
        signatureHolder.lockButton.setVisibility(View.INVISIBLE);

    }
    public void initContent() {
        Intent intent = getIntent();
        Widget widget = (Widget) intent.getSerializableExtra(MoveInandMoveOutContants.EXTRA_WIDGET);
        propName = intent.getStringExtra(MoveInandMoveOutContants.PROPERTYNAME);
        flatNumber = intent.getStringExtra(MoveInandMoveOutContants.FLATNUMBER);
        dateIn =  intent.getStringExtra(MoveInandMoveOutContants.DATEIN);
        if (widget == null) {
            return;
        }
        title = widget.getTitle();
        data = EntityManager.getInstance().getItemsHistoryDetail(propName, flatNumber, dateIn);
        if (data.getSignature()==null) {
            dataSign = EntityManager.getInstance().getSignature(propName, flatNumber, excelDateStringToJavaDate(dateIn));
            if (dataSign.getSignature()!=null) {
                data.setSignature(dataSign.getSignature());
            }
        }



    }
    private void setDrawableText( TextView textV, int background , int  frame ) {
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR,
                new int[] { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA });
        drawable.setShape(GradientDrawable.RECTANGLE);
        //    drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setCornerRadius(5);
        drawable.setColor(background);
        drawable.setStroke(4,frame, 0, 5);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textV.setBackground(drawable);
        }
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
}
