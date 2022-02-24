package edu.byu.cs.tweeter.client.model.service.backgroundTasks;

import android.os.Bundle;
import android.os.Handler;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

public abstract class CountTask extends AuthorizedTask {
    public static final String COUNT_KEY = "count";
    private User targetUser;

    public CountTask(Handler messageHandler, User targetUser, AuthToken authToken) {
        super(messageHandler, authToken);
        this.targetUser = targetUser;
    }

    @Override
    protected void loadMessageBundle(Bundle msgBundle) {
        msgBundle.putInt(COUNT_KEY, 20);
    }
}
