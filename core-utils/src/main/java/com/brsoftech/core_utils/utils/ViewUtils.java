package com.brsoftech.core_utils.utils;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 16/7/16</p>
 * <p>Time: 12:22 PM</p>
 * <p>Project: Planbeep</p>
 */
public class ViewUtils {
    private static final String TAG = ViewUtils.class.getSimpleName();

    public static void calculateHeights(final OnGetHeightsListener onGetHeightsListener, final View... views) {
        new AsyncTask<Void, Void, Void>() {
            private List<Integer> mHeights;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mHeights = new ArrayList<Integer>();
            }

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < views.length; i++) {
                    final int finalI = i;
                    views[i].post(new Runnable() {
                        @Override
                        public void run() {
                            mHeights.add(views[finalI].getMeasuredHeight());
                            Log.e(TAG, "view_" + finalI + "_height: " + views[finalI].getMeasuredHeight());
                        }
                    });
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                onGetHeightsListener.onHeightRetrieved(mHeights);
            }
        }.execute();
    }

    public interface OnGetHeightsListener {
        /**
         * Will be provided in the same order as the views were provided in {@link #on(View...)}
         *
         * @param viewHeights
         */
        void onHeightRetrieved(List<Integer> viewHeights);
    }
}
