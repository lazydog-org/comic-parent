package org.lazydog.comic.spi.repository;

import java.util.List;
import org.lazydog.comic.model.Entity;
import org.lazydog.comic.criteria.Criteria;


/**
 * Comic repository.
 *
 * @author  Ron Rickard
 */
public interface ComicRepository {

    public <T extends Entity<T>> T find(Class<T> entityClass, Integer id);

    public <T extends Entity<T>> T find(Criteria<T> criteria);

    public <T extends Entity<T>> List<T> findList(Class<T> entityClass);
    
    public <T extends Entity<T>> List<T> findList(Criteria<T> criteria);

    public <T extends Entity<T>> T persist(T entity);
    
    public <T extends Entity<T>> List<T> persistList(List<T> entities);

    public <T extends Entity<T>> void remove(T entity);
    
    public <T extends Entity<T>> void remove(Class<T> entityClass, Integer id);
}
