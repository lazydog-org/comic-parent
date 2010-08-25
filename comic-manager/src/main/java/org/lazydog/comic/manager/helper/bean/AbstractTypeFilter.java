package org.lazydog.comic.manager.helper.bean;

import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.Entity;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UICommand;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * Abstract type filter managed bean.
 * 
 * @author  Ron Rickard
 */
public abstract class AbstractTypeFilter<T extends Entity<T>>
       implements Serializable {

    private List<String> buttons;
    private ComicService comicService;

    /**
     * Activate the filter.
     *
     * @param  actionEvent        the action event.
     * @param  comicService  the comic service.
     */
    public void activateFilter(ActionEvent actionEvent,
                               ComicService comicService) {

        try {

            // Declare.
            Criteria<T> criteria;
            T type;
            String typeValue;

            // Get the type value.
            typeValue = (String)((UICommand)actionEvent
                    .getComponent()).getValue();

            // Create a new criteria.
            criteria = this.comicService.getCriteria(this.getEntityClass());

            // Modify the criteria.
            criteria.add(ComparisonOperation.eq("value", typeValue));

            // Get the type.
            type = comicService.find(this.getEntityClass(), criteria);

            // Put the type on the session.
            SessionUtility.putValue(this.getTypeSessionKey(), type);
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get the type."));
        }
    }

    /**
     * Get the buttons.
     * 
     * @return  the buttons.
     */
    public List<String> getButtons() {

        // Check if the buttons do not exist.
        if (this.buttons == null) {

            // Initialize the buttons.
            this.buttons = new ArrayList<String>();

            try {

                // Declare.
                Criteria<T> criteria;
                List<T> types;

                // Create a new criteria.
                criteria = this.comicService.getCriteria(this.getEntityClass());

                // Modify the criteria.
                criteria.addOrder(Order.asc("value"));

                // Get the types.
                types = this.comicService.findList(this.getEntityClass(), criteria);

                // Loop through the types.
                for (T type : types) {

                    // Add the button.
                    this.buttons.add(this.getTypeValue(type));
                }
            }
            catch(Exception e) {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot get the buttons."));
            }
        }

        return this.buttons;
    }
    
    /**
     * Get the button disabled map.
     * 
     * @return  the button disabled map.
     */
    public Map<String, Boolean> getButtonDisabledMap() {

        // Declare.
        Map<String, Boolean> buttonDisabledMap;

        // Initialize.
        buttonDisabledMap = new HashMap<String, Boolean>();

        // Check if there are buttons.
        if (this.getButtons() != null) {

            // Declare.
            String disabledButton;

            // Get the disabled button.
            disabledButton = this.getTypeValue(this.getType());

            // Loop through the buttons.
            for(String button : this.getButtons()) {

                // Check if the button is the disabled button.
                if (button.equals(disabledButton)) {

                    // Disable the button.
                    buttonDisabledMap.put(button, true);
                }
                else {

                    // Enable the button.
                    buttonDisabledMap.put(button, false);
                }
            }
        }

        return buttonDisabledMap;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    protected abstract Class<T> getEntityClass();

    /**
     * Get the type.
     *
     * @return  the type.
     */
    public T getType() {
        return (SessionUtility.valueExists(this.getTypeSessionKey())) ?
            SessionUtility.getValue(this.getTypeSessionKey(), this.getEntityClass()) :
            this.getUserPreferenceType(SessionUtility.getValue(SessionKey.USER_PREFERENCE, UserPreference.class));
    }

    /**
     * Get the type session key.
     * 
     * @return  the type session key.
     */
    protected abstract SessionKey getTypeSessionKey();

    /**
     * Get the type value.
     * 
     * @return  the type value.
     */
    protected abstract String getTypeValue(T type);

    /**
     * Get the user preference type.
     * 
     * @return  the user preference type.
     */
    protected abstract T getUserPreferenceType(UserPreference userPreference);

    /**
     * Set the comic service.
     *
     * @param  comicService  the comic service.
     */
    @EJB(mappedName="ejb/ComicService", beanInterface=ComicService.class)
    protected void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }
}
