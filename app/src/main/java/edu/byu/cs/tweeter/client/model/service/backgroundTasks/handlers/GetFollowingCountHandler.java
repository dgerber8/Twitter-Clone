package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class GetFollowingCountHandler extends CountNotificationHandler {
    public GetFollowingCountHandler(MainService.GetFollowCountObserver observer) {
        super(observer);
    }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to get following count";
    }
}
