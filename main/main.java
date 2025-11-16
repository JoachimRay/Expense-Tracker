package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Scanner;

 

public class main {
	public static void main(String[] args) {

		String url = "jdbc:sqlite:Database/Wallet.db"; 
		String Checkaccount = "SELECT account_id, account FROM wallet"; 
		String sql = "INSERT OR REPLACE INTO wallet (account_id, account, account_balance) VALUES(?,?,?)";

		Scanner input = new Scanner(System.in); 


		System.out.println("Welcome to Finance Tracker!"); 

		int choices = 0; 

		while(choices != 3)
		{
			choices = 0; 
		System.out.println("\n1.)Accounts\n2.)Add Account\n3.)Exit.."); 
		System.out.print("\nEnter Choice: "); 
		choices = input.nextInt(); 

		switch(choices)
		{
			case 1: 
			try {
				
				Class.forName("org.sqlite.JDBC"); 
				try(Connection conn = DriverManager.getConnection(url); 
				PreparedStatement ps = conn.prepareStatement(Checkaccount)) {


					try(ResultSet rs = ps.executeQuery()){

					System.out.println("\nYour Accounts: "); 
					while(rs.next())
					{
						double id = rs.getDouble("account_id"); 
						String account = rs.getString("account"); 
						System.out.println("Account ID: " + id + "\nAccount: " + account + "\n");
					} 

					 }

				}
			} catch (ClassNotFoundException e) {
				System.out.println("SQLite JDBC driver not found; cannot list accounts.");
			} catch(SQLException e) {
				System.out.println("Database error: " + e.getMessage());
			}

			break; 



			case 2: 

			System.out.println("Enter new account details: "); 
			System.out.println("Account ID: "); 
			double new_id = input.nextDouble(); 
			System.out.println("Account Name: ");
			String new_account = input.next(); 
			System.out.println("Initial Balance: ");
			double new_balance = input.nextDouble(); 
			
		try {
			// Ensure the JDBC driver is loaded when running with `java -cp ...`
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				// Set parameters: account_id, account, account_balance
				// Use a deterministic id for this simple demo; REPLACE will overwrite if it exists
				pstmt.setDouble(1, new_id); // account_id
				pstmt.setString(2, new_account);
				pstmt.setDouble(3, new_balance);

				pstmt.executeUpdate();

				System.out.println("Data inserted (or replaced) successfully");

			}
		} catch (ClassNotFoundException e) {
			System.out.println("SQLite JDBC driver not found on classpath; skipping DB operations.");
		} catch (SQLException e) {
			System.out.println("Error inserting data! " + e.getMessage());
		}

		break; 

		case 3:{ 
			System.out.println("Exiting...");
			System.exit(0);
		}

		default: 
		System.out.println("Invalid choice!");
		break; 

		}

		}

		


		

		


	}
}
