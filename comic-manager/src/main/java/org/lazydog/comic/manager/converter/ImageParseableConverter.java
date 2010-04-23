package org.lazydog.comic.manager.converter;

import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


/**
 * Image parseable converter.
 * 
 * @author  Ron Rickard
 */
public class ImageParseableConverter 
       implements Converter {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS");
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

        try {
            
            // Check if the object as a string exists.
            if (objectAsString != null &&
                !objectAsString.equals("")) {

                // Declare.
                ImageType imageType;
                String[] objectArray;

                objectArray = objectAsString.split(DELIMITER);

                // Set the image.
                image = new Image();
                image.setCreateTime(
                        (objectArray[0].equals("")) ? null : 
                        DATE_FORMAT.parse(objectArray[0]));
                image.setFileName(
                        (objectArray[1].equals("")) ? null :
                        objectArray[1]);
                image.setId(Integer.parseInt(objectArray[2]));
                image.setLabel(
                        (objectArray[3].equals("")) ? null :
                        objectArray[3]);
                image.setModifyTime(
                        (objectArray[4].equals("")) ? null :
                        DATE_FORMAT.parse(objectArray[4]));

                // Check if the image type exists.
                if (objectArray.length > 5) {

                    // Set the image type.
                    imageType = new ImageType();
                    imageType.setCreateTime(
                            (objectArray[5].equals("")) ? null :
                            DATE_FORMAT.parse(objectArray[5]));
                    imageType.setDirectoryPath(
                            (objectArray[6].equals("")) ? null :
                            objectArray[6]);
                    imageType.setId(Integer.parseInt(objectArray[7]));
                    imageType.setModifyTime(
                            (objectArray[8].equals("")) ? null :
                            DATE_FORMAT.parse(objectArray[8]));
                    imageType.setValue(
                            (objectArray[9].equals("")) ? null :
                            objectArray[9]);
                    image.setType(imageType);
                }
            }
        }
        catch(ParseException e) {
            // Ignore.
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
                    (image.getCreateTime() == null) ? "" : 
                    DATE_FORMAT.format(image.getCreateTime()));
            imageAsStringBuffer.append(DELIMITER);
            imageAsStringBuffer.append(
                    (image.getFileName() == null) ? "" :
                    image.getFileName());
            imageAsStringBuffer.append(DELIMITER);
            imageAsStringBuffer.append(image.getId());
            imageAsStringBuffer.append(DELIMITER);
            imageAsStringBuffer.append(
                    (image.getLabel() == null) ? "" :
                    image.getLabel());
            imageAsStringBuffer.append(DELIMITER);
            imageAsStringBuffer.append(
                    (image.getModifyTime() == null) ? "" :
                    DATE_FORMAT.format(image.getModifyTime()));

            // Check if the image type exists.
            if (image.getType() != null) {
                
                // Append the image type to the string buffer.
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(
                        (image.getType().getCreateTime() == null) ? "" :
                        DATE_FORMAT.format(image.getType().getCreateTime()));
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(
                        (image.getType().getDirectoryPath() == null) ? "" :
                        image.getType().getDirectoryPath());
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(image.getType().getId());
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(
                        (image.getType().getModifyTime() == null) ? "" :
                        DATE_FORMAT.format(image.getType().getModifyTime()));
                imageAsStringBuffer.append(DELIMITER);
                imageAsStringBuffer.append(
                        (image.getType().getValue() == null) ? "" :
                        image.getType().getValue());
            }
        }
        
        return imageAsStringBuffer.toString();
    }
}
