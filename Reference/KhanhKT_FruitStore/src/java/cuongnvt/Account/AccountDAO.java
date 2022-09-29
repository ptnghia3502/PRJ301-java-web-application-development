
package cuongnvt.Account;

import cuongnvt.Validation.DatabaseContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements Serializable{
    public AccountDTO getAccount(String Username, String Password) throws SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT * FROM Account WHERE Username = ? AND Password = ?";

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

        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException.");
        } catch (SQLException ex) {
            System.out.println("SQLException.");
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
}
