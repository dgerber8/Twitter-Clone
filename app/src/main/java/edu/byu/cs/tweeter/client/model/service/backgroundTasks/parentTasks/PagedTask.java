package edu.byu.cs.tweeter.client.model.service.backgroundTasks;

import android.os.Bundle;
import android.os.Handler;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.util.Pair;

public abstract class PagedTask<T> extends AuthorizedTask {
    public static final String ITEMS_KEY = "items";
    public static final String MORE_PAGES_KEY = "more-pages";
    private int limit;

    private T lastItem;

    private List<T> items;
    private boolean hasMorePages;

    public T getLastItem() {
        return lastItem;
    }

    public int getLimit() {
        return limit;
    }

    public PagedTask(Handler messageHandler, int limit, T lastItem, AuthToken authToken) {
        super(messageHandler, authToken);
        this.limit = limit;
        this.lastItem = lastItem;
    }

    @Override
    protected void runTask() throws IOException {
        Pair<List<T>, Boolean> pageOfItems = getItems();
        items = pageOfItems.getFirst();
        hasMorePages = pageOfItems.getSecond();

        loadImages(items);
    }

    protected abstract Pair<List<T>, Boolean> getItems();

    @Override
    protected void loadMessageBundle(Bundle msgBundle) {
        msgBundle.putSerializable(ITEMS_KEY, (Serializable) items);
        msgBundle.putBoolean(MORE_PAGES_KEY, hasMorePages);
    }

    private void loadImages(List<T> followees) throws IOException {
        for (User u : convertItemsToUsers(followees)) {
            BackgroundTaskUtils.loadImage(u);
        }
    }

    protected abstract List<User> convertItemsToUsers(List<T> items);
}
