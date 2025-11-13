package Wallets.Accounts;
import Wallets.Functions.wallet; 

public class Metrobank extends wallet{
    public Metrobank(double CurrentBalance, String Account, double account_id){
        super(CurrentBalance, Account, account_id);
    }

    // Convenience constructor: create a Metrobank account with just a balance
    public Metrobank(double CurrentBalance) {
        super(CurrentBalance, "Metrobank", 1);
    }
}