package com.brsoftech.core_utils.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.brsoftech.core_utils.BuildConfig;
import com.brsoftech.core_utils.R;
import com.brsoftech.core_utils.base.BaseAppCompatActivity;

/**
 * Created by Kartik Sharma @ B.R. Softech on 2/3/16.
 */
public class StandardUtil {
    //public static final int FLAG_NONE=0;
    //public static final int FLAG_LEFT_TO_RIGHT=1;
    //public static final int FLAG_RIGHT_TO_LEFT=2;

    public static void makeActivityFullScreen(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void setFragment(Context context, int viewResourceId, Fragment fragment, boolean withAnimation) {
        FragmentTransaction fragmentTransaction = ((Activity) context).getFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0);
        }
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.replace(viewResourceId, fragment, fragment.getClass().getSimpleName()).commit();
    }

    public static void setFragment(Context context, int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,
                    R.anim.left_in, R.anim.right_out);
        }
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.replace(viewResourceId, fragment, fragment.getClass().getSimpleName()).commit();
    }

    /*  public static void setParentFragment(Context context, int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
          FragmentTransaction fragmentTransaction = ((Activity) context).getParent().getFragmentManager().beginTransaction();
          if (withAnimation) {
              fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0);
          }
          fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
          fragmentTransaction.replace(viewResourceId, fragment, fragment.getClass().getSimpleName()).commit();
      }
  */
    public static void setFragmentWithoutBackStack(Context context, int viewResourceId, Fragment fragment, boolean withAnimation) {
        FragmentTransaction fragmentTransaction = ((Activity) context).getFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0);
        }
        fragmentTransaction.replace(viewResourceId, fragment, fragment.getClass().getSimpleName()).commit();
    }

    public static void setFragmentWithoutBackStack(Context context, int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,
                    R.anim.left_in, R.anim.right_out);
        }
        fragmentTransaction.replace(viewResourceId, fragment, fragment.getClass().getSimpleName()).commit();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setChildFragment(Fragment currFragment, int viewResourceId, Fragment fragment, boolean withAnimation) {
        FragmentTransaction fragmentTransaction = currFragment.getChildFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0);
        }
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.add(viewResourceId, fragment).commit();
    }

    public static void setChildFragment(android.support.v4.app.Fragment currFragment, int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = currFragment.getChildFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,
                    R.anim.left_in, R.anim.right_out);
        }
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.add(viewResourceId, fragment).commit();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void setChildFragmentWithoutBackStack(Fragment currFragment, int viewResourceId, Fragment fragment, boolean withAnimation) {
        FragmentTransaction fragmentTransaction = currFragment.getChildFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0);
        }
        fragmentTransaction.replace(viewResourceId, fragment).commit();
    }

    public static void setChildFragmentWithoutBackStack(android.support.v4.app.Fragment currFragment, int viewResourceId, android.support.v4.app.Fragment fragment, boolean withAnimation) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = currFragment.getChildFragmentManager().beginTransaction();
        if (withAnimation) {
            fragmentTransaction.setCustomAnimations(R.anim.right_in, R.anim.left_out,
                    R.anim.left_in, R.anim.right_out);
        }
        fragmentTransaction.replace(viewResourceId, fragment).commit();
    }

    public static void startActivity(Context context, Class<?> className) {
        Intent intent = new Intent(context, className);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> className, Bundle args) {
        Intent intent = new Intent(context, className);
        intent.putExtras(args);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> className, int flags) {
        Intent intent = new Intent(context, className);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> className, Bundle args, int flags) {
        Intent intent = new Intent(context, className);
        intent.putExtras(args);
        intent.setFlags(flags);
        context.startActivity(intent);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setGridViewHeightBasedOnChildren(GridView gridView, int columnNum) {
        BaseAdapter gridAdapter = (BaseAdapter) gridView.getAdapter();
        if (gridAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < gridAdapter.getCount(); i++) {
            View gridItem = gridAdapter.getView(i, null, gridView);
            gridItem.measure(0, 0);
            totalHeight += gridItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = (totalHeight +
                (gridView.getVerticalSpacing() * ((gridAdapter.getCount() / columnNum) - 1))) / columnNum;
        params.height += 50;
        gridView.setLayoutParams(params);
    }

    /**
     * <p>Inorder to run anything on the main thread</p>
     *
     * @param task
     */
    public static void runOnUiThread(Runnable task) {
        new Handler(Looper.getMainLooper()).post(task);
    }

    public static void openMap(Context context, double latitude, double longitude) {
        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            String errorMsg = "Google maps app not available in your system";
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }

    }

    public static void openMap(Context context, String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        } else {
            String errorMsg = "Google maps app not available in your system";
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * <p>Provides a unique deviceId</p>
     * <p><b>This id resets if user factory resets his/her device</b></p>
     *
     * @param context current {@link Context} of the application
     * @return unique deviceId in string format
     */
    public static String getDeviceId(Context context) {
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return deviceId;
    }

    public static void showKeyboard(Context context) {
        ((Activity) context).getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public static void hideKeyboard(Context context) {
        // Check if no view has focus:
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void disableEnableControls(boolean enable, ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View child = vg.getChildAt(i);
            child.setEnabled(enable);
            if (child instanceof ViewGroup) {
                disableEnableControls(enable, (ViewGroup) child);
            }
        }
    }

    public static void enableViewPagerInsideScrollVIew(final ViewPager viewPager) {
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPager.getParent().requestDisallowInterceptTouchEvent(true);
            }
        });
    }

    public static void disableTab(TabLayout tabLayout, int tabPosition) {
        LinearLayout tabStrip = ((LinearLayout) tabLayout.getChildAt(0));
        tabStrip.getChildAt(tabPosition).setEnabled(false);
        tabStrip.getChildAt(tabPosition).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @SuppressLint("LongLogTag")
    public static void printLog(String message) {
        if (BuildConfig.DEBUG) {
            Log.e(BaseAppCompatActivity.class.getSimpleName(), message);
        }
    }

    @SuppressLint("LongLogTag")
    public static void printLog(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message);
        }
    }

    @SuppressLint("LongLogTag")
    public static void printError(String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(BaseAppCompatActivity.class.getSimpleName(), message, throwable);
        }
    }

    @SuppressLint("LongLogTag")
    public static void printError(String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message, throwable);
        }
    }
}
