package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handlers.notificationHandlers;

import android.os.Bundle;

import edu.byu.cs.tweeter.client.model.service.observers.notificationService.MessageNotificationServiceObserver;

public abstract class MessageNotificationHandler<T extends MessageNotificationServiceObserver> extends BackgroundTaskHandler<T> {
    public MessageNotificationHandler(T observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(T observer, Bundle data) {
        observer.handleSuccess(getMessage());
    }

    protected abstract String getMessage();
}
