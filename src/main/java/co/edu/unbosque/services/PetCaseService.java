package co.edu.unbosque.services;

import co.edu.unbosque.dtos.PetCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class PetCaseService {
    private Connection conn;

    public PetCaseService(Connection conn) {
        this.conn = conn;
    }

    public void updatePetCase(int id,String rob,String desc) {
        Statement stmt = null;

        try {
            System.out.println("=> Updating PetCase...");
            Date date =new Date();
            String datee = date.toString();
            stmt = conn.createStatement();
            String sql = "UPDATE PetCase SET created_at  = '"+datee+"',type='"+rob+"',description='"+desc+"' WHERE pet_id=" +id;
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

