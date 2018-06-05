package com.brsoftech.core_utils.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brsoftech.core_utils.R;
import com.brsoftech.core_utils.utils.NetworkConnectionUtil;
import com.brsoftech.core_utils.utils.SnackbarUtils;
import com.brsoftech.core_utils.utils.StandardUtil;


/**
 * Created by Kartik Sharma @ B.R. Softech on 5/3/16.
 */
public class BaseSupportFragment extends Fragment {
    private String mTag = null;
    private ProgressDialog mLoadingDialog;
    private AlertDialog mErrorDialog;
    private Snackbar mSnackbarLoading;

    public void install(Class<?> currentClass) {
        mTag = currentClass.getSimpleName();
    }

    public void displayShortToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void displayLongToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void displayShortToast(@StringRes int stringResId) {
        Toast.makeText(getActivity(), getString(stringResId), Toast.LENGTH_SHORT).show();
        int i = 0;
    }

    public void displayLongToast(@StringRes int stringResId) {
        Toast.makeText(getActivity(), getString(stringResId), Toast.LENGTH_LONG).show();
    }

    public void displayShortSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public void displayLongSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    public void displayShortSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_LONG).show();
    }

    public void displayLongSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_LONG).show();
    }

    public void displayIndefiniteSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).show();
    }

    public void displayIndefiniteSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_INDEFINITE).show();
    }

    public void displayErrorSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    public void displayErrorSnackbar(View view, @StringRes int stringResId) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                })
                .show();
    }

    public void displayErrorSnackbar(View view, String message, String actionMessage, final Runnable action) {
        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.run();
                    }
                })
                .show();
    }

    public void displayErrorSnackbar(View view, @StringRes int stringResId, @StringRes int actionMessage, final Runnable action) {
        Snackbar.make(view, stringResId, Snackbar.LENGTH_INDEFINITE)
                .setAction(actionMessage, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        action.run();
                    }
                })
                .show();
    }

    @SuppressLint("LongLogTag")
    public void printLog(String message) {
        if (mTag != null) {
            Log.e(mTag, message);
        } else {
            Log.e(BaseSupportFragment.class.getSimpleName(), message);
        }
    }

    @SuppressLint("LongLogTag")
    public void printLog(String tag, String message) {
        Log.e(tag, message);
    }

    public void startActivity(Class<?> className) {
        StandardUtil.startActivity(getActivity(), className);
    }

    public void startActivity(Class<?> className, Bundle args) {
        StandardUtil.startActivity(getActivity(), className, args);
    }

    /**
     * <p>Start an activity as a new task and clear all the activities before it, if any exists.</p>
     *
     * @param className
     */
    public void startActivityAsNewTask(Class<?> className) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(getActivity(), className, intentFlags);
    }

    /**
     * <p>Start an activity as a new task and clear all the activities before it, if any exists.</p>
     *
     * @param className
     * @param args
     */
    public void startActivityAsNewTask(Class<?> className, Bundle args) {
        int intentFlags = Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK;
        StandardUtil.startActivity(getActivity(), className, args, intentFlags);
    }

    public void setFragment(int viewResourceId, android.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragment(getActivity(), viewResourceId, fragment, withAnimation);
    }

    public void setFragment(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.setFragment(getActivity(), viewResourceId, fragment, withAnimation);
    }

    public void setChildFragment(int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        StandardUtil.setChildFragment(this, viewResourceId, fragment, withAnimation);
    }

    public void displayLoadingDialog(boolean isCancellable) {
        mLoadingDialog = new ProgressDialog(getActivity());
        mLoadingDialog.setTitle(R.string.loading);
        mLoadingDialog.setMessage(getString(R.string.please_wait));
        mLoadingDialog.setIndeterminate(true);
        mLoadingDialog.setCancelable(isCancellable);
        mLoadingDialog.show();
    }

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public void updateLoadingDialogStatus(String title, String content) {
        if (mLoadingDialog != null) {
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                mLoadingDialog.setTitle(title);
                mLoadingDialog.setMessage(content);
            } else if (!TextUtils.isEmpty(title)) {
                mLoadingDialog.setTitle(title);
            } else if (!TextUtils.isEmpty(content)) {
                mLoadingDialog.setMessage(content);
            }
        }
    }

    public ProgressDialog getLoadingDialog() {
        return mLoadingDialog;
    }

    public void displayErrorDialog(String title, String content) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(content)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_24dp))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(@StringRes int titleRes, @StringRes int contentRes) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(titleRes)
                .setMessage(contentRes)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_24dp))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(String title, String content, final Runnable runnable) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(content)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_24dp))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        runnable.run();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void displayErrorDialog(@StringRes int titleRes, @StringRes int contentRes, final Runnable runnable) {
        mErrorDialog = new AlertDialog.Builder(getActivity())
                .setTitle(titleRes)
                .setMessage(contentRes)
                .setIcon(ContextCompat.getDrawable(getActivity(), R.drawable.ic_error_24dp))
                .setCancelable(false)
                .setNegativeButton(R.string.dismiss, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        runnable.run();
                    }
                })
                .create();
        mErrorDialog.show();
    }

    public void dismissErrorDialog() {
        if (mErrorDialog != null) {
            mErrorDialog.dismiss();
        }
    }

    public void displayLoadingSnackbar(View view, String content) {
        mSnackbarLoading = Snackbar.make(view, content, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity());
        snackbarLayout.addView(progressBar);
        mSnackbarLoading.show();
    }

    public void displayLoadingSnackbar(View view, @StringRes int contentRes) {
        mSnackbarLoading = Snackbar.make(view, contentRes, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleSmall);
        snackbarLayout.addView(progressBar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) progressBar.getLayoutParams();
        //layoutParams.weight = 1.0f;
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        progressBar.setLayoutParams(layoutParams);
        mSnackbarLoading.show();
    }

    public void displayFixedLoadingSnackbar(View view, String content) {
        mSnackbarLoading = Snackbar.make(view, content, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity());
        snackbarLayout.addView(progressBar);
        mSnackbarLoading.show();
        SnackbarUtils.disableDismissSwipe(mSnackbarLoading);
    }

    public void displayFixedLoadingSnackbar(View view, @StringRes int contentRes) {
        mSnackbarLoading = Snackbar.make(view, contentRes, Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) mSnackbarLoading.getView();
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyleSmall);
        snackbarLayout.addView(progressBar);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) progressBar.getLayoutParams();
        //layoutParams.weight = 1.0f;
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        progressBar.setLayoutParams(layoutParams);
        mSnackbarLoading.show();
        SnackbarUtils.disableDismissSwipe(mSnackbarLoading);
    }

    public void dismissLoadingSnackbar() {
        if (mSnackbarLoading != null && mSnackbarLoading.isShown()) {
            mSnackbarLoading.dismiss();
        }
    }

    public boolean isNetworkAvailable() {
        if (NetworkConnectionUtil.isNetworkAvailable(getActivity())) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableDialog(getActivity());
            return false;
        }
    }

    public boolean isNetworkAvailable(View view) {
        if (NetworkConnectionUtil.isNetworkAvailable(getActivity())) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableSnackbar(getActivity(), view);
            return false;
        }
    }

    public void disableViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setEnabled(false);
        }
    }

    public void enableViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            views[i].setEnabled(true);
        }
    }
}
