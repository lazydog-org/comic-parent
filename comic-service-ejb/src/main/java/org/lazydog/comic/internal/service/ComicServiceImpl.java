package org.lazydog.comic.internal.service;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import org.lazydog.comic.ComicRepository;
import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.Entity;
import org.lazydog.comic.model.User;
import org.lazydog.repository.Criteria;
import org.lazydog.ejbmonitor.interceptor.EJBMonitor;


/**
 * Comic service Enterprise Java Beans.
 * 
 * @author  Ron Rickard
 */
@Stateless(name="ejb/ComicService", mappedName="ejb/ComicService")
@Remote(ComicService.class)
@Interceptors(EJBMonitor.class)
public class ComicServiceImpl
       implements ComicService {

    @EJB(beanName="ejb/ComicRepository", beanInterface=ComicRepository.class)
    private ComicRepository comicRepository;

    /**
     * Find the entity.
     *
     * @param  entityClass  the entity class.
     * @param  id           the ID.
     *
     * @return  the entity.
     */
    @Override
    public <T extends Entity<T>> T find(Class<T> entityClass, Integer id) {
        return this.comicRepository.find(entityClass, id);
    }

    /**
     * Find the entity.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the entity.
     */
    @Override
    public <T extends Entity<T>> T find(Class<T> entityClass, Criteria<T> criteria) {
        return this.comicRepository.find(entityClass, criteria);
    }

    /**
     * Find the first entity.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the first entity.
     */
    @Override
    public <T extends Entity<T>> T findFirst(Class<T> entityClass) {
        return this.findFirst(this.findList(entityClass));
    }

    /**
     * Find the first entity.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the first entity.
     */
    @Override
    public <T extends Entity<T>> T findFirst(Class<T> entityClass, Criteria<T> criteria) {
        return this.findFirst(this.findList(entityClass, criteria));
    }

    /**
     * Find the first entity.
     *
     * @param  entities  the entities.
     * 
     * @return  the first entity.
     */
    private <T extends Entity<T>> T findFirst(List<T> entities) {
        
        // Declare.
        T firstEntity;
        
        // Initialize.
        firstEntity = null;

        // Check if the entities exist.
        if (entities != null && entities.size() > 0) {

            // Get the first entity.
            firstEntity = entities.get(0);
        }

        return firstEntity;
    }

    /**
     * Find the last entity.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the last entity.
     */
    @Override
    public <T extends Entity<T>> T findLast(Class<T> entityClass) {
        return this.findLast(this.findList(entityClass));
    }

    /**
     * Find the last entity.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the last entity.
     */
    @Override
    public <T extends Entity<T>> T findLast(Class<T> entityClass, Criteria<T> criteria) {
        return this.findLast(this.findList(entityClass, criteria));
    }

    /**
     * Find the last entity.
     *
     * @param  entities  the entities.
     * 
     * @return  the last entity.
     */
    private <T extends Entity<T>> T findLast(List<T> entities) {
        
        // Declare.
        T lastEntity;
        
        // Initialize.
        lastEntity = null;

        // Check if the entities exist.
        if (entities != null && entities.size() > 0) {

            // Get the last entity.
            lastEntity = entities.get(entities.size() - 1);
        }

        return lastEntity;
    }

    /**
     * Find the list of entities.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the list of entities.
     */
    @Override
    public <T extends Entity<T>> List<T> findList(Class<T> entityClass) {
        return this.comicRepository.findList(entityClass);
    }

    /**
     * Find the list of entities.
     *
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the list of entities.
     */
    @Override
    public <T extends Entity<T>> List<T> findList(Class<T> entityClass, Criteria<T> criteria) {
        return this.comicRepository.findList(entityClass, criteria);
    }

    /**
     * Find the next entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     * 
     * @return  the next entity.
     */
    @Override
    public <T extends Entity<T>> T findNext(T current, Class<T> entityClass) {
        return this.findNext(current, this.findList(entityClass));
    }

    /**
     * Find the next entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the next entity.
     */
    @Override
    public <T extends Entity<T>> T findNext(T current, Class<T> entityClass, Criteria<T> criteria) {
        return this.findNext(current, this.findList(entityClass, criteria));
    }

    /**
     * Find the next entity.
     *
     * @param  current   the current entity.
     * @param  entities  the entities.
     * 
     * @return  the next entity.
     */
    private <T extends Entity<T>> T findNext(T current, List<T> entities) {
        
        // Declare.
        T nextEntity;
        
        // Initialize.
        nextEntity = current;

        // Check if the entities exist.
        if (entities != null && entities.size() > 0) {

            // Declare.
            boolean found;

            // Initialize.
            found = false;

            // Loop through the entities.
            for(T entity : entities) {

                // Check if the entity was found.
                if (found &&
                    entity != null) {
                    nextEntity = entity;
                    break;
                }

                // Check if the entity is found.
                if (entity.getId().equals(current.getId())) {
                    found = true;
                }
            }
        }

        return nextEntity;
    }

    /**
     * Find the previous entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     * 
     * @return  the previous entity.
     */
    @Override
    public <T extends Entity<T>> T findPrevious(T current, Class<T> entityClass) {
        return this.findPrevious(current, this.findList(entityClass));
    }

    /**
     * Find the previous entity.
     *
     * @param  current      the current entity.
     * @param  entityClass  the entity class.
     * @param  criteria     the criteria.
     *
     * @return  the next entity.
     */
    @Override
    public <T extends Entity<T>> T findPrevious(T current, Class<T> entityClass, Criteria<T> criteria) {
        return this.findPrevious(current, this.findList(entityClass, criteria));
    }

    /**
     * Find the previous entity.
     *
     * @param  current   the current entity.
     * @param  entities  the entities.
     * 
     * @return  the previous entity.
     */
    private <T extends Entity<T>> T findPrevious(T current, List<T> entities) {
        
        // Declare.
        T previousEntity;
        
        // Initialize.
        previousEntity = current;

        // Check if the entities exist.
        if (entities != null && entities.size() > 0) {

            // Declare.
            T foundEntity;

            // Initialize.
            foundEntity = null;

            // Loop through the entities.
            for(T entity : entities) {

                // Check if the entity is found.
                if (entity.getId().equals(current.getId()) &&
                    foundEntity != null) {
                    previousEntity = foundEntity;
                    break;
                }

                foundEntity = entity;
            }
        }

        return previousEntity;
    }

    /**
     * Get the criteria.
     *
     * @param  entityClass  the entity class.
     *
     * @return  the criteria.
     */
    @Override
    public <T extends Entity<T>> Criteria<T> getCriteria(Class<T> entityClass) {
        return this.comicRepository.getCriteria(entityClass);
    }
    
    /**
     * Prepare the entity for saving.
     *
     * @param  entity       the entity.
     * @param  user         the user.
     *
     * @return  the prepared entity.
     */
    private <T extends Entity<T>> T prepEntityForSave(T entity, User user) {

        // Check to see if this is a new entity.
        if (entity.getId() == null) {

            // Set the create time and user.
            entity.setCreateTime(new Date());
            entity.setCreateUser(user);
        }
        else {

            // Set the modify time.
            entity.setModifyTime(new Date());
            entity.setModifyUser(user);
        }

        // Check for a valid entity.
        if (entity.validate().size() > 0) {

            throw new EJBException(
                    "Unable to save invalid entity.");
        }

        return entity;
    }

    /**
     * Remove the entity.
     *
     * @param  entityClass  the entity class.
     * @param  id           the ID.
     */
    @Override
    public <T extends Entity<T>> void remove(Class<T> entityClass, Integer id) {
        this.comicRepository.remove(entityClass, id);
    }
    
    /**
     * Save the entity.
     * 
     * @param  entity  the entity.
     * @param  user    the user.
     * 
     * @return  the entity.
     */
    @Override
    public <T extends Entity<T>> T save(T entity, User user) {

        // Prepare the entity for saving.
        entity = this.prepEntityForSave(entity, user);

        // Save the entity.
        return this.comicRepository.persist(entity);
    }

    /**
     * Save the list of entities.
     *
     * @param  entities  the list of entities.
     * @param  user      the user.
     * 
     * @return  the list of entities.
     */
    @Override
    public <T extends Entity<T>> List<T> saveList(List<T> entities, User user) {

        // Loop through the entities.
        for (T entity : entities) {

            // Prepare the entity for saving.
            entity = this.prepEntityForSave(entity, user);
        }

        // Save the entities.
        return this.comicRepository.persistList(entities);
    }

    /**
     * Set the comic repository.
     *
     * @param  comicRepository  the comic repository.
     */
    public void setComicRepository(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }
}
