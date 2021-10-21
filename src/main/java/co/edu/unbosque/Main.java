package co.edu.unbosque;

import co.edu.unbosque.services.UserService;
import co.edu.unbosque.services.VetServices;
import co.edu.unbosque.services.VisitServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/fourpaws";

    static final String USER = "postgres";
    static final String PASS = "qwepoi88";

    public static void main(String[] args){
        Connection conn = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            UserService userServices = new UserService(conn);
            userServices.listUsers();

            //VetServices vetServices = new VetServices(conn);
            //vetServices.countVets();

            //VisitServices visitServices = new VisitServices(conn);
            //visitServices.listVisits();

            conn.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch(ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
