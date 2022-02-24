package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handlers.notificationHandlers;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.service.observers.notificationService.BooleanNotificationServiceObserver;

public abstract class BooleanNotificationHandler<T extends BooleanNotificationServiceObserver> extends BackgroundTaskHandler<T> {
    public BooleanNotificationHandler(T observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(T observer, Bundle data) {
        observer.handleSuccess(getBool(data));
    }

    protected abstract boolean getBool(Bundle data);
}
