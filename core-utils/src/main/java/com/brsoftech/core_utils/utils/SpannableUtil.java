package com.brsoftech.core_utils.utils;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.widget.TextView;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 27/4/16</p>
 * <p>Time: 12:28 PM</p>
 * <p>Project: Grocery</p>
 * <p>Package: com.brsoftech.starterpack.utils</p>
 */
public class SpannableUtil {
    public static void strikethrough(TextView textView) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        spannableString.setSpan(new StrikethroughSpan(), 0, textView.getText().toString().length(), 0);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    public static void strikethrough(TextView textView, int start, int end) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        spannableString.setSpan(new StrikethroughSpan(), start, end, 0);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    public static void getColoredSpan(TextView textView, int color, int start, int end) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        spannableString.setSpan(new ForegroundColorSpan(color), start, end, 0);
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}
