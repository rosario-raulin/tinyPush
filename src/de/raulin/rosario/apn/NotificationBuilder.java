package de.raulin.rosario.apn;

import de.raulin.rosario.apn.frameitems.*;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Created by Rosario on 13/06/14.
 */
public class NotificationBuilder {

    private final static String MISSING_ARGUMENT_MESSAGE
            = "Notification need a token, a payload, an identifier, an expiration date and  a priority";

    private DeviceTokenFrame token;
    private PayloadFrame payload;
    private IdentifierFrame identifier;
    private ExpirationDate expirationDate;
    private PriorityFrame priority;

    public NotificationBuilder tokenFromBytes(byte[] toToken) {
        this.token = new DeviceTokenFrame(toToken);
        return this;
    }

    public NotificationBuilder tokenFromBase64(String base64Token) {
        Base64.Decoder decoder = Base64.getDecoder();
        this.token = new DeviceTokenFrame(decoder.decode(base64Token));
        return this;
    }

    public NotificationBuilder jsonPayload(String payload) {
        this.payload = new PayloadFrame(payload);
        return this;
    }

    public NotificationBuilder randomIdentifier(SecureRandom randomGenerator) {
        this.identifier = new IdentifierFrame(randomGenerator);
        return this;
    }

    public NotificationBuilder identifier(byte[] identifier) {
        this.identifier = new IdentifierFrame(identifier);
        return this;
    }

    public NotificationBuilder expiresInMinutes(int offsetInMinutes) {
        this.expirationDate = new ExpirationDate(offsetInMinutes);
        return this;
    }

    public NotificationBuilder priority(Priority prio) {
        this.priority = new PriorityFrame(prio);
        return this;
    }

    public Notification build() {
        if (token == null || payload == null || identifier == null || expirationDate == null || priority == null) {
            throw new IllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }

        Notification notification = new Notification();

        notification.addFrameItem(token);
        notification.addFrameItem(payload);
        notification.addFrameItem(identifier);
        notification.addFrameItem(expirationDate);
        notification.addFrameItem(priority);

        return notification;
    }
}
