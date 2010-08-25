package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.Publisher;
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
 * Publisher managed bean.
 * 
 * @author  Ron Rickard
 */
public class PublisherBean
       extends AbstractDataAccessBean<Publisher>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Publisher> getCriteria() {

        // Declare.
        Criteria<Publisher> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(this.getEntityClass());

            // Modify the criteria.
            criteria.addOrder(Order.asc("name"));
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get criteria."));
        }

        return criteria;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }
    
    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Publisher getNewEntity() {
        return new Publisher();
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
        for(Publisher entity : this.getEntities()) {

            // Add the entity to the select items.
            entitiesAsSelectItems.add(new SelectItem(
                entity, entity.getName()));
        }
        
        return entitiesAsSelectItems;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new Publisher();
    }
}
