package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import android.os.Bundle;

import java.util.List;

import edu.byu.cs.tweeter.client.model.service.FollowersService;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetFollowersTask;
import edu.byu.cs.tweeter.model.domain.User;

public class GetFollowersHandler extends PagedNotificationHandler {
    public GetFollowersHandler(FollowersService.FollowersObserver observer) {
        super(observer);
    }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to get followers";
    }

    @Override
    protected boolean getPages(Bundle data) {
        return data.getBoolean(GetFollowersTask.MORE_PAGES_KEY);
    }

    @Override
    protected List getItems(Bundle data) {
        return (List<User>) data.getSerializable(GetFollowersTask.ITEMS_KEY);
    }
}
