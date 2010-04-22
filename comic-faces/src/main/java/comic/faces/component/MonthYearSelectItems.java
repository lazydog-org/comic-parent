package comic.faces.component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;


/**
 * Toggle panel JavaServer Faces UI component.
 * 
 * @author  Ron Rickard
 */
public class MonthYearSelectItems 
       extends UISelectItems {

    private static final Boolean DEFAULT_EMPTY = Boolean.FALSE;
    private static final String EPOCH = "01/1900";
    private static final DateFormat monthFormat = new SimpleDateFormat("MM");
    private static final DateFormat monthYearFormat = new SimpleDateFormat("MM/yyyy");
    private static final DateFormat yearFormat = new SimpleDateFormat("yyyy");
    
    private Boolean empty;
    private Date endDate;
    private Date startDate; 

    /**
     * Get the empty attribute value.
     * 
     * @return  the empty attribute value.
     */
    private Boolean getEmpty() {
        
        // Check if the attribute is set using a value expression binding.
        if (this.empty == null && 
            this.getValueExpression("empty") != null) {
            
            // Set empty to the value expression binding.
            this.empty = (Boolean)this.getValueExpression("empty")
                    .getValue(this.getFacesContext().getELContext());
        }
        
        // Check if the empty attribute value does not exist.
        else if (this.empty == null) {
            
            // Set the empty attribute value to the default.
            this.empty = DEFAULT_EMPTY;
        }
        
        return this.empty;
    }
     
    /**
     * Get the end date attribute value.
     * 
     * @return  the end date attribute value.
     */
    private Date getEndDate() {
        
        // Check if the attribute is set using a value expression binding.
        if (this.endDate == null && 
            this.getValueExpression("endDate") != null) {
            
            // Set the end date to the value expression binding.
            this.endDate = (Date)this.getValueExpression("endDate")
                    .getValue(this.getFacesContext().getELContext());
        }
        
        // Check if the end date does not exist.
        else if (this.endDate == null) {

            // Declare.
            Calendar endDateAsCalendar;

            // Set the end date as calendar to today.
            endDateAsCalendar = Calendar.getInstance();

            // Add 3 months to the end date as calendar.
            endDateAsCalendar.add(Calendar.MONTH, +3);

            // Get the end date.
            this.endDate = endDateAsCalendar.getTime();
        }
        
        return this.endDate;
    }
        
    /**
     * Get the epoch.
     * 
     * @return  the epoch.
     */
    private Date getEpoch() {
        
        // Declare.
        Date epoch;
        
        // Initialize.
        epoch = null;
        
        try {
            
            // Get the epoch.
            epoch = monthYearFormat.parse(EPOCH);
        }
        catch(ParseException e) {
            // Ignore.
        }
        
        return epoch;
    }
    
    /**
     * Get the start date attribute value.
     * 
     * @return  the start date attribute value.
     */
    private Date getStartDate() {
        
        // Check if the attribute is set using a value expression binding.
        if (this.startDate == null && 
            this.getValueExpression("startDate") != null) {
            
            // Set the start date to the value expression binding.
            this.startDate = (Date)this.getValueExpression("startDate")
                    .getValue(this.getFacesContext().getELContext());
        }
        
        // Check if the start date does not exist.
        else if (this.startDate == null) {
            
            // Set the start date to the epoch.
            this.startDate = this.getEpoch();
        }
        
        return this.startDate;
    }

    /**
     * Get the value.
     * 
     * @return  the value.
     */
    @Override
    public Object getValue() {

        // Declare.
        List<SelectItem> selectItems;
        
        // Initialize.
        selectItems = new ArrayList<SelectItem>();
        
        try {
            
            // Declare.
            int endMonth;
            int endYear;
            int startMonth;
            int startYear;

            // Get the start month and year.
            startMonth = Integer.parseInt(monthFormat.format(this.getStartDate()));
            startYear = Integer.parseInt(yearFormat.format(this.getStartDate()));
            
            // Get the end month and year.
            endMonth = Integer.parseInt(monthFormat.format(this.getEndDate()));
            endYear = Integer.parseInt(yearFormat.format(this.getEndDate()));

            // Check if empty attribute value is true.
            if (this.getEmpty().booleanValue()) {
                
                // Add an empty month/year to the select items.
                selectItems.add(new SelectItem(null, ""));
            }
                        
            // Loop through the years.
            for(int year = endYear; year >= startYear; year--) {

                // Loop through the months.
                for(int month = 12; month >= 1; month--) {

                    // Check if the month/year combination is within the
                    // start and end dates.
                    if (!(year == endYear && month > endMonth) &&
                        !(year == startYear && month < startMonth)) {
                        
                        // Declare.
                        String label;
                        SelectItem selectItem;
                        Date value;

                        // Get the value and label.
                        value = monthYearFormat.parse(month + "/" + year);
                        label = monthYearFormat.format(value);

                        // Set the select item.
                        selectItem = new SelectItem();
                        selectItem.setLabel(label);
                        selectItem.setValue(value);
                        
                        // Add the select item to the select items.
                        selectItems.add(selectItem); 
                    }
                }
            }
        }
        catch(ParseException e) {
            // Ignore.
        }

        return selectItems;
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
        this.empty = (Boolean)values[1];
        this.endDate = (Date)values[2];
        this.startDate = (Date)values[3];
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
        values = new Object[4];

        // Save the state.
        values[0] = super.saveState(context);
        values[1] = this.empty;
        values[2] = this.endDate;
        values[3] = this.startDate;

        return values;
    }
        
    /**
     * Set the empty attribute value.
     * 
     * @param  empty  the empty attribute value.
     */
    public void setEmpty(Boolean empty) {
        this.empty = empty;
    }
    /**
     * Set the end date attribute value.
     * 
     * @param  endDate  the end date attribute value.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * Set the start date attribute value.
     * 
     * @param  startDate  the start date attribute value.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * Set the value.
     * 
     * @param  value  the value.
     */
    @Override
    public void setValue(Object value) {
        // Disable this tag attribute.
    }
}
