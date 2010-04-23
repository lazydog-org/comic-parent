package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.criterion.Order;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.Imprint;
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
public class ImprintBean
       extends AbstractDataAccessBean<Imprint>
       implements Serializable {

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Imprint> getCriteria() {

        // Declare.
        Criteria<Imprint> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create a new criteria.
            criteria = criteriaFactory.createCriteria(Imprint.class);

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
        for(Imprint entity : this.getEntities()) {

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
    protected Class<Imprint> getEntityClass() {
        return Imprint.class;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Imprint getNewEntity() {
        return new Imprint();
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new Imprint();
    }
}
