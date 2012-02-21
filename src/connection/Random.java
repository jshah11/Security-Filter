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
public class Random implements Serializable{
    
  private String id;
  private String ipaddress;
  private String random;

    public Random() {
    }

    public Random(String id, String ipaddress, String random) {
        this.id = id;
        this.ipaddress = ipaddress;
        this.random = random;
    }

  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
  
  
}
