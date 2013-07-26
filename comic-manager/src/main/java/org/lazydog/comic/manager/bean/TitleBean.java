package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.lazydog.comic.manager.utility.ButtonLinkController;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.model.Category;
import org.lazydog.comic.model.Publisher;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.TitleType;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.Order;


/**
 * Title managed bean.
 * 
 * @author  Ron Rickard
 */
public class TitleBean
       extends AbstractDataAccessBean<Title>
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<Title> getCriteria() {

        // Declare.
        Criteria<Title> criteria;

        // Initialize.
        criteria = null;
        
        try {

            // Declare.
            Category category;
            String name;
            Publisher publisher;
            TitleType type;

            // Get a new criteria.
            criteria = this.comicService.getCriteria(Title.class);

            // Get the title filter values.
            category = SessionUtility.getValue(SessionKey.TITLE_FILTER_CATEGORY, Category.class);
            name = SessionUtility.getValue(SessionKey.TITLE_FILTER_NAME, String.class);
            publisher = SessionUtility.getValue(SessionKey.TITLE_FILTER_PUBLISHER, Publisher.class);
            type = SessionUtility.getValue(SessionKey.TITLE_FILTER_TYPE, TitleType.class);

            // Check if the title filter category exists.
            if (category != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.memberOf("categories", category));
            }

            // Check if the title filter name exists.
            if (name != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.like("name", "%" + name + "%"));
            }

            // Check if the title filter publisher exists.
            if (publisher != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.memberOf("publishers", publisher));
            }

            // Check if the title filter type exists.
            if (type != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.eq("type", type));
            }

            // Order the results.
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
    public Title getCurrentEntity() {
        return SessionUtility.getValue(SessionKey.TITLE, Title.class);
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
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Title> getEntityClass() {
        return Title.class;
    }

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(Title entity) {
        return null;
    }

    /**
     * Get a new entity.
     */
    @Override
    protected Title getNewEntity() {
        
        // Declare.
        Title newEntity;

        // Create a new entity.
        newEntity = new Title();

        // Check if the title filter category exists.
        if (SessionUtility.valueExists(SessionKey.TITLE_FILTER_CATEGORY)) {

            // Declare.
            List<Category> categories;

            // Initialize.
            categories = new ArrayList<Category>();

            // Set the category in the new entity.
            categories.add(SessionUtility
                    .getValue(SessionKey.TITLE_FILTER_CATEGORY,
                    Category.class));
            newEntity.setCategories(categories);
        }

        // Check if the title filter publisher exists.
        if (SessionUtility.valueExists(SessionKey.TITLE_FILTER_PUBLISHER)) {

            // Declare.
            List<Publisher> publishers;

            // Initialize.
            publishers = new ArrayList<Publisher>();

            // Set the publishers in the new entity.
            publishers.add(SessionUtility
                    .getValue(SessionKey.TITLE_FILTER_PUBLISHER,
                    Publisher.class));
            newEntity.setPublishers(publishers);
        }

        // Check if the user preference exits.
        else if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {

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

        // Check if the title filter type exists.
        if (SessionUtility.valueExists(SessionKey.TITLE_FILTER_TYPE)) {

            // Set the type in the new entity.
            newEntity.setType(SessionUtility
                    .getValue(SessionKey.TITLE_FILTER_TYPE,
                    TitleType.class));
        }

        // Check if the user preference exits.
        else if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {

            // Set the type in the new entity.
            newEntity.setType(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getTitleType());
        }

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
    protected void initialize() {

        // Find the entities.
        this.findEntities();

        // Create a new title.
        this.entity = new Title();
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
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.EDIT) {

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

            // Find the entities.
            this.findEntities();

            // Put the perspective on the session.
            SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);

            // Check if the new entity exists.
            if (newEntity != null) {

                // Set the entity to the new entity.
                this.entity = newEntity;

                // Check if the new entity does not exist.
                if (newEntity.equals(this.getNewEntity())) {

                    // Put the perspective on the session.
                    SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
                }
                else {

                    // Put the perspective on the session.
                    SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);
                }
            }

            // Check if the entity is assigned a different type value.
            else if (!this.oldEntity.getType().getValue().equals(this.entity.getType().getValue())) {

                // Set the entity to the old entity.
                this.entity = this.oldEntity;

                // Check if the entity does not exist.
                if (this.entity.equals(this.getNewEntity())) {

                    // Put the perspective on the session.
                    SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
                }
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot save the entity."));
        }
        finally {

            // Put the button/link controller on the session.
            SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                    ButtonLinkController.newInstance(
                    this.getEntityClass(),
                    SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));
        }

        // Store the entity.
        this.storeEntity();
    }

    /**
     * Store the entity.
     */
    @Override
    protected void storeEntity() {

        // Check if this is the view perspective.
        if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.VIEW) {

            // Put the title on the session.
            SessionUtility.putValue(SessionKey.TITLE, this.entity);
        }
        else {

            // Remove the title from the session.
            SessionUtility.removeValue(SessionKey.TITLE);
        }
    }
}
