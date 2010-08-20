package org.lazydog.comic.internal.repository;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lazydog.comic.spi.repository.ComicRepository;
import org.lazydog.data.access.AbstractDataAccessObject;
import org.lazydog.utilities.ejbmonitor.interceptor.EJBMonitor;


/**
 * Comic repository Enterprise Java Bean.
 * 
 * @author  Ron Rickard
 */
@Stateless(mappedName="ejb/ComicRepository")
@Remote(ComicRepository.class)
@Interceptors(EJBMonitor.class)
public class ComicRepositoryImpl extends AbstractDataAccessObject
       implements ComicRepository {

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}