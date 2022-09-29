/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.HashMap;
import mylib.DBUtils;

/**
 *
 * @author Nghia
 */
public class OrderDao {
    public static int insertOrder(int accid, HashMap<String, Integer> cart) throws Exception {
        int rs=0;
        Connection cn = DBUtils.makeConnection();
        if (cn!=null) {
            cn.setAutoCommit(false);
            String sql = "insert Orders(OrdDate,shipdate,status,AccID)\n"
                    + "values(?,null,1,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            Date d = new Date(System.currentTimeMillis());
            pst.setDate(1,d);
            pst.setInt(2, accid);
            rs = pst.executeUpdate();
            
            sql="select top 1 OrderID\n"
                    + "from dbo.Orders\n"
                    + "order by OrderID desc";    
            pst = cn.prepareStatement(sql);
            ResultSet table = pst.executeQuery();
            if (table!=null & table.next()) {
                int orderid = table.getInt("OrderID");
                for(String pid: cart.keySet()) {
                    int quantity = cart.get(pid);
                    sql = "insert OrderDetails(OrderID, FID, quantity)\n"
                            + "values(?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, quantity);
                    rs = pst.executeUpdate();
                }
            }
            cn.commit();
            cn.setAutoCommit(true);
            cn.close();
        }
        return rs;
    }
}
