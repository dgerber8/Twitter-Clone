package edu.byu.cs.tweeter.client.model.service.observers.notificationService;

import edu.byu.cs.tweeter.client.model.service.observers.ServiceObserver;

public interface SimpleNotificationServiceObserver extends ServiceObserver {
    void handleSuccess();
}
