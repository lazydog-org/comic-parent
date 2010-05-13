package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.criterion.Order;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.ComicGrade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * Comic grade managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicGradeBean
       extends AbstractDataAccessBean<ComicGrade>
       implements Serializable {

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<ComicGrade> getCriteria() {

        // Declare.
        Criteria<ComicGrade> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create a new criteria.
            criteria = criteriaFactory.createCriteria(ComicGrade.class);

            // Modify the criteria.
            criteria.addOrder(Order.desc("scale"));
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
        for(ComicGrade entity : this.getEntities()) {

            // Add the entity to the select items.
            entitiesAsSelectItems.add(new SelectItem(
                entity, entity.getCode()));
        }
        
        return entitiesAsSelectItems;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<ComicGrade> getEntityClass() {
        return ComicGrade.class;
    }

    /**
     * Get a new entity.
     *
     * @return  a new entity.
     */
    @Override
    protected ComicGrade getNewEntity() {
        return null;
    }
}