package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.lazydog.comic.model.ComicCharacter;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Comic character managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicCharacterBean
       extends AbstractDataAccessBean<ComicCharacter>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<ComicCharacter> getCriteria() {

        // Declare.
        Criteria<ComicCharacter> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(ComicCharacter.class);

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
        for(ComicCharacter entity : this.getEntities()) {

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
    protected Class<ComicCharacter> getEntityClass() {
        return ComicCharacter.class;
    }

    /**
     * Get a new entity.
     *
     * @return  a new entity.
     */
    @Override
    protected ComicCharacter getNewEntity() {
        return new ComicCharacter();
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new ComicCharacter();
    }
}
