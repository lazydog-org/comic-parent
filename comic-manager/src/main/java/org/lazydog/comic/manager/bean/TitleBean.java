package org.lazydog.comic.manager.bean;

import org.lazydog.comic.model.Category;
import org.lazydog.comic.model.Publisher;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.User;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.helper.bean.TitleSearcher;
import org.lazydog.comic.manager.helper.bean.TitleTypeFilter;
import org.lazydog.comic.manager.utility.FormButtonController;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.TitleSearchBy;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.LogicalOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * Title managed bean.
 * 
 * @author  Ron Rickard
 */
public class TitleBean
       extends AbstractDataAccessBean<Title>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Boolean comicLinkDisabled;

    /**
     * Enable the comic link.
     *
     * @param  perspective  the perspective.
     */
    private void enableComicLink(Perspective perspective) {

        // Check if this is the view perspective.
        if (perspective == Perspective.VIEW) {

            // Enable the comic link.
            this.comicLinkDisabled = false;

            // Put the title on the session.
            SessionUtility.putValue(SessionKey.TITLE, this.entity);
        }
        else {

            // Disable the comic link.
            this.comicLinkDisabled = true;

            // Remove the title from the session.
            SessionUtility.removeValue(SessionKey.TITLE);
        }
    }

    /**
     * Get the comic link disabled.
     *
     * @return  the comic link disabled.
     */
    public Boolean getComicLinkDisabled() {
        return this.comicLinkDisabled;
    }

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Title> getCriteria() {

        // Declare.
        Criteria<Title> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            TitleTypeFilter filter;

            // Initialize.
            filter = new TitleTypeFilter();

            // Get the criteria for the title searcher.
            criteria = this.getCriteria(
                    SessionUtility.getValue(
                    SessionKey.TITLE_SEARCH_BY, TitleSearchBy.class),
                    SessionUtility.getValue(
                    SessionKey.TITLE_SEARCH_FOR, Object.class));

            // Modify the criteria.
            criteria.add(LogicalOperation.and(ComparisonOperation.eq(
                    "type", filter.getType())));
            criteria.addOrder(Order.asc("name"));
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
    public Criteria<Title> getCriteria(TitleSearchBy searchBy,
                                       Object searchFor) {

        // Declare.
        Criteria<Title> criteria;

        // Initialize.
        criteria = null;

        // Get a new criteria.
        criteria = this.comicService.getCriteria(Title.class);

        switch(searchBy) {

            case CATEGORY_NAME:

                // Modify the criteria.
                criteria.add(ComparisonOperation.memberOf(
                        "categories", (Category)searchFor));
                break;

            case PUBLISHER_NAME:

                // Modify the criteria.
                criteria.add(ComparisonOperation.memberOf(
                        "publishers", (Publisher)searchFor));
                break;

            case TITLE_NAME:

                // Modify the criteria.
                criteria.add(ComparisonOperation.like(
                        "name", "%" + (String)searchFor + "%"));
                break;
        }

        return criteria;
    }

    /**
     * Get the end date.
     * 
     * @return  the end date.
     */
    public Date getEndDate() {

        // Declare.
        Date endDate;
        Calendar endDateAsCalendar;

        // Set the end date as calendar to today.
        endDateAsCalendar = Calendar.getInstance();

        // Add 3 months to the end date as calendar.
        endDateAsCalendar.add(Calendar.MONTH, +3);

        // Get the end date.
        endDate = endDateAsCalendar.getTime();

        return endDate;
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
    protected Class<Title> getEntityClass() {
        return Title.class;
    }

    /**
     * Get a new entity.
     */
    @Override
    protected Title getNewEntity() {
        
        // Declare.
        TitleTypeFilter filter;
        Title newEntity;

        // Initialize.
        filter = new TitleTypeFilter();
        
        // Create a new entity.
        newEntity = new Title();

        // Check if the user preference exits.
        if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {
            
            // Declare.
            List<Publisher> publishers;
        
            // Initialize.
            publishers = new ArrayList<Publisher>();
        
            // Set the publishers in the new entity.
            publishers.add(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getPublisher());
            newEntity.setPublishers(publishers);
        }

        // Set the type in the new entity.
        newEntity.setType(filter.getType());

        return newEntity;
    }

    /**
     * Get the start date.
     * 
     * @return  the start date.
     */
    public Date getStartDate() {
        
        // Declare.
        Date startDate;
        
        // Initialize.
        startDate = null;

        // Check if the user preference exists.
        if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {
            
            // The start date is the user preference minimum publish date.
            startDate = SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getMinimumPublishDate();
        }
        else {
            
            // The start date is today.
            startDate = new Date();
        }
        
        return startDate;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Disable the comic link.
        this.comicLinkDisabled = true;

        // Create a new title.
        this.entity = new Title();
    }

    /**
     * Process the cancel button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processCancelButton(ActionEvent actionEvent) {
        super.processCancelButton(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }

    /**
     * Process the delete button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processDeleteButton(ActionEvent actionEvent) {
        super.processDeleteButton(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }

    /**
     * Process the filter button.
     *
     * @param  actionEvent  the action event.
     */
    public void processFilterButton(ActionEvent actionEvent) {

        // Declare.
        TitleTypeFilter filter;

        // Initialize.
        filter = new TitleTypeFilter();

        // Activate the filter.
        filter.activateFilter(actionEvent, this.comicService);

        // Enable the first button.
        this.processFirstButton(actionEvent);
    }

    /**
     * Process the first button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processFirstButton(ActionEvent actionEvent) {
        super.processFirstButton(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }

    /**
     * Process the last button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processLastButton(ActionEvent actionEvent) {
        super.processLastButton(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }

    /**
     * Process the next button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processNextButton(ActionEvent actionEvent) {
        super.processNextButton(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }

    /**
     * Process the OK button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processOkButton(ActionEvent actionEvent) {

        // Convert the java.utils.Arrays$ArrayList to java.utils.ArrayList.
        this.entity.setCategories(new ArrayList<Category>(this.entity.getCategories()));
        this.entity.setPublishers(new ArrayList<Publisher>(this.entity.getPublishers()));

        try {

            // Declare.
            Title newEntity;

            // Initialize.
            newEntity = null;

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
            this.entity = this.comicService.save(
                    this.entity,
                    SessionUtility.getValue(SessionKey.USER, User.class));

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
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot save the entity."));
        }
        finally {

            // Configure the form buttons.
            this.formButtonController = new FormButtonController(this.getEntityClass(), this.perspective);
        }

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }
        
    /**
     * Process the previous button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processPreviousButton(ActionEvent actionEvent) {
        super.processPreviousButton(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }

    /**
     * Process the row click.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processRowClick(ActionEvent actionEvent) {
        super.processRowClick(actionEvent);

        // Enable the comic link.
        this.enableComicLink(this.perspective);
    }
}
