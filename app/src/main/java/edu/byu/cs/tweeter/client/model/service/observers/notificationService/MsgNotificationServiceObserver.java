package edu.byu.cs.tweeter.client.model.service.observers.notificationService;

import edu.byu.cs.tweeter.client.model.service.observers.ServiceObserver;

public interface MessageNotificationServiceObserver extends ServiceObserver {
    void handleSuccess(String message);
}
