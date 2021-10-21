package co.edu.unbosque;

import co.edu.unbosque.dtos.PetCase;
import co.edu.unbosque.services.PetCaseService;
import co.edu.unbosque.services.UserService;
import co.edu.unbosque.services.VetServices;
import co.edu.unbosque.services.VisitServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/fourpaws";

    static final String USER = "postgres";
    static final String PASS = "1234";

    private static Scanner tc;

    public static void main(String[] args){
        tc = new Scanner(System.in);
        Connection conn = null;

        try {

            Class.forName(JDBC_DRIVER);
            System.out.println("contectando al server...");

            String menu= "============================\n"+"Ciudadanos de 4 patas\n"+
            "1. Consultar la lista de usuarios registrados para un rol dado.\n"+
            "2. Contar la lista de veterinarias registradas en la plataforma.\n"+
            "3. Consultar las visitas que se han registrado para un ID de mascota dado.\n"+
            "4. Registrar un nuevo caso de robo de una mascota dado su ID.\n"+
            "5. Salir\n"+
            "Ingrese la opción deseada:\n"+"=================================";

            boolean bandera = true;

            while(bandera){
                System.out.println(menu);
                int option=tc.nextInt();
                switch (option){
                    case 1:
                        System.out.println("Ingrese el rol que desea consultar");
                        String rol = tc.next();
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        UserService userServices = new UserService(conn);
                        userServices.listUsers(rol);
                        conn.close();
                        break;
                    case 2:
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        VetServices vetServices = new VetServices(conn);
                        vetServices.countVets();
                        conn.close();
                        break;
                    case 3:
                        System.out.println("Ingrese el ID para consultar las visitas");
                        int id = tc.nextInt();
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        VisitServices visitServices = new VisitServices(conn);
                        visitServices.listVisits(id);
                        conn.close();
                        break;
                    case 4:
                        System.out.println("Ingrese el ID");
                        int id1= tc.nextInt();
                        String robo = "Robo";
                        String des = "Se ha robado la mascota";
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        PetCaseService petcaseservice = new PetCaseService(conn);
                        petcaseservice. updatePetCase(id1,robo,des);
                        conn.close();
                        break;
                    case 5:
                        bandera=false;
                        System.out.println("Gracias por usar Ciudadanos de 4 patas");

                }
            }
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
