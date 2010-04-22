package comic.manager.converter;

import java.util.TimeZone;
import javax.faces.convert.DateTimeConverter;


/**
 * Custom date/time converter.
 * 
 * @author  Ron Rickard
 */
public class CustomDateTimeConverter 
       extends DateTimeConverter {

    /**
     * Constructor.
     */
    public CustomDateTimeConverter() {
        
        super();
        
        // Set the time zone.
        this.setTimeZone(TimeZone.getDefault());
        
        // Set the date/time pattern.
        this.setPattern("MM/dd/yyyy HH:mm:ss a");
    }
}
