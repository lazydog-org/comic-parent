package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.criterion.Order;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.Category;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * Category managed bean.
 * 
 * @author  Ron Rickard
 */
public class CategoryBean
       extends AbstractDataAccessBean<Category>
       implements Serializable {

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

            // Declare.
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create a new criteria.
            criteria = criteriaFactory.createCriteria(Category.class);

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
        for(Category entity : this.getEntities()) {

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
    protected Class<Category> getEntityClass() {
        return Category.class;
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
    public void initialize() {

        // Create a new entity.
        this.entity = new Category();
    }
}
