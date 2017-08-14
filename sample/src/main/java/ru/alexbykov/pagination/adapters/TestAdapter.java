package ru.alexbykov.pagination.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.alexbykov.pagination.R;


public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {


    private static final int LAYOUT = R.layout.item_layout;
    private List<Integer> items;

    public TestAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(LAYOUT, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvNumber.setText(items.get(position) + "");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItems(List<Integer> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNumber;


        public ViewHolder(View itemView) {
            super(itemView);
            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
        }
    }
}
