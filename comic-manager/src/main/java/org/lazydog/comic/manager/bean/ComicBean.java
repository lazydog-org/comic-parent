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
import javax.faces.model.SelectItem;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.ComicCharacter;
import org.lazydog.comic.model.ComicType;
import org.lazydog.comic.model.Distribution;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.Trait;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.model.Want;
import org.lazydog.comic.manager.utility.ButtonLinkController;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.LogicalOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Comic managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicBean
       extends AbstractDataAccessBean<Comic>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer endNumber;

    /**
     * Get the add to wishlist rendered value.
     *
     * @return  the add to wishlist rendered value.
     */
    public Boolean getAddToWishlistRendered() {

        // Declare.
        Boolean addToWishlistRendered;

        addToWishlistRendered = Boolean.TRUE;

        try {
            // Declare.
            Want want;

            // Initialize.
            want = null;
            
            // Check if there is a comic on the session.
            if (SessionUtility.valueExists(SessionKey.COMIC)) {

                // Declare.
                Criteria<Want> criteria;

                // Create the criteria.
                criteria = this.comicService.getCriteria(Want.class);
                criteria.add(ComparisonOperation.eq("comic",
                        SessionUtility.getValue(SessionKey.COMIC, Comic.class)));
                criteria.add(LogicalOperation.and(ComparisonOperation.eq("uuid",
                        SessionUtility.getValue(SessionKey.UUID, String.class))));

                // Get the want.
                want = this.comicService.find(Want.class, criteria);
            }

            // Check if there is a want.
            if (want != null) {

                // Set the add to wishlist rendered to false.
                addToWishlistRendered = Boolean.FALSE;
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Unable to determine wishlist status."));
        }

        return addToWishlistRendered;
    }

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<Comic> getCriteria() {

        // Declare.
        Criteria<Comic> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            Distribution distribution;
            Title title;
            ComicType type;

            // Get a new criteria.
            criteria = this.comicService.getCriteria(Comic.class);

            // Add the title criterion.
            criteria.add(ComparisonOperation.eq(
                    "title", SessionUtility.getValue(SessionKey.TITLE, Title.class)));

            // Get the comic filter values.
            distribution = SessionUtility.getValue(SessionKey.COMIC_FILTER_DISTRIBUTION, Distribution.class);
            type = SessionUtility.getValue(SessionKey.COMIC_FILTER_TYPE, ComicType.class);

            // Check if the comic filter distribution exists.
            if (distribution != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.eq("distribution", distribution));
            }

            // Check if the comic filter type exists.
            if (type != null) {

                // Add the criterion.
                criteria = addCriterion(criteria, ComparisonOperation.eq("type", type));
            }

            // Order the results.
            criteria.addOrder(Order.asc("number"));
            criteria.addOrder(Order.asc("variant"));
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
    public Comic getCurrentEntity() {
        return SessionUtility.getValue(SessionKey.COMIC, Comic.class);
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
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Comic> getEntityClass() {
        return Comic.class;
    }

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(Comic entity) {
        return null;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Comic getNewEntity() {
        
        // Declare.
        Comic newEntity;

        // Create a new entity.
        newEntity = new Comic();

        // Set the title in the new entity.
        newEntity.setTitle(SessionUtility.getValue(SessionKey.TITLE, Title.class));

        // Check if the comic filter distribution exists.
        if (SessionUtility.valueExists(SessionKey.COMIC_FILTER_DISTRIBUTION)) {

            // Set the distribution in the new entity.
            newEntity.setDistribution(SessionUtility
                    .getValue(SessionKey.COMIC_FILTER_DISTRIBUTION,
                    Distribution.class));
        }

        // Check if the user preference exits.
        else if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {

            // Set the distribution in the new entity.
            newEntity.setDistribution(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getDistribution());
        }

        // Check if the comic filter type exists.
        if (SessionUtility.valueExists(SessionKey.COMIC_FILTER_TYPE)) {

            // Set the type in the new entity.
            newEntity.setType(SessionUtility
                    .getValue(SessionKey.COMIC_FILTER_TYPE,
                    ComicType.class));
        }

        // Check if the user preference exists.
        else if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {

            // Set the type in the new entity.
            newEntity.setType(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getComicType());
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
    @Override
    @PostConstruct
    protected void initialize() {
        super.initialize();

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
            want.setUuid(SessionUtility.getValue(SessionKey.UUID, String.class));

            // Save the want.
            want = this.comicService.save(want);
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Unable to add the comic to the wishlist."));
        }

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity.
        this.storeEntity();
    }

    /**
     * Process the OK button.
     * 
     * @param  actionEvent  the action event.
     */
    @Override
    public void processOkButton(ActionEvent actionEvent) {

        // Convert the java.utils.Arrays$ArrayList to java.utils.ArrayList.
        this.entity.setCharacters(new ArrayList<ComicCharacter>(this.entity.getCharacters()));
        this.entity.setTraits(new ArrayList<Trait>(this.entity.getTraits()));

        // Check if this is the add, duplicate, or edit perspective.
        if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.ADD ||
            SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.DUPLICATE ||
            SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.EDIT) {

            try {

                // Declare.
                Comic newEntity;

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

                // Get the entities.
                this.entities = this.comicService.findList(
                        this.getEntityClass(), this.getCriteria());

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

                    // Set the comic to the old comic.
                    this.entity = this.oldEntity;

                    // Check if the entity does not exist.
                    if (this.entity.equals(this.getNewEntity())) {

                        // Put the perspective on the session.
                        SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
                    }
                }
            }
            catch(Exception e) {
e.printStackTrace();
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
        }

        // Check if this is the add many perspective.
        else if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.ADD_MANY) {

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
                        addEntity.setId(null);
                        addEntity.setNumber(x);

                        // Add the new entity to the comics.
                        entities.add(addEntity);
                    }

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

                    // Save the entities.
                    entities = this.comicService.saveList(entities);

                    // Get the entities.
                    this.entities = this.comicService.findList(
                            this.getEntityClass(), this.getCriteria());

                    // Set the entity to the first saved entity.
                    this.entity = entities.get(0);

                    // Put the perspective on the session.
                    SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);

                    // Check if the new comic exists.
                    if (newEntity != null) {

                        // Set the entity to the new entity.
                        this.entity = newEntity;

                        // Check if the new comic does not exist.
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

                        // Set the comic to the old comic.
                        this.entity = this.oldEntity;

                        // Check if the comic does not exist.
                        if (this.entity.equals(this.getNewEntity())) {

                            // Put the perspective on the session.
                            SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
                        }
                    }
                }
                else {

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Invalid range specified."));
                }
            }
            catch(Exception e) {
e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot save the entities."));
            }
            finally {

                // Put the button/link controller on the session.
                SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                        ButtonLinkController.newInstance(
                        this.getEntityClass(),
                        SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

            }
        }

        // Store the entity.
        this.storeEntity();
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
            Want want;

            // Create the criteria.
            criteria = this.comicService.getCriteria(Want.class);
            criteria.add(ComparisonOperation.eq("comic",
                    SessionUtility.getValue(SessionKey.COMIC, Comic.class)));
            criteria.add(LogicalOperation.and(ComparisonOperation.eq("uuid",
                    SessionUtility.getValue(SessionKey.UUID, String.class))));

            // Get the want.
            want = this.comicService.find(Want.class, criteria);

            // Remove the want.
            this.comicService.remove(Want.class, want.getId());
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Unable to remove the comic from the wishlist."));
        }

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity.
        this.storeEntity();
    }

    /**
     * Process the reset button.
     * 
     * @param  actionEvent  the action event.
     */
    @Override
    public void processResetButton(ActionEvent actionEvent) {

        // Check if this is the add or add many perspective.
        if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.ADD ||
            SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.ADD_MANY) {

            // Get a new entity.
            this.entity = this.getNewEntity();

            // Clear the end number.
            this.endNumber = null;
        }

        // Check if this is the edit perspective.
        else if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.EDIT) {

            // Set the entity to the old entity.
            this.entity = this.oldEntity.copy();
        }

        // Check if this is the duplicate perspective.
        else if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.DUPLICATE) {

            // Get the entity.
            this.entity = this.oldEntity.copy();

            // Clear the identifier.
            this.entity.setId(null);
        }

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));
    }

    /**
     * Set the end number.
     *  
     * @param  endNumber  the end number.
     */
    public void setEndNumber(Integer endNumber) {
        this.endNumber = endNumber;
    }

    /**
     * Store the entity.
     */
    @Override
    protected void storeEntity() {

        // Check if this is the view perspective.
        if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.VIEW) {

            // Put the comic on the session.
            SessionUtility.putValue(SessionKey.COMIC, this.entity);

            // Put the comic image on the session.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE, this.entity.getImage());

            // Put the upload image button disabled flag on the session.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE_BUTTON_DISABLED, Boolean.TRUE);
        }
        else {

            // Remove the comic from the session.
            SessionUtility.removeValue(SessionKey.COMIC);

            // Remove the comic image from the session.
            SessionUtility.removeValue(SessionKey.UPLOAD_IMAGE);

            // Remove the upload image button disabled flag from the session.
            SessionUtility.removeValue(SessionKey.UPLOAD_IMAGE_BUTTON_DISABLED);
        }
    }
}
