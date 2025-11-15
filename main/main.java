package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import Wallets.Accounts.Gcash;
import Wallets.Accounts.Metrobank;

public class main {
	public static void main(String[] args) {
		Gcash gcash = new Gcash(800.0, "Gcash", 10);
		Metrobank metro = new Metrobank(7000.0);

		gcash.DisplayInfo();
		metro.DisplayInfo();

		String url = "jdbc:sqlite:Database/Wallet.db"; 

		String sql = "INSERT OR REPLACE INTO wallet (account_id, account, account_balance) VALUES(?,?,?)";

		try {
			// Ensure the JDBC driver is loaded when running with `java -cp ...`
			Class.forName("org.sqlite.JDBC");

			try (Connection conn = DriverManager.getConnection(url);
				 PreparedStatement pstmt = conn.prepareStatement(sql)) {

				// Set parameters: account_id, account, account_balance
				// Use a deterministic id for this simple demo; REPLACE will overwrite if it exists
				pstmt.setDouble(1, 2); // account_id
				pstmt.setString(2, "Metrobank");
				pstmt.setDouble(3, 10000.0);

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
