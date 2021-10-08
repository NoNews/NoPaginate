package ru.alexbykov.nopaginate.item;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author @Marko Milos, original repository: https://github.com/MarkoMilos/Paginate
 */


public interface BaseLinearLayoutManagerItem {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
}
