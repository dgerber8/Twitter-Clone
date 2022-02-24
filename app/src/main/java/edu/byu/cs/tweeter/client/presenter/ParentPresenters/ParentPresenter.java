package edu.byu.cs.tweeter.client.presenter;

import android.widget.EditText;

import edu.byu.cs.tweeter.client.model.service.MainService;

public abstract class ParentPresenter {

    public interface View {
        void displayErrorMessage(String message);
    }

    public ParentPresenter(View view) {
        this.view = view;
    }

    private View view;

    protected void failureMessage(String message) {
        view.displayErrorMessage(message);
    }

    protected void authenticateLogin(EditText alias, EditText password) {
        if (alias.getText().charAt(0) != '@') {
            throw new IllegalArgumentException("Alias must begin with @.");
        }
        if (alias.getText().length() < 2) {
            throw new IllegalArgumentException("Alias must contain 1 or more characters after the @.");
        }
        if (password.getText().length() == 0) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }

}
