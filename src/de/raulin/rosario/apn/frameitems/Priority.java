package de.raulin.rosario.apn.frameitems;

/**
 * Created by Rosario on 14/06/14.
 */
public enum Priority {
    IMMEDIATELY ((byte)10),
    BATTERY_SAVING ((byte)5);

    private byte value;

    Priority(byte value) {
        this.value = value;
    }

    public byte value() {
        return value;
    }
}
