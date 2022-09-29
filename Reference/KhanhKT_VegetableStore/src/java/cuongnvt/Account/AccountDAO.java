package cuongnvt.Account;

import cuongnvt.Validation.DatabaseContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO implements Serializable {

    public static AccountDTO getAccount(String Username, String Password) throws SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT Username, Password, Role, FullName "
                + "FROM Account "
                + "WHERE Username = ? AND Password = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            PreS.setString(2, Password);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                AccountDTO acc
                        = new AccountDTO(ReS.getString(1), ReS.getString(2), ReS.getBoolean(3), ReS.getString(4));
                return acc;

            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }
            if (ReS != null) {
                ReS.close();
            }

        }
        return null;

    }

    public static ArrayList<AccountDTO> getAccounts(String Username, String Password) throws SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT Username, Password, Role, FullName "
                + "FROM Account ";

        ArrayList<AccountDTO> accounts = new ArrayList<>();
        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            PreS.setString(2, Password);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                AccountDTO acc = new AccountDTO(ReS.getString(1), ReS.getString(2), ReS.getBoolean(3), ReS.getString(4));
                accounts.add(acc);

            }
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }
            if (ReS != null) {
                ReS.close();
            }

        }
        return null;

    }

    public static boolean insertAccount(String Username, String Password, boolean Role, String FullName) throws SQLException, ClassNotFoundException {
        String SQLQuery = "INSERT INTO Account(Username, Password, Role, FullName) "
                + "VALUES (?,?,?,?) ";

        Connection conn = null;
        PreparedStatement PreS = null;
        boolean allowtoinsert = false;
        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, Username);
            PreS.setString(2, Password);
            PreS.setBoolean(3, Role);
            PreS.setString(4, FullName);
            final int affectedRow = PreS.executeUpdate();
            if (affectedRow > 0){
                allowtoinsert = true;
            }
            
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (PreS != null) {
                PreS.close();
            }

        }
        return allowtoinsert;

    }
}
