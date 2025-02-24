package tdd;

import java.util.Optional;

public class SmartDoorLockImpl implements SmartDoorLock {
    private Optional<Integer> pin = Optional.empty();
    private boolean locked;
    private int failedAttempts;
    private static final int MAX_ATTEMPTS = 10;


    @Override
    public void setPin(int pin) throws IllegalArgumentException {
        if (!hasFourDigits(pin)) {
            throw new IllegalArgumentException("The pin has more than 5 digits.");
        }
        this.pin = Optional.of(pin);
    }

    private boolean hasFourDigits(int pin) {
        return pin <= 9999;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin.isPresent() && pin == this.pin.get() && !isBlocked()) {
            locked = false;
        } else {
            failedAttempts++;
        }
    }

    @Override
    public void lock() {
        if (pin.isEmpty()) {
            throw new IllegalStateException("The lock cannot be locked without a pin.");
        }
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
        this.pin = Optional.empty();
    }
}
