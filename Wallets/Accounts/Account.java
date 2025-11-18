package Wallets.Accounts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Account {

	// Make the method return void (was missing a return type)
	public static void listAccounts() { 

        String url = "jdbc:sqlite:Database/Wallet.db"; 
		String Checkaccount = "SELECT account_id, account FROM wallet"; 
        
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
    }
    
}
