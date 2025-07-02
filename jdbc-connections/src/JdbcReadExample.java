import java.sql.*;

public class JdbcReadExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/employees";
        String user = "test";
        String password = "test";

        String query = "SELECT id, name, department FROM employees";

        try {
            // 1. Load MySQL JDBC Driver (optional in newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create Connection
            Connection conn = DriverManager.getConnection(url, user, password);

            // 3. Create Statement
            Statement stmt = conn.createStatement();

            // 4. Execute Query
            ResultSet rs = stmt.executeQuery(query);

            // 5. Process Result
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String dept = rs.getString("department");

                System.out.println(id + " | " + name + " | " + dept);
            }

            // 6. Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
