package com.mulia754.detikPS.util;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dani on 4/6/17.
 */

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    private int mMargin;

    public MarginItemDecoration(int margin) {
        this.mMargin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view) > 0) {
            outRect.top = mMargin;
        }
    }
}
