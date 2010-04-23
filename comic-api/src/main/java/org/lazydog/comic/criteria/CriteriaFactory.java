package org.lazydog.comic.criteria;

import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import org.lazydog.comic.model.Entity;


/**
 * Criteria factory.
 * 
 * @author  Ron Rickard
 */
public abstract class CriteriaFactory {

    private static ServiceLoader<CriteriaFactory> criteriaFactoryLoader
            = ServiceLoader.load(CriteriaFactory.class);

    public abstract <T extends Entity<T>>
           Criteria<T> createCriteria(Class<T> entityClass)
           throws CriteriaFactoryException;
           
    /**
     * Get an implementation of the criteria factory.
     *
     * @return  an implementation of the criteria factory.
     *
     * @throws  CriteriaFactoryException  if unable to get an implementation
     *                                    of the criteria factory.
     */
    public static CriteriaFactory instance()
           throws CriteriaFactoryException {

        // Declare.
        CriteriaFactory criteriaFactory;

        // Initialize.
        criteriaFactory = null;

        try {

            // Loop through the criteria factory implementations.
            for (CriteriaFactory foundCriteriaFactory : criteriaFactoryLoader) {

                // Check if a criteria factory implementation has not been found.
                if (criteriaFactory == null) {

                    // Set the criteria factory.
                    criteriaFactory = foundCriteriaFactory;
                }
                else {
                    throw new CriteriaFactoryException(
                        "More than one CriteriaFactory implementation found.");
                }
            }

            // Check if a criteria factory implementation has not been found.
            if (criteriaFactory == null) {
                throw new CriteriaFactoryException(
                    "No CriteriaFactory implementation found.");
            }
        }
        catch(ServiceConfigurationError e) {
            throw new CriteriaFactoryException(
                "Unable to determine CriteriaFactory implementation.", e);
        }

        return criteriaFactory;
    }
}
