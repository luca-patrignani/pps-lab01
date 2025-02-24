package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private int pin;
    private boolean locked;
    private int failedAttempts;
    private static final int MAX_ATTEMPTS = 10;


    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (pin == this.pin) {
            locked = false;
        } else {
            failedAttempts++;
        }
    }

    @Override
    public void lock() {
        locked = true;
    }

    @Override
    public boolean isLocked() {
        return locked;
    }

    @Override
    public boolean isBlocked() {
        return failedAttempts >= MAX_ATTEMPTS;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return failedAttempts;
    }

    @Override
    public void reset() {
        this.locked = false;
        this.failedAttempts = 0;
    }
}
