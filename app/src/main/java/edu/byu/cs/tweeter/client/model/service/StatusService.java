package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.byu.cs.tweeter.client.cache.Cache;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetFeedTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetStoryTask;
import edu.byu.cs.tweeter.model.domain.Status;
import edu.byu.cs.tweeter.model.domain.User;

public class StatusService {

    public interface StoryObserver {
        void handleSuccess(List<Status>Statuses, boolean hasMorePages) throws MalformedURLException;
        void handleFailure(String message);
    }

    public void LoadMoreItems(User user, int PAGE_SIZE, Status lastStatus, StoryObserver storyObserver) {
        GetStoryTask getStoryTask = new GetStoryTask(Cache.getInstance().getCurrUserAuthToken(),
                user, PAGE_SIZE, lastStatus, new StatusService.GetStoryHandler(storyObserver));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getStoryTask);
    }

    public void LoadFeedItems(User user, int pageSize, Status lastStatus, StoryObserver storyObserver) {
        GetFeedTask getFeedTask = new GetFeedTask(Cache.getInstance().getCurrUserAuthToken(),
                user, pageSize, lastStatus, new StatusService.GetFeedHandler(storyObserver));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(getFeedTask);
    }

    /**
     * Message handler (i.e., observer) for GetStoryTask.
     */
    private class GetStoryHandler extends Handler {

        private StatusService.StoryObserver observer;

        public GetStoryHandler(StatusService.StoryObserver observer) {
            this.observer = observer;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {

            boolean success = msg.getData().getBoolean(GetStoryTask.SUCCESS_KEY);
            if (success) {
                List<Status> statuses = (List<Status>) msg.getData().getSerializable(GetStoryTask.STATUSES_KEY);
                boolean hasMorePages = msg.getData().getBoolean(GetStoryTask.MORE_PAGES_KEY);
                try {
                    observer.handleSuccess(statuses, hasMorePages);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (msg.getData().containsKey(GetStoryTask.MESSAGE_KEY)) {
                String message = msg.getData().getString(GetStoryTask.MESSAGE_KEY);
                message = "Failed to get story: " + message;
                observer.handleFailure(message);
            } else if (msg.getData().containsKey(GetStoryTask.EXCEPTION_KEY)) {
                Exception ex = (Exception) msg.getData().getSerializable(GetStoryTask.EXCEPTION_KEY);
                String message = "Failed to get story because of exception: " + ex.getMessage();
                observer.handleFailure(message);
            }
        }
    }

    private class GetFeedHandler extends Handler {
        private StatusService.StoryObserver observer;
        public GetFeedHandler(StatusService.StoryObserver observer) {
            this.observer = observer;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            boolean success = msg.getData().getBoolean(GetFeedTask.SUCCESS_KEY);
            if (success) {
                List<Status> statuses = (List<Status>) msg.getData().getSerializable(GetFeedTask.STATUSES_KEY);
                boolean hasMorePages = msg.getData().getBoolean(GetFeedTask.MORE_PAGES_KEY);
                try {
                    observer.handleSuccess(statuses, hasMorePages);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            } else if (msg.getData().containsKey(GetFeedTask.MESSAGE_KEY)) {
                String message = msg.getData().getString(GetFeedTask.MESSAGE_KEY);
                observer.handleFailure("Failed to get feed: " + message);
            } else if (msg.getData().containsKey(GetFeedTask.EXCEPTION_KEY)) {
                Exception ex = (Exception) msg.getData().getSerializable(GetFeedTask.EXCEPTION_KEY);
                String message = "Failed to get feed because of exception: " + ex.getMessage();
                observer.handleFailure(message);
            }
        }
    }
}
