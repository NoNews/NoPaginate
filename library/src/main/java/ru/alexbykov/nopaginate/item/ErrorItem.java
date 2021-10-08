package ru.alexbykov.nopaginate.item;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.widget.RecyclerView;
import ru.alexbykov.nopaginate.R;
import ru.alexbykov.nopaginate.callback.OnRepeatListener;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public interface ErrorItem {


    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    void onBindViewHolder(RecyclerView.ViewHolder holder, int position, OnRepeatListener onRepeatListener);

    ErrorItem DEFAULT = new ErrorItem() {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_error, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, final OnRepeatListener onRepeatListener) {

            Button btnRepeat = (Button) holder.itemView.findViewById(R.id.btnRepeat);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                btnRepeat.setBackgroundResource(R.drawable.no_pagination_button_ripple);
            } else {
                btnRepeat.setBackgroundResource(R.drawable.no_pagination_button_selector);
            }

            btnRepeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRepeatListener != null) {
                        onRepeatListener.onClickRepeat();
                    }
                }
            });
        }


    };


}
