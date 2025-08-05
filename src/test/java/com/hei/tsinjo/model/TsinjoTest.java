package com.hei.tsinjo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TsinjoTest {

    @Test
    public void help_ok() {
        // Créer un compte Tsinjo avec un solde initial
        Tsinjo tsinjo = new Tsinjo(1000L);

        // Vérifier le solde initial
        assertNotNull(tsinjo.getBalance());
        assertEquals(1000L, tsinjo.getBalance());

        // Effectuer une aide de 250
        tsinjo.help(250L);

        // Vérifier que le solde a diminué
        assertNotNull(tsinjo.getBalance());
        assertEquals(750L, tsinjo.getBalance());
    }
}
