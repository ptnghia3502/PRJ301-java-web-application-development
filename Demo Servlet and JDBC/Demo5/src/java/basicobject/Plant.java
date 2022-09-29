/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basicobject;

/**
 *
 * @author Nghia
 */
public class Plant {
    private int id;
    private String name;
    private int price;
    private String imgpath;
    private String desString;
    private int status;
    private int cateid;
    private String catename;

    public Plant() {
    }

    public Plant(int id, String name, int price, String imgpath, String desString, int status, int cateid, String catename) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgpath = imgpath;
        this.desString = desString;
        this.status = status;
        this.cateid = cateid;
        this.catename = catename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getDesString() {
        return desString;
    }

    public void setDesString(String desString) {
        this.desString = desString;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateid() {
        return cateid;
    }

    public void setCateid(int cateid) {
        this.cateid = cateid;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }
    
    
}
