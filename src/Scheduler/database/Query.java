package Scheduler.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**manages database queries*/
public class Query {

    private static PreparedStatement statement;

    /**sets database query as a prepared statement*/
    public static void setStatement(Connection conn, String sql) throws SQLException {
        statement = conn.prepareStatement(sql);
    }
    /**gets database prepared statement*/
    public static PreparedStatement getStatement(){
        return statement;
    }
}
