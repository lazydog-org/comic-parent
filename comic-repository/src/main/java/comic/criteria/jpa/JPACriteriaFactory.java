package comic.criteria.jpa;

import comic.api.criteria.CriteriaFactory;
import comic.api.criteria.Criteria;
import comic.api.model.Entity;


/**
 * Criteria factory implemented using the Java Persistence API.
 * 
 * @author  Ron Rickard
 */
public class JPACriteriaFactory
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
        criteria = new JPACriteria<T>(entityClass);

        return criteria;
    }
}
