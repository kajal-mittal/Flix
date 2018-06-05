package com.brsoftech.core_utils.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 3/5/16</p>
 * <p>Time: 9:42 AM</p>
 * <p>Project: Grocery</p>
 * <p>Package: com.brsoftech.starterpack.utils</p>
 */
public class Validator {
    /**
     * <p>Validate editText values and check if any of them are empty</p>
     * <p>Make sure, you are passing the views in a sequential manner,
     * otherwise the focus will be triggered on a random view</p>
     *
     * @param editTexts array of editTexts to validate
     * @param errorMsg  error message to display if editText is empty
     * @return true if empty, else false
     */
    public static boolean validateEmpty(EditText[] editTexts, String errorMsg) {
        int status = 0;
        editTexts[0].requestFocus();
        //StandardUtil.showKeyboard(context);
        for (EditText editText : editTexts) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                //this generates weird glitch if the edit texts are in a scrollView
                //editText.setError(errorMsg);
                status = 1;
            }
        }
        return status != 0;
    }

    public static boolean validateEmpty(EditText... editTexts) {
        int status = 0;
        editTexts[0].requestFocus();
        for (EditText editText : editTexts) {
            if (TextUtils.isEmpty(editText.getText().toString())) {
                status = 1;
            }
        }
        return status != 0;
    }

    public static boolean validateEmpty(String... values) {
        int status = 0;
        for (String value : values) {
            if (TextUtils.isEmpty(value)) {
                status = 1;
            }
        }
        return status != 0;
    }

    public static boolean validateEmail(EditText editText) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(editText.getText().toString()).matches();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
