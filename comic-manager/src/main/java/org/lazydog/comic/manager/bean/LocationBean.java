package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.lazydog.comic.model.Location;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Location managed bean.
 * 
 * @author  Ron Rickard
 */
public class LocationBean
       extends AbstractDataAccessBean<Location>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Location> getCriteria() {

        // Declare.
        Criteria<Location> criteria;

        // Initialize.
        criteria = null;

        try {

            // Check if the UUID session values exist.
            if (SessionUtility.valueExists(SessionKey.UUID)) {

                // Create a new criteria.
                criteria = this.comicService.getCriteria(Location.class);

                // Modify the criteria.
                criteria.add(ComparisonOperation.eq(
                        "uuid",
                        SessionUtility.getValue(SessionKey.UUID, String.class)));
                criteria.addOrder(Order.desc("name"));
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get criteria."));
        }

        return criteria;
    }

    /**
     * Get the entities as select items.
     * 
     * @return  the entities as select items.
     */
    @Override
    public List<SelectItem> getEntitiesAsSelectItems() {
        
        // Declare.
        List<SelectItem> entitiesAsSelectItems;
        
        // Initialize.
        entitiesAsSelectItems = new ArrayList<SelectItem>();
        
        // Loop through the entities.
        for(Location entity : this.getEntities()) {

            // Add the entity to the select items.
            entitiesAsSelectItems.add(new SelectItem(
                entity, entity.getName()));
        }
        
        return entitiesAsSelectItems;
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
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Location getNewEntity() {

        // Declare.
        Location newEntity;

        // Create a new entity.
        newEntity = new Location();

        // Set the UUID in the new entity.
        newEntity.setUuid(SessionUtility.getValue(SessionKey.UUID, String.class));

        return newEntity;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new Location();
    }
}
