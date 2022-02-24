package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handlers.notificationHandlers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.net.MalformedURLException;

import edu.byu.cs.tweeter.client.model.service.observers.ServiceObserver;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.FollowTask;

public abstract class BackgroundTaskHandler<T extends ServiceObserver> extends Handler {

    private T observer;

    public BackgroundTaskHandler(T observer) {
        this.observer = observer;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        boolean success = msg.getData().getBoolean(FollowTask.SUCCESS_KEY);
        if (success) {
            try {
                handleSuccessMessage(observer, msg.getData());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        } else if (msg.getData().containsKey(FollowTask.MESSAGE_KEY)) {
            String message = getFailedMessagePrefix() + ": " + msg.getData().getString(FollowTask.MESSAGE_KEY);
            observer.handleFailure(message);
        } else if (msg.getData().containsKey(FollowTask.EXCEPTION_KEY)) {
            Exception ex = (Exception) msg.getData().getSerializable(FollowTask.EXCEPTION_KEY);
            String message = getFailedMessagePrefix() + " because of exception: " + ex.getMessage();
            observer.handleFailure(message);
        }
    }

    protected abstract void handleSuccessMessage(T observer, Bundle data) throws MalformedURLException;

    protected abstract String getFailedMessagePrefix();

}
