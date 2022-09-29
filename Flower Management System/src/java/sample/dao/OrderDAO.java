/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import sample.dto.Order;
import sample.dto.OrderDetail;
import sample.utils.DBUtils;

/**
 *
 * @author Nghia
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipDate, Orders.status as 'status', Orders.AccID as 'AccID'\n"
                        + "from Orders join Accounts on Orders.AccID=Accounts.accID\n"
                        + "where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int OrderID = rs.getInt("OrderID");
                        String OrdDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipDate");
                        int status = rs.getInt("status");
                        int AccID = rs.getInt("AccID");
                        Order ord = new Order(OrderID, OrdDate, shipDate, status, AccID);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) {
        Connection cn = null;
        ArrayList<OrderDetail> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select DetailId, OrderID, PID, PName, price, imgPath, quantity\n"
                        + "from OrderDetails, Plants\n"
                        + "where OrderID=? and OrderDetails.FID=Plants.PID";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PlantID = rs.getInt("PID");
                        String PlaneName = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgPath = rs.getString("imgPath");
                        int quantity = rs.getInt("quantity");
                        OrderDetail orderdetail = new OrderDetail(detailID, orderID, PlantID, PlaneName, price, imgPath, quantity);
                        list.add(orderdetail);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static boolean updateOrderStatus(String orderID, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update dbo.Orders \n"
                        + "set status = ? \n"
                        + "where OrderID = ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, orderID);
                result = pst.executeUpdate() > 0;
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false);
                //get account id by email
                String sql = "select accID from Accounts where Accounts.email=?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }
                //insert a new order
                System.out.println("accountid:" + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date:" + d);
                sql = "insert Orders(OrdDate, status, AccID) values(?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();

                //get order id that is the largest number
                sql = "select top 1 orderID from Orders order by orderId desc";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                System.out.println("orderid:" + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "insert OrderDetails values(?,?,?) ";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();
                    cn.commit();
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("k chen order dc");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            result = false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<Order> getOrdersByDate(Date from, Date to) {
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT OrderID, OrdDate, shipDate, status, AccID FROM Orders\n"
                        + "WHERE OrdDate BETWEEN ? AND ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, from);
                pst.setDate(2, to);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int OrderID = rs.getInt("OrderID");
                        String OrdDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipDate");
                        int status = rs.getInt("status");
                        int AccID = rs.getInt("AccID");
                        Order ord = new Order(OrderID, OrdDate, shipDate, status, AccID);
                        list.add(ord);
                    }
                }
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //for workshop 7
    public static ArrayList<Order> getOrders() {
        Connection cn = null;
        ArrayList<Order> list = new ArrayList<>();
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select OrderID, OrdDate, shipDate, Orders.status as 'status', Orders.AccID as 'AccID'\n"
                        + "from Orders join Accounts on Orders.AccID=Accounts.accID\n";
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (rs != null) {
                    while (rs.next()) {
                        int OrderID = rs.getInt("OrderID");
                        String OrdDate = rs.getString("OrdDate");
                        String shipDate = rs.getString("shipDate");
                        int status = rs.getInt("status");
                        int AccID = rs.getInt("AccID");
                        Order ord = new Order(OrderID, OrdDate, shipDate, status, AccID);
                        list.add(ord);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

}
