package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import android.os.Bundle;

import java.util.List;

import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetStoryTask;
import edu.byu.cs.tweeter.model.domain.Status;

public class GetStoryHandler extends PagedNotificationHandler {
    public GetStoryHandler(StatusService.StoryObserver observer) {
        super(observer);
    }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to get story";
    }

    @Override
    protected boolean getPages(Bundle data) {
        return data.getBoolean(GetStoryTask.MORE_PAGES_KEY);
    }

    @Override
    protected List getItems(Bundle data) {
        return (List<Status>) data.getSerializable(GetStoryTask.ITEMS_KEY);
    }
}
