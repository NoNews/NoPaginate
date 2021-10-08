package ru.alexbykov.pagination.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.alexbykov.nopaginate.paginate.NoPaginate;
import ru.alexbykov.pagination.R;
import ru.alexbykov.pagination.adapters.TestAdapter;
import ru.alexbykov.pagination.presenters.MainActivityPresenter;
import ru.alexbykov.pagination.views.IMainActivityView;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    private static final int LAYOUT = R.layout.activity_main;

    private TestAdapter adapter;
    private MainActivityPresenter mainActivityPresenter;
    private RecyclerView recyclerView;
    private NoPaginate noPaginate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        setupRecycler();
        mainActivityPresenter = new MainActivityPresenter(this);
        setupPagination();
    }

    private void setupRecycler() {
        adapter = new TestAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupPagination() {
        noPaginate = NoPaginate.with(recyclerView)
                .setOnLoadMoreListener(mainActivityPresenter)
                .setLoadingTriggerThreshold(3)
                .build();
    }


    @Override
    public void addItems(@NonNull List<Integer> items) {
        adapter.addItems(items);
    }


    @Override
    public void showPaginateLoading(boolean isPaginateLoading) {
        noPaginate.showLoading(isPaginateLoading);
    }

    @Override
    public void showPaginateError(boolean isPaginateError) {
        noPaginate.showError(isPaginateError);
    }

    @Override
    public void setPaginateNoMoreData(boolean isNoMoreItems) {
        noPaginate.setNoMoreItems(isNoMoreItems);
    }


    @Override
    public void onDestroy() {
        noPaginate.unbind();
        super.onDestroy();
    }
}
