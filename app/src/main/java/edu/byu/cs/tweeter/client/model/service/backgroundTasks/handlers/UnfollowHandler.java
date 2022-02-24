package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class UnfollowHandler extends SimpleNotificationHandler {
    public UnfollowHandler(MainService.UnfollowObserver observer) {
        super(observer);
    }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to unfollow";
    }
}
