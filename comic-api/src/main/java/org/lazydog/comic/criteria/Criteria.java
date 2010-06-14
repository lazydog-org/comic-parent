package org.lazydog.comic.criteria;

import java.util.List;
import java.util.Map;
import org.lazydog.comic.criteria.criterion.Criterion;
import org.lazydog.comic.model.Entity;


/**
 * Interface for a criteria.
 * 
 * @author  Ron Rickard
 */
public interface Criteria<T extends Entity<T>> {

    public Criteria<T> add(Criterion criterion);

    public Criteria<T> add(List<Criterion> criterions);

    public Criteria<T> addOrder(Criterion criterion);

    public Criteria<T> addOrders(List<Criterion> criterion);

    public abstract Map<String, Object> getParameters();

    public abstract String getQlString();
}
