package co.edu.unbosque.services;

import co.edu.unbosque.dtos.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {
    private Connection conn;

    public UserService(Connection conn) {

        this.conn = conn;
    }

    public void listUsers(){
        Statement stmt = null;

        List<UserApp> userApps = new ArrayList<>();


        try {

            stmt = conn.createStatement();

            Scanner roleLector = new Scanner(System.in);
            //roleLector.nextLine();
            System.out.println("=> Listing users...");
            String sql = "SELECT * FROM UserApp WHERE role = 'official'";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                // Extracting row values by column name
                String username  = rs.getString("email");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");

                // Creating a new UserApp class instance and adding it to the array list
                userApps.add(new UserApp(username, password, email, role));
            }

            // Printing results
            System.out.println("Email | Password | Role");
            for (UserApp user : userApps) {
                System.out.print(user.getUsername() + " | ");
                System.out.print(user.getPassword() + " | ");
                System.out.print(user.getEmail() + " | ");
                System.out.println(user.getRole());
            }

            System.out.println("Total of users retrieved: " + userApps.size() + "\n");


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
