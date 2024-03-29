package org.lazydog.comic.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a trait.
 *
 * @author  Ron Rickard
 */
public class Trait extends Entity<Trait> implements Comparable<Trait> {
    
    private static final long serialVersionUID = 1L;

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
    public int compareTo(Trait that) {
        
        // Declare.
        int lastCompare;
        String thatValue;
        String thisValue;

        // Initialize.
        lastCompare = 0;
        thatValue = replaceNull(that.getValue(), "");
        thisValue = replaceNull(this.getValue(), "");
        
        // Compare this object to the object.
        lastCompare = thisValue.compareTo(thatValue);

        return lastCompare;
    }
                                
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Trait copy() {
        
        // Declare.
        Trait copy;
        
        // Create a copy.
        copy = super.copy();
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
        if (object instanceof Trait &&
            this.compareTo((Trait)object) == 0) {
            equals = true;
        }
        
        return equals;
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
        String thisValue;
        
        // Initialize.
        thisValue = replaceNull(this.getValue(), "");
        
        return thisValue.hashCode();
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
        
        toString.append("Trait [");
        toString.append("value = ").append(this.getValue());
        toString.append("]");
        
        return toString.toString();
    }
}

