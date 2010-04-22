package comic.faces.component;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;


/**
 * Toggle panel JavaServer Faces UI component.
 * 
 * @author  Ron Rickard
 */
public class TogglePanel 
       extends UIComponentBase {

    public static final String COMPONENT_FAMILY = "comic.faces.TogglePanel";
    public static final String COMPONENT_TYPE = "comic.faces.TogglePanel";
    private static final Boolean DEFAULT_OPENED = Boolean.TRUE;
    
    private Boolean expanded;
    private Boolean opened; 
    
    /**
     * Get the expanded hidden field value.
     * 
     * @return  the expanded hidden field value.
     */
    public Boolean getExpanded() {
        return this.expanded;
    }
    
    /**
     * Get family.
     * 
     * @return  the family.
     */
    @Override
    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    /**
     * Get the opened attribute value.
     * 
     * @return  the opened attribute value.
     */
    public Boolean getOpened() {

        // Check if the attribute is set using a value expression binding.
        if (this.opened == null && 
            this.getValueExpression("opened") != null) {

            // Set the opened attribute value to the value expression binding.
            this.opened = (Boolean)this.getValueExpression("opened").getValue(
                this.getFacesContext().getELContext());
        }
        
        // Check if the opened attribute value does not exist.
        if (this.opened == null) {
            
            // Set the opened attribute value to the default.
            this.opened = DEFAULT_OPENED;
        }

        return this.opened;
    }

    /**
     * Restore the state of this component.
     * 
     * @param  context  the faces context.
     * @param  state    the state of this component.
     */
    @Override
    public void restoreState(FacesContext context, 
                             Object state) {

        // Declare.
        Object[] values;
           
        // Initialize.
        values = (Object[])state;

        // Restore the state.
        super.restoreState(context, values[0]);
        this.opened = (Boolean)values[1];
        this.expanded = (Boolean)values[2];
    }
    
    /**
     * Save the state of this component.
     * 
     * @param  context  the faces context.
     * 
     * @return  the state of this component.
     */
    @Override
    public Object saveState(FacesContext context) {

        // Declare.
        Object[] values;
        
        // Initialize.
        values = new Object[3];

        // Save the state.
        values[0] = super.saveState(context);
        values[1] = this.opened;
        values[2] = this.expanded;

        return values;
    }
    
    /**
     * Set the expanded hidden field value.
     * 
     * @param  expanded  the expanded hidden field value.
     */
    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }
    
    /**
     * Set the opened attribute value.
     * 
     * @param  opened  the opened attribute value.
     */
    public void setOpened(Boolean opened) {
        this.opened = opened;
    }
}
