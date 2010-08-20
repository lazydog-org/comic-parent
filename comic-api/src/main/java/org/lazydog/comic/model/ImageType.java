package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a image type.
 *
 * @author  Ron Rickard
 */
public class ImageType 
       extends Entity<ImageType>
       implements Comparable<ImageType>,
                  Serializable {
    
    private static final long serialVersionUID = 1L;

    @NotNull(message="Directory path is required.")
    @Size(max=100, message="Directory path cannot contain more than 100 characters.")
    private String directoryPath;
    @NotNull(message="Value is required.") 
    @Size(max=50, message="Value cannot contain more than 50 characters.")
    private String value;

    /**
     * Compare this object to the specified object.
     *
     * @param  that  the object to compare this object against.
     *
     * @return  the value 0 if this object is equal to the object;
     *          a value less than 0 if this object is less than the object;
     *          and a value greater than 0 if this object is greater than the
     *          object.
     */
    @Override
    public int compareTo(ImageType that) {
        
        // Declare.
        int lastCompare;
        String thatDirectoryPath;
        String thatValue;
        String thisDirectoryPath;
        String thisValue;

        // Initialize.
        lastCompare = 0;
        thatDirectoryPath = normalize(that.getDirectoryPath(),String.class);
        thatValue = normalize(that.getValue(), String.class);
        thisDirectoryPath = normalize(this.getDirectoryPath(), String.class);
        thisValue = normalize(this.getValue(), String.class);
        
        // Compare this object to the object.
        lastCompare = thisValue.compareTo(thatValue);
        lastCompare = (lastCompare != 0) ? lastCompare : thisDirectoryPath.compareTo(thatDirectoryPath);

        return lastCompare;
    }
                
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public ImageType copy() {
        
        // Declare.
        ImageType copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setDirectoryPath(this.getDirectoryPath());
        copy.setValue(this.getValue());
        
        return copy;
    }
    
    /**
     * Compare this object to the specified object.
     *
     * @param  object  the object to compare this object against.
     *
     * @return  true if the objects are equal; false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        
        // Declare.
        boolean equals;

        // Initialize.
        equals = false;
        
        // Check if the object is an instance of this class
        // and is equal to this object.
        if (object instanceof ImageType &&
            this.compareTo((ImageType)object) == 0) {
            equals = true;
        }
        
        return equals;
    }
        
    /**
     * Get the directory path.
     * 
     * @return  the directory path.
     */
    public String getDirectoryPath() {
        return this.directoryPath;
    }
    
    /**
     * Get the value.
     *
     * @return  the value.
     */
    public String getValue() {
        return this.value;
    }
    
    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        String thisDirectoryPath;
        String thisValue;
        
        // Initialize.
        thisDirectoryPath = normalize(this.getDirectoryPath(), String.class);
        thisValue = normalize(this.getValue(), String.class);
        
        return thisValue.hashCode()*31
             + thisDirectoryPath.hashCode();
    }

    /**
     * Set the directory path.
     * 
     * @param  directoryPath  the directory path.
     */
    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = trimmed(directoryPath);
    }
    
    /**
     * Set the value.
     *
     * @param  value  the value.
     */
    public void setValue(String value) {
        this.value = trimmed(value);
    }
     
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {
                
        // Declare.
        StringBuffer toString;
        
        // Initialize.
        toString = new StringBuffer();
        
        toString.append("ImageType [");
        toString.append("directoryPath = ").append(this.getDirectoryPath());
        toString.append(", value = ").append(this.getValue());
        toString.append("]");
        
        return toString.toString();
    }
}

