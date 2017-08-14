package ru.alexbykov.nopaginate.item;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public interface BaseItem {

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);
}
