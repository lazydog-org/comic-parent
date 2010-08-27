package org.lazydog.comic.internal.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.lazydog.comic.internal.repository.ComicRepositoryImpl;


/**
 * Comic repository wrapper.
 *
 * @author  Ron Rickard
 */
public class ComicRepositoryWrapper extends ComicRepositoryImpl {

    /**
     * Constructor.
     */
    public ComicRepositoryWrapper () {

        // Declare.
        EntityManager entityManager;

        // Get a entity manager.
        entityManager = Persistence.createEntityManagerFactory("ComicCollectionPUTest").createEntityManager();

        // Inject the entity manager.
        this.setEntityManager(entityManager);
        entityManager.clear();
    }
}
