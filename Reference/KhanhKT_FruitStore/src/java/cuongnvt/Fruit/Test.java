
package cuongnvt.Fruit;

import java.sql.SQLException;

public class Test {
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
      FruitDAO dao = new FruitDAO();
    //  System.out.println(dao.getFruit("DRA").getName());
    System.out.println(dao.getFruitList("A").size());
    } 
}
