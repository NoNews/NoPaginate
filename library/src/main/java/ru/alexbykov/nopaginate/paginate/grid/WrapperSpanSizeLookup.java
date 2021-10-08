package ru.alexbykov.nopaginate.paginate.grid;

import androidx.recyclerview.widget.GridLayoutManager;

import ru.alexbykov.nopaginate.item.BaseGridLayoutManagerItem;
import ru.alexbykov.nopaginate.paginate.WrapperAdapter;


/**
 * @author @Marko Milos, original repository: https://github.com/MarkoMilos/Paginate
 */


public final class WrapperSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private final GridLayoutManager.SpanSizeLookup wrappedSpanSizeLookup;
    private final BaseGridLayoutManagerItem loadingListItemSpanLookup;
    private final WrapperAdapter wrapperAdapter;

    public WrapperSpanSizeLookup(GridLayoutManager.SpanSizeLookup gridSpanSizeLookup,
                                 BaseGridLayoutManagerItem loadingListItemSpanLookup,
                                 WrapperAdapter wrapperAdapter) {
        this.wrappedSpanSizeLookup = gridSpanSizeLookup;
        this.loadingListItemSpanLookup = loadingListItemSpanLookup;
        this.wrapperAdapter = wrapperAdapter;
    }

    @Override
    public int getSpanSize(int position) {
        if (wrapperAdapter.isLoadingItem(position) || wrapperAdapter.isErrorItem(position)) {
            return loadingListItemSpanLookup.getSpanSize();
        } else {
            return wrappedSpanSizeLookup.getSpanSize(position);
        }
    }

    public GridLayoutManager.SpanSizeLookup getWrappedSpanSizeLookup() {
        return wrappedSpanSizeLookup;
    }
}
