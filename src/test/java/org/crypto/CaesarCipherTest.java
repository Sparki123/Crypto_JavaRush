package org.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaesarCipherTest {

    private final CaesarCipher caesarCipher = new CaesarCipher();

    @Test
    void encryptTest() {
        String actual = caesarCipher.encrypt("This is for Test", 4);
        Assertions.assertEquals(actual, "Xlmw/mw/jsv/Xiwx");
    }

    @Test
    void decryptTest() {
        String actual = caesarCipher.decode("Xlmw/mw/jsv/Xiwx", 4);
        Assertions.assertEquals(actual, "This is for Test");
    }
}