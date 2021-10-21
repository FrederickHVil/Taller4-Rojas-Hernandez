package co.edu.unbosque.services;

import co.edu.unbosque.dtos.UserApp;
import co.edu.unbosque.dtos.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VisitServices {
    private Connection conn;

    public VisitServices(Connection conn) {
        this.conn = conn;
    }

    public void listVisits(int ID){
        Statement stmt = null;

        List<Visit> visits = new ArrayList<>();


        try {
            System.out.println("=> Listing visits...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Visit WHERE pet_id = '"+ID+"'";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // Extracting row values by column name
                int visit_id  = rs.getInt("visit_id");
                String created_at = rs.getString("created_at");
                String type = rs.getString("type");
                String description = rs.getString("description");
                String vet_id = rs.getString("vet_id");
                int pet_id = rs.getInt("pet_id");

                // Creating a new UserApp class instance and adding it to the array list
                visits.add(new Visit(visit_id, created_at, type, description, vet_id, pet_id));
            }

            // Printing results
            System.out.println("visit_id | created_at | type | description | vet_id | pet_id");
            for (Visit user : visits) {
                System.out.print(user.getVisit_id() + " | ");
                System.out.print(user.getCreated_at() + " | ");
                System.out.print(user.getType() + " | ");
                System.out.print(user.getDescription() + " | ");
                System.out.print(user.getVet_id() + " | ");
                System.out.println(user.getPet_id());
            }

            System.out.println("visits from this pet: " + visits.size() + "\n");


            rs.close();
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
