package ru.alexbykov.nopaginate.paginate;

import android.support.v7.widget.RecyclerView;

import ru.alexbykov.nopaginate.callback.OnLoadMore;
import ru.alexbykov.nopaginate.item.ErrorItem;
import ru.alexbykov.nopaginate.item.LoadingItem;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public class PaginateBuilder {


    private RecyclerView recyclerView;
    private OnLoadMore paginateCallback;
    private LoadingItem loadingItem;
    private ErrorItem errorItem;
    private int loadingTriggerThreshold = 5;

    public PaginateBuilder() {

    }

    public PaginateBuilder with(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        return this;
    }

    public PaginateBuilder setCallback(OnLoadMore paginateCallback) {
        this.paginateCallback = paginateCallback;
        return this;
    }


    public PaginateBuilder setLoadingTriggerThreshold(int loadingTriggerThreshold) {
        this.loadingTriggerThreshold = loadingTriggerThreshold;
        return this;
    }


    public PaginateBuilder setCustomLoadingItem(LoadingItem loadingItem) {
        this.loadingItem = loadingItem;
        return this;
    }

    public PaginateBuilder setCustomErrorItem(ErrorItem errorItem) {
        this.errorItem = errorItem;
        return this;
    }



    public Paginate build() {
        if (loadingItem == null) {
            loadingItem = LoadingItem.DEFAULT;
        }
        if (errorItem == null) {
            errorItem = ErrorItem.DEFAULT;
        }

        return new Paginate(recyclerView, paginateCallback, loadingTriggerThreshold, loadingItem, errorItem);
    }


}
