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

    /**
     * 
     */
    public BlacklistedIp() {
        this.date = new Date();
    }

    /**
     * 
     * @param Ip
     */
    public BlacklistedIp(String Ip) {
        this.Ip = Ip;
        this.date = new Date();
    }

    /**
     * 
     * @return
     */
    public String getIp() {
        return Ip;
    }

    /**
     * 
     * @param Ip
     */
    public void setIp(String Ip) {
        this.Ip = Ip;
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
    public Date getDate() {
        return date;
    }

    /**
     * 
     * @param date
     */
    public void setDate(Date date) {
        this.date = new Date();
    }
    
    
}
