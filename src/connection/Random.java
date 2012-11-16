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

  /**
   * 
   */
  public Random() {
    }

    /**
     * 
     * @param id
     * @param ipaddress
     * @param random
     */
    public Random(String id, String ipaddress, String random) {
        this.id = id;
        this.ipaddress = ipaddress;
        this.random = random;
    }

  
    /**
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    public String getIpaddress() {
        return ipaddress;
    }

    /**
     * 
     * @param ipaddress
     */
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    /**
     * 
     * @return
     */
    public String getRandom() {
        return random;
    }

    /**
     * 
     * @param random
     */
    public void setRandom(String random) {
        this.random = random;
    }
  
  
}
