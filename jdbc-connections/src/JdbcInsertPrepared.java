import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JdbcInsertPrepared {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "test";
        String password = "test";

        String insertQuery = "INSERT INTO employees (id, name, department) VALUES (?, ?, ?)";

        try {
            // 1. Load MySQL JDBC Driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // 3. Create PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);

            // 4. Set parameters
            pstmt.setInt(1, 3);                      // id
            pstmt.setString(2, "Charlie");           // name
            pstmt.setString(3, "Finance");           // department

            // 5. Execute insert
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s)");

            // 6. Close resources
            pstmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
