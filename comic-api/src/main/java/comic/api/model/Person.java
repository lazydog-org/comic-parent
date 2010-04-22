package comic.api.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Entity class used to represent a person.
 *
 * @author  Ron Rickard
 */
public class Person
       extends Entity<Person>
       implements Comparable<Person>,
                  Serializable {
    
    // Declare.  
    @NotNull(message="First name is required.")
    @Size(max=50, message="First name cannot contain more than 50 characters.")
    private String firstName;
    @NotNull(message="Last name is required.")
    @Size(max=50, message="Last name cannot contain more than 50 characters.")
    private String lastName;
    
    /**
     * Constructor.
     */
    public Person() {
        
        super();
        this.setFirstName(null);
        this.setLastName(null);
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
    public int compareTo(Person object) {
        
        // Declare.
        int lastCompare;
        String thatFirstName;
        String thatLastName;
        String thisFirstName;
        String thisLastName;

        // Initialize.
        lastCompare = 0;
        thatFirstName = (object.getFirstName() == null) ? "" : object.getFirstName();
        thatLastName = (object.getLastName() == null) ? "" : object.getLastName();
        thisFirstName = (this.getFirstName() == null) ? "" : this.getFirstName();
        thisLastName = (this.getLastName() == null) ? "" : this.getLastName();
        
        // Compare this object to the object.
        lastCompare = thisLastName.compareTo(thatLastName);
        lastCompare = (lastCompare != 0) ? lastCompare : thisFirstName.compareTo(thatFirstName);

        return lastCompare;
    }
                                        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Person copy() {
        
        // Declare.
        Person copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setFirstName(this.getFirstName());
        copy.setLastName(this.getLastName());

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
        if (object instanceof Person &&
            this.compareTo((Person)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the first name.
     *
     * @return  the first name.
     */
    public String getFirstName() {
        return this.firstName;
    }
                   
    /**
     * Get the last name.
     *
     * @return  the last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        String thisFirstName;
        String thisLastName;
        
        // Initialize.
        thisFirstName = (this.getFirstName() == null) ? "" : this.getFirstName();
        thisLastName = (this.getLastName() == null) ? "" : this.getLastName();
        
        return thisLastName.hashCode()*31
             + thisFirstName.hashCode();
    }
 
    /**
     * Set the first name.
     *
     * @param  firstName  the first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = this.trimmed(firstName);
    }
         
    /**
     * Set the last name.
     *
     * @param  lastName  the last name.
     */
    public void setLastName(String lastName) {
        this.lastName = this.trimmed(lastName);
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
        
        toString.append("Person [");
        toString.append("firstName = ").append(this.getFirstName());
        toString.append(", lastName = ").append(this.getLastName());
        toString.append("]");
        
        return toString.toString();
    }
}