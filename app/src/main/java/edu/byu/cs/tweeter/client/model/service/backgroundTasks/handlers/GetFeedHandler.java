package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import android.os.Bundle;

import java.util.List;

import edu.byu.cs.tweeter.client.model.service.StatusService;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.GetFeedTask;
import edu.byu.cs.tweeter.model.domain.Status;

public class GetFeedHandler extends PagedNotificationHandler {
    public GetFeedHandler(StatusService.StoryObserver observer) { super(observer); }

    @Override
    protected String getFailedMessagePrefix() {
        return "Failed to get feed";
    }

    @Override
    protected boolean getPages(Bundle data) {
        return data.getBoolean(GetFeedTask.MORE_PAGES_KEY);
    }

    @Override
    protected List getItems(Bundle data) {
        return (List<Status>) data.getSerializable(GetFeedTask.ITEMS_KEY);
    }
}
