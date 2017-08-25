package com.ibuildapp.moveinandmoveout.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import com.ibuildapp.moveinandmoveout.MoveOutActivity;
import com.ibuildapp.moveinandmoveout.R;
import com.ibuildapp.moveinandmoveout.database.EntityManager;
import com.ibuildapp.moveinandmoveout.model.SpreadsheetItemMove;
import com.ibuildapp.moveinandmoveout.utils.DateUtils;
import com.ibuildapp.moveinandmoveout.utils.MoveInandMoveOutContants;
import com.ibuildapp.moveinandmoveout.utils.NumberUtils;
import com.ibuildapp.moveinandmoveout.utils.StaticData;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by web-developer on 17.04.2017.
 */

public class MoveOutFirstFragment extends Fragment {
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
    private TextView tenantnameEdit;

    private TextView dateTitle;
    private TextView dateEdit;
    private Date dateIn_;

    public String propName;
    public  String flatNumber;
    public String tenantName;
    public  String dateIn;
    public MoveOutActivity context;
    public Bundle bundleMoveIn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragment = LayoutInflater.from(getActivity()).inflate(R.layout.moveinandmoveout_moveout, container, false);
        bundleMoveIn = getArguments();
        data = (SpreadsheetItemMove) bundleMoveIn.getSerializable(MoveInandMoveOutContants.ITEM);
        cloneData = SpreadsheetItemMove.newInstance(data);
        mainView = fragment.findViewById(R.id.moveinandmoveout_moveout_main);
        mainView.setBackgroundColor(StaticData.getXmlParsedData().getColorSkin().getColor1());
        propName = data.getPropertyname();
        flatNumber = data.getFlatnumber();
        tenantName = data.getTenantname();
        cloneData = EntityManager.getInstance().getDataMovebyFlatTenantName(propName,flatNumber,tenantName);
        dateIn = cloneData.getDateIn();

        propertynameTitle = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_propertyname_title);
        propertynameTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        propertynameValue = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_propertyname_value);
        propertynameValue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        propertynameValue.setText(propName);

        cloneData.setPropertyname(propName);
        cloneData.setFlatnumber(flatNumber);
        cloneData.setTenantname(tenantName);
        cloneData.setDateIn(dateIn);

        unitTitle = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_unit_tittle);
        unitTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        unitvalue = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_unit_value);
        unitvalue.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        unitvalue.setText(flatNumber);

        tenantnameTitle = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_tenentname_tittle);
        tenantnameTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        tenantnameEdit = (TextView)fragment.findViewById(R.id.moveinandmoveout_moveout_tenentname_value);
        tenantnameEdit.setText(tenantName);


        dateTitle=(TextView)fragment.findViewById(R.id.moveinandmoveout_edit_date_title_moveout);
        dateTitle.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        dateEdit=(TextView)fragment.findViewById(R.id.moveinandmoveout_edit_date_value_moveout);
        dateEdit.setTextColor(StaticData.getXmlParsedData().getColorSkin().getColor3());
        String dateInString = data.getDateOut();
        if (dateInString != null) {
            BigDecimal value = NumberUtils.tryParse(dateInString);
            if (value != null) {
                dateIn_= DateUtils.excelDateToJavaDate(value);
            }
        }
        dateEdit.setText(DateUtils.toEditDate(getContext(),new java.util.Date()));
        cloneData.setDateOut(DateUtils.toEditDate(getContext(),new java.util.Date()));
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar currentCalendar = Calendar.getInstance();
                int year = currentCalendar.get(Calendar.YEAR);
                int month = currentCalendar.get(Calendar.MONTH);
                int day = currentCalendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(year, monthOfYear, dayOfMonth);
                        cloneData.setDateOut(selectedCalendar.getTime().toString());
                        dateEdit.setText(DateUtils.toEditDate(getContext(), selectedCalendar.getTime()));
                        cloneData.setDateOut(dateEdit.getText().toString());
                    }
                }, year, month, day);
                dialog.show();
            }
        });

        bundleMoveIn.putSerializable(MoveInandMoveOutContants.ITEMMOVE, cloneData);
        return fragment;
    }

}
