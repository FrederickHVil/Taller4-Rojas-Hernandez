package co.edu.unbosque.services;

import co.edu.unbosque.dtos.PetCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PetCaseService {
    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void updatePetCase(PetCase petcase) {
        Statement stmt = null;

        try {
            System.out.println("=> Updating PetCase...");

            stmt = conn.createStatement();
            //String sql = "UPDATE PetCase SET create_at  = '" + petcase.getCreated_at() ,type= ''+ "' WHERE pet_id = "+ petcase.getPet_id();
            System.out.println(sql);
            int rowsUpdated = stmt.executeUpdate(sql);

            // Printing results
            System.out.println("Rows updated: " + rowsUpdated + "\n");

            // Closing resources
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }


}

