package de.raulin.rosario.apn;

import de.raulin.rosario.apn.frameitems.FrameItem;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rosario on 13/06/14.
 */
public class Notification {

    private final List<FrameItem> frameItems;

    Notification() {
        this.frameItems = new ArrayList<>();
    }

    void addFrameItem(FrameItem item) {
        frameItems.add(item);
    }

    public int length() {
        return 1 // Command
        + 4 // Frame length
        + frameItems.stream().mapToInt(FrameItem::length).sum() // frame item data
        + frameItems.size() * (1 + 2); // frame item id + frame item length
    }

    public byte[] getBytes() {
        int length = length();
        ByteBuffer buffer = ByteBuffer.allocate(length);

        int payloadLen = length - 1 - 4;
        buffer.put((byte) 2);
        buffer.putInt(payloadLen);

        for (FrameItem item : frameItems) {
            buffer.put(item.id());
            buffer.putShort(item.length());
            buffer.put(item.data().getBytes());
        }

        return buffer.array();
    }
}
