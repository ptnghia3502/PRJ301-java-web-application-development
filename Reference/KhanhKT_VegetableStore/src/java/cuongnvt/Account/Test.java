
package cuongnvt.Account;

import java.sql.SQLException;

public class Test {
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
        AccountDAO dao = new AccountDAO();
        System.out.print(dao.insertAccount("nhoc_ddd2", "123456", false, "Luu Chi Bao"));
      
    }
}
