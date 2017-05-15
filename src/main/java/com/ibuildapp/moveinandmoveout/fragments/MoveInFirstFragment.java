package com.ibuildapp.moveinandmoveout.fragments;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.ibuildapp.moveinandmoveout.MoveInActivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.DateUtils;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.NumberUtils;
import com.ibuildapp.moveinandmoveout.utils.SimpleTextWatcher;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by web-developer on 13.04.2017.
 */

public class MoveInFirstFragment extends Fragment {
    SpreadsheetItemMove data;

    public SpreadsheetItemMove getCloneData() {
        return cloneData;
    }

    public SpreadsheetItemMove cloneData;

    private View mainView;
    private TextView propertynameTitle;
    private TextView propertynameValue;

    private TextView unitTitle;
    private TextView unitvalue;

    private TextView tenantnameTitle;
    private EditText tenantnameEdit;
    private View nameSeparator;
    private View dateSeparator;

    private TextView dateTitle;
    private TextView dateEdit;
    private Date dateIn;

    public String propName;
    public  String flatNumber;
    public MoveInActivity context;
    public Bundle bundleMoveIn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_move_in, container, false);
        bundleMoveIn = getArguments();
        data = (SpreadsheetItemMove) bundleMoveIn.getSerializable(MoveInandMoveOutContants.ITEM);
        cloneData = SpreadsheetItemMove.newInstance(data);

        mainView = fragment.findViewById(R.id.moveinandmoveout_movein_main);
        mainView.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());

        propName = data.getPropertyname();
        flatNumber = data.getFlatnumber();

        propertynameTitle = (TextView)fragment.findViewById(R.id.moveinandmoveout_movein_propertyname_title);
        propertynameTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        propertynameValue = (TextView)fragment.findViewById(R.id.moveinandmoveout_movein_propertyname_value);
        propertynameValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        propertynameValue.setText(propName);

        cloneData.setPropertyname(propName);
        cloneData.setFlatnumber(flatNumber);

        unitTitle = (TextView)fragment.findViewById(R.id.moveinandmoveout_movein_unit_tittle);
        unitTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        unitvalue = (TextView)fragment.findViewById(R.id.moveinandmoveout_movein_unit_value);
        unitvalue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        unitvalue.setText(flatNumber);

        tenantnameTitle = (TextView)fragment.findViewById(R.id.moveinandmoveout_edit_tenantname_title);
        tenantnameTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        tenantnameEdit = (EditText)fragment.findViewById(R.id.moveinandmoveout_edit_tennantname_value);
        tenantnameEdit.addTextChangedListener(new SimpleTextWatcher(){
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cloneData.setTenantname(s.toString());
            }
        });
        nameSeparator = fragment.findViewById(R.id.moveinandmoveout_edit_tennantname_separator);
        tenantnameEdit.setOnFocusChangeListener(new EditFocusListener(nameSeparator));

        dateTitle=(TextView)fragment.findViewById(R.id.moveinandmoveout_edit_date_title);
        dateTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        dateEdit=(TextView)fragment.findViewById(R.id.moveinandmoveout_edit_date_value);
        dateEdit.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        dateSeparator = fragment.findViewById(R.id.moveinandmoveout_edit_date_separator);
        dateEdit.setOnFocusChangeListener(new EditFocusListener(dateSeparator));
        String dateInString = data.getDateIn();
        if (dateInString != null) {
            BigDecimal value = NumberUtils.tryParse(dateInString);
            if (value != null) {
                dateIn=DateUtils.excelDateToJavaDate(value);
            }
        }
        dateEdit.setText(DateUtils.toEditDate(getContext(),new java.util.Date()));
        cloneData.setDateIn(DateUtils.toEditDate(getContext(),new java.util.Date()));
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenantnameEdit.clearFocus();
                dateEdit.requestFocus();
                tenantnameEdit.setFocusableInTouchMode(false);
                tenantnameEdit.setFocusable(false);
                tenantnameEdit.setFocusableInTouchMode(true);
                tenantnameEdit.setFocusable(true);

                final Calendar currentCalendar = Calendar.getInstance();
                int year = currentCalendar.get(Calendar.YEAR);
                int month = currentCalendar.get(Calendar.MONTH);
                int day = currentCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        cloneData.setDateIn(selectedCalendar.getTime().toString());

                        dateEdit.setText(DateUtils.toEditDate(getContext(), selectedCalendar.getTime()));
                        cloneData.setDateIn(dateEdit.getText().toString());
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        bundleMoveIn.putSerializable(MoveInandMoveOutContants.ITEMMOVE, cloneData);
        return fragment;
    }

    public boolean isNameEntered() {
        return !TextUtils.isEmpty(tenantnameEdit.getText().toString());
    }

    public void underlineName() {
        nameSeparator.setBackgroundColor(Color.parseColor("#FF0000"));
    }

    public SpreadsheetItemMove getData() {
        return data;

    }

    public class EditFocusListener implements View.OnFocusChangeListener{

        private float focusedHeight = 2 * MoveInFirstFragment.this.getResources().getDisplayMetrics().density;
        private View animateSeparator;

        public EditFocusListener(View animateSeparator){
            this.animateSeparator = animateSeparator;
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                animateShow();
            else animateHide();
        }

        private void animateHide(){
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                animateSeparator.animate().scaleY(1f)
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new AccelerateInterpolator());

                ObjectAnimator anim = ObjectAnimator.ofObject(animateSeparator,
                        "backgroundColor",
                        new ArgbEvaluator(),
                        StaticData.getXmlParsedData().getColorSkin().getColor5(), Color.parseColor("#1A000000"));

                anim.setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION);
                anim.start();
            }
        }
        private void animateShow(){
            if (android.os.Build.VERSION.SDK_INT >= 12) {
                animateSeparator.animate().scaleY(focusedHeight)
                        .setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION)
                        .setInterpolator(new DecelerateInterpolator());

                ObjectAnimator anim = ObjectAnimator.ofObject(animateSeparator,
                        "backgroundColor",
                        new ArgbEvaluator(),
                        Color.parseColor("#1A000000"), StaticData.getXmlParsedData().getColorSkin().getColor5());

                anim.setDuration(MoveInandMoveOutContants.SHORT_ANIMATION_DURATION);
                anim.start();
            }
        }
    }


}
