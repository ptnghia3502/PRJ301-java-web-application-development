/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import basicobject.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author Nghia
 */
public class AccountDao {

    //ham de lay tat ca account trong database
    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList<>();
        //step 1: make connection
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet sql va execute
            String sql = "select accID, email, password, fullname, phone, status, role"
                    + " from dbo.Accounts";
            Statement st = cn.createStatement();
            ResultSet table = st.executeQuery(sql);
            //step 3: xu li ket qua cua step 2
            if (table != null) {
                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    Account acc = new Account(accid, fullname, email, password, phone, status, role);
                    list.add(acc);
                } //het while
            } //het if
            //step 4
            cn.close();
        }
        return list;
    }

    //ham lay 1 account dua vao accid
    //input: accid
    public static Account getAccount(int accid) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID, email, password, fullname, phone, status, role\n"
                    + "from dbo.Accounts\n"
                    + "where accID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, accid);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accid, fullname, email, password, phone, status, role);
            } //het if
            cn.close();
        }// het if
        return acc;
    }// het function

    //ham return account khi biet email va password
    //input: email va pass
    //output: 
    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "select accID, email, password, fullname, phone, status, role\n"
                    + "from dbo.Accounts\n"
                    + "where email=? and password=? COLLATE Latin1_General_CS_AS";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accid = table.getInt("accID");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accid, fullname, email, password, phone, status, role);
            } //het if
            cn.close();
        }// het if
        return acc;
    }// het function

    //chi update column status in bang account
    public static int updateAccount(int accid, int oldstatus) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "update dbo.Accounts\n"
                    + "set status=?\n"
                    + "where accID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            int newstatus = (oldstatus==1)?0:1;
            pst.setInt(1, newstatus);
            pst.setInt(2, accid);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
}
