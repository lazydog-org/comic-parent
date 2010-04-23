package org.lazydog.comic.manager.converter;

import org.lazydog.comic.model.Publisher;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 * Publisher list converter.
 * 
 * @author  Ron Rickard
 */
public class PublisherListConverter 
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
     * Get the publisher list as a string.
     * 
     * @param  context        the faces context.
     * @param  component      the UI component.
     * @param  publisherList  the publisher list.
     * 
     * @return  the publisher list as a string.
     */
    @Override
    @SuppressWarnings("unchecked")
    public String getAsString(FacesContext context, 
                              UIComponent component, 
                              Object publisherList) {
        
        // Declare.
        StringBuffer publisherListAsStringBuffer;
        
        // Initialize.
        publisherListAsStringBuffer = new StringBuffer();
      
        // Check if the object is an instance of the list class.
        if (publisherList instanceof List) {
            
            // Loop through the publisher list.
            for (Publisher publisher : (List<Publisher>)publisherList) {

                // Check if the publisher list as a string buffer 
                // does not have characters.
                if (publisherListAsStringBuffer.length() == 0) {
                    publisherListAsStringBuffer.append(publisher.getName());
                }
                else {
                    publisherListAsStringBuffer.append("/")
                            .append(publisher.getName());
                }
            }
        }
        
        return publisherListAsStringBuffer.toString();
    }
}
