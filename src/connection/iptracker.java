/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;
import java.io.Serializable;
/**
 *
 * @author Jinal Shah
 */
public class iptracker implements Serializable {
    private int id;
    private String ipaddress;
    private int number_of_hits;

    public iptracker() {
    }

    public iptracker(String ipaddress, int number_of_hits) {
        
        this.ipaddress = ipaddress;
        this.number_of_hits = number_of_hits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public int getNumber_of_hits() {
        return number_of_hits;
    }

    public void setNumber_of_hits(int number_of_hits) {
        this.number_of_hits = number_of_hits;
    }
    
    
    
}
