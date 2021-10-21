package co.edu.unbosque.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VetServices {
    private Connection conn;

    public VetServices(Connection conn) {
        this.conn = conn;

    }

    public void countVets() {
        Statement stmt = null;


        try {

            System.out.println("=> Counting pets by species...");
            stmt = conn.createStatement();


            String sql = "SELECT COUNT(*) AS count FROM Vet";
            ResultSet rs = stmt.executeQuery(sql);

            // Pointing to fist row
            rs.next();

            // Printing results
            System.out.println("Total of vets: " + rs.getInt("count") + "\n");

            // Closing resources
            rs.close();
            stmt.close();


        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

}
