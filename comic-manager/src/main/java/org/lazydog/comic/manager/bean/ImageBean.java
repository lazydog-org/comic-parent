package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.LogicalOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Image managed bean.
 * 
 * @author  Ron Rickard
 */
public class ImageBean
       extends AbstractDataAccessBean<Image>
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the comic images as select items.
     * 
     * @return  the comic images as select items.
     */
    public List<SelectItem> getComicImagesAsSelectItems() {
        
        // Declare.
        List<SelectItem> comicImagesAsSelectItems;
        
        // Initialize.
        comicImagesAsSelectItems = new ArrayList<SelectItem>();
        
        try {
         
            // Declare.
            Criteria<Image> criteria;
            List<Image> images;
            String titleName;

            // Get the title name.
            titleName = SessionUtility.getValue(SessionKey.TITLE, Title.class).getName();

            // Create a new criteria.
            criteria = this.comicService.getCriteria(Image.class);

            // Modify the criteria.
            criteria.add(ComparisonOperation.eq("type.value", "Comic"));
            criteria.add(LogicalOperation.and(ComparisonOperation.like(
                    "label", titleName + "%")));
            criteria.addOrder(Order.asc("label"));

            // Get the images.
            images = this.comicService.findList(Image.class, criteria);
            
            // Add an empty image to the select items.
            comicImagesAsSelectItems.add(new SelectItem(null, ""));
            
            // Loop through the images.
            for(Image image : images) {

                // Add the image to the select items.
                comicImagesAsSelectItems.add(new SelectItem(
                    image, image.getLabel()));
            }
        }
        catch(Exception e) {
            
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Cannot get images."));
        }
        
        
        return comicImagesAsSelectItems;
    }

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<Image> getCriteria() {

        // Declare.
        Criteria<Image> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            String fileName;
            ImageType type;

            // Get a new criteria.
            criteria = this.comicService.getCriteria(Image.class);

            // Get the image filter values.
            fileName = SessionUtility.getValue(SessionKey.IMAGE_FILTER_FILE_NAME, String.class);
            type = SessionUtility.getValue(SessionKey.IMAGE_FILTER_TYPE, ImageType.class);

            // Check if the image filter file name exists.
            if (fileName != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.like("fileName", "%" + fileName + "%"));
            }

            // Check if the image filter type exists.
            if (type != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.eq("type", type));
            }

            // Order the results.
            criteria.addOrder(Order.asc("fileName"));
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
    public Image getCurrentEntity() {
        return SessionUtility.getValue(SessionKey.IMAGE, Image.class);
    }

    /**
     * Get the entities as select items.
     *
     * @return  the entities as select items.
     */
    @Override
    public List<SelectItem> getEntitiesAsSelectItems() {
        return null;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Image> getEntityClass() {
        return Image.class;
    }

    /**
     * Get a new entity.
     */
    @Override
    protected Image getNewEntity() {
        
        // Declare.
        Image newEntity;

        // Create a new entity.
        newEntity = new Image();

        // Check if the image filter type exists.
        if (SessionUtility.valueExists(SessionKey.IMAGE_FILTER_TYPE)) {

            // Set the type in the new entity.
            newEntity.setType(SessionUtility
                    .getValue(SessionKey.IMAGE_FILTER_TYPE,
                    ImageType.class));
        }

        // Check if the user preference exits.
        else if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {

            // Set the type in the new entity.
            newEntity.setType(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getImageType());
        }

        return newEntity;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    protected void initialize() {

        // Create a new entity.
        this.entity = new Image();
    }

    /**
     * Store the entity.
     */
    @Override
    protected void storeEntity() {

        // Check if this is the view perspective.
        if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.VIEW) {

            // Put the image on the session.
            SessionUtility.putValue(SessionKey.IMAGE, this.entity);
        }
        else {

            // Remove the image from the session.
            SessionUtility.removeValue(SessionKey.IMAGE);
        }
    }
}
