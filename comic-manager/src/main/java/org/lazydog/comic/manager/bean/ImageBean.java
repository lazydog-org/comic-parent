package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.manager.helper.bean.ImageTypeFilter;
import org.lazydog.comic.manager.utility.FileUtility;
import org.lazydog.comic.manager.utility.FormButtonController;
import org.lazydog.comic.manager.utility.ImageSearchBy;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.LogicalOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;
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
     * Generate the label.
     *
     * @param  image  the image.
     *
     * @return  the generated label or null if no label could be generated.
     */
    private String generateLabel(Image image) {

        // Declare.
        String label;

        // Initialize.
        label = null;

        try {

            // Declare.
            Matcher fileNameMatcher;
            Pattern fileNamePattern;

            // Initialize.
            fileNamePattern = Pattern.compile(
                    "(.+)-(\\d*[A-Z])-(\\d{4}).jpg");
            fileNameMatcher = fileNamePattern.matcher(image.getFileName());

            // Check if the pattern matches the file name.
            if (fileNameMatcher.matches()) {

                // Declare.
                Criteria<Image> criteria;
                String fileNamePrefix;
                List<Image> images;
                String numberVariant;
                String year;

                // Get the file name prefix, number/variant, and year
                // from the file name.
                fileNamePrefix = fileNameMatcher.group(1);
                numberVariant = fileNameMatcher.group(2);
                year = fileNameMatcher.group(3);

                // Set the criteria.
                criteria = this.comicService.getCriteria(Image.class);
                criteria.add(ComparisonOperation.like(
                        "fileName", fileNamePrefix + "-%-" + year + ".jpg"));
                criteria.add(LogicalOperation.and(
                        ComparisonOperation.isNotNull("label")));

                // Get the images that matches the file name prefix.
                images = this.comicService.findList(Image.class, criteria);

                // Check if there are images.
                if (images != null && images.size() > 0) {

                    // Declare.
                    Matcher labelMatcher;
                    Pattern labelPattern;

                    // Initialize.
                    labelPattern = Pattern.compile("(.+) \\d*[A-Z] \\d{4}");
                    labelMatcher = labelPattern.matcher(
                            images.get(0).getLabel());

                    // Check if the pattern matches the label.
                    if (labelMatcher.matches()) {

                        // Declare.
                        String labelPrefix;

                        // Get the label prefix from the first images label.
                        labelPrefix = labelMatcher.group(1);

                        // Generate the label.
                        label = labelPrefix + " " + numberVariant + " " + year;
                    }
                }
            }
        }
        catch(Exception e) {
            // Ignore.
        }

        return label;
    }

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
     * Process the delete button.
     *
     * @return  actionEvent  the action event.
     */
    @Override
    public void processDeleteButton(ActionEvent actionEvent) {

        try {

            // Declare.
            Image newEntity;

            // Get the next entity.
            newEntity = this.comicService.findNext(
                    this.entity, this.getEntityClass(), this.getCriteria());

            // Check if the next entity does not exist.
            if (this.entity.equals(newEntity)) {

                // Get the previous entity.
                newEntity = this.comicService.findPrevious(
                        this.entity, this.getEntityClass(), this.getCriteria());
            }

            // Delete the entity.
            this.comicService.remove(
                    this.getEntityClass(), this.entity.getId());

            // Delete the file and check the delete.
            if (!FileUtility.deleteFile(this.entity.getType().getDirectoryPath(),
                    this.entity.getFileName())) {

                // Restore the entity.
                this.comicService.save(this.entity);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot delete the entity."));
            }
            else {
                
                // Check if the next or previous entity is not the deleted entity.
                if (!this.entity.equals(newEntity)) {

                    // Set the entity to the new entity.
                    this.entity = newEntity;

                    // Modify the perspective.
                    this.perspective = Perspective.VIEW;
                }
                else {

                    // Clear the entity.
                    this.entity = this.getNewEntity();

                    // Modify the perspective.
                    this.perspective = Perspective.FRESH;
                }
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot delete the entity."));
        }
        finally {

            // Configure the form buttons.
            this.formButtonController = new FormButtonController(
                    this.getEntityClass(), this.perspective);
        }
    }

    /**
     * Process the edit button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processEditButton(ActionEvent actionEvent) {
        super.processEditButton(actionEvent);

        // Generate a default label for the entity.
        this.entity.setLabel(this.generateLabel(this.entity));
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

    /**
     * Process the delete button.
     *
     * @return  actionEvent  the action event.
     */
    @Override
    public void processOkButton(ActionEvent actionEvent) {

        try {

            // Declare.
            Image newEntity;
            Image restoreEntity;

            // Initialize.
            newEntity = null;

            // Create a restore entity.
            restoreEntity = this.entity.copy();

            // Check if the entity is assigned a different type value and the
            // perspective is the edit perspective.
            if (!this.oldEntity.getType().getValue().equals(this.entity.getType().getValue()) &&
                this.perspective == Perspective.EDIT) {

                // Get the next entity.
                newEntity = this.comicService.findNext(
                        this.entity, this.getEntityClass(), this.getCriteria());

                // Check if the next entity does not exist.
                if (newEntity == null) {

                    // Get the previous entity.
                    newEntity = this.comicService.findPrevious(
                            this.entity, this.getEntityClass(), this.getCriteria());

                    // Check if the previous entity does not exist.
                    if (newEntity == null) {

                        // Get a new entity.
                        newEntity = this.getNewEntity();
                    }
                }
            }

            // Save the entity.
            this.entity = this.comicService.save(this.entity);

            // Move the file and check the move.
            if (!FileUtility.moveFile(
                    this.oldEntity.getType().getDirectoryPath(),
                    this.oldEntity.getFileName(),
                    this.entity.getType().getDirectoryPath(),
                    this.entity.getFileName())) {

                // Restore the entity.
                this.comicService.save(this.oldEntity);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot save the entity."));
            }
            else {

                // Modify the perspective.
                this.perspective = Perspective.VIEW;

                // Check if the new entity exists.
                if (newEntity != null) {

                    // Set the entity to the new entity.
                    this.entity = newEntity;

                    // Check if the new entity does not exist.
                    if (newEntity.equals(this.getNewEntity())) {

                        // Modify the perspective.
                        this.perspective = Perspective.FRESH;
                    }
                    else {

                        // Modify the perspective.
                        this.perspective = Perspective.VIEW;
                    }
                }

                // Check if the entity is assigned a different type value.
                else if (!this.oldEntity.getType().getValue().equals(this.entity.getType().getValue())) {

                    // Set the entity to the old entity.
                    this.entity = this.oldEntity;

                    // Check if the entity does not exist.
                    if (this.entity.equals(this.getNewEntity())) {

                        // Modify the perspective.
                        this.perspective = Perspective.FRESH;
                    }
                }
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot save the entity."));
        }
        finally {

            // Configure the form buttons.
            this.formButtonController = new FormButtonController(this.getEntityClass(), this.perspective);
        }
    }
}
