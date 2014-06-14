package de.raulin.rosario.apn.frameitems;

/**
 * Created by Rosario on 13/06/14.
 */
public class DeviceTokenFrame implements FrameItem {

    private byte[] toToken;

    public DeviceTokenFrame(byte[] toToken) {
        this.toToken = toToken;
    }

    @Override
    public byte id() {
        return 1;
    }

    @Override
    public short length() {
        return 32;
    }

    @Override
    public ItemData data() {
        return () -> toToken;
    }
}
