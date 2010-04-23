package org.lazydog.comic.internal.repository.interceptor;

import java.lang.management.ManagementFactory;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.management.InstanceNotFoundException;
import javax.management.MalformedObjectNameException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import org.lazydog.comic.internal.repository.mbean.Monitor;


/**
 * Service monitor interceptor.
 * 
 * @author  Ron Rickard
 */
public class ServiceMonitor {
    
    /**
     * Invoke the addServiceException() on the monitor.
     * 
     * @param  monitorName  the monitor name.
     * 
     * @throws  Exception  if unable to invoke the addServiceException() on
     *                     the monitor.
     */
    private void addServiceException(ObjectName monitorName) 
            throws Exception {
    
        try {
            // Declare.
            MBeanServer mBeanServer;

            // Get the MBean server.
            mBeanServer = ManagementFactory.getPlatformMBeanServer();
            
            // Invoke the addServiceException() method on the monitor.
            mBeanServer.invoke(monitorName, "addServiceException", null, null);
        }
        catch(InstanceNotFoundException e) {
            throw new Exception("The MBean, " + 
                    monitorName.getCanonicalName() + ", is not registered.", 
                    e);
        }
        catch(MBeanException e) {
            // Ignore since addServiceException() method 
            // does not throw an exception.
        }
        catch(ReflectionException e) {
            throw new Exception("The MBean, " +
                    monitorName.getCanonicalName() + 
                    ", does not contain the method addServiceException().", 
                    e);
        }
    }
    
    /**
     * Invoke the addServiceRequest() on the monitor.
     * 
     * @param  monitorName  the monitor name.
     * @param  serviceTime  the service time.
     * 
     * @throws  Exception  if unable to invoke the addServiceRequest() on
     *                     the monitor.
     */
    private void addServiceRequest(ObjectName monitorName,
                                   long serviceTime) 
            throws Exception {
        
                try {
            // Declare.
            MBeanServer mBeanServer;

            // Get the MBean server.
            mBeanServer = ManagementFactory.getPlatformMBeanServer();
            
            // Invoke the addServiceRequest() method on the monitor.
            mBeanServer.invoke(monitorName, "addServiceRequest", 
                    new Object[]{serviceTime}, 
                    new String[]{"long"});
        }
        catch(InstanceNotFoundException e) {
            throw new Exception("The MBean, " + 
                    monitorName.getCanonicalName() + ", is not registered.", 
                    e);
        }
        catch(MBeanException e) {
            // Ignore since addServiceRequest() method 
            // does not throw an exception.
        }
        catch(ReflectionException e) {
            throw new Exception("The MBean, " +
                    monitorName.getCanonicalName() + 
                    ", does not contain the method addServiceRequest().", 
                    e);
        }
    }
    
    /**
     * Get the monitor name.
     * 
     * @param  className    the class name.
     * @param  methodName   the method name.
     * 
     * @return  the monitor name.
     * 
     * @throws  Exception  if unable to get the monitor name.
     */
    private ObjectName getMonitorName(String className,
                                      String methodName)
            throws Exception {
        
        // Declare.
        ObjectName monitorName;
        String name;
        
        // Initialize.
        monitorName = null;
        
        // Set the object name of the MBean.
        name = className + ":method=" + methodName;
        
        try {
            
            // Declare.
            MBeanServer mBeanServer;

            // Get the MBean server.
            mBeanServer = ManagementFactory.getPlatformMBeanServer();

            // The monitor name.
            monitorName = new ObjectName(name);

            // Check if the monitor is not registered with the MBean server.
            if (!mBeanServer.isRegistered(monitorName)) {

                // Register the monitor with the MBean server.
                mBeanServer.registerMBean(new Monitor(), monitorName);
            }
        }
        catch(MalformedObjectNameException e) {
            throw new Exception("The monitor name " + name + " is malformed.", 
                    e);
        }
        
        return monitorName;
    }
    
    /**
     * Monitor the EJB service method call.
     * 
     * @param  invocationContext  the generic representation of the EJB service
     *                            method.
     * 
     * @return  the result of the EJB service method call.
     * 
     * @throws  Exception  if unable to monitor the EJB service method call.
     */
    @AroundInvoke
    public Object monitor(InvocationContext invocationContext) 
           throws Exception {
        
        // Declare.
        boolean exceptionOccurred;
        Object result;
        long startTime;
        
        // Initialize.
        exceptionOccurred = false;
        result = null;
        
        // Get the time the EJB service started.
        startTime = System.currentTimeMillis();
        
        try {
            
            // Proceed to the next entry in the interceptor chain.
            result = invocationContext.proceed();
        }
        catch(Exception e) {
            exceptionOccurred = true;
            throw e;
        }
        finally {
            
            try {
                
                // Declare.
                String className;
                String methodName;
                ObjectName monitorName;

                // Get the EJB service class and method name.
                className = invocationContext.getMethod().getDeclaringClass().getName();
                methodName = invocationContext.getMethod().getName();

                // Get the monitor name.
                monitorName = this.getMonitorName(className, methodName);

                // Check if an exception occurred.
                if (exceptionOccurred) {

                    // Add the service exception to the monitor.
                    this.addServiceException(monitorName);
                }
                else {

                    // Declare.
                    long serviceTime;

                    // Calculate the duration of the EJB service.
                    serviceTime = System.currentTimeMillis() - startTime;

                    // Add the service request to the monitor.
                    this.addServiceRequest(monitorName, serviceTime);
                }
            }
            catch(Exception e) {
                // Ignore.
            }
        }
        
        return result;
    }
}
