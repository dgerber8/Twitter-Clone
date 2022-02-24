package edu.byu.cs.tweeter.client.model.service.observers;

import java.net.MalformedURLException;
import java.util.List;

public interface PagedNotificationServiceObserver<T> extends ServiceObserver {
    void handleSuccess(List<T> items, boolean hasMorePages) throws MalformedURLException;
}
