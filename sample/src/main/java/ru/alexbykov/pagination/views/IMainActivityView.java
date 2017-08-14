package ru.alexbykov.pagination.views;

import java.util.List;

import ru.alexbykov.nopaginate.callback.PaginateView;

/**
 * Created by Alex Bykov on 11.08.2017.
 * You can contact me at: me@alexbykov.ru.
 */

public interface IMainActivityView extends PaginateView {

    void addItems(List<Integer> items);

}
