package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.manager.helper.bean.ImageTypeFilter;
import org.lazydog.comic.manager.utility.ImageSearchBy;
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
    protected Criteria<Image> getCriteria() {

        // Declare.
        Criteria<Image> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            ImageTypeFilter filter;

            // Initialize.
            filter = new ImageTypeFilter();


            // Get the criteria for the image searcher.
            criteria = this.getCriteria(
                    SessionUtility.getValue(
                    SessionKey.IMAGE_SEARCH_BY, ImageSearchBy.class),
                    SessionUtility.getValue(
                    SessionKey.IMAGE_SEARCH_FOR, Object.class));


            // Modify the criteria.
            criteria.add(LogicalOperation.and(ComparisonOperation.eq(
                    "type", filter.getType())));
            criteria.addOrder(Order.asc("fileName"));
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get criteria."));
        }

        return criteria;
    }

    /**
     * Get the criteria.
     *
     * @param  searchBy   the search by.
     * @param  searchFor  the search for.
     *
     * @return  the criteria.
     */
    private Criteria<Image> getCriteria(ImageSearchBy searchBy,
                                        Object searchFor) {

        // Declare.
        Criteria<Image> criteria;

        // Initialize.
        criteria = null;

        // Get a new criteria.
        criteria = this.comicService.getCriteria(Image.class);

        switch(searchBy) {

            case IMAGE_FILE_NAME:

                // Modify the criteria.
                criteria.add(ComparisonOperation.like(
                        "fileName", "%" + (String)searchFor + "%"));
                break;
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
        ImageTypeFilter filter;
        Image newEntity;

        // Initialize.
        filter = new ImageTypeFilter();

        // Create a new entity.
        newEntity = new Image();

        // Set the type in the new entity.
        newEntity.setType(filter.getType());
        
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
     * Process the filter button.
     *
     * @param  actionEvent  the action event.
     */
    public void processFilterButton(ActionEvent actionEvent) {

        // Declare.
        ImageTypeFilter filter;

        // Initialize.
        filter = new ImageTypeFilter();

        // Activate the filter.
        filter.activateFilter(actionEvent, this.comicService);

        // Enable the first button.
        this.processFirstButton(actionEvent);
    }
}
