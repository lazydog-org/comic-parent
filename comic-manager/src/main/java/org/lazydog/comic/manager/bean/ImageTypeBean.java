package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.ImageType;
import org.lazydog.data.access.criterion.Order;
import org.lazydog.data.access.Criteria;
import org.lazydog.data.access.CriteriaFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * Image type managed bean.
 * 
 * @author  Ron Rickard
 */
public class ImageTypeBean
       extends AbstractDataAccessBean<ImageType>
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<ImageType> getCriteria() {

        // Declare.
        Criteria<ImageType> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create a new criteria.
            criteria = criteriaFactory.createCriteria(ImageType.class);

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
        for(ImageType entity : this.getEntities()) {

            // Check if the entity value is not Incoming and Trash.
            if (!entity.getValue().equals("Incoming") &&
                !entity.getValue().equals("Trash")) {
                
                // Add the entity to the select items.
                entitiesAsSelectItems.add(new SelectItem(
                    entity, entity.getValue()));
            }
        }
        
        return entitiesAsSelectItems;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<ImageType> getEntityClass() {
        return ImageType.class;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected ImageType getNewEntity() {
        return new ImageType();
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new ImageType();
    }
}
