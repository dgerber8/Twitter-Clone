package edu.byu.cs.tweeter.client.presenter;

import android.content.Intent;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.FollowService;
import edu.byu.cs.tweeter.client.model.service.UserService;
import edu.byu.cs.tweeter.client.view.main.MainActivity;
import edu.byu.cs.tweeter.model.domain.User;

public class FollowingPresenter {

    public interface FollowingView {
        void displayErrorMessage(String message);
        void displayMoreItems(List<User> followees, boolean hasMorePages);
        void removeLoadingFooter();
        void startUser(User user);
        void displayInfoMessage(String message);
        void clearInfoMessage();
    }

    private User lastFollowee;

    private boolean hasMorePages;
    private boolean isLoading = false;

    private FollowingView view;
    private FollowService followService;
    private UserService userService;

    public FollowingPresenter(FollowingView view) {
        this.view = view;
        followService = new FollowService();
        userService = new UserService();
    }

    public void loadMoreItems(User user, int pageSize, User lastFollowee) {
        view.removeLoadingFooter();
        followService.loadMoreItems(user, pageSize, lastFollowee, new FollowService.FollowingObserver() {
            @Override
            public void handleSuccess(List<User> followees, boolean hasMorePages) {
                view.displayMoreItems(followees, hasMorePages);
            }

            @Override
            public void handleFailure(String message) {
                view.displayErrorMessage(message);
            }
        });
    }

    public void getUser(String alias) {
        view.displayInfoMessage("Getting user's profile...");
        userService.getUser(alias, new UserService.GetUserObserver() {

            @Override
            public void handleSuccess(User user) {
                view.clearInfoMessage();
                view.startUser(user);
            }

            @Override
            public void handleFailure(String message) {
                view.clearInfoMessage();
                view.displayErrorMessage(message);
            }
        });

        //where will this go?
    }

    public class GetUserObserver implements UserService.GetUserObserver {

        @Override
        public void handleSuccess(User user) {
            view.clearInfoMessage();
            view.startUser(user);
        }

        @Override
        public void handleFailure(String message) {
            view.clearInfoMessage();
            view.displayErrorMessage(message);
        }
    }

}
