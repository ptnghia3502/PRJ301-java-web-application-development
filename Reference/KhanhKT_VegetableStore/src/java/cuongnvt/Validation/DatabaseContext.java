package cuongnvt.Validation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseContext {

    public static Connection makeConnection()
            throws ClassNotFoundException, SQLException {
        String instance = "";
        String serverName = "TEACHERSTARCI";
        String portNumber = "1433";
        String dbName = "VegetableStore";
        String userID = "sa";
        String password = "123456";
        String url;
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        } else {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(url, userID, password);

    }
}
