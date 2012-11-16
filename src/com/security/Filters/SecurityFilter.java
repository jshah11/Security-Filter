/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.security.Filters;

import connection.*;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Jinal Shah
 */
public class SecurityFilter implements Filter {

    private static final boolean debug = false;
    private boolean check = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    /**
     * 
     */
    public SecurityFilter() {
    }

    private void doBeforeProcessing(RequestWrapper request, ResponseWrapper response)
            throws IOException, ServletException {
        if (debug) {
            log("NewFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(RequestWrapper request, ResponseWrapper response)
            throws IOException, ServletException {
        if (debug) {
            log("NewFilter:DoAfterProcessing");
        }
        if (!request.getRequestURI().equals("/Test1/Security/Error.jsp")) {
            if (check) {
                String random = random();
                request.getSession().setAttribute(request.getSession().getId(), random);
                Database db = new Database();
                db.opensession();
                db.addtoDatabase(new Random(request.getSession().getId(), request.getRemoteHost(), random));
                db.closesession();
            }
        }
    }

    /**
     * 
     * @return Random String generated
     */
    public String random() {
        SecureRandom srandom = new SecureRandom();

        return new BigInteger(176, srandom).toString(32);
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("NewFilter:doFilter()");
        }

        // Create wrappers for the request and response objects.
        // Using these, you can extend the capabilities of the
        // request and response, for example, allow setting parameters
        // on the request before sending the request to the rest of the filter chain,
        // or keep track of the cookies that are set on the response.
        //
        // Caveat: some servers do not handle wrappers very well for forward or
        // include requests.
        RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
        ResponseWrapper wrappedResponse = new ResponseWrapper((HttpServletResponse) response);
        if (((HttpServletRequest) request).getMethod().equalsIgnoreCase("POST")) {
            //check((HttpServletRequest) request, (HttpServletResponse) response);
            BruteForce((HttpServletRequest) request, (HttpServletResponse) response);
        }
        doBeforeProcessing(wrappedRequest, wrappedResponse);

        Throwable problem = null;

        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
        }

        doAfterProcessing(wrappedRequest, wrappedResponse);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     * @return 
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter 
     * @param filterConfig 
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("NewFilter: Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("NewFilter()");
        }
        StringBuilder sb = new StringBuilder("NewFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());

    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * 
     * @param t
     * @return
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    /**
     * 
     * @param msg
     */
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

    /**
     * 
     * @param request
     * @param httpServletResponse
     */
    private void check(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Database db = new Database();
        db.opensession();
        if (!request.getSession().isNew()) {
            if (!db.checkRequest(new Random(request.getSession().getId(), request.getRemoteAddr(), (String) request.getSession().getAttribute(request.getSession().getId())))) {
                try {
                    httpServletResponse.sendRedirect("Security/Error.jsp");
                    check = false;
                } catch (IOException ex) {
                    Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            try {
                httpServletResponse.sendRedirect("Security/Error.jsp");
                check = false;
            } catch (IOException ex) {
                Logger.getLogger(SecurityFilter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        db.closesession();
    }

    /**
     * This request wrapper class extends the support class HttpServletRequestWrapper,
     * which implements all the methods in the HttpServletRequest interface, as
     * delegations to the wrapped request. 
     * You only need to override the methods that you need to change.
     * You can get access to the wrapped request using the method getRequest()
     */
    class RequestWrapper extends HttpServletRequestWrapper {

        public RequestWrapper(HttpServletRequest request) {
            super(request);
        }
        // You might, for example, wish to add a setParameter() method. To do this
        // you must also override the getParameter, getParameterValues, getParameterMap,
        // and getParameterNames methods.
        protected Hashtable localParams = null;

        public void setParameter(String name, String[] values) {
            if (debug) {
                System.out.println("NewFilter::setParameter(" + name + "=" + values + ")" + " localParams = " + localParams);
            }

            if (localParams == null) {
                localParams = new Hashtable();
                // Copy the parameters from the underlying request.
                Map wrappedParams = getRequest().getParameterMap();
                Set keySet = wrappedParams.keySet();
                for (Iterator it = keySet.iterator(); it.hasNext();) {
                    Object key = it.next();
                    Object value = wrappedParams.get(key);
                    localParams.put(key, value);
                }
            }
            localParams.put(name, values);
        }

        @Override
        public String getParameter(String name) {
            if (debug) {
                System.out.println("NewFilter::getParameter(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return cleanXSS(getRequest().getParameter(name));
            }
            Object val = localParams.get(name);
            if (val instanceof String) {
                return cleanXSS((String) val);
            }
            if (val instanceof String[]) {
                String[] values = (String[]) val;
                return cleanXSS(values[0]);
            }
            return (val == null ? null : cleanXSS(val.toString()));
        }

        @Override
        public String[] getParameterValues(String name) {
            if (debug) {
                System.out.println("NewFilter::getParameterValues(" + name + ") localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterValues(name);
            }
            return (String[]) localParams.get(name);
        }

        @Override
        public Enumeration getParameterNames() {
            if (debug) {
                System.out.println("NewFilter::getParameterNames() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterNames();
            }
            return localParams.keys();
        }

        @Override
        public Map getParameterMap() {
            if (debug) {
                System.out.println("NewFilter::getParameterMap() localParams = " + localParams);
            }
            if (localParams == null) {
                return getRequest().getParameterMap();
            }
            return localParams;
        }
        
        /**
         * Cleans the parameters to avoid xss attack
         * @param value
         * @return String which is xss free
         */
        private String cleanXSS(String value) {
            //You'll need to remove the spaces from the html entities below
            value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
            value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
            value = value.replaceAll("'", "& #39;");
            value = value.replaceAll("eval\\((.*)\\)", "");
            value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
            value = value.replaceAll("script", "");
            return value;
        }
    }

    /**
     * This response wrapper class extends the support class HttpServletResponseWrapper,
     * which implements all the methods in the HttpServletResponse interface, as
     * delegations to the wrapped response. 
     * You only need to override the methods that you need to change.
     * You can get access to the wrapped response using the method getResponse()
     */
    class ResponseWrapper extends HttpServletResponseWrapper {

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }
    }

    /**
     * Checks for Brute Force Attack
     * @param request
     * @param response
     */
    public void BruteForce(HttpServletRequest request, HttpServletResponse response) {
        try {
            //Database Object created
            Database connection = new Database();
            //connection opened
            connection.opensession();
            //Checks if the IP is already blacklisted or not
            if (connection.findIp(BlacklistedIp.class, request.getRemoteAddr()) == 0) {
                //Checks if the IP is already Verified or not
                if (connection.findIp(verifiedip.class, request.getRemoteAddr()) == 0) {
                    iptracker t = null;
                    //Gets the number of hits that the IP has made before verification
                    t = connection.findIpTracker(request.getRemoteAddr());
                    if (t == null) {
                        //creates a new object and sets the hit count to 1 if the IP is new
                        t = new iptracker(request.getRemoteAddr(), 1);
                        connection.addtoDatabase(t);
                        connection.addtoDatabase(new timeclicked(t, (new Date()).getTime(), new Date()));
                        // Access Welcome Page
                    } else {
                        //Increments the count
                        t.setNumber_of_hits(t.getNumber_of_hits() + 1);
                        // Get the list of all the clicks made by that IP
                        List<timeclicked> clicked = connection.findTimeclicked(t.getId());
                        // if the clicks exceed 10 then check
                        if (!clicked.isEmpty() && clicked.size() > 10) {

                            long currenttime = (new Date()).getTime();
                            //avg time between request
                            long avgtime = avgs(clicked);
                            //if the avg time between 10 request and the current time difference is less than a second then show captcha
                            if (((currenttime - avgtime) / 1000) < 1000) {
                                if (request.getServletContext().getAttribute(request.getRemoteAddr()) == null) {
                                    request.getServletContext().setAttribute(request.getRemoteAddr(), 1);
                                } else {

                                    int z = (Integer) request.getServletContext().getAttribute(request.getRemoteAddr());
                                    //if there are 10 wrong attempts of catcha then blacklist that IP
                                    if (z > 10) {
                                        connection.addtoDatabase(new BlacklistedIp(request.getRemoteAddr()));
                                    }
                                    request.getServletContext().setAttribute(request.getRemoteAddr(), z + 1);
                                    response.sendRedirect("Security/captcha.jsp");
                                    check = false;
                                }

                            }
                        }
                        connection.addtoDatabase(new timeclicked(t, (new Date()).getTime(), new Date()));
                    }
                }

            } else {
                // Error Page
                response.sendRedirect("Security/Error.jsp");
                check = false;
            }
            connection.closesession();
        } catch (IOException ex) {
        } finally {
        }
    }

    /**
     * This gives average of the List of time clicked
     * @param clicked
     * @return
     */
    public long avgs(List<timeclicked> clicked) {
        long sum = 0;
        for (timeclicked t : clicked) {
            sum = sum + t.getTime();

        }
        sum = (long) sum / clicked.size();
        return sum;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
