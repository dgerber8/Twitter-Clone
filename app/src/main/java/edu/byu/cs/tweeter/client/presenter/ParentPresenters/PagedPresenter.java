package edu.byu.cs.tweeter.client.presenter;

import java.net.MalformedURLException;
import java.util.List;

import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.client.model.service.observers.GetUserObserver;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class PagedPresenter<T> extends ParentPresenter {

    public interface View<T> extends ParentPresenter.View {
        void displayInfoMessage(String s);
        void displayUser(User user);
        void clearInfoMessage();
        void displayMoreItems(List<T> items, boolean hasMorePages);
        void addLoadingFooter() throws MalformedURLException;
        void removeLoadingFooter();
    }

    public PagedPresenter(View view) {
        super(view);
        this.view = view;
        userService = new UserService();
    }

    private View view;
    private UserService userService;

    private static final int PAGE_SIZE = 10;

    private boolean hasMorePages = true;
    private boolean isLoading = false;

    protected boolean parentIsLoading() {
        return isLoading;
    }

    protected boolean isHasMorePages() { return hasMorePages; }

    protected static int getPageSize() { return PAGE_SIZE; }

    protected void loadStartup() throws MalformedURLException {
        isLoading = true;
        view.addLoadingFooter();
    }

    protected T lastItem(List<T> items) {
        return (items.size() > 0) ? items.get(items.size() - 1) : null;
    }

    protected void pagedSuccess(List<T> items, boolean hasMorePages) {
        this.hasMorePages = hasMorePages;

        isLoading = false;

        view.removeLoadingFooter();
        view.displayMoreItems(items, hasMorePages);
    }

    protected void pagedFailure(String message) {
        isLoading = false;
        view.removeLoadingFooter();
        view.displayErrorMessage(message);
    }

    public void getUser(String alias) {
        view.displayInfoMessage("Getting user's profile...");
        userService.getUser(alias, new GetUserObserver() {

            @Override
            public void handleSuccess(User user) {
                view.clearInfoMessage();
                view.displayUser(user);
            }

            @Override
            public void handleFailure(String message) {
                view.clearInfoMessage();
                view.displayErrorMessage(message);
            }
        });
    }
}
