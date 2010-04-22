package comic.faces.component;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;


/**
 * Toggle panel trigger JavaServer Faces UI component.
 * 
 * @author  Ron Rickard
 */
public class TogglePanelTrigger 
       extends UIComponentBase {

    public static final String COMPONENT_FAMILY = "comic.faces.TogglePanelTrigger";
    public static final String COMPONENT_TYPE = "comic.faces.TogglePanelTrigger";  

    private String forId;
    
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
     * Get the for ID attribute value.
     * 
     * @return  the for ID attribute value.
     */
    public String getForId() {

        // Check if attribute is set using a value expression binding.
        if (this.forId == null && 
            this.getValueExpression("forId") != null) {

            // Set the for ID to the value expression binding.
            this.forId = (String)this.getValueExpression("forId").getValue(
                this.getFacesContext().getELContext());
        }

        return this.forId;
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
        this.forId = (String)values[1];
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
        values = new Object[2];

        // Save the state.
        values[0] = super.saveState(context);
        values[1] = this.forId;

        return values;
    }
    
    /**
     * Set the for ID attribute value.
     * 
     * @param  forId  the for ID attribute value.
     */
    public void setForId(String forId) {
        this.forId = forId;
    }
}
