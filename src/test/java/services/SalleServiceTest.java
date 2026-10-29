package services;

import entities.Salle;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test unitaire validant le fonctionnement du service Salle.
 */
public class SalleServiceTest {

    @Test
    public void testCreateAndGetSalle() {
        SalleService service = new SalleService();
        Salle salle = new Salle("TEST-ROOM", "Salle de test unitaire");
        boolean created = service.create(salle);
        Assert.assertTrue("La creation de salle doit retourner true", created);

        Salle found = service.getById(salle.getId());
        Assert.assertNotNull("La salle creee doit pouvoir etre recuperee", found);
        Assert.assertEquals("Le code doit correspondre", "TEST-ROOM", found.getCode());
    }
}
