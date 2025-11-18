package Wallets.Functions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Scanner; 

public class Transactions {
    public static void AddTransactions() 
    {
		Scanner input = new Scanner(System.in);
		String url = "jdbc:sqlite:Database/Wallet.db"; 
		// Let SQLite assign the primary key (tranx_id) automatically by omitting it from the INSERT.
		String Addtransaction = "INSERT INTO transactions(account_id, category_id, tranx_type_id, tranx_amount, tranx_time_date) VALUES (?,?,?,?,?)";
		String DisplayCategories = "SELECT category_id, category FROM categories";
		String DisplayTransactionTypes = "SELECT tranx_type_id, tranx_type FROM transaction_type";

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
		// consume the rest of the line after nextDouble()
		input.nextLine();
		System.out.print("Transaction Date and Time (YYYY-MM-DD HH:MM:SS): "); 
		String T_date_time = input.nextLine();

		try{ 

			Class.forName("org.sqlite.JDBC"); 

			try(Connection conn = DriverManager.getConnection(url);
				PreparedStatement pstmt = conn.prepareStatement(Addtransaction)){

				pstmt.setDouble(1, acc_id);
				pstmt.setDouble(2, Category_id);
				pstmt.setDouble(3, T_T_id);
				pstmt.setDouble(4, T_amount);
				pstmt.setString(5, T_date_time);

				int rows = pstmt.executeUpdate();
				if (rows > 0) {
					System.out.println("Transaction added successfully!");
				} else {
					System.out.println("No rows were inserted.");
				}
			}

		}catch(ClassNotFoundException e) {
			System.out.println("SQLite JDBC driver not found on classpath; skipping DB operations.");
		} catch (SQLException e) {
			System.out.println("Error inserting data! " + e.getMessage());
		}
    }
}
