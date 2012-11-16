/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Jinal Shah
 */
public class Database
{

    Session session = null;
    

    /**
     * opens the Hibernate Session
     */
    public void opensession()
    {
       
        session = HibernateUtil.getSessionFactory().openSession();
    }

    /**
     * 
     */
    public Database() 
    {
        
    }
    
    
    /**
     * Saves or updates the object in the database
     * @param ip
     */
    public void addtoDatabase(Object ip)
    {
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.saveOrUpdate(ip);
        transaction.commit();
        transaction = null;
    }
    /**
     * closes the session
     */
    public void closesession()
    {
        session.flush();
        session.close();
        
    }

    /**
     * 
     * @param c
     * @param remoteAddr
     * @return integer checking whether IP is there or not
     */
    public int findIp(Class c,String remoteAddr) {
        
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        Query query = null;
        String[] classname = c.getName().split(".");
        Date d = new Date();
        if(classname.length>1)
        {
            
            query = session.createQuery("from "+classname[classname.length-1]+" where ipaddress like '"+remoteAddr+"' AND date >= '"+(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+"'");
        }
        else
        query = session.createQuery("from "+c.getName()+" where ipaddress like '"+remoteAddr+"' AND date >= '"+(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+"'");
        if(query.list().size()>0)
        {
            return 1;
        }
        return 0;
        
    }
    
    /**
     * 
     * @param remoteAddr
     * @return iptracker object if found
     */
    public iptracker findIpTracker(String remoteAddr) {
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        Query query = null;
        query = session.createQuery("from iptracker where ipaddress like '"+remoteAddr+"'");
        if(query.list().size()>0)
        {
            return (iptracker)query.list().get(0);
        }
        return null;
        
    }
    
    /**
     * 
     * @param id
     * @return
     */
    public List<timeclicked> findTimeclicked(int id) {
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        Query query = null;
        Date d = new Date();
        query = session.createQuery("from timeclicked where IpTrackerId = "+id+" AND date = '"+(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate()+"'");
        
        if(query.list().size()>0)
        {
            
            return query.list();
        }
        return (new ArrayList<timeclicked>());
        
    }

    /**
     * 
     * @param random
     * @return
     */
    public boolean checkRequest(Random random) {
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        if(session.get(Random.class,random.getId())!=null)
        {
            return true;
        }
        return false;
    }
    
    
}
