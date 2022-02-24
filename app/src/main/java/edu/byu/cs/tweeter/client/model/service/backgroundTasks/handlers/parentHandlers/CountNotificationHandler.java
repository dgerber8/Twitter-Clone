package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handlers.notificationHandlers;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.service.observers.notificationService.CountNotificationServiceObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetFollowersCountTask;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetFollowingCountTask;

public abstract class CountNotificationHandler<T extends CountNotificationServiceObserver> extends BackgroundTaskHandler<T> {
    public CountNotificationHandler(T observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(T observer, Bundle data) {
        int count;
        count = data.getInt(GetFollowersCountTask.COUNT_KEY);
        observer.handleSuccessFollower(count);
        count = data.getInt(GetFollowingCountTask.COUNT_KEY);
        observer.handleSuccessFollowee(count);
    }
}
