package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.TitleType;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


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
     * Get the current entity.
     *
     * @return  the current entity.
     */
    @Override
    public TitleType getCurrentEntity() {
        return null;
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
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(TitleType entity) {
        return entity.getValue();
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
    @Override
    @PostConstruct
    protected void initialize() {
        super.initialize();

        // Create a new entity.
        this.entity = new TitleType();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
