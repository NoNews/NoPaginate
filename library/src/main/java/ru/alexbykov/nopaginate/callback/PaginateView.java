package ru.alexbykov.nopaginate.callback;

/**
 * Date: 12.08.2017
 * Time: 18:48
 * Project: NoPagination
 *
 * @author Alex Bykov
 *         You can contact me at me@alexbykov.ru
 */
public interface PaginateView {

    void showPaginateLoading(boolean show);

    void showPaginateError(boolean show);

    void setPaginateNoMoreData(boolean show);
}
