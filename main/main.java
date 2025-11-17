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
		String Addtransaction = "INSERT OR REPLACE into transactions(tranx_id, account_id, category_id, tranx_type_id, tranx_amount, tranx_time_date) VALUES (?,?,?,?,?,?)";  
		String DisplayCategories = "SELECT category_id, category FROM categories";
		String DisplayTransactionTypes = "SELECT tranx_type_id, tranx_type FROM transaction_type";

		Scanner input = new Scanner(System.in); 


		System.out.println("Welcome to Finance Tracker!"); 

		int choices = 0; 
		double tranx_id = 0; 

		while(choices != 4)
		{
			choices = 0; 
		System.out.println("\n1.)Accounts\n2.)Add Account\n3.)Add Transaction\n4.)Exit.."); 
		System.out.print("\nEnter Choice: "); 
		choices = input.nextInt(); 

		switch(choices)
		{
			case 1: 
			{

			
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
		  }



			case 2: 
			{ 

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
	}

		case 3: 
		{
			System.out.println("Categories: "); 
			try{ 
				Class.forName("org.sqlite.JDBC"); 

				try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement ps = conn.prepareStatement(DisplayCategories)){ 
					try(ResultSet rs = ps.executeQuery())
					{
						while(rs.next())
						{
							int cat_id = rs.getInt("category_id"); 
							String cat_name = rs.getString("category"); 
							System.out.println("Category ID: " + cat_id + " Category Name: " + cat_name + "\n");
						}
					}

				};
			}catch (ClassNotFoundException e) {
			System.out.println("SQLite JDBC driver not found on classpath; skipping DB operations.");
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
		}

		System.out.println("Transaction Types: "); 

		try{ 
				Class.forName("org.sqlite.JDBC"); 

				try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement ts = conn.prepareStatement(DisplayTransactionTypes)){ 
					try(ResultSet rs = ts.executeQuery())
					{
						while(rs.next())
						{
							int T_id = rs.getInt("tranx_type_id"); 
							String T_name = rs.getString("tranx_type"); 
							System.out.println("Transaction Type ID: " + T_id + " Transaction Type Name: " + T_name + "\n");
						}
					}

				};
			}catch (ClassNotFoundException e) {
			System.out.println("SQLite JDBC driver not found on classpath; skipping DB operations.");
		} catch (SQLException e) {
			System.out.println("Error inserting data! " + e.getMessage());
		}
		
		System.out.println("---------------------------------------------"); 
		System.out.println("Enter Transaction Details: "); 
		System.out.print("Account ID: "); 
		double acc_id = input.nextDouble(); 
		System.out.print("Category ID: ");
		double Category_id = input.nextDouble(); 
		System.out.print("Transaction Type ID: "); 
		double T_T_id = input.nextDouble(); 
		System.out.print("Transaction Amount: "); 
		double T_amount = input.nextDouble(); 
		System.out.print("Transaction Date and Time (YYYY-MM-DD HH:MM:SS): "); 
		String T_date_time = input.next(); 

		try{ 

			Class.forName("org.sqlite.JDBC"); 

			try(Connection conn = DriverManager.getConnection(url); 
			PreparedStatement pstmt = conn.prepareStatement(Addtransaction)){

				tranx_id++; 

				pstmt.setDouble(1,tranx_id); 
				pstmt.setDouble(2, acc_id);
				pstmt.setDouble(3, Category_id); 
				pstmt.setDouble(4, T_T_id); 
				pstmt.setDouble(5, T_amount); 
				pstmt.setString(6, T_date_time);
				pstmt.executeUpdate(); 

				System.out.println("Transaction added successfully!"); 
			}

		}catch(ClassNotFoundException e) {
			System.out.println("SQLite JDBC driver not found on classpath; skipping DB operations.");
		} catch (SQLException e) {
			System.out.println("Error inserting data! " + e.getMessage());
		}
		break; 


		}

		case 4:{ 
			System.out.println("Exiting...");
			System.exit(0);
			break; 
		}

		default: 
		System.out.println("Invalid choice!");
		break; 

		}

		}

		


		

		


	}
}
