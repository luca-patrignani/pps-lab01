package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl lock;


    @BeforeEach
    public void beforeEach() {
        lock = new SmartDoorLockImpl();
    }

    @Test
    public void testLockIsInitiallyLocked() {
        assertFalse(lock.isLocked());
    }
}
