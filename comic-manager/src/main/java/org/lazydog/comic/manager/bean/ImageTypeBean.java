package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.ImageType;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


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

            // Create a new criteria.
            criteria = this.comicService.getCriteria(ImageType.class);

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
    public ImageType getCurrentEntity() {
        return null;
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
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(ImageType entity) {
        return entity.getValue();
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
    protected void initialize() {

        // Find the entities.
        this.findEntities();

        // Create a new entity.
        this.entity = new ImageType();
    }

    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
