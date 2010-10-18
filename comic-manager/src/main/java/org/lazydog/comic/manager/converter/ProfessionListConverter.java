package org.lazydog.comic.manager.converter;

import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.lazydog.comic.model.Profession;


/**
 * Profession list converter.
 * 
 * @author  Ron Rickard
 */
public class ProfessionListConverter
       implements Converter {

    /**
     * This method is not implemented.
     * 
     * @param  context    the faces context.
     * @param  component  the UI component.
     * @param  noString   no string.
     * 
     * @return  null.
     */
    @Override
    public Object getAsObject(FacesContext context, 
                              UIComponent component, 
                              String noString) {
        return null;
    }
    
    /**
     * Get the profession list as a string.
     * 
     * @param  context         the faces context.
     * @param  component       the UI component.
     * @param  professionList  the profession list.
     * 
     * @return  the profession list as a string.
     */
    @Override
    @SuppressWarnings("unchecked")
    public String getAsString(FacesContext context, 
                              UIComponent component, 
                              Object professionList) {
        
        // Declare.
        StringBuffer professionListAsStringBuffer;
        
        // Initialize.
        professionListAsStringBuffer = new StringBuffer();
      
        // Check if the object is an instance of the list class.
        if (professionList instanceof List) {
            
            // Loop through the profession list.
            for (Profession profession : (List<Profession>)professionList) {

                // Check if the profession list as a string buffer
                // does not have characters.
                if (professionListAsStringBuffer.length() == 0) {
                    professionListAsStringBuffer.append(profession.getValue());
                }
                else {
                    professionListAsStringBuffer.append("/")
                            .append(profession.getValue());
                }
            }
        }
        
        return professionListAsStringBuffer.toString();
    }
}
