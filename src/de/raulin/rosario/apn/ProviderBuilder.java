package de.raulin.rosario.apn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosario on 14/06/14.
 */
public class ProviderBuilder {

    private String serverUrl = Provider.PUSH_SERVER_URL;
    private int serverPort = Provider.PUSH_SERVER_PORT;

    private String feedbackServerUrl = Provider.FEEDBACK_SERVER_URL;
    private int feedbackServerPort = Provider.FEEDBACK_SERVER_PORT;

    private String certPath;
    private String certPass = "";

    private List<Notification> notifications;

    public ProviderBuilder() {
        this.notifications = new ArrayList<>();
    }

    public ProviderBuilder serverUrl(String url) {
        this.serverUrl = url;
        return this;
    }

    public ProviderBuilder serverPort(int port) {
        this.serverPort = port;
        return this;
    }

    public ProviderBuilder certificatePath(String path) {
        this.certPath = path;
        return this;
    }

    public ProviderBuilder certificatePassword(String password) {
        this.certPass = password;
        return this;
    }

    public ProviderBuilder feedbackServerUrl(String url) {
        this.feedbackServerUrl = url;
        return this;
    }

    public ProviderBuilder feedbackServerPort(int port) {
        this.feedbackServerPort = port;
        return this;
    }

    public ProviderBuilder addNotification(Notification notification) {
        this.notifications.add(notification);
        return this;
    }

    public Provider build() {
        if (certPath == null) {
            throw new IllegalArgumentException("Valid certificate missing");
        }

        if (notifications.isEmpty()) {
            throw new IllegalArgumentException("Notifications missing");
        }

        return new Provider(serverUrl, serverPort, certPath, certPass, notifications);
    }
}

