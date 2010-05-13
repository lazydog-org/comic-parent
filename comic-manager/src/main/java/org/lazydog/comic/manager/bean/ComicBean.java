package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.criterion.ComparisonOperation;
import org.lazydog.comic.criteria.criterion.LogicalOperation;
import org.lazydog.comic.criteria.criterion.Order;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.Character;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.Trait;
import org.lazydog.comic.model.User;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.model.Want;
import org.lazydog.comic.manager.helper.bean.ComicTypeFilter;
import org.lazydog.comic.manager.utility.FormButtonController;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
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
 * Comic managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicBean
       extends AbstractDataAccessBean<Comic>
       implements Serializable {

    private Integer endNumber;
    private Boolean listLinkDisabled;

    /**
     * Enable the list link.
     *
     * @param  perspective  the perspective.
     */
    private void enableListLink(Perspective perspective) {

        // Check if this is the view perspective.
        if (perspective == Perspective.VIEW) {

            // Enable the list link.
            this.listLinkDisabled = false;

            // Put the comic on the session.
            SessionUtility.putValue(SessionKey.COMIC, this.entity);
        }
        else {

            // Disable the list link.
            this.listLinkDisabled = true;

            // Remove the comic from the session.
            SessionUtility.removeValue(SessionKey.COMIC);
        }
    }

    /**
     * Get the add to wishlist rendered.
     *
     * @return  the add link rendered.
     */
    public Boolean getAddToWishlistRendered() {

        // Declare.
        Boolean addLinkRendered;

        // Initialize.
        addLinkRendered = false;

        try {
            // Declare.
            Want want;
            Criteria<Want> criteria;
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create the criteria.
            criteria = criteriaFactory.createCriteria(Want.class);
            criteria.add(ComparisonOperation.eq("comic",
                    SessionUtility.getValue(SessionKey.COMIC, Comic.class)));
            criteria.add(LogicalOperation.and(ComparisonOperation.eq("createUser",
                    SessionUtility.getValue(SessionKey.USER, User.class))));

            // Get the want.
            want = this.comicService.find(criteria);

            // Check if there is not already a want.
            if (want == null) {

                // Render the add link.
                addLinkRendered = true;
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Unable to determine wishlist status."));
        }

        return addLinkRendered;
    }

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Comic> getCriteria() {

        // Declare.
        Criteria<Comic> criteria;

        // Initialize.
        criteria = null;

        try {

            // Check if the title session value exists.
            if (SessionUtility.valueExists(SessionKey.TITLE)) {

                // Declare.
                CriteriaFactory criteriaFactory;
                ComicTypeFilter filter;

                // Initialize criteria factory.
                criteriaFactory = CriteriaFactory.instance();

                // Initialize.
                filter = new ComicTypeFilter();

                // Create a new criteria.
                criteria = criteriaFactory.createCriteria(Comic.class);

                // Modify the criteria.
                criteria.add(ComparisonOperation.eq(
                        "title",
                        SessionUtility.getValue(SessionKey.TITLE, Title.class)));
                criteria.add(LogicalOperation.and(ComparisonOperation.eq(
                        "type", filter.getType())));
                criteria.addOrder(Order.asc("number"));
                criteria.addOrder(Order.asc("variant"));
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get criteria."));
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
        
        // Initialize.
        endDate = null;
        
        // Check if the publish end date exists.
        if (this.entity.getTitle() != null &&
            this.entity.getTitle().getPublishEndDate() != null) {
            
            // The end date is the publish end date.
            endDate = this.entity.getTitle().getPublishEndDate();
        }
        else {
            
            // Declare.
            Calendar endDateAsCalendar;

            // Set the end date as calendar to today.
            endDateAsCalendar = Calendar.getInstance();

            // Add 3 months to the end date as calendar.
            endDateAsCalendar.add(Calendar.MONTH, +3);

            // Get the end date.
            endDate = endDateAsCalendar.getTime();
        }
        
        return endDate;
    }
 
    /**
     * Get the end number.
     * 
     * @return  the end number.
     */
    public Integer getEndNumber() {
        return this.endNumber;
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
    protected Class<Comic> getEntityClass() {
        return Comic.class;
    }

    /**
     * Get the list link disabled.
     *
     * @return  the list link disabled.
     */
    public Boolean getListLinkDisabled() {
        return this.listLinkDisabled;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Comic getNewEntity() {
        
        // Declare.
        ComicTypeFilter filter;
        Comic newEntity;

        // Initialize.
        filter = new ComicTypeFilter();

        // Create a new entity.
        newEntity = new Comic();

        // Set the title in the new entity.
        newEntity.setTitle(SessionUtility.getValue(SessionKey.TITLE, Title.class));
        
        // Check if the user preference exits.
        if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {
 
            // Set the distribution in the new entity.
            newEntity.setDistribution(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getDistribution());
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
        
        // Check if the publish start date exists.
        if (this.entity.getTitle().getPublishStartDate() != null) {
            
            // The start date is the publish start date.
            startDate = this.entity.getTitle().getPublishStartDate();
        }
        
        // Check if the user preference exists.
        else if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {
            
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
     * Get the variants as select items.
     * 
     * @return  the variants as select items.
     */
    public List<SelectItem> getVariantsAsSelectItems() {

        // Declare.
        String[] variants;
        List<SelectItem> variantsAsSelectItems;

        // Initialize.
        variants = new String[]{
            "A","B","C","D","E","F","G","H","I","J",
            "K","L","M","N","O","P","Q","R","S","T",
            "U","V","W","X","Y","Z"};
        variantsAsSelectItems = new ArrayList<SelectItem>();

        // Loop through the variants.
        for(String variant : variants) {

            // Add the variant to the select items.
            variantsAsSelectItems.add(
                    new SelectItem(variant, variant));
        }
        
        return variantsAsSelectItems;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Disable the list link.
        this.listLinkDisabled = true;

        // Create a new entity.
        this.entity = new Comic();
    }


    /**
     * Process the add to wishlist.
     *
     * @param  actionEvent  the action event.
     */
    public void processAddToWishlist(ActionEvent actionEvent) {

        try {

            // Declare.
            Want want;

            // Get the want.
            want = new Want();
            want.setComic(SessionUtility.getValue(SessionKey.COMIC, Comic.class));

            // Save the want.
            want = this.comicService.save(
                    want, SessionUtility.getValue(SessionKey.USER, User.class));
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Unable to add the comic to the wishlist."));
        }

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(this.getEntityClass(), this.perspective);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the cancel button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processCancelButton(ActionEvent actionEvent) {
        super.processCancelButton(actionEvent);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the delete button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processDeleteButton(ActionEvent actionEvent) {
        super.processDeleteButton(actionEvent);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the filter button.
     *
     * @param  actionEvent  the action event.
     */
    public void processFilterButton(ActionEvent actionEvent) {

        // Declare.
        ComicTypeFilter filter;

        // Initialize.
        filter = new ComicTypeFilter();

        // Activate the filter.
        filter.activateFilter(actionEvent, this.comicService);

        // Process the first button.
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

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the last button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processLastButton(ActionEvent actionEvent) {
        super.processLastButton(actionEvent);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the next button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processNextButton(ActionEvent actionEvent) {
        super.processNextButton(actionEvent);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the OK button.
     * 
     * @param  actionEvent  the action event.
     */
    @Override
    public void processOkButton(ActionEvent actionEvent) {

        // Convert the java.utils.Arrays$ArrayList to java.utils.ArrayList.
        this.entity.setCharacters(new ArrayList<Character>(this.entity.getCharacters()));
        this.entity.setTraits(new ArrayList<Trait>(this.entity.getTraits()));

        // Check if this is the add, duplicate, or edit perspective.
        if (this.perspective == Perspective.ADD ||
            this.perspective == Perspective.DUPLICATE ||
            this.perspective == Perspective.EDIT) {

            try {

                // Declare.
                Comic newEntity;

                // Initialize.
                newEntity = null;

                // Check if the entity is assigned a different type value and the
                // perspective is the edit perspective.
                if (!this.oldEntity.getType().getValue().equals(this.entity.getType().getValue()) &&
                    this.perspective == Perspective.EDIT) {

                    // Get the next entity.
                    newEntity = this.comicService.findNext(
                            this.entity, this.getCriteria());

                    // Check if the next entity does not exist.
                    if (newEntity == null) {

                        // Get the previous entity.
                        newEntity = this.comicService.findPrevious(
                                this.entity, this.getCriteria());

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

                    // Set the comic to the old comic.
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
        }

        // Check if this is the add many perspective.
        else if (this.perspective == Perspective.ADD_MANY) {

            try {

                // Declare.
                Integer beginNumber;

                // Get the begin number.
                beginNumber = this.entity.getNumber();

                // Check if the begin number and end number exist and
                // the end number is greater than or equal to the begin number.
                if (beginNumber != null &&
                    this.endNumber != null &&
                    this.endNumber >= beginNumber) {

                        // Declare.
                        List<Comic> entities;
                        Comic newEntity;

                        // Initialize.
                        entities = new ArrayList<Comic>();
                        newEntity = null;

                        // Loop through the number range.
                        for (int x = beginNumber; x <= this.endNumber; x++) {

                            // Declare.
                            Comic addEntity;

                            // Set the new entity.
                            addEntity = this.entity.copy();
                            addEntity.setCreateTime(null);
                            addEntity.setId(null);
                            addEntity.setModifyTime(null);
                            addEntity.setNumber(x);

                            // Add the new entity to the comics.
                            entities.add(addEntity);
                        }

                        // Check if the entity is assigned a different type value and the
                        // perspective is the edit perspective.
                        if (!this.oldEntity.getType().getValue().equals(this.entity.getType().getValue()) &&
                            this.perspective == Perspective.EDIT) {

                            // Get the next entity.
                            newEntity = this.comicService.findNext(
                                    this.entity, this.getCriteria());

                            // Check if the next entity does not exist.
                            if (newEntity == null) {

                                // Get the previous entity.
                                newEntity = this.comicService.findPrevious(
                                        this.entity, this.getCriteria());

                                // Check if the previous entity does not exist.
                                if (newEntity == null) {

                                    // Get a new entity.
                                    newEntity = this.getNewEntity();
                                }
                            }
                        }

                        // Save the entities.
                        entities = this.comicService.saveList(
                                entities,
                                SessionUtility.getValue(SessionKey.USER, User.class));

                        // Set the entity to the first saved entity.
                        this.entity = entities.get(0);

                        // Modify the perspective.
                        this.perspective = Perspective.VIEW;

                        // Check if the new comic exists.
                        if (newEntity != null) {

                            // Set the entity to the new entity.
                            this.entity = newEntity;

                            // Check if the new comic does not exist.
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

                            // Set the comic to the old comic.
                            this.entity = this.oldEntity;

                            // Check if the comic does not exist.
                            if (this.entity.equals(this.getNewEntity())) {

                                // Modify the perspective..
                                this.perspective = Perspective.FRESH;
                            }
                        }


                }
                else {

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Invalid range specified."));
                }
            }
            catch(Exception e) {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot save the entities."));
            }
            finally {

                // Configure the form buttons.
                this.formButtonController = new FormButtonController(this.getEntityClass(), this.perspective);

            }
        }

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the previous button.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processPreviousButton(ActionEvent actionEvent) {
        super.processPreviousButton(actionEvent);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the remove from wishlist.
     *
     * @param  actionEvent  the action event.
     */
    public void processRemoveFromWishlist(ActionEvent actionEvent) {

        try {

            // Declare.
            Criteria<Want> criteria;
            CriteriaFactory criteriaFactory;
            Want want;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create the criteria.
            criteria = criteriaFactory.createCriteria(Want.class);
            criteria.add(ComparisonOperation.eq("comic",
                    SessionUtility.getValue(SessionKey.COMIC, Comic.class)));
            criteria.add(LogicalOperation.and(ComparisonOperation.eq("createUser",
                    SessionUtility.getValue(SessionKey.USER, User.class))));

            // Get the want.
            want = this.comicService.find(criteria);

            // Remove the want.
            this.comicService.remove(Want.class, want.getId());
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Unable to remove the comic from the wishlist."));
        }

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(this.getEntityClass(), this.perspective);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Process the reset button.
     * 
     * @param  actionEvent  the action event.
     */
    @Override
    public void processResetButton(ActionEvent actionEvent) {

        // Check if this is the add or add many perspective.
        if (this.perspective == Perspective.ADD ||
            this.perspective == Perspective.ADD_MANY) {

            // Get a new entity.
            this.entity = this.getNewEntity();

            // Clear the end number.
            this.endNumber = null;
        }

        // Check if this is the edit perspective.
        else if (this.perspective == Perspective.EDIT) {

            // Set the entity to the old entity.
            this.entity = this.oldEntity.copy();
        }

        // Check if this is the duplicate perspective.
        else if (this.perspective == Perspective.DUPLICATE) {

            // Get the entity.
            this.entity = this.oldEntity.copy();

            // Clear the ID, create time, and modify time.
            this.entity.setId(null);
            this.entity.setCreateTime(null);
            this.entity.setModifyTime(null);
        }

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(this.getEntityClass(), this.perspective);
    }

    /**
     * Process the row click.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processRowClick(ActionEvent actionEvent) {
        super.processRowClick(actionEvent);

        // Enable the list link.
        this.enableListLink(this.perspective);
    }

    /**
     * Set the end number.
     *  
     * @param  endNumber  the end number.
     */
    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }
}