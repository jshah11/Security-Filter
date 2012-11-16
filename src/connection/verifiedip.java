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

    /**
     * 
     */
    public verifiedip() {
        this.date = new Date();
    }

    /**
     * 
     * @param ipaddress
     */
    public verifiedip(String ipaddress) {
        this.date = new Date();
        this.ip = ipaddress;
    }
    

    /**
     * 
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * 
     * @param ipaddress
     */
    public void setIp(String ipaddress) {
        this.ip = ipaddress;
    }

    /**
     * 
     * @param date
     */
    public void setDate(Date date) {
        this.date = new Date();
    }

    /**
     * 
     * @return
     */
    public Date getDate() {
        return date;
    }
    
    
    
}
