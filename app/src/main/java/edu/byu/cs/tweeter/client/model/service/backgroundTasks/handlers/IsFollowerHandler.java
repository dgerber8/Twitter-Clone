package edu.byu.cs.tweeter.client.model.service.backgroundTasks.handler;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import edu.byu.cs.tweeter.client.model.service.MainService;
import edu.byu.cs.tweeter.client.model.service.backgroundTasks.IsFollowerTask;

public class IsFollowerHandler extends Handler {
    private MainService.IsFollowerObserver observer;

    public IsFollowerHandler(MainService.IsFollowerObserver observer) {
        this.observer = observer;
    }


    @Override
    public void handleMessage(@NonNull Message msg) {
        boolean success = msg.getData().getBoolean(IsFollowerTask.SUCCESS_KEY);
        if (success) {
            boolean isFollower = msg.getData().getBoolean(IsFollowerTask.IS_FOLLOWER_KEY);
            observer.handleSuccess(isFollower);
        } else if (msg.getData().containsKey(IsFollowerTask.MESSAGE_KEY)) {
            String message = msg.getData().getString(IsFollowerTask.MESSAGE_KEY);
            observer.handleFailure("Failed to determine following relationship: " + message);
        } else if (msg.getData().containsKey(IsFollowerTask.EXCEPTION_KEY)) {
            Exception ex = (Exception) msg.getData().getSerializable(IsFollowerTask.EXCEPTION_KEY);
            String message = "Failed to determine following relationship because of exception: " + ex.getMessage();
            observer.handleFailure(message);
        }
    }
}
