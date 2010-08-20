package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;


/**
 * Entity class used to represent an imprint.
 *
 * @author  Ron Rickard
 */
public class Imprint 
       extends Entity<Imprint>
       implements Comparable<Imprint>,
                  Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Image image;
    @NotNull(message="Name is required.") 
    @Size(max=50, message="Name cannot contain more than 50 characters.")
    private String name;
    @Valid @NotNull(message="Publisher is required.")
    private Publisher publisher;

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
    public int compareTo(Imprint that) {
        
        // Declare.
        int lastCompare;
        String thatName;
        Publisher thatPublisher;
        String thisName;
        Publisher thisPublisher;

        // Initialize.
        lastCompare = 0;
        thatName = replaceNull(that.getName(), "");
        thatPublisher = replaceNull(that.getPublisher(), new Publisher());
        thisName = replaceNull(this.getName(), "");
        thisPublisher = replaceNull(this.getPublisher(), new Publisher());

        // Compare this object to the object.
        lastCompare = thisPublisher.compareTo(thatPublisher);
        lastCompare = (lastCompare != 0) ? lastCompare : thisName.compareTo(thatName);

        return lastCompare;
    }
                
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Imprint copy() {
        
        // Declare.
        Imprint copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setImage(this.getImage());
        copy.setName(this.getName());
        copy.setPublisher(this.getPublisher());
        
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
        if (object instanceof Imprint &&
            this.compareTo((Imprint)object) == 0) {
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
     * Get the publisher.
     * 
     * @return  the publisher.
     */
    public Publisher getPublisher() {
        return this.publisher;
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
        Publisher thisPublisher;
        
        // Initialize.
        thisName = replaceNull(this.getName(), "");
        thisPublisher = replaceNull(this.getPublisher(), new Publisher());
        
        return thisPublisher.hashCode()*31
             + thisName.hashCode();
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
     * Set the publisher.
     * 
     * @param  publisher  the publisher.
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
        
        toString.append("Imprint [");
        toString.append("image = ").append(this.getImage());
        toString.append(", name = ").append(this.getName());
        toString.append(", publisher = ").append(this.getPublisher());
        toString.append("]");
        
        return toString.toString();
    }
}