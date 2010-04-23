package org.lazydog.comic.internal.criteria;

import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.Entity;


/**
 * Criteria factory implemented using the Java Persistence API.
 * 
 * @author  Ron Rickard
 */
public class CriteriaFactoryImpl
       extends CriteriaFactory {

    /**
     * Create the criteria.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the criteria.
     */
    @Override
    public <T extends Entity<T>>
           Criteria<T> createCriteria(Class<T> entityClass) {

        // Declare.
        Criteria<T> criteria;

        // Instantiate the criteria.
        criteria = new CriteriaImpl<T>(entityClass);

        return criteria;
    }
}
