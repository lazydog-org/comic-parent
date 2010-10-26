package org.lazydog.comic.manager.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
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
                InflaterInputStream inflaterInputStream;
                ObjectInputStream objectInputStream;

                // Decode the hex string to a byte array.
                bytes = Hex.decodeHex(objectAsString.toCharArray());
                
                // Get the byte array as an object.
                byteArrayInputStream = new ByteArrayInputStream(bytes);
                inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
                objectInputStream = new ObjectInputStream(inflaterInputStream);
                object = objectInputStream.readObject();
                
                // Close the stream.
                objectInputStream.close();
            }
        }
        catch(Exception e) {
e.printStackTrace();
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
                DeflaterOutputStream deflaterOutputStream;
                ObjectOutputStream objectOutputStream;

                // Get the object as a byte array.
                byteArrayOutputStream = new ByteArrayOutputStream(512);
                deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(Deflater.BEST_COMPRESSION));
                objectOutputStream = new ObjectOutputStream(deflaterOutputStream);
                objectOutputStream.writeObject(object);

                // Flush and close the object output stream.
                objectOutputStream.flush();
                objectOutputStream.close();

                // Finish compression and close the deflater output stream.
                deflaterOutputStream.finish();
                deflaterOutputStream.close();

                bytes = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();

                // Encode the byte array to a hex string.
                objectAsString = new String(Hex.encodeHex(bytes));
            }
        }
        catch(IOException e) {
e.printStackTrace();
        }

        return objectAsString;
    }
}
