package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.Entity;
import org.lazydog.comic.manager.utility.ButtonLinkController;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.Criterion;
import org.lazydog.repository.criterion.LogicalOperation;
import org.richfaces.component.html.HtmlDataTable;


/**
 * Abstract data access managed bean.
 * 
 * @author  Ron Rickard
 */
public abstract class AbstractDataAccessBean<T extends Entity<T>>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Enumeration for a find type.
     */
    public enum FindType {
        CURRENT,
        FIRST,
        LAST,
        NEXT,
        PREVIOUS;
    };

    protected ComicService comicService;
    protected HtmlDataTable dataTable;
    private List<T> entities;
    protected T entity;
    protected Class<T> entityClass;
    protected T oldEntity;

    /**
     * Add the criterion.
     *
     * @param  criteria   the criteria.
     * @param  criterion  the criterion to add.
     *
     * @return  the criteria.
     */
    protected Criteria<T> addCriterion(Criteria<T> criteria, Criterion criterion) {

        // Check if there are previous restriction criterion.
        if (criteria.restrictionExists()) {

            // Add the criterion to the existing criterion.
            criteria.add(LogicalOperation.and(criterion));
        }
        else {

            // Add the criterion.
            criteria.add(criterion);
        }

        return criteria;
    }

    /**
     * Find the entities.
     */
    protected void findEntities() {
        java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " findEntities() entered");
        try {

            // Check if the criteria exists.
            if (this.getCriteria() != null) {

                // Get the entities.
                this.entities = this.comicService.findList(
                        this.getEntityClass(), this.getCriteria());
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get the entities."));
        }
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " findEntities() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Find the entity specified by find type.
     *
     * @param  findType  the find type.
     */
    private void findEntity(FindType findType) {

        try {

            // Declare.
            T entity;

            // Initialize.
            entity = null;

            switch (findType) {

                case CURRENT:

                    // Get the current entity.
                    entity = this.getCurrentEntity();
                    break;

                case FIRST:

                    // Get the first entity.
                    entity = this.comicService.findFirst(
                            this.getEntityClass(), this.getCriteria());
                    break;

                case LAST:

                    // Get the last entity.
                    entity = this.comicService.findLast(
                            this.getEntityClass(), this.getCriteria());
                    break;

                case NEXT:

                    // Get the next entity.
                    entity = this.comicService.findNext(
                            this.entity, this.getEntityClass(),
                            this.getCriteria());
                    break;

                case PREVIOUS:

                    // Get the previous entity.
                    entity = this.comicService.findPrevious(
                            this.entity, this.getEntityClass(),
                            this.getCriteria());
                    break;
            }

            // Check if the entity exists.
            if (entity != null) {

                // Set the entity to the entity.
                this.entity = entity;

                // Put the perspective on the session.
                SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);
            }
            else {

                // Set the entity to a new entity.
                this.entity = this.getNewEntity();

                // Put the perspective on the session.
                SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get the " +
                    findType.toString().toLowerCase() + " entity."));
        }
        finally {

            // Put the button/link controller on the session.
            SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                    ButtonLinkController.newInstance(
                    this.getEntityClass(),
                    SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));
        }
    }

    /**
     * Get the button/link controller.
     *
     * @return  the button/link controller.
     */
    public ButtonLinkController getButtonLinkController() {
        return SessionUtility.getValue(
                SessionKey.BUTTON_LINK_CONTROLLER, ButtonLinkController.class);
    }

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    protected abstract Criteria<T> getCriteria();

    /**
     * Get a current entity.
     *
     * @return  a current entity.
     */
    public abstract T getCurrentEntity();

    /**
     * Get the data table.
     *
     * @return  the data table.
     */
    public HtmlDataTable getDataTable() {
        return this.dataTable;
    }

    /**
     * Get the entities.
     *
     * @return  the entities.
     */
    public List<T> getEntities() {
        return this.entities;
    }

    /**
     * Get the entities as select items.
     *
     * @return  the entities as select items.
     */

    /**
     * Get the entities as select items.
     *
     * @return  the entities as select items.
     */
    public List<SelectItem> getEntitiesAsSelectItems() {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " getEntitiesAsSelectItems() entered");
        // Declare.
        List<SelectItem> entitiesAsSelectItems;

        // Initialize.
        entitiesAsSelectItems = new ArrayList<SelectItem>();

        // Loop through the entities.
        for(T entity : this.entities) {

            // Add the entity to the select items.
            entitiesAsSelectItems.add(new SelectItem(
                entity, this.getEntitySelectProperty(entity)));
        }
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " getEntitiesAsSelectItems() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
        return entitiesAsSelectItems;
    }

    /**
     * Get the entity.
     *
     * @return  the entity.
     */
    public T getEntity() {
        return this.entity;
    }

    /**
     * Get the entity class.
     * 
     * @return  the entity class.
     */
    protected abstract Class<T> getEntityClass();

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    protected abstract String getEntitySelectProperty(T entity);

    /**
     * Get a new entity.
     *
     * @return  a new entity.
     */
    protected abstract T getNewEntity();
    
    /**
     * Get the old entity.
     *
     * @return  the old entity.
     */
    public T getOldEntity() {
        return this.oldEntity;
    }

    /**
     * Get the perspective.
     * 
     * @return  the perspective.
     */
    public Perspective getPerspective() {
        return SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class);
    }

    /**
     * Process the add button.
     *
     * @param  actionEvent  the action event.
     */
    public void processAddButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processAddButton() entered");
        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Get a new entity.
        this.entity = this.getNewEntity();

        // Put the perspective on the session.
        SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.ADD);

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processAddButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the add many button.
     *
     * @param  actionEvent  the action event.
     */
    public void processAddManyButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processAddManyButton() entered");
        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Get a new entity.
        this.entity = this.getNewEntity();

        // Put the perspective on the session.
        SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.ADD_MANY);

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processAddManyButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the cancel button.
     *
     * @param  actionEvent  the action event.
     */
    public void processCancelButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processCancelButton() entered");
        // Check if the old entity exists.
        if (this.oldEntity != null && this.oldEntity.getId() != null) {

            // Set the entity to the old entity.
            this.entity = this.oldEntity.copy();

            // Clear the old entity.
            this.oldEntity = null;

            // Put the perspective on the session.
            SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);
        }
        else {

            // Set the entity to a new entity.
            this.entity = this.getNewEntity();

            // Put the perspective on the session.
            SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
        }

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processCancelButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the current button.
     *
     * @param  actionEvent  the action event.
     */
    public void processCurrentButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processCurrentButton() entered");
        this.findEntity(FindType.CURRENT);

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processCurrentButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the delete button.
     *
     * @param  actionEvent  the action event.
     */
    public void processDeleteButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processDeleteButton() entered");
        try {

            // Declare.
            T newEntity;

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

            // Find the entities.
            this.findEntities();

            // Check if the next or previous entity is not the deleted entity.
            if (!this.entity.equals(newEntity)) {

                // Set the entity to the new entity.
                this.entity = newEntity;

                // Put the perspective on the session.
                SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);
            }
            else {

                // Clear the entity.
                this.entity = this.getNewEntity();

                // Put the perspective on the session.
                SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.FRESH);
            }
        }
        catch(Exception e) {
e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot delete the entity."));
        }
        finally {

            // Put the button/link controller on the session.
            SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                    ButtonLinkController.newInstance(
                    this.getEntityClass(),
                    SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));
        }

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processDeleteButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }


    /**
     * Process the duplicate button.
     *
     * @param  actionEvent  the action event.
     */
    public void processDuplicateButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processDuplicateButton() entered");
        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Clear the identifier.
        this.entity.setId(null);

        // Put the perspective on the session.
        SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.DUPLICATE);

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processDuplicateButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the edit button.
     *
     * @param  actionEvent  the action event.
     */
    public void processEditButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processEditButton() entered");
        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Put the perspective on the session.
        SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.EDIT);

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processEditButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the first button.
     *
     * @param  actionEvent  the action event.
     */
    public void processFirstButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processFirstButton() entered");
        this.findEntity(FindType.FIRST);

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processFirstButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the last button.
     *
     * @param  actionEvent  the action event.
     */
    public void processLastButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processLastButton() entered");
        this.findEntity(FindType.LAST);

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processLastButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }


    /**
     * Process the next button.
     *
     * @param  actionEvent  the action event.
     */
    public void processNextButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processNextButton() entered");
        this.findEntity(FindType.NEXT);

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processNextButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the OK button.
     *
     * @param  actionEvent  the action event.
     */
    public void processOkButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processOkButton() entered");
        try {

            // Save the entity.
            this.entity = this.comicService.save(this.entity);

            // Find the entities.
            this.findEntities();

            // Put the perspective on the session.
            SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);
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

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processOkButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the previous button.
     *
     * @param  actionEvent  the action event.
     */
    public void processPreviousButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processPreviousButton() entered");
        this.findEntity(FindType.PREVIOUS);

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processPreviousButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Process the reset button.
     *
     * @param  actionEvent  the action event.
     */
    public void processResetButton(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processResetButton() entered");
        // Check if this is the add perspective.
        if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.ADD) {

            // Get a new entity.
            this.entity = this.getNewEntity();
        }

        // Check if this is the edit perspective.
        else if (SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class) == Perspective.EDIT) {

            // Set the entity to the old entity.
            this.entity = this.oldEntity.copy();
        }

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processResetButton() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }
    
    /**
     * Process the row click.
     *
     * @param  actionEvent  the action event.
     */
    @SuppressWarnings("unchecked")
    public void processRowClick(ActionEvent actionEvent) {
java.util.Date startDate = new java.util.Date();
System.err.println(startDate + " " + this.getEntityClass().getName() + " processRowClick() entered");
        // Get the entity.
        this.entity = (T)this.dataTable.getRowData();

        // Put the perspective on the session.
        SessionUtility.putValue(SessionKey.PERSPECTIVE, Perspective.VIEW);

        // Put the button/link controller on the session.
        SessionUtility.putValue(SessionKey.BUTTON_LINK_CONTROLLER,
                ButtonLinkController.newInstance(
                this.getEntityClass(),
                SessionUtility.getValue(SessionKey.PERSPECTIVE, Perspective.class)));

        // Store the entity on the session.
        this.storeEntity();
java.util.Date endDate = new java.util.Date();
System.err.println(endDate + " " + this.getEntityClass().getName() + " processRowClick() exited (" + (endDate.getTime() - startDate.getTime()) + " ms)");
    }

    /**
     * Set the comic service.
     *
     * @param  comicService  the comic service.
     */
    @EJB(mappedName="ejb/ComicService", beanInterface=ComicService.class)
    protected void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    /**
     * Set the data table.
     *
     * @param  dataTable  the data table.
     */
    public void setDataTable(HtmlDataTable dataTable) {
        this.dataTable = dataTable;
    }

    /**
     * Set the entity.
     *
     * @param  entity  the entity.
     */
    public void setEntity(T entity) {
        this.entity = entity;
    }

    /**
     * Set the old entity.
     *
     * @param  oldEntity  the old entity.
     */
    public void setOldEntity(T oldEntity) {
        this.oldEntity = oldEntity;
    }

    /**
     * Set the perspective.
     * 
     * @param  perspective  the perspective.
     */
    public void setPerspective(Perspective perspective) {
        SessionUtility.putValue(SessionKey.PERSPECTIVE, perspective);
    }

    /**
     * Store the entity.
     */
    protected abstract void storeEntity();
}
