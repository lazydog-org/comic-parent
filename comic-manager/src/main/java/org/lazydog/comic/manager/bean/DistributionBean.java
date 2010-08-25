package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.Distribution;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * Distribution managed bean.
 * 
 * @author  Ron Rickard
 */
public class DistributionBean
       extends AbstractDataAccessBean<Distribution>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Distribution> getCriteria() {

        // Declare.
        Criteria<Distribution> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(Distribution.class);

            // Modify the criteria.
            criteria.addOrder(Order.asc("value"));
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
        for(Distribution entity : this.getEntities()) {

            // Add the entity to the select items.
            entitiesAsSelectItems.add(new SelectItem(
                entity, entity.getValue()));
        }
        
        return entitiesAsSelectItems;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Distribution> getEntityClass() {
        return Distribution.class;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Distribution getNewEntity() {
        return new Distribution();
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new Distribution();
    }
}
