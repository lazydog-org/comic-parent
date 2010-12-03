package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.Category;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Category managed bean.
 * 
 * @author  Ron Rickard
 */
public class CategoryBean
       extends AbstractDataAccessBean<Category>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Category> getCriteria() {

        // Declare.
        Criteria<Category> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(Category.class);

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
    public Category getCurrentEntity() {
        return null;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(Category entity) {
        return entity.getName();
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Category getNewEntity() {
        return new Category();
    }

    /**
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {

        // Find the entities.
        this.findEntities();

        // Create a new entity.
        this.entity = new Category();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
