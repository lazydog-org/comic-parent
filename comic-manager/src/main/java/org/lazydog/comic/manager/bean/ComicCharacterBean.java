package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
     * Get the current entity.
     *
     * @return  the current entity.
     */
    @Override
    public ComicCharacter getCurrentEntity() {
        return null;
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
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(ComicCharacter entity) {
        return entity.getName();
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
    protected void initialize() {

        // Find the entities.
        this.findEntities();
        
        // Create a new entity.
        this.entity = new ComicCharacter();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
