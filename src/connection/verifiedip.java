/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author Jinal Shah
 */
public class verifiedip implements Serializable{
    private int id;
    private String ip;
    private Date date;

    public verifiedip() {
        this.date = new Date();
    }

    public verifiedip(String ipaddress) {
        this.date = new Date();
        this.ip = ipaddress;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ipaddress) {
        this.ip = ipaddress;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }

    public Date getDate() {
        return date;
    }
    
    
    
}
