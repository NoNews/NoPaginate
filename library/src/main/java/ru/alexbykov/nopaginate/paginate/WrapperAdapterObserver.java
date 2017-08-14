package ru.alexbykov.nopaginate.paginate;

import android.support.v7.widget.RecyclerView;

import ru.alexbykov.nopaginate.callback.ObserverCallback;


/**
 * @author @Marko Milos, original repository: https://github.com/MarkoMilos/Paginate
 */

class WrapperAdapterObserver extends RecyclerView.AdapterDataObserver {

    private WrapperAdapter wrapperAdapter;
    private ObserverCallback observerCallback;


    WrapperAdapterObserver(ObserverCallback observerCallback, WrapperAdapter wrapperAdapter) {
        this.wrapperAdapter = wrapperAdapter;
        this.observerCallback = observerCallback;
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        wrapperAdapter.notifyItemRangeChanged(positionStart, itemCount);
        observerCallback.onAdapterChange();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        wrapperAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        observerCallback.onAdapterChange();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        wrapperAdapter.notifyItemRangeChanged(positionStart, itemCount);
        observerCallback.onAdapterChange();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        wrapperAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        observerCallback.onAdapterChange();
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        wrapperAdapter.notifyItemMoved(fromPosition, toPosition);
        observerCallback.onAdapterChange();
    }

    @Override
    public void onChanged() {
        wrapperAdapter.notifyDataSetChanged();
        observerCallback.onAdapterChange();
    }


}
