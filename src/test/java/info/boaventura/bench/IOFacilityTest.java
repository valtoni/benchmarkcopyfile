package info.boaventura.bench;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class IOFacilityTest {
    @Test
    void closeResourceAcceptsNull() {
        assertDoesNotThrow(() -> IOFacility.closeResource(null));
    }
}
