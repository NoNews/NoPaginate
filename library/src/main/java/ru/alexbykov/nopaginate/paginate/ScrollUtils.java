package ru.alexbykov.nopaginate.paginate;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Date: 12.08.2017
 * Time: 15:20
 * Project: NoPagination
 *
 * @author Alex Bykov
 *         You can contact me at me@alexbykov.ru
 */
final class ScrollUtils {


    private ScrollUtils() {
    }

    static boolean isOnBottom(@NonNull RecyclerView recyclerView, int loadingTriggerThreshold) {
        final int visibleItemCount = recyclerView.getChildCount();
        final int totalItemCount = recyclerView.getLayoutManager().getItemCount();
        final int firstVisibleItemPosition = getFirstVisibleItemPositionByLayoutManager(recyclerView.getLayoutManager());
        // Check if end of the list is reached (counting threshold) or if there is no items at all
        return (totalItemCount - visibleItemCount) <= (firstVisibleItemPosition + loadingTriggerThreshold)
                || totalItemCount == 0;

    }

    private static int getFirstVisibleItemPositionByLayoutManager(@NonNull RecyclerView.LayoutManager
                                                                          layoutManager) {
        int firstVisibleItemPosition;
        if (layoutManager instanceof LinearLayoutManager) {
            firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            // https://code.google.com/p/android/issues/detail?id=181461
            if (layoutManager.getChildCount() > 0) {
                firstVisibleItemPosition = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null)[0];
            } else {
                firstVisibleItemPosition = 0;
            }

        } else {
            throw new IllegalStateException("LayoutManager needs to subclass LinearLayoutManager or StaggeredGridLayoutManager");
        }
        return firstVisibleItemPosition;
    }


    static void fullScrollToBottom(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.Adapter
            adapter) {
        recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, adapter.getItemCount() - 1);
    }
}
