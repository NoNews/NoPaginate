package ru.alexbykov.nopaginate.item;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


/**
 * @author @Marko Milos, original repository: https://github.com/MarkoMilos/Paginate
 */


public interface BaseLinearLayoutManagerItem {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
}
