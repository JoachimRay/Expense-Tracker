package Wallets.Functions;

public class wallet {
    private double CurrentBalance; 
    private String Account;
    private double account_id; 


    public wallet(double CurrentBalance, String Account, double account_id)
    { 
        this.CurrentBalance = CurrentBalance;
        this.Account = Account; 
        this.account_id = account_id; 
        
        if("Metrobank".equals(this.Account) && this.CurrentBalance < 2000) 
        { 
            System.out.println("Balance is below minimum threshold!"); 
        } else if("Gcash".equals(this.Account) && this.CurrentBalance < 500)
        {
            System.out.println("Balance is below minimum threshold!"); 
        }
    }


    public double getCurrentBalance() 
    {
        return(CurrentBalance); 
    }

    public double GetAccount_id()
    {
        return(account_id);
    }

    public String GetAccount() 
    {
        return(Account); 
    }

    public void setCurrentBalance(double NewBalance)
    {
        this.CurrentBalance = NewBalance; 
    }

    public void setAccount(String newAccount)
    {
        this.Account = newAccount; 
    }

    public void setAccount_id(double newAccount_id)
    {
        this.account_id = newAccount_id; 
    }

    public void DisplayInfo() 
    {
        System.out.println( "\n" +Account + ": " + "\nAccount ID: " + account_id +"\nCurrent Balance: " + CurrentBalance + " Pesos"); 
    }


}
