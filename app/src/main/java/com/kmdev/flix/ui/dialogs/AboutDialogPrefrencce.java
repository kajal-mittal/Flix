package com.kmdev.flix.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.kmdev.flix.R;

/**
 * Created by Kajal on 10/30/2016.
 */
public class AboutDialogPrefrencce extends DialogPreference {
    public AboutDialogPrefrencce(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        builder.setMessage(R.string.message_about);
        builder.setTitle(R.string.title_about);
        super.onPrepareDialogBuilder(builder);
    }
}
