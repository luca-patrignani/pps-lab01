import example.model.AccountHolder;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    private AccountHolder accountHolder;
    private AccountHolder wrongHolder;
    private SimpleBankAccount bankAccount;

    @BeforeEach
    void beforeEach() {
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
        wrongHolder = new AccountHolder("Luigi", "Bianchi", 2);
    }

    @Test
    void testInitialBalance() {
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), 100);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.deposit(wrongHolder.getId(), 50);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(accountHolder.getId(), 70);
        assertEquals(29, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), 100);
        bankAccount.withdraw(wrongHolder.getId(), 70);
        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void testWithdrawWithFee() {
        bankAccount.deposit(accountHolder.getId(), 200);
        bankAccount.withdraw(accountHolder.getId(), 70);
        assertEquals(129, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdrawWithFee() {
        bankAccount.deposit(accountHolder.getId(), 200);
        bankAccount.withdraw(accountHolder.getId(), 200);
        assertEquals(200, bankAccount.getBalance());
    }
}
