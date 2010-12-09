package org.lazydog.comic.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 * Month/year converter.
 * 
 * @author  Ron Rickard
 */
public class MonthYearConverter 
       implements Converter {

    private static final DateFormat MONTH_YEAR_FORMAT 
            = new SimpleDateFormat("MM/yyyy");
    
    /**
     * Get the object.
     * 
     * @param  context         the faces context.
     * @param  component       the UI component.
     * @param  objectAsString  the object as a string.
     * 
     * @return  the object.
     */
    @Override
    public Object getAsObject(FacesContext context, 
                              UIComponent component, 
                              String objectAsString) {
        
        // Declare.
        Date monthYear;
        
        // Initialize.
        monthYear = null;
        
        try {
            
            // Check if the object as a string exists.
            if (objectAsString != null &&
                !objectAsString.equals("")) {

                // Get the month/year.
                monthYear = MONTH_YEAR_FORMAT.parse(objectAsString);
            }
        }
        catch(ParseException e) {
            // Ignore.
        }
        
        return monthYear;
    }
    
    /**
     * Get the object as a string.
     * 
     * @param  context    the faces context.
     * @param  component  the UI component.
     * @param  object     the object.
     * 
     * @return  the object as a string.
     */
    @Override
    public String getAsString(FacesContext context, 
                              UIComponent component, 
                              Object object) {    
        
        // Declare.
        String objectAsString;
        
        // Initialize.
        objectAsString = null;
      
        // Check if the object is an instance of the date class.
        if (object instanceof Date) {

            // Get the month/year as a string.
            objectAsString = MONTH_YEAR_FORMAT.format((Date)object);
        }
        
        return objectAsString;
    }
}
