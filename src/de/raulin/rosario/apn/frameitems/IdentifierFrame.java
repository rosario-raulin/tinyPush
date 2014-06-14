package de.raulin.rosario.apn.frameitems;

import java.security.SecureRandom;

/**
 * Created by Rosario on 13/06/14.
 */
public class IdentifierFrame implements FrameItem {

    private final byte[] identifier;

    public IdentifierFrame(SecureRandom randomGenerator) {
        this.identifier = new byte[4];
        randomGenerator.nextBytes(identifier);
    }

    public IdentifierFrame(byte[] identifier) {
        if (identifier.length != 4) {
            throw new IllegalArgumentException("identifiers must be 4 bytes long");
        }
        this.identifier = new byte[4];
        System.arraycopy(identifier, 0, this.identifier, 0, identifier.length);
    }

    @Override
    public byte id() {
        return 3;
    }

    @Override
    public short length() {
        return 4;
    }

    @Override
    public ItemData data() {
        return () -> identifier;
    }
}
