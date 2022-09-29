/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

import java.util.ArrayList;
import static java.util.Collections.list;
import sample.dao.AccountDAO;
import sample.dao.PlantDAO;

/**
 *
 * @author Nghia
 */
public class testConnection {

    public static void main(String[] args) throws Exception {
        Account acc = AccountDAO.getAccount("test@gmail.com", "999999");
        if (acc != null) {
            if (acc.getRole() == 1) {
                System.out.println("i am an admin");
            } else {
                System.out.println("i am a user");
            }
        } else {
            System.out.println("login fail");
        }

        /*   String keyword = "sa";
        String searchby = "byname";
        ArrayList<Plant> list = PlantDAO.getPlants(keyword, searchby);
        for (Plant plant : list) {
            System.out.println(plant.getId() + "," + plant.getName() + "," + plant.getCateid());
        } */
        
        
        //test challenge 1
        /*
        try{
            ArrayList<Account> list = AccountDAO.getAccounts();
            for (Account account : list) {
            System.out.println(account.getAccID()+","+ account.getEmail()+","+ account.getPassword()+","
                    + account.getFullname()+","+ account.getStatus()+","+ account.getPhone()+","+ account.getRole());
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        
        //test challenge 2
        /* 
        try {
            if(AccountDAO.updateAccountStatus("test@gmail.com", 0))
                System.out.println("update status succesfully");
            else System.out.println("cannot update status");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        */
        
        //test challenge 3
        /*try {
            if(AccountDAO.updateAccount("test@gmail.com", "999999", "thino", "654321"))
                System.out.println("updated profile");
            else System.out.println("update profile fail");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        */

        //test challenge 4
        /*try {
            if (AccountDAO.insertAccount("test2@gmail.com", "222222", "chipheo", "123123123", 1, 0)!=0)
            System.out.println("added new account");
            else System.out.println("insert a new account fail");
        } catch(Exception e) {
            e.printStackTrace();
        }
        */
    }
}
