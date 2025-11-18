import java.sql.*;

public class DBInspect {
    public static void main(String[] args) throws Exception {
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:Database/Wallet.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            System.out.println("Connected to: " + new java.io.File("Database/Wallet.db").getAbsolutePath());
            try (Statement st = conn.createStatement()) {
                System.out.println("--- transactions schema ---");
                try (ResultSet rs = st.executeQuery("SELECT sql FROM sqlite_master WHERE type='table' AND name='transactions'")) {
                    if (rs.next()) {
                        System.out.println(rs.getString(1));
                    } else {
                        System.out.println("transactions table not found in this DB file.");
                    }
                }

                System.out.println("--- last 10 rows from transactions (rowid desc) ---");
                try (ResultSet rs2 = st.executeQuery("SELECT rowid, * FROM transactions ORDER BY rowid DESC LIMIT 10")) {
                    ResultSetMetaData md = rs2.getMetaData();
                    int cols = md.getColumnCount();
                    while (rs2.next()) {
                        for (int i = 1; i <= cols; i++) {
                            System.out.print(md.getColumnName(i) + ":" + rs2.getString(i) + " ");
                        }
                        System.out.println();
                    }
                } catch (SQLException e) {
                    System.out.println("Error reading transactions table: " + e.getMessage());
                }
            }
        }
    }
}
