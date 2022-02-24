package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import edu.byu.cs.tweeter.client.model.service.MainService;

public class LogoutHandler extends SimpleNotificationHandler {
    public LogoutHandler(MainService.LogoutObserver observer) {
        super(observer);
    }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to logout";
    }
}
