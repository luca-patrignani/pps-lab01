package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLockImpl lock;
    private static final int INITIAL_PIN = 1234;
    private static final int WRONG_PIN = 9999;
    private static final int NEW_PIN = 8008;

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
        for (int i = 0; i < lock.getMaxAttempts()-1; i++) {
            lock.unlock(WRONG_PIN);
        }
        assertEquals(lock.getMaxAttempts()-1, lock.getFailedAttempts());
        assertFalse(lock.isBlocked());
    }

    private void blockLock(final SmartDoorLock lock) {
        lock.lock();
        for (int i = 0; i < lock.getMaxAttempts(); i++) {
            lock.unlock(WRONG_PIN);
        }
    }

    @Test
    public void testTooManyAttempts() {
        lock.lock();
        blockLock(lock);
        assertEquals(lock.getMaxAttempts(), lock.getFailedAttempts());
        assertTrue(lock.isBlocked());
    }

    @Test
    public void testUnlockWhenBlocked() {
        blockLock(lock);
        lock.unlock(INITIAL_PIN);
        assertTrue(lock.isLocked());
    }

    @Test
    public void testReset() {
        blockLock(lock);
        lock.reset();
        assertFalse(lock.isLocked());
        assertFalse(lock.isBlocked());
        assertEquals(0, lock.getFailedAttempts());
    }

    @Test
    public void test5DigitPin() {
        assertThrows(IllegalArgumentException.class, () -> lock.setPin(12345));
    }
}
