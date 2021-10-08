package ru.alexbykov.nopaginate.paginate;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import ru.alexbykov.nopaginate.callback.OnRepeatListener;
import ru.alexbykov.nopaginate.item.ErrorItem;
import ru.alexbykov.nopaginate.item.LoadingItem;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public final class WrapperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_VIEW_TYPE_LOADING = 46699933;
    private static final int ITEM_VIEW_TYPE_ERROR = 46699932;

    private RecyclerView.Adapter userAdapter;
    private LoadingItem loadingItem;
    private ErrorItem errorItem;
    private PaginateStatus paginateStatus = PaginateStatus.LOADING;
    private OnRepeatListener repeatListener;


    WrapperAdapter(RecyclerView.Adapter userAdapter, LoadingItem loadingItem, ErrorItem errorItem) {
        this.userAdapter = userAdapter;
        this.loadingItem = loadingItem;
        this.errorItem = errorItem;
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadingItem(position)) {
            return ITEM_VIEW_TYPE_LOADING;
        } else if (isErrorItem(position)) {
            return ITEM_VIEW_TYPE_ERROR;
        } else {
            return userAdapter.getItemViewType(position);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_LOADING) {
            return loadingItem.onCreateViewHolder(parent, viewType);
        } else if (viewType == ITEM_VIEW_TYPE_ERROR) {
            return errorItem.onCreateViewHolder(parent, viewType);
        } else {
            return userAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isLoadingItem(position)) {
            loadingItem.onBindViewHolder(holder, position);
        } else if (isErrorItem(position)) {
            errorItem.onBindViewHolder(holder, position, repeatListener);
        } else {
            userAdapter.onBindViewHolder(holder, position);
        }
    }


    public boolean isErrorItem(int position) {
        return paginateStatus == PaginateStatus.ERROR && position == getErrorOrLoadingItemPosition();
    }


    public boolean isLoadingItem(int position) {
        return paginateStatus == PaginateStatus.LOADING && position == getErrorOrLoadingItemPosition();
    }


    private int getErrorOrLoadingItemPosition() {
        return isErrorOrLoading() ? getItemCount() - 1 : -1;
    }

    private boolean isErrorOrLoading() {
        return paginateStatus == PaginateStatus.LOADING || paginateStatus == PaginateStatus.ERROR;
    }

    @Override
    public int getItemCount() {
        return isErrorOrLoading() ? userAdapter.getItemCount() + 1 : userAdapter.getItemCount();
    }

    void stateChanged(PaginateStatus status) {
        if (this.paginateStatus != status) {
            this.paginateStatus = status;
            notifyDataSetChanged();
        }
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
        userAdapter.setHasStableIds(hasStableIds);
    }

    void setRepeatListener(OnRepeatListener repeatListener) {
        this.repeatListener = repeatListener;
    }

    void unbind() {
        repeatListener = null;
    }
}
