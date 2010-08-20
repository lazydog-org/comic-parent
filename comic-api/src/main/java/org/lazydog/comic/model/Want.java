package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;


/**
 * Entity class used to represent a want.
 *
 * @author  Ron Rickard
 */
public class Want
       extends Entity<Want>
       implements Comparable<Want>,
                  Serializable {

    private static final long serialVersionUID = 1L;
    
    @Valid @NotNull(message="Comic is required.")
    private Comic comic;

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
        User thatCreateUser;
        Comic thisComic;
        User thisCreateUser;

        // Initialize.
        lastCompare = 0;
        thatComic = normalize(that.getComic(), Comic.class);
        thatCreateUser = normalize(that.getCreateUser(), User.class);
        thisComic = normalize(this.getComic(), Comic.class);
        thisCreateUser = normalize(this.getCreateUser(), User.class);
        
        // Compare this object to the object.
        lastCompare = thisCreateUser.compareTo(thatCreateUser);
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
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        Comic thisComic;
        User thisCreateUser;
        
        // Initialize.
        thisComic = normalize(this.getComic(), Comic.class);
        thisCreateUser = normalize(this.getCreateUser(), User.class);
        
        return thisCreateUser.hashCode()*31
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
        toString.append("]");
        
        return toString.toString();
    }
}

