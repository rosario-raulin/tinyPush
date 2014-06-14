package de.raulin.rosario.apn.frameitems;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by Rosario on 13/06/14.
 */
public class ExpirationDate implements FrameItem {

    private int timestamp;

    public ExpirationDate(int offsetInMinutes) {
        Instant now = Instant.now();
        this.timestamp = (int)(now.getEpochSecond() + Duration.ofMinutes(offsetInMinutes).getSeconds());
    }

    @Override
    public byte id() {
        return 4;
    }

    @Override
    public short length() {
        return 4;
    }

    @Override
    public ItemData data() {
        return () -> ByteBuffer.allocate(4).putInt(timestamp).array();
    }
}
