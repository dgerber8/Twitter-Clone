package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handlers.notificationHandlers;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.service.observers.notificationService.SimpleNotificationServiceObserver;

public abstract class SimpleNotificationHandler<T extends SimpleNotificationServiceObserver> extends BackgroundTaskHandler<T> {

    public SimpleNotificationHandler(T observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(T observer, Bundle data) {
        observer.handleSuccess();
    }
}
