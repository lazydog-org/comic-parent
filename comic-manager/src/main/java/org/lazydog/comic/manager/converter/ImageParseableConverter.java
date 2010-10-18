package org.lazydog.comic.manager.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;


/**
 * Image parseable converter.
 * 
 * @author  Ron Rickard
 */
public class ImageParseableConverter 
       implements Converter {

    private static final String DELIMITER = ";";
    
    /**
     * Get the object.
     * 
     * @param  context         the faces context.
     * @param  component       the UI component.
     * @param  objectAsString  the object as a String.
     * 
     * @return  the image.
     */
    @Override
    public Object getAsObject(FacesContext context, 
                              UIComponent component, 
                              String objectAsString) {
        
        // Declare.
        Image image;
        
        // Initialize.
        image = null;

        // Check if the object as a string exists.
        if (objectAsString != null &&
            !objectAsString.equals("")) {

            // Declare.
            ImageType imageType;
            String[] objectArray;

            objectArray = objectAsString.split(DELIMITER);

            // Set the image.
            image = new Image();
            image.setFileName(
                    (objectArray[0].equals("")) ? null :
                    objectArray[0]);
            image.setId(Integer.parseInt(objectArray[1]));
            image.setLabel(
                    (objectArray[2].equals("")) ? null :
                    objectArray[2]);

            // Check if the image type exists.
            if (objectArray.length > 3) {

                // Set the image type.
                imageType = new ImageType();
                imageType.setDirectoryPath(
                        (objectArray[3].equals("")) ? null :
                        objectArray[3]);
                imageType.setId(Integer.parseInt(objectArray[4]));
                imageType.setValue(
                        (objectArray[5].equals("")) ? null :
                        objectArray[5]);
                image.setType(imageType);
            }
        }
        
        return image;
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
        StringBuffer imageAsStringBuffer;
        
        // Initialize.
        imageAsStringBuffer = new StringBuffer();
      
        // Check if the object is an instance of the image class
        // and the ID exists.
        if (object instanceof Image &&
            ((Image)object).getId() != null) {
            
            // Declare.
            Image image;
            
            // Cast the object to a image.
            image = (Image)object;
            
            // Get the image as a string buffer.
            imageAsStringBuffer.append(
                    (image.getFileName() == null) ? "" :
                    image.getFileName());
            imageAsStringBuffer.append(DELIMITER);
            imageAsStringBuffer.append(image.getId());
            imageAsStringBuffer.append(DELIMITER);
            imageAsStringBuffer.append(
                    (image.getLabel() == null) ? "" :
                    image.getLabel());

            // Check if the image type exists.
            if (image.getType() != null) {
                
                // Append the image type to the string buffer.
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(
                        (image.getType().getDirectoryPath() == null) ? "" :
                        image.getType().getDirectoryPath());
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(image.getType().getId());
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(
                        (image.getType().getValue() == null) ? "" :
                        image.getType().getValue());
            }
        }
        
        return imageAsStringBuffer.toString();
    }
}
