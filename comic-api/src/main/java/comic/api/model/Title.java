package comic.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;


/**
 * Entity class used to represent a title.
 *
 * @author  Ron Rickard
 */
public class Title 
       extends Entity<Title>
       implements Comparable<Title>,
                  Serializable {

    // Declare.
    private List<Category> categories = new ArrayList<Category>();
    private List<Comic> comics = new ArrayList<Comic>();
    private Image image;
    @NotNull(message="Name is required.") 
    @Size(max=100, message="Name cannot contain more than 100 characters.")
    private String name;
    private Date publishEndDate;
    private Date publishStartDate;
    @Valid @NotNull(message="Publisher is required.")
    private List<Publisher> publishers = new ArrayList<Publisher>();
    @Valid @NotNull(message="Title type is required.")
    private TitleType type;
    @NotNull(message="Volume is required.")
    private Integer volume;
     
    /**
     * Constructor.
     */
    public Title() {

        super();
        this.setCategories(null);
        this.setComics(null);
        this.setImage(null);
        this.setName(null);
        this.setPublishEndDate(null);
        this.setPublishStartDate(null);
        this.setPublishers(null);
        this.setType(null);
        this.setVolume(null);
    }

    /**
     * Compare this object to the specified object.
     *
     * @param  object  the object to compare this object against.
     *
     * @return  the value 0 if this object is equal to the object;
     *          a value less than 0 if this object is less than the object;
     *          and a value greater than 0 if this object is greater than the
     *          object.
     */
    @Override
    public int compareTo(Title object) {
        
        // Declare.
        int lastCompare;
        String thatName;
        Date thatPublishEndDate;
        Date thatPublishStartDate;
        List<Publisher> thatPublishers;
        TitleType thatType;
        Integer thatVolume;
        String thisName;
        Date thisPublishEndDate;
        Date thisPublishStartDate;
        List<Publisher> thisPublishers;
        TitleType thisType;
        Integer thisVolume;

        // Initialize.
        lastCompare = 0;
        thatName = (object.getName() == null) ? "" : object.getName();
        thatPublishEndDate = (object.getPublishEndDate() == null) ? this.getEpoch() : object.getPublishEndDate();
        thatPublishStartDate = (object.getPublishStartDate() == null) ? this.getEpoch() : object.getPublishStartDate();
        thatPublishers = (object.getPublishers() == null) ? new ArrayList<Publisher>() : object.getPublishers();
        thatType = (object.getType() == null) ? new TitleType() : object.getType();
        thatVolume = object.getVolume();
        thisName = (this.getName() == null) ? "" : this.getName();
        thisPublishEndDate = (this.getPublishEndDate() == null) ? this.getEpoch() : this.getPublishEndDate();
        thisPublishStartDate = (this.getPublishStartDate() == null) ? this.getEpoch() : this.getPublishStartDate();
        thisPublishers = (this.getPublishers() == null) ? new ArrayList<Publisher>() : this.getPublishers();
        thisType = (this.getType() == null) ? new TitleType() : this.getType();
        thisVolume = this.getVolume();
        
        // Compare this object to the object.
        lastCompare = thisName.compareTo(thatName);
        if (lastCompare == 0) {
            if (thisPublishers.size() < thatPublishers.size()) {
                lastCompare = -1;
            }
            else if (thisPublishers.size() > thatPublishers.size()) {
                lastCompare = 1;
            }
            else {
                Collections.sort(thisPublishers);
                Collections.sort(thatPublishers);
                for(int x = 0; x < thisPublishers.size(); x++) {
                    lastCompare = (lastCompare != 0) ? lastCompare : thisPublishers.get(x).compareTo(thatPublishers.get(x));
                }
            }
        }
        lastCompare = (lastCompare != 0) ? lastCompare : thisVolume.compareTo(thatVolume);
        lastCompare = (lastCompare != 0) ? lastCompare : thisType.compareTo(thatType);
        lastCompare = (lastCompare != 0) ? lastCompare : thisPublishStartDate.compareTo(thatPublishStartDate);
        lastCompare = (lastCompare != 0) ? lastCompare : thisPublishEndDate.compareTo(thatPublishEndDate);
        
        return lastCompare;
    }
                                    
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Title copy() {
        
        // Declare.
        Title copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setCategories(this.getCategories());
        copy.setComics(this.getComics());
        copy.setImage(this.getImage());
        copy.setName(this.getName());
        copy.setPublishEndDate(this.getPublishEndDate());
        copy.setPublishStartDate(this.getPublishStartDate());
        copy.setPublishers(this.getPublishers());
        copy.setType(this.getType());
        copy.setVolume(this.getVolume());

        return copy;
    }
    
    /**
     * Compare this object to the specified object.
     *
     * @param  object  the object to compare this object against.
     *
     * @return  true if the objects are equal; false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        
        // Declare.
        boolean equals;

        // Initialize.
        equals = false;
        
        // Check if the object is an instance of this class
        // and is equal to this object.
        if (object instanceof Title &&
            this.compareTo((Title)object) == 0) {
            equals = true;
        }

        
        return equals;
    }
     
    /**
     * Get the categories.
     *
     * @return  the categories.
     */
    public List<Category> getCategories() {
        return this.categories;
    }
           
    /**
     * Get the comics.
     *
     * @return  the comics.
     */
    public List<Comic> getComics() {
        return this.comics;
    }
 
    /**
     * Get the image.
     *
     * @return  the image.
     */
    public Image getImage() {
        return this.image;
    }
  
    /**
     * Get the name.
     *
     * @return  the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the publish end date.
     *
     * @return  the publish end date.
     */
    public Date getPublishEndDate() {
        return this.publishEndDate;
    }
   
    /**
     * Get the publish start date.
     *
     * @return  the publish start date.
     */
    public Date getPublishStartDate() {
        return this.publishStartDate;
    }
                          
    /**
     * Get the publishers.
     *
     * @return  the publishers.
     */
    public List<Publisher> getPublishers() {
        return this.publishers;
    }
    
    /**
     * Get the type.
     *
     * @return  the type.
     */
    public TitleType getType() {
        return this.type;
    }
        
    /**
     * Get the volume.
     *
     * @return  the volume.
     */
    public Integer getVolume() {
        return this.volume;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        String thisName;
        Date thisPublishEndDate;
        Date thisPublishStartDate;
        List<Publisher> thisPublishers;      
        TitleType thisType;
        Integer thisVolume;
        
        // Initialize.
        thisName = (this.getName() == null) ? "" : this.getName();
        thisPublishEndDate = (this.getPublishEndDate() == null) ? this.getEpoch() : this.getPublishEndDate();
        thisPublishStartDate = (this.getPublishStartDate() == null) ? this.getEpoch() : this.getPublishStartDate();
        thisPublishers = (this.getPublishers() == null) ? new ArrayList<Publisher>() : this.getPublishers();
        thisType = (this.getType() == null) ? new TitleType() : this.getType();
        thisVolume = this.getVolume();
        
        return thisName.hashCode()*7^5
             + thisPublishers.hashCode()*7^4
             + thisVolume.hashCode()*7^3
             + thisType.hashCode()*7^2
             + thisPublishStartDate.hashCode()*7
             + thisPublishEndDate.hashCode();
    }
        
    /**
     * Set the categories.
     *
     * @param  categories  the categories.
     */
    public void setCategories(List<Category> categories) {
        
        if (categories == null) {
            this.categories = new ArrayList<Category>();
        }
        else {
            this.categories = categories;
        }
    }
                  
    /**
     * Set the comics.
     *
     * @param  comics  the comics.
     */
    public void setComics(List<Comic> comics) {
        
        if (comics == null) {
            this.comics = new ArrayList<Comic>();
        }
        else {
            this.comics = comics;
        }
    }
           
    /**
     * Set the image.
     *
     * @param  image  the image.
     */
    public void setImage(Image image) {
        this.image = image;
    }
    
    /**
     * Set the name.
     *
     * @param  name  the name.
     */
    public void setName(String name) {
        this.name = this.trimmed(name);
    }
     
    /**
     * Set the publish end date.
     *
     * @param  publishEndDate  the publish end date.
     */
    public void setPublishEndDate(Date publishEndDate) {
        
        if (publishEndDate == null) {
            this.publishEndDate = null;
        }
        else {
            
            // Declare.
            Calendar publishEndDateAsCalendar;

            // Initialize.
            publishEndDateAsCalendar = Calendar.getInstance();
            
            // Preserve the month and year only.
            publishEndDateAsCalendar.setTime(publishEndDate);
            publishEndDateAsCalendar.set(Calendar.DAY_OF_MONTH, 1);
            publishEndDateAsCalendar.set(Calendar.HOUR_OF_DAY, 0);
            publishEndDateAsCalendar.set(Calendar.MINUTE, 0);
            publishEndDateAsCalendar.set(Calendar.SECOND, 0);
            publishEndDateAsCalendar.set(Calendar.MILLISECOND, 0);
            
            this.publishEndDate = publishEndDateAsCalendar.getTime();
        }
    }
                               
    /**
     * Set the publishers.
     *
     * @param  publishers  the publishers.
     */
    public void setPublishers(List<Publisher> publishers) {
        
        if (publishers == null) {
            this.publishers = new ArrayList<Publisher>();
        }
        else {
            this.publishers = publishers;
        }
    }
      
    /**
     * Set the publish start date.
     *
     * @param  publishStartDate  the publish start date.
     */
    public void setPublishStartDate(Date publishStartDate) {
        
        if (publishStartDate == null) {
            this.publishStartDate = null;
        }
        else {
            
            // Declare.
            Calendar publishStartDateAsCalendar;

            // Initialize.
            publishStartDateAsCalendar = Calendar.getInstance();
            
            // Preserve the month and year only.
            publishStartDateAsCalendar.setTime(publishStartDate);
            publishStartDateAsCalendar.set(Calendar.DAY_OF_MONTH, 1);
            publishStartDateAsCalendar.set(Calendar.HOUR_OF_DAY, 0);
            publishStartDateAsCalendar.set(Calendar.MINUTE, 0);
            publishStartDateAsCalendar.set(Calendar.SECOND, 0);
            publishStartDateAsCalendar.set(Calendar.MILLISECOND, 0);
            
            this.publishStartDate = publishStartDateAsCalendar.getTime();
        }
    }
    
    /**
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(TitleType type) {
        
        this.type = type;
    }
       
    /**
     * Set the volume.
     *
     * @param  volume  the volume.
     */
    public void setVolume(Integer volume) {
        
        if (volume == null || volume.intValue() < 1) {
            this.volume = new Integer(1);
        }
        else {
            this.volume = volume;
        }
    }
    
    /**
     * Get this object as a String.
     *
     * @return  this object as a String.
     */
    @Override
    public String toString() {

        // Declare.
        StringBuffer toString;
        
        // Initialize.
        toString = new StringBuffer();
        
        toString.append("Title [");
        toString.append("categories = ").append(this.getCategories());
        toString.append(", image = ").append(this.getImage());
        toString.append(", name = ").append(this.getName());
        toString.append(", publishEndDate = ").append(this.getPublishEndDate());
        toString.append(", publishStartDate = ").append(this.getPublishStartDate());
        toString.append(", publishers = ").append(this.getPublishers());
        toString.append(", type = ").append(this.getType());
        toString.append(", volume = ").append(this.getVolume());
        toString.append("]");
        
        return toString.toString();
    }
}
