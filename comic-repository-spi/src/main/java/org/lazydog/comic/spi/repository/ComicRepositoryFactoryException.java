package org.lazydog.comic.spi.repository;

import java.io.Serializable;


/**
 * Thrown to indicate that an error occurred in the ComicRepositoryFactory class.
 *
 * @author  Ronald Rickard
 */
public class ComicRepositoryFactoryException
       extends Exception 
       implements Serializable {

    /**
     * Constructs a new exception with no detail message.
     */
    public ComicRepositoryFactoryException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param  message  message for exception.
     */
    public ComicRepositoryFactoryException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param  message  the detail message.
     * @param  cause    the cause.
     */
    public ComicRepositoryFactoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new exception with the specified cause.
     *
     * @param  cause  the cause.
     */
    public ComicRepositoryFactoryException(Throwable cause) {
        super(cause);
    }
}
