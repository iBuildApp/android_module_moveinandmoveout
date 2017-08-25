package com.ibuildapp.moveinandmoveout.utils;


import android.content.Context;
import com.ibuildapp.moveinandmoveout.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public abstract class DateUtils {
    public  enum DateFormats{
        NULL(null, ""), SERIAL(null , "SERIAL"),
        ENGLISH_FULL_YEAR(Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4}$"), "MM/dd/yyyy"),
        ENGLISH_SHORT_YEAR (Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{2}$"), "MM/dd/yy"),
        EUR_FULL_YEAR(Pattern.compile("^\\d{1,2}\\.\\d{1,2}\\.\\d{4}$"), "dd.MM.yyyy"),
        EUR_SHORT_YEAR(Pattern.compile("^\\d{1,2}\\.\\d{1,2}\\.\\d{2}$"), "dd.MM.yy");

        private Pattern regexTemplate;
        private String dateTemplate;

        DateFormats(Pattern regexTemplate, String dateTemplate ){
            this.regexTemplate = regexTemplate;
            this.dateTemplate = dateTemplate;
        }

        public Pattern getRegexTemplate() {
            return regexTemplate;
        }

        public String getDateTemplate() {
            return dateTemplate;
        }
    }

    private static final List<DateFormats> DATE_FORMAT_REGEXPS = new ArrayList<DateFormats>() {{
        add(DateFormats.ENGLISH_FULL_YEAR);
        add(DateFormats.ENGLISH_SHORT_YEAR);
        add(DateFormats.EUR_FULL_YEAR);
        add(DateFormats.EUR_SHORT_YEAR);

    }};

    private static SimpleDateFormat DETAILS_DATE_TIME_FORMAT;
    private static SimpleDateFormat EDIT_DATE_FORMAT;

    public static DateFormats determineDateFormat(String dateString) {
        for (DateFormats regexp : DATE_FORMAT_REGEXPS) {
            if (regexp.getRegexTemplate().matcher(dateString).matches()) {
                return regexp;
            }
        }
        return DateFormats.NULL; // Unknown format.
    }

    public static String toDetailsDate(Context context, Date date) {
        if (DETAILS_DATE_TIME_FORMAT == null)
            DETAILS_DATE_TIME_FORMAT = new SimpleDateFormat(context.getString(R.string.moveinandmoveout_details_date_format), Locale.getDefault());

        return DETAILS_DATE_TIME_FORMAT.format(date);
    }
    public static String toEditDate(Context context, Date date) {
        if (EDIT_DATE_FORMAT == null)
            EDIT_DATE_FORMAT = new SimpleDateFormat(context.getString(R.string.moveinandmoveout_edit_date_format), Locale.getDefault());

        return EDIT_DATE_FORMAT.format(date);
    }

    public static Date excelDateToJavaDate(BigDecimal serialDate){
        double doubleSerial = serialDate.doubleValue();

        double utcDays = Math.floor(doubleSerial) - 25569;
        double utcValue = utcDays * 86400;

        Calendar dateInfo = Calendar.getInstance();
        dateInfo.setTimeInMillis((long) (utcValue * 1000));

        double fractionalDay = doubleSerial - Math.floor(doubleSerial) + 0.0000001;
        double totalSeconds = Math.floor(86400 * fractionalDay);

        double seconds = totalSeconds % 60;
        totalSeconds -= seconds;
        double hours = Math.floor(totalSeconds / (60 * 60));
        double minutes = Math.floor(totalSeconds / 60) % 60;
        Calendar resultDate = Calendar.getInstance();
        resultDate.set(dateInfo.get(Calendar.YEAR), dateInfo.get(Calendar.MONTH),
                dateInfo.get(Calendar.DAY_OF_MONTH), (int)hours, (int)minutes, (int)seconds);
        return resultDate.getTime();
    }

    public static Date excelDateToJavaDate(float serialDate){

        float utcDays = (float) (Math.floor(serialDate) - 25569);
        float utcValue = utcDays * 86400;

        Calendar dateInfo = Calendar.getInstance();
        dateInfo.setTimeInMillis((long) (utcValue * 1000));

        float fractionalDay = (float) (serialDate - Math.floor(serialDate) + 0.0000001);
        float totalSeconds = (float) Math.floor(86400 * fractionalDay);

        float seconds = totalSeconds % 60;
        totalSeconds -= seconds;
        float hours = (float) Math.floor(totalSeconds / (60 * 60));
        float minutes = (float) (Math.floor(totalSeconds / 60) % 60);
        Calendar resultDate = Calendar.getInstance();
        resultDate.set(dateInfo.get(Calendar.YEAR), dateInfo.get(Calendar.MONTH),
                dateInfo.get(Calendar.DAY_OF_MONTH), (int)hours, (int)minutes, (int)seconds);
        return resultDate.getTime();
    }

    public static Date tryParse(String dateString, String format) {
        try {
            return new SimpleDateFormat(format, Locale.getDefault()).parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static BigDecimal javaDateToExcelSerial(Date date){
        Calendar dateInfo = Calendar.getInstance();
        dateInfo.setTime(date);

        Calendar onlyDays = Calendar.getInstance();
        onlyDays.set(Calendar.YEAR, dateInfo.get(Calendar.YEAR));
        onlyDays.set(Calendar.MONTH, dateInfo.get(Calendar.MONTH));
        onlyDays.set(Calendar.DAY_OF_MONTH, dateInfo.get(Calendar.DAY_OF_MONTH));
        onlyDays.set(Calendar.HOUR_OF_DAY, 0);
        onlyDays.set(Calendar.MINUTE, 0);
        onlyDays.set(Calendar.SECOND, 0);

        long mills = onlyDays.getTime().getTime()/86400000;
        float result = (float) (mills + 25570);

        float seconds = dateInfo.get(Calendar.SECOND);
        float minutes = dateInfo.get(Calendar.MINUTE);
        float hours = dateInfo.get(Calendar.HOUR_OF_DAY);

        float totalSeconds = seconds + minutes * 60 + hours * 3600;
        BigDecimal dec = new BigDecimal(totalSeconds);
        dec = dec.divide(new BigDecimal(86400), 5, RoundingMode.HALF_UP );
        dec = dec.add(new BigDecimal(result));
        return dec;
    }
    public static BigDecimal javaDateToExcelSerialMov(Date date) {


        Calendar dateInfo = Calendar.getInstance();
        dateInfo.setTime(date);

        Calendar onlyDays = Calendar.getInstance();
        onlyDays.set(Calendar.YEAR, dateInfo.get(Calendar.YEAR));
        onlyDays.set(Calendar.MONTH, dateInfo.get(Calendar.MONTH));
        onlyDays.set(Calendar.DAY_OF_MONTH, dateInfo.get(Calendar.DAY_OF_MONTH));
        onlyDays.set(Calendar.HOUR_OF_DAY, 0);
        onlyDays.set(Calendar.MINUTE, 0);
        onlyDays.set(Calendar.SECOND, 0);

        long mills = onlyDays.getTime().getTime() / 86400000;
        float result = (float) (mills + 25570);

        float seconds = dateInfo.get(Calendar.SECOND);
        float minutes = dateInfo.get(Calendar.MINUTE);
        float hours = dateInfo.get(Calendar.HOUR_OF_DAY);

        float totalSeconds = seconds + minutes * 60 + hours * 3600;
        BigDecimal dec = new BigDecimal(totalSeconds);
        dec = dec.divide(new BigDecimal(86400), 5, RoundingMode.HALF_UP);
        dec = dec.add(new BigDecimal(result));
        return dec;
    }

}
