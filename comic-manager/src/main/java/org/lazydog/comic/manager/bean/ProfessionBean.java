package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.Profession;
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
 * Profession managed bean.
 * 
 * @author  Ron Rickard
 */
public class ProfessionBean
       extends AbstractDataAccessBean<Profession>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Profession> getCriteria() {

        // Declare.
        Criteria<Profession> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(Profession.class);

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
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Profession getNewEntity() {
        return new Profession();
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
        for(Profession entity : this.getEntities()) {

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
    protected Class<Profession> getEntityClass() {
        return Profession.class;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new Profession();
    }
}
