package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl lock;
    private static final int INITIAL_PIN = 1234;
    private static final int WRONG_PIN = 9999;
    private static final int NEW_PIN = 8008;
    private static final int MAX_ATTEMPTS = 10;

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

    @Test
    public void testChangePin() {
        lock.unlock(INITIAL_PIN);
        lock.setPin(NEW_PIN);
        lock.lock();
        lock.unlock(INITIAL_PIN);
        assertTrue(lock.isLocked());
        lock.unlock(NEW_PIN);
        assertFalse(lock.isLocked());
    }

    @Test
    public void testManyAttempts() {
        lock.lock();
        for (int i = 0; i < MAX_ATTEMPTS-1; i++) {
            lock.unlock(WRONG_PIN);
        }
        assertEquals(MAX_ATTEMPTS-1, lock.getFailedAttempts());
        assertFalse(lock.isBlocked());
    }

    @Test
    public void testTooManyAttempts() {
        lock.lock();
        for (int i = 0; i < MAX_ATTEMPTS; i++) {
            lock.unlock(WRONG_PIN);
        }
        assertEquals(MAX_ATTEMPTS, lock.getFailedAttempts());
        assertTrue(lock.isBlocked());
    }
}
