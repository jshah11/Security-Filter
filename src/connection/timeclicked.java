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
    

    public timeclicked() {
    }

    public timeclicked(iptracker IpTrackerId, long time, Date date) {
        
        this.IpTrackerId = IpTrackerId;
        this.time = time;
        this.date = date;
        
    }

    public iptracker getIpTrackerId() {
        return IpTrackerId;
    }

    public void setIpTrackerId(iptracker IpTrackerId) {
        this.IpTrackerId = IpTrackerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
