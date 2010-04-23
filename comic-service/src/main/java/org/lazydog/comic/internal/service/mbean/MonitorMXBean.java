package org.lazydog.comic.internal.service.mbean;


/**
 * Monitor MXBean interface.
 * 
 * @author  Ron Rickard
 */
public interface MonitorMXBean {
    
    public void addServiceException();
    
    public void addServiceRequest(long serviceTime);
    
    public long getAverageServiceTime();
    
    public long getMaximumServiceTime();
    
    public long getMinimumServiceTime();
    
    public int getServiceRequests();
    
    public int getServiceExceptions();
    
    public long getTotalServiceTime();
    
    public void reset();
}
