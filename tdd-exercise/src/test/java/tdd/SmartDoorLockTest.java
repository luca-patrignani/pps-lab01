package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl lock;
    private static final int INITIAL_PIN = 1234;

    @BeforeEach
    public void beforeEach() {
        lock = new SmartDoorLockImpl();
        lock.setPin(INITIAL_PIN);
    }

    @Test
    public void testLockIsInitiallyLocked() {
        assertFalse(lock.isLocked());
    }

    @Test
    public void testLock() {
        lock.lock();
        assertTrue(lock.isLocked());
    }

    @Test
    public void testLockAndThenUnlock() {
        lock.lock();
        lock.unlock(INITIAL_PIN);
        assertFalse(lock.isLocked());
    }
}
