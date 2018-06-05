package com.brsoftech.core_utils.utils;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 2/7/16</p>
 * <p>Time: 11:20 AM</p>
 * <p>Project: Planbeep</p>
 */
public class TimePickerUtil implements TimePickerDialog.OnTimeSetListener {
    private Context mContext;
    private OnTimeListener mOnTimeListener;
    private boolean mIs24Hours;

    public TimePickerUtil create(Context context) {
        mContext = context;
        return this;
    }

    public TimePickerUtil callback(OnTimeListener onTimeListener) {
        mOnTimeListener = onTimeListener;
        return this;
    }

    public TimePickerUtil is24Hours(boolean is24Hours) {
        mIs24Hours = is24Hours;
        return this;
    }

    public void show() {
        // Use the current time as the default time in the picker
        final Calendar c = Calendar.getInstance();
        int hours = c.get(Calendar.HOUR_OF_DAY);
        int minutes = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog
        new TimePickerDialog(mContext,
                this,
                hours,
                minutes,
                mIs24Hours).show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
        mOnTimeListener.onSuccess(hours, minutes);
    }

    public interface OnTimeListener {
        void onSuccess(int hours, int minutes);
    }
}
