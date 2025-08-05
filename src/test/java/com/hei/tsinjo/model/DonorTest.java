package com.hei.tsinjo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class DonorTest {

  @Test
  public void doDonation_ok() throws SQLException {
    // Créer un donateur
    Donor donor = new Donor(1000L);
    Long subject = donor.getBalance();

    // Vérifier le solde initial
    assertNotNull(donor.getBalance());
    assertEquals(1000L, subject);

    // Effectuer un don
    donor.doDonation(500L);

    // Vérifier le solde mis à jour
    assertNotNull(donor.getBalance());
    assertEquals(500L, donor.getBalance());
  }
}
