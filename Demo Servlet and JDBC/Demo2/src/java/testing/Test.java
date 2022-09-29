/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import basicobject.Account;
import dao.AccountDao;
import java.util.ArrayList;

/**
 *
 * @author Nghia
 */
public class Test {
    public static void main(String[] args) throws Exception {
        try{
            ArrayList<Account> list = AccountDao.getAccounts();
            for (Account account: list) {
                System.out.println(account);
            }
            
            Account acc = AccountDao.getAccount(1);
            if (acc!= null) System.out.println("found:" +acc);
            else System.out.println("not found");
            
            Account acc2 = AccountDao.getAccount("nghia@gmail.com", "nghia");
            if (acc2!= null) System.out.println("found 2:" +acc2);
            else System.out.println("not found");
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
