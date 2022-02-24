package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handlers.notificationHandlers;

import android.os.Bundle;

import java.net.MalformedURLException;
import java.util.List;

import edu.byu.cs.tweeter.client.model.service.observers.notificationService.PagedNotificationServiceObserver;

public abstract class PagedNotificationHandler<T extends PagedNotificationServiceObserver> extends BackgroundTaskHandler<T> {
    public PagedNotificationHandler(T observer) {
        super(observer);
    }

    @Override
    protected void handleSuccessMessage(T observer, Bundle data) throws MalformedURLException {
        observer.handleSuccess(getItems(data), getPages(data));
    }

    protected abstract boolean getPages(Bundle data);

    protected abstract List getItems(Bundle data);
}
