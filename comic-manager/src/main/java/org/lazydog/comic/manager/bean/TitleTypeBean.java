package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.TitleType;
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
 * Title type managed bean.
 * 
 * @author  Ron Rickard
 */
public class TitleTypeBean
       extends AbstractDataAccessBean<TitleType>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<TitleType> getCriteria() {

        // Declare.
        Criteria<TitleType> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(TitleType.class);

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
        for(TitleType entity : this.getEntities()) {

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
    protected Class<TitleType> getEntityClass() {
        return TitleType.class;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected TitleType getNewEntity() {
        return new TitleType();
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new TitleType();
    }
}
