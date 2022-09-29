/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicobject;

import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author Nghia
 */
public class Order {
    private int orderid;
    private Date orderdate;
    private Date shipdate;
    private int status;
    private int accid;

    public Order() {
    }

    public Order(int orderid, Date orderdate, Date shipdate, int status, int accid) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.shipdate = shipdate;
        this.status = status;
        this.accid = accid;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Date getShipdate() {
        return shipdate;
    }

    public void setShipdate(Date shipdate) {
        this.shipdate = shipdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

}
