package org.lazydog.comic.internal.data.access;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.lazydog.comic.ComicDataAccess;
import org.lazydog.data.access.AbstractDataAccessObject;
import org.lazydog.utilities.ejbmonitor.interceptor.EJBMonitor;


/**
 * Comic data access Enterprise Java Bean.
 * 
 * @author  Ron Rickard
 */
@Stateless(mappedName="ejb/ComicDataAccess")
@Remote(ComicDataAccess.class)
@Interceptors(EJBMonitor.class)
public class ComicDataAccessImpl extends AbstractDataAccessObject
       implements ComicDataAccess {

    /**
     * Set the entity manager.
     *
     * @param  entityManager  the entity manager.
     */
    @PersistenceContext
    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}