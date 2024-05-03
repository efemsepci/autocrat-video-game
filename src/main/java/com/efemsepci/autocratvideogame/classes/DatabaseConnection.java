package com.efemsepci.autocratvideogame.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/autocrat_video_game";
        String username = "efemsepci";
        String password = "efemsepci";

        return DriverManager.getConnection(url, username, password);
    }
}
