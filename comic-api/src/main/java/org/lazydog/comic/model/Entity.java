package org.lazydog.comic.model;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


/**
 * Entity.
 * 
 * @author  Ron Rickard
 */
public abstract class Entity<T extends Entity<T>>
       implements Serializable {

    private static final String EPOCH = "01/01/1900";
    private static final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    
    // Declare.
    @NotNull(message="Create time is required.")
    private Date createTime;
    @Valid @NotNull(message="Create user is required.")
    private User createUser;
    private Class<T> entityClass;
    private Integer id;
    private Date modifyTime;
    private User modifyUser;
    

    /**
     * Constructor.
     */
    @SuppressWarnings("unchecked")
    public Entity() {
        
        this.setCreateTime(null);
        this.setId(null);
        this.setModifyTime(null);
        
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

        // Declare.
        T copy;
        
        // Initialize.
        copy = null;

        try {

            // Create an instance of this entity class.
            copy = this.entityClass.newInstance();

            // Create a copy.
            copy.setCreateTime(this.getCreateTime());
            copy.setCreateUser(this.getCreateUser());
            copy.setId(this.getId());
            copy.setModifyTime(this.getModifyTime());
            copy.setModifyUser(this.getModifyUser());
        }
        catch (IllegalAccessException e) {
            // Ignore.
        }
        catch(InstantiationException e) {
            // Ignore.
        }

        return copy;
    }

    /**
     * Get the create time.
     *
     * @return  the create time.
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * Get the create user.
     *
     * @return  the create user.
     */
    public User getCreateUser() {
        return this.createUser;
    }

    /**
     * Get the epoch.
     * 
     * @return  the epoch.
     */
    protected Date getEpoch() {
        
        // Declare.
        Date epoch;
        
        // Initialize.
        epoch = null;
        
        try {
            
            // Get the epoch.
            epoch = df.parse(EPOCH);
        }
        catch(ParseException e) {
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
     * Get the modify time.
     *
     * @return  the modify time.
     */
    public Date getModifyTime() {
        return this.modifyTime;
    }

    /**
     * Get the modify user.
     *
     * @return  the modify user.
     */
    public User getModifyUser() {
        return this.modifyUser;
    }

    /**
     * Set the create time.
     *
     * @param  createTime  the create time.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Set the create user.
     *
     * @param  createUser  the create user.
     */
    public void setCreateUser(User createUser) {
        this.createUser = createUser;
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
     * Set the modify time.
     *
     * @param  modifyTime  the modify time.
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * Set the modify user.
     *
     * @param  modifyUser  the modify user.
     */
    public void setModifyUser(User modifyUser) {
        this.modifyUser = modifyUser;
    }

    /**
     * Get the trimmed string.
     *
     * @param  value  the string.
     *
     * @return  the trimmed string.
     */
    protected String trimmed(String value) {
        
        // Declare.
        String newValue;
        
        // Initialize.
        newValue = null;
        
        // Check if the string is not null and is not the empty string.
        if (value != null && !value.trim().equals("")) {
            newValue = value.trim();
        }
        
        return newValue;
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
