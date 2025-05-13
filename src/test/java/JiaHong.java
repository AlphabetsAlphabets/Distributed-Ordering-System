import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dcoms.client.ClientInterface;
import dcoms.remote.RemoteInterface;
import dcoms.utils.Env;

@TestInstance(Lifecycle.PER_CLASS)
public class JiaHong {
    RemoteInterface fn;
    @BeforeAll
    void setup() throws RemoteException, MalformedURLException, NotBoundException {
        Env.loadEnv();
        new ClientInterface();
        fn = ClientInterface.getFunction();
    }

    @Test 
    void getUserRoleForExistingUser() {
        assertDoesNotThrow(() -> fn.getUserRole("Jayden"));
    }

    @Test 
    void getUserRoleForExistingUserAndIsAdmin() {
        assertDoesNotThrow(() -> {
            int role = fn.getUserRole("admin");
            assertEquals(1, role, "should have the role of 1.");
        });
    }

    @Test
    void getUserRoleForExistingUserAndIsClient() {
        assertDoesNotThrow(() -> {
            var role = fn.getUserRole("client");
            assertEquals(0, role, "should have the role of 0.");
        } );
    }

    @Test
    void getUserRoleForNotExistingUser() {
        assertThrows(SQLException.class, () -> fn.getUserRole("asdljalksdj"));
    }

    @Test
    void getQuantity() {
        assertDoesNotThrow(() -> {
            int quantity = fn.getQuantity("Nasi Lemak");
            assertTrue(quantity >= 0, "Quantity should be non-negative");
        });
    }

    @Test
    void getQuantityForExistingFood() {
        // Check that the method doesn't throw an exception AND that quantity is non-negative
        assertDoesNotThrow(() -> {
            int quantity = fn.getQuantity("Nasi Lemak");
            assertTrue(quantity >= 0, "Quantity should be non-negative for existing food");
        });
    }

    @Test
    void getQuantityForNonExistingFood() {
        assertDoesNotThrow(() -> {
            int quantity = fn.getQuantity("NonExistentFood");
            assertTrue(quantity == -1, "Quantity should be -1 for non-existing food");
        });
    }

    @Test
    void getQuantityWithNull() {
        assertDoesNotThrow(() -> {
            int quantity = fn.getQuantity(null);
            assertTrue(quantity == -1, "Quantity should be -1 for non-existing food");
        });
    }

    @Test
    void getQuantityWithEmptyString() {
        assertDoesNotThrow(() -> {
            int quantity = fn.getQuantity("");
            assertTrue(quantity == -1, "Quantity should be -1 for empty food name");
        });
    }
}
