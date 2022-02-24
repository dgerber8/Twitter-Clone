package edu.byu.cs.tweeter.client.model.service.observers;

import edu.byu.cs.tweeter.client.model.service.observers.ServiceObserver;
import edu.byu.cs.tweeter.model.domain.User;

public interface UserNotificationServiceObserver extends ServiceObserver {
    void handleSuccess(User user);
}
