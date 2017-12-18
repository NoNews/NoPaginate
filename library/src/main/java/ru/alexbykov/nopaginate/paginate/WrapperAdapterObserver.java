package ru.alexbykov.nopaginate.paginate;

import android.support.v7.widget.RecyclerView;

import ru.alexbykov.nopaginate.callback.OnAdapterChangeListener;


/**
 * @author @Marko Milos, original repository: https://github.com/MarkoMilos/Paginate
 */

class WrapperAdapterObserver extends RecyclerView.AdapterDataObserver {

    private WrapperAdapter wrapperAdapter;
    private OnAdapterChangeListener adapterChangeListener;


    WrapperAdapterObserver(OnAdapterChangeListener adapterChangeListener, WrapperAdapter wrapperAdapter) {
        this.wrapperAdapter = wrapperAdapter;
        this.adapterChangeListener = adapterChangeListener;
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount) {
        wrapperAdapter.notifyItemRangeChanged(positionStart, itemCount);
        adapterChangeListener.onAdapterChange();
    }

    @Override
    public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
        wrapperAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        adapterChangeListener.onAdapterChange();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        wrapperAdapter.notifyItemRangeChanged(positionStart, itemCount);
        adapterChangeListener.onAdapterChange();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
        wrapperAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        adapterChangeListener.onAdapterChange();
    }

    @Override
    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
        wrapperAdapter.notifyItemMoved(fromPosition, toPosition);
        adapterChangeListener.onAdapterChange();
    }

    @Override
    public void onChanged() {
        wrapperAdapter.notifyDataSetChanged();
        adapterChangeListener.onAdapterChange();
    }


}
