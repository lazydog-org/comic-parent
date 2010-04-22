package comic.api.criteria;

import java.io.Serializable;


/**
 * Thrown to indicate that an error occurred in the CriteriaFactory class.
 *
 * @author  Ronald Rickard
 */
public class CriteriaFactoryException
       extends Exception 
       implements Serializable {

    /**
     * Constructs a new exception with no detail message.
     */
    public CriteriaFactoryException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param  message  message for exception.
     */
    public CriteriaFactoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param  message  the detail message.
     * @param  cause    the cause.
     */
    public CriteriaFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new exception with the specified cause.
     *
     * @param  cause  the cause.
     */
    public CriteriaFactoryException(Throwable cause) {
        super(cause);
    }
}
