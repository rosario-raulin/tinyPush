package de.raulin.rosario.apn.frameitems;

/**
 * Created by Rosario on 13/06/14.
 */
public class PayloadFrame implements FrameItem {

    private String jsonData;

    public PayloadFrame(String jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public byte id() {
        return 2;
    }

    @Override
    public short length() {
        return (short)data().getBytes().length;
    }

    @Override
    public ItemData data() {
        return jsonData::getBytes;
    }
}
