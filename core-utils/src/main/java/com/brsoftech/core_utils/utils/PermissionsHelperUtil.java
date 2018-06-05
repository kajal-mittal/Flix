package com.brsoftech.core_utils.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ubuntu on 8/4/16.
 */
public class PermissionsHelperUtil {
    public static final int PermissionrequestCode = 1223;
    private static final String MSG_PERMISSIONS = "Please accept the respective permissions inorder to access complete features of the application.";
    private static final List<String> permissions = Arrays.asList(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    );
    private static final int MAX_PERMISSION_LABEL_LENGTH = 20;
    public static boolean requestrunning = false;

    static List<String> getPermissionConstants(Context context) {
        return permissions;
    }


    private static List<PermissionInfo> getPermissions(Context context, List<String> required) {

        List<PermissionInfo> permissionInfoList = new ArrayList<>();

        PackageManager pm = context.getPackageManager();
        for (String permission : required) {
            PermissionInfo pinfo = null;
            try {
                pinfo = pm.getPermissionInfo(permission, PackageManager.GET_META_DATA);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                continue;
            }

            permissionInfoList.add(pinfo);
        }
        return permissionInfoList;
    }

    private static String[] getPermissionNames(Context context, List<String> required) {
        PackageManager pm = context.getPackageManager();
        String[] names = new String[required.size()];
        int i = 0;
        for (PermissionInfo permissionInfo : getPermissions(context, required)) {
            CharSequence label = permissionInfo.loadLabel(pm);
//            if (label.length() > MAX_PERMISSION_LABEL_LENGTH) {
//                label = label.subSequence(0, MAX_PERMISSION_LABEL_LENGTH);
//            }
            names[i] = label.toString();
            i++;
        }
        return names;
    }

    private static boolean[] getPermissionsState(Context context) {
        boolean[] states = new boolean[getPermissionConstants(context).size()];
        int i = 0;
        for (String permission : getPermissionConstants(context)) {
            states[i] = isPermissionGranted(context, permission);
            i++;
        }
        return states;
    }


    public static void show(final Context context, String title, final List<String> required) {
        if (required == null) return;
        if (requestrunning) return;

        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(MSG_PERMISSIONS)
                .setItems(getPermissionNames(context, required), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestrunning = true;
                        ActivityCompat.requestPermissions(scanForActivity(context),
                                required.toArray(new String[required.size()]), PermissionrequestCode);
                    }
                })
                .setCancelable(false)
                .show();
    }

    private static Activity scanForActivity(Context cont) {
        if (cont == null)
            return null;
        else if (cont instanceof Activity)
            return (Activity) cont;
        else if (cont instanceof ContextWrapper)
            return scanForActivity(((ContextWrapper) cont).getBaseContext());

        return null;
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        return PermissionChecker.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean areExplicitPermissionsRequired() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static void show(final Context context) {
        show(context, null, null);
    }

    public static List<String> isAllPremissiongranted(Context context) {
        List<String> premissions = getPermissionConstants(context);
        List<String> requiredPremisiion = new ArrayList<String>();
        for (int i = 0; i < premissions.size(); i++) {
            if (!isPermissionGranted(context, premissions.get(i))) {
                requiredPremisiion.add(premissions.get(i));
            }

        }
        return requiredPremisiion;
    }

}
