package edu.byu.cs.tweeter.client.model.service.observers.notificationService;

import edu.byu.cs.tweeter.client.model.service.observers.ServiceObserver;

public interface CountNotificationServiceObserver extends ServiceObserver {
    void handleSuccessFollower(int count);
    void handleSuccessFollowee(int count);
}
