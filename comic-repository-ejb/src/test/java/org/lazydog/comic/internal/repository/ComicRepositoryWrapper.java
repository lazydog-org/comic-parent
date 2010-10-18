package org.lazydog.comic.internal.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


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
        entityManager = Persistence.createEntityManagerFactory("ComicPUTest").createEntityManager();

        // Inject the entity manager.
        this.setEntityManager(entityManager);
        entityManager.clear();
    }
}
