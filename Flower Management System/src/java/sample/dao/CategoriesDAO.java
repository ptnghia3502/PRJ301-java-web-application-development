/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Categories;
import sample.utils.DBUtils;

/**
 *
 * @author Nghia
 */
public class CategoriesDAO {
    public static ArrayList<Categories> getCategories() throws Exception{
        ArrayList<Categories> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if(cn!=null) {
            String sql="select CateID, CateName\n" +
                        "from Categories";
            Statement st = cn.createStatement();
            ResultSet table = st.executeQuery(sql);
            if (table!=null) {
                while(table.next()){
                    int CateID = table.getInt("CateID");
                    String CateName = table.getString("CateName");
                    Categories cate = new Categories(CateID, CateName);
                    list.add(cate);
                }
            }
            cn.close();
        }
        return list;
    }

}
