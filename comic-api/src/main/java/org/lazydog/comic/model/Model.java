package org.lazydog.comic.model;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Model.
 * 
 * @author  Ron Rickard
 */
public class Model implements Serializable {
          
    private static final long serialVersionUID = 1L;
    
    /**
     * Compare this object to the specified object.
     * 
     * @param  object  the object to compare this object against.
     * 
     * @return  true if the objects are equal; false otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        return EqualsBuilder.reflectionEquals(this, object, "id");
    }
    
    /**
     * Returns a hash code for this object.
     * 
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "id");
    }
   
    /**
     * Replace the original object with the replacement object if the original object is null.
     * 
     * @param  original     the original object.
     * @param  replacement  the replacement object.
     * 
     * @return  the original object if it is not null, otherwise the replacement object.
     * 
     * @throws  IllegalArgumentException  if the replacement object is null.
     */
    protected static <U, V extends U> U replaceNull(final U original, final V replacement) {
    
        // Check if the replacement object is null.
        if (replacement == null) {
            throw new IllegalArgumentException("The replacement object cannot be null.");
        }
        
        return (original == null) ? replacement : original;
    }
        
    /**
     * Get this object as a string.
     *
     * @return  this object as a string.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
