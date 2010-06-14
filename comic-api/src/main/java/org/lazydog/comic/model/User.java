package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a user.
 *
 * @author  Ron Rickard
 */
public class User 
       extends Entity<User>
       implements Comparable<User>,
                  Serializable {
    
    // Declare.
    @NotNull(message="Name is required.") 
    @Size(max=50, message="Name cannot contain more than 50 characters.")
    private String name;
    private UserPreference userPreference;
    
    /**
     * Constructor.
     */
    public User() {

        super();
        this.setName(null);
        this.setUserPreference(null);
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
    public int compareTo(User object) {
        
        // Declare.
        int lastCompare;
        String thatName;
        String thisName;

        // Initialize.
        lastCompare = 0;
        thatName = (object.getName() == null) ? "" : object.getName();
        thisName = (this.getName() == null) ? "" : this.getName();
        
        // Compare this object to the object.
        lastCompare = thisName.compareTo(thatName);

        return lastCompare;
    }
                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public User copy() {
        
        // Declare.
        User copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setName(this.getName());
        copy.setUserPreference(this.getUserPreference());

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
        if (object instanceof User &&
            this.compareTo((User)object) == 0) {
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
     * Get the user preference.
     *
     * @return  the user preference.
     */
    public UserPreference getUserPreference() {
        return this.userPreference;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        String thisName;
        
        // Initialize.
        thisName = (this.getName() == null) ? "" : this.getName();
        
        return thisName.hashCode();
    }
    
    /**
     * Set the name.
     *
     * @param  value  the value.
     */
    public void setName(String name) {
        this.name = this.trimmed(name);
    }

    /**
     * Set the user preference.
     *
     * @param  userPreference  the user preference.
     */
    public void setUserPreference(UserPreference userPreference) {
        this.userPreference = userPreference;
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
        
        toString.append("User [");
        toString.append("name = ").append(this.getName());
        toString.append("]");
        
        return toString.toString();
    }
}

