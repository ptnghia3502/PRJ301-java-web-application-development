/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Account;
import sample.utils.DBUtils;

/**
 *
 * @author Nghia
 */
public class AccountDAO {

    public static Account getAccount(String email, String password) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role\n"
                        + "from dbo.Accounts\n"
                        + "where status=1 and email=? and password=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
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
        return acc;
    }
    
    //challenge 1-----------------------------------------------------------
    public static ArrayList<Account> getAccounts() throws Exception{
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if(cn!=null) {
            String sql="select accID, email, password, fullname, phone, status, role"
                    + " from dbo.Accounts";
            Statement st = cn.createStatement();
            ResultSet table = st.executeQuery(sql);
            if (table!=null) {
                while(table.next()){
                    int AccID = table.getInt("accID");
                    String Email = table.getString("email");
                    String Password = table.getString("password");
                    String Fullname = table.getString("fullname");
                    String Phone = table.getString("phone");
                    int Status = table.getInt("status");
                    int Role = table.getInt("role");
                    Account acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                    list.add(acc);
                }
            }
            cn.close();
        }
        return list;
    }
    
    //challenge 2-----------------------------------------------------------
    public static boolean updateAccountStatus(String email, int status) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts \n"
                        + "set status = ? \n"
                        + "where email = ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setString(2, email);
                result = pst.executeUpdate() > 0;
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //challenge 3-----------------------------------------------------------
    public static boolean updateAccount(String email, String newPassword, String newFullname, String newPhone) {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts \n"
                        + "set password = ?, fullname = ?, phone = ? \n"
                        + "where email = ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, newPassword);
                pst.setString(2, newFullname);
                pst.setString(3, newPhone);
                pst.setString(4, email);
                result = pst.executeUpdate() > 0;
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    //challenge 4-----------------------------------------------------------
    public static int insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newSatus, int newRole) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "insert dbo.Accounts(email, password, fullname, phone, status, role)\n"
                    + "values(?,?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newEmail);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setInt(5, newSatus);
            pst.setInt(6, newRole);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }

    public static boolean updateToken(String email, String token) throws Exception {
        boolean result = false;
        try {
            Connection cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "update Accounts \n"
                        + "set token = ? \n"
                        + "where email = ? ";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                pst.setString(2, email);
                result = pst.executeUpdate() > 0;
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public static Account getAccount(String token) {
        Connection cn = null;
        Account acc = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "select accID, email, password, fullname, phone, status, role, token\n"
                        + "from dbo.Accounts\n"
                        + "where token=? COLLATE Latin1_General_CS_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, token);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    int AccID = rs.getInt("accID");
                    String Email = rs.getString("email");
                    String Password = rs.getString("password");
                    String Fullname = rs.getString("fullname");
                    String Phone = rs.getString("phone");
                    int Status = rs.getInt("status");
                    int Role = rs.getInt("role");
                    String Token = rs.getString("token");
                    acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role, Token);
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
        return acc;
    }
    
    //for workshop 7
    public static ArrayList<Account> getAccountByEmail( String email) throws Exception{
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if(cn!=null) {
            String sql="select accID, email, password, fullname, phone, status, role"
                    + " from dbo.Accounts where email=? COLLATE Latin1_General_CS_AS";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet table = pst.executeQuery();
            if (table!=null) {
                while(table.next()){
                    int AccID = table.getInt("accID");
                    String Email = table.getString("email");
                    String Password = table.getString("password");
                    String Fullname = table.getString("fullname");
                    String Phone = table.getString("phone");
                    int Status = table.getInt("status");
                    int Role = table.getInt("role");
                    Account acc = new Account(AccID, Email, Password, Fullname, Status, Phone, Role);
                    list.add(acc);
                }
            }
            cn.close();
        }
        return list;
    }
}