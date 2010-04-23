package org.lazydog.comic.internal.service;

import java.util.Date;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.model.Entity;
import org.lazydog.comic.model.User;
import org.lazydog.comic.service.ComicService;
import org.lazydog.comic.spi.repository.ComicRepository;
import org.lazydog.comic.spi.repository.ComicRepositoryFactory;
import org.lazydog.comic.spi.repository.ComicRepositoryFactoryException;


/**
 * Comic service Enterprise Java Beans.
 * 
 * @author  Ron Rickard
 */
@Stateless(mappedName="ejb/ComicService")
@Remote(ComicService.class)
public class ComicServiceImpl
       implements ComicService {

    /**
     * Find the entity.
     *
     * @param  entityClass  the entity class.
     * @param  id           the ID.
     *
     * @return  the entity.
     */
    @Override
    public <T extends Entity<T>> T find(Class<T> entityClass,
                                        Integer id) {
        return this.getComicRepository().find(entityClass, id);
    }

    /**
     * Find the entity.
     *
     * @param  criteria  the criteria.
     *
     * @return  the entity.
     */
    @Override
    public <T extends Entity<T>> T find(Criteria<T> criteria) {
        return this.getComicRepository().find(criteria);
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
     * @param  criteria  the criteria.
     *
     * @return  the first entity.
     */
    @Override
    public <T extends Entity<T>> T findFirst(Criteria<T> criteria) {
        return this.findFirst(this.findList(criteria));
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
     * @param  criteria  the criteria.
     *
     * @return  the last entity.
     */
    @Override
    public <T extends Entity<T>> T findLast(Criteria<T> criteria) {
        return this.findLast(this.findList(criteria));
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
        return this.getComicRepository().findList(entityClass);
    }

    /**
     * Find the list of entities.
     *
     * @param  criteria  the criteria.
     *
     * @return  the list of entities.
     */
    @Override
    public <T extends Entity<T>> List<T> findList(Criteria<T> criteria) {
        return this.getComicRepository().findList(criteria);
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
    public <T extends Entity<T>> T findNext(T current,
                                            Class<T> entityClass) {
        return this.findNext(current, this.findList(entityClass));
    }

    /**
     * Find the next entity.
     *
     * @param  current      the current entity.
     * @param  criteria     the criteria.
     *
     * @return  the next entity.
     */
    @Override
    public <T extends Entity<T>> T findNext(T current,
                                            Criteria<T> criteria) {
        return this.findNext(current, this.findList(criteria));
    }

    /**
     * Find the next entity.
     *
     * @param  current   the current entity.
     * @param  entities  the entities.
     * 
     * @return  the next entity.
     */
    private <T extends Entity<T>> T findNext(T current,
                                             List<T> entities) {
        
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
    public <T extends Entity<T>> T findPrevious(T current,
                                                Class<T> entityClass) {
        return this.findPrevious(current, this.findList(entityClass));
    }

    /**
     * Find the previous entity.
     *
     * @param  current      the current entity.
     * @param  criteria     the criteria.
     *
     * @return  the next entity.
     */
    @Override
    public <T extends Entity<T>> T findPrevious(T current,
                                                Criteria<T> criteria) {
        return this.findPrevious(current, this.findList(criteria));
    }

    /**
     * Find the previous entity.
     *
     * @param  current   the current entity.
     * @param  entities  the entities.
     * 
     * @return  the previous entity.
     */
    private <T extends Entity<T>> T findPrevious(T current,
                                                 List<T> entities) {
        
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
     * Get the comic repository.
     *
     * @return  the comic repository.
     */
    private ComicRepository getComicRepository() {

        // Declare.
        ComicRepository comicRepository;

        // Initialize.
        comicRepository = null;

        try {

            // Declare.
            ComicRepositoryFactory comicRepositoryFactory;

            // Get the comic repository factory.
            comicRepositoryFactory = ComicRepositoryFactory.instance();

            // Get the comic repository.
            comicRepository = comicRepositoryFactory.createComicRepository();
        }
        catch(ComicRepositoryFactoryException e) {
            // Ignore.
        }

        return comicRepository;
    }

    /**
     * Prepare the entity for saving.
     *
     * @param  entity       the entity.
     * @param  user         the user.
     *
     * @return  the prepared entity.
     */
    private <T extends Entity<T>> T prepEntityForSave(T entity,
                                                      User user) {

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
    public <T extends Entity<T>> void remove(Class<T> entityClass,
                                             Integer id) {
        this.getComicRepository().remove(entityClass, id);
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
    public <T extends Entity<T>> T save(T entity,
                                        User user) {

        // Prepare the entity for saving.
        entity = this.prepEntityForSave(entity, user);

        // Save the entity.
        return this.getComicRepository().persist(entity);
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
    public <T extends Entity<T>> List<T> saveList(List<T> entities,
                                                  User user) {

        // Loop through the entities.
        for (T entity : entities) {

            // Prepare the entity for saving.
            entity = this.prepEntityForSave(entity, user);
        }

        // Save the entities.
        return this.getComicRepository().persistList(entities);
    }
}
