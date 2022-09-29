package cuongnvt.Fruit;

import cuongnvt.Validation.DatabaseContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FruitDAO implements Serializable {

    public FruitDTO getFruit(String ID) throws SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT * FROM Fruit WHERE ID = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, ID);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                FruitDTO fruit
                        = new FruitDTO(ReS.getString(1), ReS.getString(2), ReS.getString(3), ReS.getString(4), ReS.getInt(5));
                return fruit;
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

    public ArrayList<FruitDTO> getFruitList(String ID) throws SQLException, ClassNotFoundException {
        ArrayList<FruitDTO> fruits = new ArrayList<>();
        if (ID.trim().equals("")) {
            return fruits;
        }
        if (ID.equalsIgnoreCase("all")){
            ID = "";
        }
        String SQLQuery = "SELECT * FROM Fruit WHERE ID LIKE ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, "%" + ID + "%");
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                FruitDTO fruit
                        = new FruitDTO(ReS.getString(1), ReS.getString(2), ReS.getString(3), ReS.getString(4), ReS.getInt(5));
                fruits.add(fruit);
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
        return fruits;

    }
      public static boolean DeleteFruit(String Fruit_ID)
            throws SQLException, ClassNotFoundException {
        FruitDTO Fruit = null;
        Connection conn = null;
        PreparedStatement preS = null;
        boolean flag = false;
        try {
            conn = DatabaseContext.makeConnection();
            String SQLQuery = "DELETE FROM Fruit WHERE Fruit_ID = ?";
            preS = conn.prepareCall(SQLQuery);
            preS.setString(1, Fruit_ID);
            final int AffectRow = preS.executeUpdate();
            if (AffectRow > 0) {
                flag = true;
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException.");
        } catch (SQLException ex) {
            System.out.println("SQLException.");
        } finally {
            if (conn != null) {
                conn.close();
            }
            if (preS != null) {
                preS.close();
            }
        }
        return flag;
    }
}
