package ru.alexbykov.nopaginate.paginate;

import android.support.v7.widget.RecyclerView;

import ru.alexbykov.nopaginate.callback.OnLoadMore;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.item.ErrorItem;
import ru.alexbykov.nopaginate.item.LoadingItem;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public class NoPaginateBuilder {


    private RecyclerView recyclerView;
    private OnLoadMoreListener loadMoreListener;
    private LoadingItem loadingItem;
    private ErrorItem errorItem;
    private int loadingTriggerThreshold = 0;


    NoPaginateBuilder(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    /**
     * This method setup recyclerView
     *
     * @param recyclerView which you want to paginate
     * @return current object
     */


    public static NoPaginateBuilder with(RecyclerView recyclerView) {
        return new NoPaginateBuilder(recyclerView);
    }


    /**
     * This method setup OnLoadMoreListener object, which will called when you need load data
     *
     * @param loadMoreListener object of {@link OnLoadMoreListener}
     * @return current object
     */
    public NoPaginateBuilder setOnLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
        return this;
    }


    /**
     * Set the offset from the end of the list at which the load more event needs to be triggered. Default offset
     * if 5.
     *
     * @param loadingTriggerThreshold number of items from the end of the list.
     * @return current object
     */
    public NoPaginateBuilder setLoadingTriggerThreshold(int loadingTriggerThreshold) {
        this.loadingTriggerThreshold = loadingTriggerThreshold;
        return this;
    }

    /**
     * This method set custom loading item.
     *
     * @param loadingItem is implementation of {@link LoadingItem}
     * @return current object
     */
    public NoPaginateBuilder setCustomLoadingItem(LoadingItem loadingItem) {
        this.loadingItem = loadingItem;
        return this;
    }

    /**
     * This method set custom error item.
     *
     * @param errorItem is implementation of {@link ErrorItem}
     * @return current object
     */
    public NoPaginateBuilder setCustomErrorItem(ErrorItem errorItem) {
        this.errorItem = errorItem;
        return this;
    }

    /**
     * This method build all configurations
     *
     * @return object of {@link Paginate}
     */
    public NoPaginate build() {
        if (loadingItem == null) {
            loadingItem = LoadingItem.DEFAULT;
        }
        if (errorItem == null) {
            errorItem = ErrorItem.DEFAULT;
        }

        return new NoPaginate(recyclerView, loadMoreListener, loadingTriggerThreshold, loadingItem, errorItem);
    }


}
