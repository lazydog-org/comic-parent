package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;


/**
 * Entity class used to represent an image.
 *
 * @author  Ron Rickard
 */
public class Image 
       extends Entity<Image>
       implements Comparable<Image>,
                  Serializable {
    
    // Declare.
    @NotNull(message="File name is required.") 
    @Size(max=100, message="File name cannot contain more than 100 character.")
    private String fileName;
    @Size(max=100, message="Label cannot contain more than 100 character.")
    private String label;
    @Valid @NotNull(message="Image type is required.")
    private ImageType type;

    /**
     * Constructor.
     */
    public Image() {
        
        super();
        this.setFileName(null);
        this.setLabel(null);
        this.setType(null);
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
    public int compareTo(Image object) {
        
        // Declare.
        int lastCompare;
        String thatFileName;
        String thatLabel;
        ImageType thatType;
        String thisFileName;
        String thisLabel;
        ImageType thisType;

        // Initialize.
        lastCompare = 0;
        thatFileName = (object.getFileName() == null) ? "" : object.getFileName();
        thatLabel = (object.getLabel() == null) ? "" : object.getLabel();
        thatType = (object.getType() == null) ? new ImageType() : object.getType();
        thisFileName = (this.getFileName() == null) ? "" : this.getFileName();
        thisLabel = (this.getLabel() == null) ? "" : this.getLabel();
        thisType = (this.getType() == null) ? new ImageType() : this.getType();
        
        // Compare this object to the object.
        lastCompare = thisType.compareTo(thatType);
        lastCompare = (lastCompare != 0) ? lastCompare : thisLabel.compareTo(thatLabel);
        lastCompare = (lastCompare != 0) ? lastCompare : thisFileName.compareTo(thatFileName);

        return lastCompare;
    }
                                    
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Image copy() {
        
        // Declare.
        Image copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setFileName(this.getFileName());
        copy.setLabel(this.getLabel());
        copy.setType(this.getType());

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
        // and is equal to this object.t.
        if (object instanceof Image &&
            this.compareTo((Image)object) == 0) {
            equals = true;
        }
        
        return equals;
    }
     
    /**
     * Get the file name.
     *
     * @return  the file name.
     */
    public String getFileName() {
        return this.fileName;
    }
            
    /**
     * Get the label.
     * 
     * @return  the label.
     */
    public String getLabel() {
        return this.label;
    }
    
    /**
     * Get the type.
     *
     * @return  the type.
     */
    public ImageType getType() {
        return this.type;
    }
        
    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        String thisFileName;
        String thisLabel;
        ImageType thisType;
        
        // Initialize.
        thisFileName = (this.getFileName() == null) ? "" : this.getFileName();
        thisLabel = (this.getLabel() == null) ? "" : this.getLabel();
        thisType = (this.getType() == null) ? new ImageType() : this.getType();
        
        return thisType.hashCode()*7^2 +
               thisLabel.hashCode()*7 +
               thisFileName.hashCode();
    }

    /**
     * Set the file name.
     *
     * @param  fileName  the file name.
     */
    public void setFileName(String fileName) {
        this.fileName = this.trimmed(fileName);
    }
          
    /**
     * Set the label.
     * 
     * @param  label  the label.
     */
    public void setLabel(String label) {
        this.label = this.trimmed(label);
    }
    
    /**
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(ImageType type) {
        this.type = type;
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
        
        toString.append("Image [");
        toString.append("fileName = ").append(this.getFileName());
        toString.append(", label = ").append(this.getLabel());
        toString.append(", type = ").append(this.getType());
        toString.append("]");
        
        return toString.toString();
    }
}

