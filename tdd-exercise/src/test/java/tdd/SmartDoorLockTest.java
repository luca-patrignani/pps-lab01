package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl lock;
    private static final int INITIAL_PIN = 1234;
    private static final int WRONG_PIN = 9999;

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

    @Test
    public void testLockAndThenUnlockWrong() {
        lock.lock();
        lock.unlock(WRONG_PIN);
        assertTrue(lock.isLocked());
    }
}
