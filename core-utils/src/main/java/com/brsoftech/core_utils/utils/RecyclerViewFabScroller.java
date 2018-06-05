package com.brsoftech.core_utils.utils;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 12/7/16</p>
 * <p>Time: 11:08 AM</p>
 * <p>Project: Planbeep</p>
 */
public class RecyclerViewFabScroller extends RecyclerView.OnScrollListener {

    private RecyclerViewFabScroller() {
    }

    public static void attachTo(RecyclerView recyclerView, final FloatingActionButton fab) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    //scroll up
                    fab.show();
                } else {
                    //scroll down
                    fab.hide();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
