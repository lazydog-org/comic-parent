package org.lazydog.comic.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a comic character.
 *
 * @author  Ron Rickard
 */
public class ComicCharacter extends Entity<ComicCharacter> implements Comparable<ComicCharacter> {
    
    private static final long serialVersionUID = 1L;

    private Image image;
    @NotNull(message="Name is required.") 
    @Size(max=50, message="Name cannot contain more than 50 characters.")
    private String name;

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
    public int compareTo(ComicCharacter that) {
        
        // Declare.
        int lastCompare;
        String thatName;
        String thisName;

        // Initialize.
        lastCompare = 0;
        thatName = replaceNull(that.getName(), "");
        thisName = replaceNull(this.getName(), "");

        // Compare this object to the object.
        lastCompare = thisName.compareTo(thatName);

        return lastCompare;
    }
        
    /**
     * Create a copy of this object.y.
     *
     * @return  a copy of this object.
     */
    @Override
    public ComicCharacter copy() {
        
        // Declare.
        ComicCharacter copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setImage(this.getImage());
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
        if (object instanceof ComicCharacter &&
            this.compareTo((ComicCharacter)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the image.
     *
     * @return  the image.
     */
    public Image getImage() {
        return this.image;
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
        thisName = replaceNull(this.getName(), "");
        
        return thisName.hashCode();
    }
 
    /**
     * Set the image.
     *
     * @param  image  the image.
     */
    public void setImage(Image image) {
        this.image = image;
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
        
        toString.append("Character [");
        toString.append("image = ").append(this.getImage());
        toString.append(", name = ").append(this.getName());
        toString.append("]");
        
        return toString.toString();
    }
}