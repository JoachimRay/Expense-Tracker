package Wallets.Accounts;
import Wallets.Functions.wallet;

public class Gcash extends wallet{
    public Gcash(double CurrentBalance, String Account, double account_id) {
        super(CurrentBalance, Account, account_id);
    }

    // Convenience constructor: create a Gcash account with just a balance
    public Gcash(double CurrentBalance) {
        super(CurrentBalance, "Gcash", 10);
    }
    
}
