package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class FollowHandler extends SimpleNotificationHandler {
    public FollowHandler(MainService.FollowObserver observer) {
        super(observer);
    }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to follow";
    }
}
