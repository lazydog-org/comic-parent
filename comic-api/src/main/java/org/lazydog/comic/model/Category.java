package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a category.
 *
 * @author  Ron Rickard
 */
public class Category 
       extends Entity<Category>
       implements Comparable<Category>,
                  Serializable {
    
    // Declare.
    @NotNull(message="Name is required.") 
    @Size(max=50, message="Name cannot contain more than 50 characters.")
    private String name;

    /**
     * Constructor.
     */
    public Category() {
        
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
    public int compareTo(Category object) {
        
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
    public Category copy() {
        
        // Declare.
        Category copy;
        
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
        if (object instanceof Category &&
            this.compareTo((Category)object) == 0) {
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
        String thisName;
        
        // Initialize.
        thisName = (this.getName() == null) ? "" : this.getName();
        
        return thisName.hashCode();
    }
      
    /**
     * Set the name.
     *
     * @param  name  the name.
     */
    public void setName(String name) {
        this.name = trimmed(name);
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
        
        toString.append("Category [");
        toString.append("name = ").append(this.getName());
        toString.append("]");
        
        return toString.toString();
    }
}

