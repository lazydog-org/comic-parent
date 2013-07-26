package org.lazydog.comic.model;

import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * Entity.
 * 
 * @author  Ron Rickard
 */
public abstract class Entity<T extends Entity<T>> extends Model {

    private static final long serialVersionUID = 1L;
    private static final String EPOCH = "01/01/1900";
    private static final String NIL_UUID = "000000000000000000000000000000000000";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");

    private Class<T> entityClass;
    private Integer id;    

    /**
     * Constructor.
     */
    @SuppressWarnings("unchecked")
    public Entity() {

        // Set the entity class.
        this.entityClass = (Class<T>)((ParameterizedType)this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Create a copy of this object.
     * 
     * @return  a copy of this object.
     */
    public T copy() {

        T copy = null;

        try {

            // Create an instance of this entity class.
            copy = this.entityClass.newInstance();

            // Create a copy.
            copy.setId(this.getId());
        } catch (IllegalAccessException e) {
            // Ignore.
        } catch (InstantiationException e) {
            // Ignore.
        }

        return copy;
    }

    /**
     * Get the epoch.
     *
     * @return  the epoch.
     */
    protected static Date epoch() {

        Date epoch = null;

        try {

            // Get the epoch.
            epoch = DATE_FORMAT.parse(EPOCH);
        } catch (ParseException e) {
            // Ignore.
        }

        return epoch;
    }

    /**
     * Get the ID.
     *
     * @return  the ID.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Get the nil universally unique identifier (UUID).
     * 
     * @return  the nil universally unique identifier (UUID).
     */
    protected static String nilUuid() {
        return NIL_UUID;
    }

    /**
     * Set the ID.
     *
     * @param  id  the ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get the trimmed string.
     *
     * @param  value  the string.
     *
     * @return  the trimmed string.
     */
    protected static String trimmed(String value) {
        return (value != null) ? value.trim() : null;
    }
   
    /**
     * Validate this object.
     * 
     * @return  a set of constraint violations or an empty set if there are no
     *          constraint violations.
     */
    @SuppressWarnings("unchecked")
    public Set<ConstraintViolation<T>> validate() {

        // Declare.
        Set<ConstraintViolation<T>> constraintViolations;
        Validator validator;
        ValidatorFactory validatorFactory;

        // Get the validator factory.
        validatorFactory = Validation.buildDefaultValidatorFactory();

        // Get the validator for this object type.
        validator = validatorFactory.getValidator();

        // Validate this object.
        constraintViolations = validator.validate((T)this);

        return constraintViolations;
    }
}
