package com.brsoftech.core_utils.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.brsoftech.core_utils.R;

/**
 * Created by Kartik Sharma @ B.R. Softech on 23/3/16.
 */
public class NetworkConnectionUtil {
    private static final String NO_INTERNET_CONN = "No Internet Available";
    private static final String NO_INTERNET_CONN_DESC = "Unable to detect an internet connection. Please turn it on in your network settings.";
    private static final String SETTINGS = "Settings";
    private static final String DISMISS = "Dismiss";

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public static void showNetworkUnavailableDialog(final Context context) {
        new AlertDialog.Builder(context)
                .setTitle(NO_INTERNET_CONN)
                .setMessage(NO_INTERNET_CONN_DESC)
                .setIcon(R.drawable.ic_error_24dp)
                .setPositiveButton(SETTINGS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        context.startActivity(wifiSettingsIntent);
                    }
                })
                .setNegativeButton(DISMISS, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    public static void showNetworkUnavailableSnackbar(final Context context, View view) {
        Snackbar.make(view, NO_INTERNET_CONN, Snackbar.LENGTH_INDEFINITE)
                .setAction(SETTINGS, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent wifiSettingsIntent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        context.startActivity(wifiSettingsIntent);
                    }
                }).show();
    }
}
