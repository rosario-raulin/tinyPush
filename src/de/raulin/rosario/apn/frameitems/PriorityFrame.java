package de.raulin.rosario.apn.frameitems;

/**
 * Created by Rosario on 13/06/14.
 */
public class PriorityFrame implements FrameItem {
    private Priority priority;

    public PriorityFrame(Priority priority) {
        this.priority = priority;
    }

    @Override
    public byte id() {
        return 5;
    }

    @Override
    public short length() {
        return 1;
    }

    @Override
    public ItemData data() {
        return () -> new byte[]{priority.value()};
    }
}
