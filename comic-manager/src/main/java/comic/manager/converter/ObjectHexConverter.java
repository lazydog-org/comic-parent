package comic.manager.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.apache.commons.codec.binary.Hex;


/**
 * Object hex converter.
 * 
 * @author  Ron Rickard
 */
public class ObjectHexConverter 
       implements Converter {

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
        Object object;
        
        // Initialize.
        object = null;

        try {

            // Check if the object as a string exists.
            if (objectAsString != null &&
                !objectAsString.equals("")) {
                
                // Declare.
                ByteArrayInputStream byteArrayInputStream;
                byte[] bytes;
                ObjectInputStream objectInputStream;

                // Decode the hex string to a byte array.
                bytes = Hex.decodeHex(objectAsString.toCharArray());
                
                // Get the byte array as an object.
                byteArrayInputStream = new ByteArrayInputStream(bytes);
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                object = objectInputStream.readObject();
                
                // Close the stream.
                objectInputStream.close();
            }
        }
        catch(Exception e) {
            // Ignore.
        }

        return object;
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

        try {

            // Check if the object exists.
            if (object != null) {

                // Declare.
                ByteArrayOutputStream byteArrayOutputStream;
                byte[] bytes;
                ObjectOutputStream objectOutputStream;

                // Get the object as a byte array.
                byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(object);
                bytes = byteArrayOutputStream.toByteArray();

                // Encode the byte array to a hex string.
                objectAsString = new String(Hex.encodeHex(bytes));

                // Close the stream.
                objectOutputStream.close();
            }
        }
        catch(IOException e) {
            // Ignore.
        }

        return objectAsString;
    }
}
