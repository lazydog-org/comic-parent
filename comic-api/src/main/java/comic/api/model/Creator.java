package comic.api.model;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;


/**
 * Entity class used to represent an creator.
 *
 * @author  Ron Rickard
 */
public class Creator 
       extends Entity<Creator>
       implements Comparable<Creator>,
                  Serializable {

    // Declare.
    @Valid @NotNull(message="Person is required.")
    private Person person;
    @Valid @NotNull(message="Profession is required.")
    private Profession profession;
    
    /**
     * Constructor.
     */
    public Creator() {
        
        super();
        this.setPerson(null);
        this.setProfession(null);
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
    public int compareTo(Creator object) {
        
        // Declare.
        int lastCompare;
        Person thatPerson;
        Profession thatProfession;
        Person thisPerson;
        Profession thisProfession;

        // Initialize.
        lastCompare = 0;
        thatPerson = (object.getPerson() == null) ? new Person() : object.getPerson();
        thatProfession = (object.getProfession() == null) ? new Profession() : object.getProfession();
        thisPerson = (this.getPerson() == null) ? new Person() : this.getPerson();
        thisProfession = (this.getProfession() == null) ? new Profession() : this.getProfession();
        
        // Compare this object to the object.
        lastCompare = thisPerson.compareTo(thatPerson);
        lastCompare = (lastCompare != 0) ? lastCompare : thisProfession.compareTo(thatProfession);
        
        return lastCompare;
    }
                                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Creator copy() {
        
        // Declare.
        Creator copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setPerson(this.getPerson());
        copy.setProfession(this.getProfession());

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
        if (object instanceof Creator &&
            this.compareTo((Creator)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the person.
     *
     * @return  the person.
     */
    public Person getPerson() {
        return this.person;
    }
      
    /**
     * Get the profession.
     *
     * @return  the profession.
     */
    public Profession getProfession() {
        return this.profession;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        Person thisPerson;
        Profession thisProfession;
        
        // Initialize.
        thisPerson = (this.getPerson() == null) ? new Person() : this.getPerson();
        thisProfession = (this.getProfession() == null) ? new Profession() : this.getProfession();
        
        return thisPerson.hashCode()*31
             + thisProfession.hashCode();
    }
  
    /**
     * Set the person.
     *
     * @param  person  the person.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
               
    /**
     * Set the profession.
     *
     * @param  profession  the profession.
     */
    public void setProfession(Profession profession) {
        this.profession = profession;
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
        
        toString.append("Creator [");
        toString.append("person = ").append(this.getPerson());
        toString.append(", profession = ").append(this.getProfession());
        toString.append("]");
        
        return toString.toString();
    }
}

