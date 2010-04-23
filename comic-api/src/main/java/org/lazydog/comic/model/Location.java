package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a location.
 *
 * @author  Ron Rickard
 */
public class Location
       extends Entity<Location>
       implements Comparable<Location>,
                  Serializable {
    
    // Declare.       
    @NotNull(message="Name is required.") 
    @Size(max=50, message="Name cannot contain more than 50 characters.")
    private String name;
    
    /**
     * Constructor.
     */
    public Location() {
        
        super();
        this.setName(null);
    }

    /**
     * Compare this object to the specified object.
     *
     * @param  object  the object to compare this object against.
     *
     * @return  the value 0 if this object is equal to the object;
     *          a value less than 0 if this object is less than the object;
     *          and a value greater than 0 if this object is greater than the
     *          object.
     */
    @Override
    public int compareTo(Location object) {
        
        // Declare.
        int lastCompare;
        User thatCreateUser;
        String thatName;
        User thisCreateUser;
        String thisName;

        // Initialize.
        lastCompare = 0;
        thatCreateUser = (object.getCreateUser() == null) ? new User() : object.getCreateUser();
        thatName = (object.getName() == null) ? "" : object.getName();
        thisCreateUser = (this.getCreateUser() == null) ? new User() : this.getCreateUser();
        thisName = (this.getName() == null) ? "" : this.getName();
        
        // Compare this object to the object.
        lastCompare = thisCreateUser.compareTo(thatCreateUser);
        lastCompare = (lastCompare != 0) ? lastCompare : thisName.compareTo(thatName);

        return lastCompare;
    }
        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Location copy() {
        
        // Declare.
        Location copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setName(this.getName());
        
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
        if (object instanceof Location &&
            this.compareTo((Location)object) == 0) {
            equals = true;
        }

        return equals;
    }
    
    /**
     * Get the name.
     *
     * @return  the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        User thisCreateUser;
        String thisName;
        
        // Initialize.
        thisCreateUser = (this.getCreateUser() == null) ? new User() : this.getCreateUser();
        thisName = (this.getName() == null) ? "" : this.getName();
        
        return thisCreateUser.hashCode()*31
             + thisName.hashCode();
    }
     
    /**
     * Set the name.
     *
     * @param  name  the name.
     */
    public void setName(String name) {
        this.name = this.trimmed(name);
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
        
        toString.append("Location [");
        toString.append("name = ").append(this.getName());
        toString.append("]");
        
        return toString.toString();
    }
}

