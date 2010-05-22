package org.lazydog.comic.service;

import java.util.List;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.model.ApplicationUser;
import org.lazydog.comic.model.Entity;


/**
 * Comic service.
 *
 * @author  Ron Rickard
 */
public interface ComicService {

    public <T extends Entity<T>> T find(Class<T> entityClass, Integer id);

    public <T extends Entity<T>> T find(Criteria<T> criteria);

    public <T extends Entity<T>> T findFirst(Class<T> entityClass);

    public <T extends Entity<T>> T findFirst(Criteria<T> criteria);

    public <T extends Entity<T>> T findLast(Class<T> entityClass);

    public <T extends Entity<T>> T findLast(Criteria<T> criteria);

    public <T extends Entity<T>> List<T> findList(Class<T> entityClass);

    public <T extends Entity<T>> List<T> findList(Criteria<T> criteria);

    public <T extends Entity<T>> T findNext(T current, Class<T> entityClass);

    public <T extends Entity<T>> T findNext(T current, Criteria<T> criteria);

    public <T extends Entity<T>> T findPrevious(T current, Class<T> entityClass);

    public <T extends Entity<T>> T findPrevious(T current, Criteria<T> criteria);

    public <T extends Entity<T>> void remove(Class<T> entityClass, Integer id);
    
    public <T extends Entity<T>> T save(T entity, ApplicationUser user);
    
    public <T extends Entity<T>> List<T> saveList(List<T> entities, ApplicationUser user);
}
