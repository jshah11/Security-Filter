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
public class timeclicked implements Serializable{

    private int id;
    private iptracker IpTrackerId;
    private long time;
    private Date date;
    

    /**
     * 
     */
    public timeclicked() {
    }

    /**
     * 
     * @param IpTrackerId
     * @param time
     * @param date
     */
    public timeclicked(iptracker IpTrackerId, long time, Date date) {
        
        this.IpTrackerId = IpTrackerId;
        this.time = time;
        this.date = date;
        
    }

    /**
     * 
     * @return
     */
    public iptracker getIpTrackerId() {
        return IpTrackerId;
    }

    /**
     * 
     * @param IpTrackerId
     */
    public void setIpTrackerId(iptracker IpTrackerId) {
        this.IpTrackerId = IpTrackerId;
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
    public long getTime() {
        return time;
    }

    /**
     * 
     * @param time
     */
    public void setTime(long time) {
        this.time = time;
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
        this.date = date;
    }
    
    
    
}
