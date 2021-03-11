package Scheduler.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**manages database connection*/
public class DBConnection {
    private static final String protocol ="jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress ="//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ08NHo";

    private static final String jdbcURL =  protocol + vendorName + ipAddress + dbName;

    private static final String mySQLDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "U08NHo";
    private static Connection conn = null;

    /**starts database connection*/
    public static void startConnection(){
        try{
            Class.forName(mySQLDriver);
            conn = DriverManager.getConnection(jdbcURL,username,Password.getPassword());
        }catch(SQLException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**gets database connection*/
    public static Connection getConnection(){
        return conn;
    }
    /**closes database connection*/
    public static void closeConnection(){
        try{
            conn.close();
        }catch(Exception e){

        }
    }
}
