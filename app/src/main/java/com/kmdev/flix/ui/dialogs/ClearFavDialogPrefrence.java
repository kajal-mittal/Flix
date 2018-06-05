package com.kmdev.flix.ui.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.kmdev.flix.R;
import com.kmdev.flix.utils.DataBaseHelper;

/**
 * Created by Kajal on 10/30/2016.
 */
public class ClearFavDialogPrefrence extends DialogPreference {
    boolean isDataChanged = false;

    public ClearFavDialogPrefrence(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);

        setDialogIcon(null);
    }


    @Override
    protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
        builder.setTitle(R.string.app_name);
        builder.setMessage(R.string.clear_favourite);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                DataBaseHelper db = new DataBaseHelper(getContext());
                db.deleteAll();

            }
        });
        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        super.onPrepareDialogBuilder(builder);
    }
}
