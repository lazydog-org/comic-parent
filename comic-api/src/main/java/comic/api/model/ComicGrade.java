package comic.api.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a comic grade.
 *
 * @author  Ron Rickard
 */
public class ComicGrade 
       extends Entity<ComicGrade>
       implements Comparable<ComicGrade>,
                  Serializable {
    
    // Declare.
    @NotNull(message="Code is required.")
    @Size(max=5, message="Code cannot contain more than 5 characters.")
    private String code;
    @NotNull(message="Name is required.")
    @Size(max=20, message="Name cannot contain more than 20 characters.")
    private String name;
    @NotNull(message="Scale is required.")
    private Double scale;
    
    /**
     * Constructor.
     */
    public ComicGrade() {
        
        super();
        this.setCode(null);
        this.setName(null);
        this.setScale(null);
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
    public int compareTo(ComicGrade object) {
        
        // Declare.
        int lastCompare;
        Double thatScale;
        Double thisScale;

        // Initialize.
        lastCompare = 0;
        thatScale = object.getScale();
        thisScale = this.getScale();
        
        // Compare this object to the object.
        lastCompare = thisScale.compareTo(thatScale);
        
        return lastCompare;
    }
        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public ComicGrade copy() {
        
        // Declare.
        ComicGrade copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setCode(this.getCode());
        copy.setName(this.getName());
        copy.setScale(this.getScale());
        
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
        if (object instanceof ComicGrade &&
            this.compareTo((ComicGrade)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the code.
     *
     * @return  the code.
     */
    public String getCode() {
        return this.code;
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
     * Get the scale.
     *
     * @return  the scale.
     */
    public Double getScale() {
        return this.scale;
    }
     
    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        Double thisScale;
        
        // Initialize.
        thisScale = this.getScale();
        
        return thisScale.hashCode();
    }
 
    /**
     * Set the code.
     *
     * @param  code  the code.
     */
    public void setCode(String code) {
        this.code = this.trimmed(code);
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
     * Set the scale.
     *
     * @param  scale  the scale.
     */
    public void setScale(Double scale) {
        
        if (scale == null) {
            this.scale = new Double(0.0);
        }
        else {
            this.scale = scale;
        }
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
        
        toString.append("ComicGrade [");
        toString.append("code = ").append(this.getCode());
        toString.append(", name = ").append(this.getName());
        toString.append(", scale = ").append(this.getScale());
        toString.append("]");
        
        return toString.toString();
    }
}
