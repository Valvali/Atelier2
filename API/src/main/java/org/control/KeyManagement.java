package org.control;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class KeyManagement {

    public Key generateKey() {
        String keyString = "super secret key goes here";
        Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
        return key;
    }
}
