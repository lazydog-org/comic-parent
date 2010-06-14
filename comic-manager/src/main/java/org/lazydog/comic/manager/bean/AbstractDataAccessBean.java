package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.model.Entity;
import org.lazydog.comic.model.User;
import org.lazydog.comic.service.ComicService;
import org.lazydog.comic.manager.utility.FormButtonController;
import org.lazydog.comic.manager.utility.Perspective;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.richfaces.component.html.HtmlDataTable;


/**
 * Abstract data access managed bean.
 * 
 * @author  Ron Rickard
 */
public abstract class AbstractDataAccessBean<T extends Entity<T>>
       implements Serializable {

    /**
     * Enumeration for a find type.
     */
    public enum FindType {
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
    protected FormButtonController formButtonController;
    protected T oldEntity;
    protected Perspective perspective;

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

                case FIRST:

                    // Get the first entity.
                    entity = this.comicService.findFirst(this.getCriteria());
                    break;
                case LAST:

                    // Get the last entity.
                    entity = this.comicService.findLast(this.getCriteria());
                    break;

                case NEXT:

                    // Get the next entity.
                    entity = this.comicService.findNext(
                            this.entity, this.getCriteria());
                    break;

                case PREVIOUS:

                    // Get the previous entity.
                    entity = this.comicService.findPrevious(
                            this.entity, this.getCriteria());
                    break;
            }

            // Check if the entity exists.
            if (entity != null) {

                // Set the entity to the entity.
                this.entity = entity;

                // Modify the perspective.
                this.perspective = Perspective.VIEW;
            }
            else {

                // Set the entity to a new entity.
                this.entity = this.getNewEntity();

                // Modify the perspective.
                this.perspective = Perspective.FRESH;
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get the " +
                    findType.toString().toLowerCase() + " entity."));
        }
        finally {

            // Configure the form buttons.
            this.formButtonController = new FormButtonController(
                    this.getEntityClass(), this.perspective);
        }
    }

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    protected abstract Criteria<T> getCriteria();

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

        // Check if the entities exist.
        if (this.entities == null) {

            try {

                // Declare.
                Criteria<T> criteria;

                // Get the criteria.
                criteria = this.getCriteria();

                // Check if the criteria exists.
                if (criteria != null) {

                    // Get the entities.
                    this.entities = this.comicService.findList(
                            this.getCriteria());
                }
            }
            catch(Exception e) {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot get the entities."));
            }
        }

        return entities;
    }

    /**
     * Get the entities as select items.
     *
     * @return  the entities as select items.
     */
    public abstract List<SelectItem> getEntitiesAsSelectItems();

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
     * Get the form button controller.
     *
     * @return  the form button controller.
     */
    public FormButtonController getFormButtonController() {
        return this.formButtonController;
    }

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
        return this.perspective;
    }

    /**
     * Process the add button.
     *
     * @param  actionEvent  the action event.
     */
    public void processAddButton(ActionEvent actionEvent) {

        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Get a new entity.
        this.entity = this.getNewEntity();

        // Modify the perspective.
        this.perspective = Perspective.ADD;

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
    }

    /**
     * Process the add many button.
     *
     * @param  actionEvent  the action event.
     */
    public void processAddManyButton(ActionEvent actionEvent) {

        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Get a new entity.
        this.entity = this.getNewEntity();

        // Modify the perspective.
        this.perspective = Perspective.ADD_MANY;

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
    }

    /**
     * Process the cancel button.
     *
     * @param  actionEvent  the action event.
     */
    public void processCancelButton(ActionEvent actionEvent) {

        // Check if the old entity exists.
        if (this.oldEntity != null && this.oldEntity.getId() != null) {

            // Set the entity to the old entity.
            this.entity = this.oldEntity.copy();

            // Clear the old entity.
            this.oldEntity = null;

            // Modify the perspective.
            this.perspective = Perspective.VIEW;
        }
        else {

            // Set the entity to a new entity.
            this.entity = this.getNewEntity();

            // Modify the perspective.
            this.perspective = Perspective.FRESH;
        }

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
    }

    /**
     * Process the delete button.
     *
     * @param  actionEvent  the action event.
     */
    public void processDeleteButton(ActionEvent actionEvent) {

        try {

            // Declare.
            T newEntity;

            // Get the next entity.
            newEntity = this.comicService.findNext(
                    this.entity, this.getCriteria());

            // Check if the next entity does not exist.
            if (this.entity.equals(newEntity)) {

                // Get the previous entity.
                newEntity = this.comicService.findPrevious(
                        this.entity, this.getCriteria());
            }

            // Delete the entity.
            this.comicService.remove(
                    this.getEntityClass(), this.entity.getId());

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
     * Process the duplicate button.
     *
     * @param  actionEvent  the action event.
     */
    public void processDuplicateButton(ActionEvent actionEvent) {

        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Clear the ID, create time, and modify time.
        this.entity.setId(null);
        this.entity.setCreateTime(null);
        this.entity.setModifyTime(null);


        // Modify the perspective.
        this.perspective = Perspective.DUPLICATE;

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
    }

    /**
     * Process the edit button.
     *
     * @param  actionEvent  the action event.
     */
    public void processEditButton(ActionEvent actionEvent) {

        // Set the old entity to the entity.
        this.oldEntity = this.entity.copy();

        // Modify the perspective.
        this.perspective = Perspective.EDIT;

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
    }

    /**
     * Process the first button.
     *
     * @param  actionEvent  the action event.
     */
    public void processFirstButton(ActionEvent actionEvent) {
        this.findEntity(FindType.FIRST);
    }

    /**
     * Process the last button.
     *
     * @param  actionEvent  the action event.
     */
    public void processLastButton(ActionEvent actionEvent) {
        this.findEntity(FindType.LAST);
    }


    /**
     * Process the next button.
     *
     * @param  actionEvent  the action event.
     */
    public void processNextButton(ActionEvent actionEvent) {
        this.findEntity(FindType.NEXT);
    }

    /**
     * Process the OK button.
     *
     * @param  actionEvent  the action event.
     */
    public void processOkButton(ActionEvent actionEvent) {

        try {

            // Save the entity.
            this.entity = this.comicService.save(
                    this.entity,
                    SessionUtility.getValue(SessionKey.USER, User.class));

            // Modify the perspective.
            this.perspective = Perspective.VIEW;
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot save the entity."));
        }
        finally {

            // Configure the form buttons.
            this.formButtonController = new FormButtonController(
                    this.getEntityClass(), this.perspective);
        }
    }

    /**
     * Process the previous button.
     *
     * @param  actionEvent  the action event.
     */
    public void processPreviousButton(ActionEvent actionEvent) {
        this.findEntity(FindType.PREVIOUS);
    }

    /**
     * Process the reset button.
     *
     * @param  actionEvent  the action event.
     */
    public void processResetButton(ActionEvent actionEvent) {

        // Check if this is the add perspective.
        if (this.perspective == Perspective.ADD) {

            // Get a new entity.
            this.entity = this.getNewEntity();
        }

        // Check if this is the edit perspective.
        else if (this.perspective == Perspective.EDIT) {

            // Set the entity to the old entity.
            this.entity = this.oldEntity.copy();
        }

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
    }
    
    /**
     * Process the row click.
     *
     * @param  actionEvent  the action event.
     */
    @SuppressWarnings("unchecked")
    public void processRowClick(ActionEvent actionEvent) {

        // Get the entity.
        this.entity = (T)this.dataTable.getRowData();

        // Modify the perspective.
        this.perspective = Perspective.VIEW;

        // Configure the form buttons.
        this.formButtonController = new FormButtonController(
                this.getEntityClass(), this.perspective);
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
        this.perspective = perspective;
    }
}
