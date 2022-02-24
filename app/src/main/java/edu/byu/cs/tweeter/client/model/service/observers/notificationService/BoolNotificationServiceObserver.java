package edu.byu.cs.tweeter.client.model.service.observers.notificationService;

import edu.byu.cs.tweeter.client.model.service.observers.ServiceObserver;

public interface BooleanNotificationServiceObserver extends ServiceObserver {
    void handleSuccess(boolean b);
}
