package org.lazydog.comic.internal.service.mbean;


/**
 * Monitor.
 * 
 * @author  Ron Rickard
 */
public class Monitor 
       implements MonitorMXBean {

    private long maximumServiceTime;
    private long minimumServiceTime;
    private int serviceRequests;
    private int serviceExceptions;
    private long totalServiceTime;
        
    /**
     * Add a service exception.
     */
    @Override
    public void addServiceException() {
        this.serviceExceptions++;
    }
    
    /**
     * Add a service request.
     * 
     * @param  serviceTime  the service time for the service request.
     */
    @Override
    public void addServiceRequest(long serviceTime) {
        
        // Check if the maximum service time is less than the service time.
        if (this.serviceRequests == 0 || this.maximumServiceTime < serviceTime) {
            
            // Set the maximum service time to the service time.
            this.maximumServiceTime = serviceTime;
        }
        
        // Check if the minimum service time is more than the service time.
        if (this.serviceRequests == 0 || this.minimumServiceTime > serviceTime) {
            
            // Set the minimum service time to the service time.
            this.minimumServiceTime = serviceTime;
        }
        
        this.serviceRequests++;
        this.totalServiceTime += serviceTime;
    }

    /**
     * Get the average service time.
     * 
     * @return  the average service time.
     */
    @Override
    public long getAverageServiceTime() {
        return (this.serviceRequests != 0) ? this.totalServiceTime / this.serviceRequests : 0;
    }
    
    /**
     * Get the maximum service time.
     * 
     * @return  the maximum service time.
     */
    @Override
    public long getMaximumServiceTime() {
        return this.maximumServiceTime;
    }
    
    /**
     * Get the minimum service time.
     * 
     * @return  the minimum service time.
     */
    @Override
    public long getMinimumServiceTime() {
        return this.minimumServiceTime;
    }
    
    /**
     * Get the number of service requests.
     * @return  the number of service requests.
     */
    @Override
    public int getServiceRequests() {
        return this.serviceRequests;
    }
    
    /**
     * Get the number of service exceptions.
     * 
     * @return  the number of service exceptions.
     */
    @Override
    public int getServiceExceptions() {
        return this.serviceExceptions;
    }
    
    /**
     * Get the total service time.
     * 
     * @return  the total service time.
     */
    @Override
    public long getTotalServiceTime() {
        return this.totalServiceTime;
    }
    
    /**
     * Reset the monitor.
     */
    @Override
    public void reset() {
        
        this.maximumServiceTime = 0L;
        this.minimumServiceTime = 0L;
        this.serviceRequests = 0;
        this.serviceExceptions = 0;
        this.totalServiceTime = 0L;
    }
}
