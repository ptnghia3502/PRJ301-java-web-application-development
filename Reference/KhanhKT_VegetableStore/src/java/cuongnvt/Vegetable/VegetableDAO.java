package cuongnvt.Vegetable;

import cuongnvt.Validation.DatabaseContext;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VegetableDAO implements Serializable {

    public static VegetableDTO getVegetable(String ID) throws SQLException, ClassNotFoundException {
        String SQLQuery = "SELECT ID, Name, Color, Description, Value, Image "
                +" FROM Vegetable "
                + "WHERE ID = ?";

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, ID);
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                VegetableDTO vege
                        = new VegetableDTO(ReS.getString(1), ReS.getString(2), ReS.getString(3), ReS.getString(4), ReS.getInt(5), ReS.getString(6));
                return vege;
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

    public static ArrayList<VegetableDTO> getVegetables(String ID) throws SQLException, ClassNotFoundException {
        ArrayList<VegetableDTO> fruits = new ArrayList<>();
        if (ID.trim().equals("")) {
            return fruits;
        }
        if (ID.equalsIgnoreCase("all")){
            ID = "";
        }
        String SQLQuery = "SELECT ID, Name, Color, Description, Value, Image "
                +" FROM Vegetable "
                + "WHERE ID LIKE ?";;

        Connection conn = null;
        PreparedStatement PreS = null;
        ResultSet ReS = null;

        try {
            conn = DatabaseContext.makeConnection();
            PreS = conn.prepareCall(SQLQuery);
            PreS.setString(1, "%" + ID + "%");
            ReS = PreS.executeQuery();
            while (ReS.next()) {
                VegetableDTO fruit
                        = new VegetableDTO(ReS.getString(1), ReS.getString(2), ReS.getString(3), ReS.getString(4), ReS.getInt(5), ReS.getString(6));
                fruits.add(fruit);
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
        return fruits;

    }
}
