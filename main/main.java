package main;

import Wallets.Accounts.Account;
import Wallets.Accounts.Naccount;
import Wallets.Functions.Transactions;
import java.util.Scanner;


public class main {
	public static void main(String[] args) {



		Scanner input = new Scanner(System.in); 


		System.out.println("Welcome to Finance Tracker!"); 
		int choices = 0; 

		while(choices != 4)
		{
			choices = 0; 
		System.out.println("\n1.)Accounts\n2.)Add Account\n3.)Add Transaction\n4.)Exit.."); 
		System.out.print("\nEnter Choice: "); 
		choices = input.nextInt(); 

		switch(choices)
		{
			case 1: 

			Account.listAccounts(); 
			break; 
		

			case 2: 

			Naccount.NewAccount();
			break;


		    case 3: 
	
			Transactions.AddTransactions();
		    break; 


		case 4: 
			System.out.println("Exiting...");
			System.exit(0);
			break; 


		default: 
		System.out.println("Invalid choice!");
		break; 

		}

		}

		


		

		


	}
}
