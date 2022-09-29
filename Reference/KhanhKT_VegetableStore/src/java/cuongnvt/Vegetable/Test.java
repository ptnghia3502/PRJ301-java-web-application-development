
package cuongnvt.Vegetable;

import java.sql.SQLException;

public class Test {
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
    System.out.print(VegetableDAO.getVegetables("all").size());
    } 
}
