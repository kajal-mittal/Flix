package com.kmdev.flix.utils;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.kmdev.flix.R;

/**
 * Created by Kajal on 10/17/2016.
 */
public class Utils {
    private static AlertDialog mAlertDialogReview;

    public static void displayReviewDetails(Context context, String title, String content) {
        mAlertDialogReview = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(content)
                .setCancelable(true)
                .setNegativeButton(com.brsoftech.core_utils.R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mAlertDialogReview.show();
    }

    public static void applyFontForToolbarTitle(Activity context) {
        Toolbar toolbar = (Toolbar) context.findViewById(R.id.toolbar);
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                Typeface titleFont = Typeface.
                        createFromAsset(context.getAssets(), "fonts/Montserrat-Bold.ttf");
                if (tv.getText().equals(context.getTitle())) {
                    tv.setTypeface(titleFont);
                    break;
                }
            }
        }
    }

    @RequiresPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public static void downloadFile(Context context,
                                    String fileURL,
                                    String fileName,
                                    String title) {
        String servicestring = Context.DOWNLOAD_SERVICE;
        DownloadManager downloadmanager;
        downloadmanager = (DownloadManager) context.getSystemService(servicestring);
        Uri uri = Uri.parse(fileURL);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        Long reference = downloadmanager.enqueue(request.setAllowedNetworkTypes(
                DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE)
                .setAllowedOverRoaming(false)
                .setTitle(title)
                .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)

        );
    }
}
