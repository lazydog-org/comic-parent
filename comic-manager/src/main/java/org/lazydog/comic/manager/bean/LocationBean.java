package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.model.Location;
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Location managed bean.
 * 
 * @author  Ron Rickard
 */
public class LocationBean extends AbstractDataAccessBean<Location> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(LocationBean.class);
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Location> getCriteria() {

        Criteria<Location> criteria = null;

        try {

            // Check if the UUID session values exist.
            if (SessionUtility.valueExists(SessionKey.UUID)) {

                String uuid = SessionUtility.getValue(SessionKey.UUID, String.class);
                logger.debug("Get the uuid {} from the session.", uuid);
                
                // Create a new criteria.
                criteria = this.comicService.getCriteria(Location.class);

                // Modify the criteria.
                criteria.add(ComparisonOperation.eq("uuid", uuid));
                criteria.addOrder(Order.desc("name"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot get criteria."));
        }

        return criteria;
    }

    /**
     * Get the current entity.
     *
     * @return  the current entity.
     */
    @Override
    public Location getCurrentEntity() {
        return null;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Location> getEntityClass() {
        return Location.class;
    }

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(Location entity) {
        return entity.getName();
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Location getNewEntity() {

        // Set the UUID in the new entity.
        Location newEntity = new Location();
        newEntity.setUuid(SessionUtility.getValue(SessionKey.UUID, String.class));

        return newEntity;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {

        // Find the entities.
        this.findEntities();

        // Create a new entity.
        this.entity = new Location();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
