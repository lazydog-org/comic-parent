package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.ComicType;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Comic type managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicTypeBean
       extends AbstractDataAccessBean<ComicType>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<ComicType> getCriteria() {

        // Declare.
        Criteria<ComicType> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(ComicType.class);

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
    public ComicType getCurrentEntity() {
        return null;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<ComicType> getEntityClass() {
        return ComicType.class;
    }

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(ComicType entity) {
        return entity.getValue();
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected ComicType getNewEntity() {
        return new ComicType();
    }

    /**
     * Initialize.
     */
    @Override
    @PostConstruct
    protected void initialize() {
        super.initialize();

        // Create a new entity.
        this.entity = new ComicType();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
