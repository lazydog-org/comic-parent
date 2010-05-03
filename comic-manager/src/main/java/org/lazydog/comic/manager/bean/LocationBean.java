package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.criterion.ComparisonOperation;
import org.lazydog.comic.criteria.criterion.Order;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.Location;
import org.lazydog.comic.model.User;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * Location managed bean.
 * 
 * @author  Ron Rickard
 */
public class LocationBean
       extends AbstractDataAccessBean<Location>
       implements Serializable {

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

            // Check if the user session values exist.
            if (SessionUtility.valueExists(SessionKey.USER)) {

                // Declare.
                CriteriaFactory criteriaFactory;

                // Initialize criteria factory.
                criteriaFactory = CriteriaFactory.instance();

                // Create a new criteria.
                criteria = criteriaFactory.createCriteria(Location.class);

                // Modify the criteria.
                criteria.add(ComparisonOperation.eq(
                        "createUser",
                        SessionUtility.getValue(SessionKey.USER, User.class)));
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
        return new Location();
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
