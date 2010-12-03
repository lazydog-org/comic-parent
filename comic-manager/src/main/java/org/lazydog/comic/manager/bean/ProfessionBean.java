package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.Profession;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


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
     * Get the current entity.
     *
     * @return  the current entity.
     */
    @Override
    public Profession getCurrentEntity() {
        return null;
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
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(Profession entity) {
        return entity.getValue();
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
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {

        // Find the entities.
        this.findEntities();

        // Create a new entity.
        this.entity = new Profession();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
