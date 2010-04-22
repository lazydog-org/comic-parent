package comic.api.repository;

import comic.api.model.Entity;
import comic.api.criteria.Criteria;
import java.util.List;


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
