package ru.alexbykov.nopaginate.paginate;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import ru.alexbykov.nopaginate.callback.OnAdapterChangeListener;
import ru.alexbykov.nopaginate.callback.OnLoadMore;
import ru.alexbykov.nopaginate.callback.OnLoadMoreListener;
import ru.alexbykov.nopaginate.callback.OnRepeatListener;
import ru.alexbykov.nopaginate.item.DefaultGridLayoutItem;
import ru.alexbykov.nopaginate.item.ErrorItem;
import ru.alexbykov.nopaginate.item.LoadingItem;
import ru.alexbykov.nopaginate.paginate.grid.WrapperSpanSizeLookup;


/**
 * @author Alex Bykov and Marko Milos, original repository: https://github.com/MarkoMilos/Paginate
 */


public class Paginate implements OnAdapterChangeListener, OnRepeatListener {


    private int loadingTriggerThreshold;
    private RecyclerView recyclerView;
    private OnLoadMore paginateCallback;
    private OnLoadMoreListener loadMoreListener;
    private WrapperAdapter wrapperAdapter;
    private LoadingItem loadingItem;
    private ErrorItem errorItem;
    private WrapperAdapterObserver wrapperAdapterObserver;
    private RecyclerView.Adapter userAdapter;
    private WrapperSpanSizeLookup wrapperSpanSizeLookup;
    private boolean isError;
    private boolean isLoading;
    private boolean isLoadedAllItems;


    Paginate(RecyclerView recyclerView, OnLoadMore paginateCallback, OnLoadMoreListener loadMoreListener, int loadingTriggerThreshold, LoadingItem loadingItem, ErrorItem errorItem) {
        this.recyclerView = recyclerView;
        this.loadMoreListener = loadMoreListener;
        this.loadingTriggerThreshold = loadingTriggerThreshold;
        this.paginateCallback = paginateCallback;
        this.loadingItem = loadingItem;
        this.errorItem = errorItem;
        setupWrapper();
        setupScrollListener();
    }

    private void setupWrapper() {
        this.userAdapter = recyclerView.getAdapter();
        wrapperAdapter = new WrapperAdapter(userAdapter, loadingItem, errorItem);
        wrapperAdapterObserver = new WrapperAdapterObserver(this, wrapperAdapter);
        userAdapter.registerAdapterDataObserver(wrapperAdapterObserver);
        recyclerView.setAdapter(wrapperAdapter);
        wrapperAdapter.setRepeatListener(this);
        checkGridLayoutManager();
    }

    private void checkGridLayoutManager() {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            DefaultGridLayoutItem item = new DefaultGridLayoutItem(recyclerView.getLayoutManager());
            wrapperSpanSizeLookup = new WrapperSpanSizeLookup(((GridLayoutManager) recyclerView.getLayoutManager()).getSpanSizeLookup(),
                    item,
                    wrapperAdapter);
            ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanSizeLookup(wrapperSpanSizeLookup);
        }
    }


    private void setupScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                checkScroll();
            }
        });
    }

    private void checkAdapterState() {
        if (isCanLoadMore()) {
            if (loadMoreListener != null) {
                loadMoreListener.onLoadMore();
            }
            if (paginateCallback != null) {
                paginateCallback.onLoadMore();
            }
        }
    }

    private boolean isCanLoadMore() {
        return !isLoading && !isError && !isLoadedAllItems;
    }

    @Override
    public void onAdapterChange() {

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                final PaginateStatus status = PaginateStatus.getStatus(isLoadedAllItems, isError);
                wrapperAdapter.stateChanged(status);
                checkScroll();
            }
        });
    }


    private void checkScroll() {
        if (ScrollUtils.isOnBottom(recyclerView, loadingTriggerThreshold)) {
            checkAdapterState();
        }
    }


    /**
     * This method will show error on the bottom of your recyclerView.
     *
     * @param isShowError - true if show, false if hide
     */
    public void showError(boolean isShowError) {
        if (isShowError) {
            isError = true;
            wrapperAdapter.stateChanged(PaginateStatus.ERROR);
            ScrollUtils.fullScrollToBottom(recyclerView, wrapperAdapter);
        } else {
            isError = false;
        }
    }


    /**
     * This method will show error on the bottom of your recyclerView.
     *
     * @param show - true if show, false if hide
     */
    public void showLoading(boolean show) {
        if (show) {
            isLoading = true;
            wrapperAdapter.stateChanged(PaginateStatus.LOADING);
        } else {
            isLoading = false;
        }
    }

    /**
     * This method  show error on the bottom of your recyclerView.
     *
     * @param isNoMoreItems - true if items ended, false if no
     */
    public void setNoMoreItems(boolean isNoMoreItems) {
        if (isNoMoreItems) {
            this.isLoadedAllItems = true;
            wrapperAdapter.stateChanged(PaginateStatus.NO_MORE_ITEMS);
        } else {
            this.isLoadedAllItems = false;
        }
    }

    @Override
    public void onClickRepeat() {
        showError(false);
        checkScroll();
    }

    /**
     * @deprecated use method {@link #setNoMoreItems(boolean)} instead this
     */
    @Deprecated
    public void setPaginateNoMoreItems(boolean isNoMoreItems) {
        setNoMoreItems(isNoMoreItems);
    }

    /**
     * @deprecated use method {@link #unbind()} instead this
     */
    @Deprecated
    public void unSubscribe() {
        unbind();
    }

    /**
     * This method unsubscribe observer and change listeners reference to null
     * for avoid memory leaks.
     */
    public void unbind() {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            wrapperAdapter.unbind();
            userAdapter.unregisterAdapterDataObserver(wrapperAdapterObserver);
            recyclerView.setAdapter(userAdapter);

        } else if (recyclerView.getLayoutManager() instanceof GridLayoutManager && wrapperSpanSizeLookup != null) {
            GridLayoutManager.SpanSizeLookup spanSizeLookup = wrapperSpanSizeLookup.getWrappedSpanSizeLookup();
            ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanSizeLookup(spanSizeLookup);
        }
    }

}

