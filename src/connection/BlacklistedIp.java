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
public class BlacklistedIp implements Serializable{
    private int id;
    private String Ip;
    private Date date;

    public BlacklistedIp() {
        this.date = new Date();
    }

    public BlacklistedIp(String Ip) {
        this.Ip = Ip;
        this.date = new Date();
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new Date();
    }
    
    
}
