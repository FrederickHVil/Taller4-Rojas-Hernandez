package co.edu.unbosque.services;

import java.sql.Connection;
import java.sql.Statement;

public class UserService {
    private Connection conn;

    public UserService(Connection conn) {

        this.conn = conn;
    }

    public void listUsers(){
        Statement stmt = null;
    }


}
