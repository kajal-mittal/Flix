package com.brsoftech.core_utils.base;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.brsoftech.core_utils.R;
import com.brsoftech.core_utils.utils.NetworkConnectionUtil;
import com.brsoftech.core_utils.utils.StandardUtil;


/**
 * Created by Kartik Sharma @ B.R. Softech on 4/4/16.
 */
public class BaseDialogFragment extends DialogFragment {
    private String mTag = null;
    private ProgressDialog mLoadingDialog;
    private AlertDialog mErrorDialog;

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
    }

    public void displayLongToast(@StringRes int stringResId) {
        Toast.makeText(getActivity(), getString(stringResId), Toast.LENGTH_LONG).show();
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

    public boolean isNetworkAvailable() {
        if (NetworkConnectionUtil.isNetworkAvailable(getActivity())) {
            return true;
        } else {
            NetworkConnectionUtil.showNetworkUnavailableDialog(getActivity());
            return false;
        }
    }
}
