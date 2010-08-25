package org.lazydog.comic.internal.repository;

import javax.persistence.Persistence;


/**
 * Comic repository wrapper.
 *
 * @author  Ron Rickard
 */
public class ComicRepositoryWrapper extends ComicRepositoryImpl {

    public ComicRepositoryWrapper () {
        this.setEntityManager(Persistence.createEntityManagerFactory("ComicCollectionPU").createEntityManager());
    }
}
