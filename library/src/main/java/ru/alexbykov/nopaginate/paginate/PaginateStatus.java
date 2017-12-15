package ru.alexbykov.nopaginate.paginate;

/**
 * Date: 12.08.2017
 * Time: 17:44
 * Project: NoPagination
 *
 * @author Alex Bykov
 *         You can contact me at me@alexbykov.ru
 */
enum PaginateStatus {

    NO_MORE_ITEMS, LOADING, ERROR;

    public static PaginateStatus getStatus(boolean loadedAllItems, boolean adapterError) {
        if (loadedAllItems) return NO_MORE_ITEMS;
        else if (adapterError) {
            return ERROR;
        } else {
            return LOADING;
        }
    }
}
