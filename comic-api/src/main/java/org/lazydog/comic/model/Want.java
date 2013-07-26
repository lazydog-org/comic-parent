package org.lazydog.comic.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


/**
 * Entity class used to represent a want.
 *
 * @author  Ron Rickard
 */
public class Want extends Entity<Want> implements Comparable<Want> {

    private static final long serialVersionUID = 1L;
    
    @Valid @NotNull(message="Comic is required.")
    private Comic comic;
    @NotNull(message="UUID is required.")
    private String uuid;

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
    public int compareTo(Want that) {
        
        // Declare.
        int lastCompare;
        Comic thatComic;
        String thatUuid;
        Comic thisComic;
        String thisUuid;

        // Initialize.
        lastCompare = 0;
        thatComic = replaceNull(that.getComic(), new Comic());
        thatUuid = replaceNull(that.getUuid(), nilUuid());
        thisComic = replaceNull(this.getComic(), new Comic());
        thisUuid = replaceNull(this.getUuid(), nilUuid());
        
        // Compare this object to the object.
        lastCompare = thisUuid.compareTo(thatUuid);
        lastCompare = (lastCompare != 0) ? lastCompare : thisComic.compareTo(thatComic);
        
        return lastCompare;
    }
                                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Want copy() {
        
        // Declare.
        Want copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setComic(this.getComic());
        copy.setUuid(this.getUuid());

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
        if (object instanceof Want &&
            this.compareTo((Want)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the comic.
     *
     * @return  the comic.
     */
    public Comic getComic() {
        return this.comic;
    }

    /**
     * Get the universally unique identifier (UUID).
     *
     * @return  the universally unique identifier (UUID).
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        Comic thisComic;
        String thisUuid;
        
        // Initialize.
        thisComic = replaceNull(this.getComic(), new Comic());
        thisUuid = replaceNull(this.getUuid(), nilUuid());
        
        return thisUuid.hashCode()*31
             + thisComic.hashCode();
    }
  
    /**
     * Set the comic.
     *
     * @param  comic  the comic.
     */
    public void setComic(Comic comic) {
        this.comic = comic;
    }

    /**
     * Set the universally unique identifier (UUID).
     *
     * @param  uuid  the universally unique identifier (UUID).
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        
        toString.append("Want [");
        toString.append("comic = ").append(this.getComic());
        toString.append(", uuid = ").append(this.getUuid());
        toString.append("]");
        
        return toString.toString();
    }
}

