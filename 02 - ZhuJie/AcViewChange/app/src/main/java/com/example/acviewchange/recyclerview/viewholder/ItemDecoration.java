package com.example.acviewchange.recyclerview.viewholder;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    // 列数
    private final int spanCount;

    // 间隔
    private final int space;

    // 是否包含边缘
    private final boolean includeEdge;

    public ItemDecoration() {
        this(1, 0, false);
    }

    public ItemDecoration(int spanCount, int space) {
        this(spanCount, space, false);
    }

    public ItemDecoration(int spanCount, int space, boolean includeEdge) {
        this.spanCount = spanCount;
        this.space = space;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        WindowManager vm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = vm.getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        int width = point.x;
        // view 所在的下标
        int position = parent.getChildAdapterPosition(view);
        // view 所在的列数
        int column = position % spanCount;
        if (includeEdge) {
            width = (width - (spanCount + 1) * space) / spanCount;
            // 需要包含边界来计算间距（不贴左右两边）
            outRect.left = space;
            if (column == spanCount - 1) {
                outRect.right = space;
            }
            if (position < spanCount) {
                outRect.top = space;
            }
            outRect.bottom = space;
        } else {
            width = (width - (spanCount - 1) * space) / spanCount;
            // 贴边
            outRect.bottom = space;
            if (column != (spanCount - 1)) {
                outRect.right = space;
            }
        }
        layoutParams.width = width;
        layoutParams.height = (int) (width * 0.618);
        view.setLayoutParams(layoutParams);
    }
}
