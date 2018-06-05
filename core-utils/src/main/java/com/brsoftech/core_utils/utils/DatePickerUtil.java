package com.brsoftech.core_utils.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Kartik Sharma @ B.R. Softech on 8/4/16.
 */
public class DatePickerUtil implements DatePickerDialog.OnDateSetListener {
    private Context mContext;
    private OnDateListener mOnDateListener;

    public DatePickerUtil create(Context context) {
        mContext = context;
        return this;
    }

    public DatePickerUtil callback(OnDateListener onDateListener) {
        mOnDateListener = onDateListener;
        return this;
    }

    public void show() {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog
        new DatePickerDialog(mContext, this, year, month, day).show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);
        Date date = calendar.getTime();
        mOnDateListener.onSuccess(date);
    }


    public interface OnDateListener {
        void onSuccess(Date date);
    }
}
