package Wallets.Accounts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Naccount {
    public static void NewAccount() 
    {
        Scanner input = new Scanner(System.in);
		String url = "jdbc:sqlite:Database/Wallet.db";
        String sql = "INSERT OR REPLACE INTO wallet (account_id, account, account_balance) VALUES(?,?,?)"; 

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
    }
}
