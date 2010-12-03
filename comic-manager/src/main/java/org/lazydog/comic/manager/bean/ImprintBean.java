package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.Imprint;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Location managed bean.
 * 
 * @author  Ron Rickard
 */
public class ImprintBean
       extends AbstractDataAccessBean<Imprint>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
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

            // Create a new criteria.
            criteria = this.comicService.getCriteria(Imprint.class);

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
    public Imprint getCurrentEntity() {
        return null;
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
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(Imprint entity) {
        return entity.getName();
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
    protected void initialize() {

        // Find the entities.
        this.findEntities();

        // Create a new entity.
        this.entity = new Imprint();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
