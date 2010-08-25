package org.lazydog.comic.internal.repository;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


/**
 * Comic repository wrapper.
 *
 * @author  Ron Rickard
 */
public class ComicRepositoryWrapper extends ComicRepositoryImpl {

    public ComicRepositoryWrapper () {

        // Declare.
        EntityManager entityManager;

        entityManager = Persistence.createEntityManagerFactory("ComicCollectionPU").createEntityManager();

        this.setEntityManager(entityManager);
        entityManager.clear();
    }
}
