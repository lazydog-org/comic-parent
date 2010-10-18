package org.lazydog.comic.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;


/**
 * Entity class used to represent user preference.
 *
 * @author  Ron Rickard
 */
public class UserPreference 
       extends Entity<UserPreference>
       implements Comparable<UserPreference>,
                  Serializable {

    private static final long serialVersionUID = 1L;

    @Valid @NotNull(message="Comic grade is required.")
    private ComicGrade comicGrade;
    @Valid @NotNull(message="Comic type is required.")
    private ComicType comicType;
    @Valid @NotNull(message="Distribution is required.")
    private Distribution distribution;
    @Valid @NotNull(message="Image type is required.")
    private ImageType imageType;
    @NotNull(message="Minimum publish date is required.")
    private Date minimumPublishDate;
    @Valid @NotNull(message="Publisher is required.")
    private Publisher publisher;
    @Valid @NotNull(message="Title type is required.")
    private TitleType titleType;
    @NotNull(message="UUID is required.")
    private String uuid;

    /**
     * Compare this object to the specified object.
     *
     * @param  that  the object to compare this object against.
     *
     * @return  the value 0 if this object is equal to the object;
     *          a value less than 0 if this object is less than the object;
     *          and a value greater than 0 if this object is greater than the
     *          object.
     */
    @Override
    public int compareTo(UserPreference that) {

        // Declare.
        int lastCompare;
        String thatUuid;
        String thisUuid;

        // Initialize.
        lastCompare = 0;
        thatUuid = replaceNull(that.getUuid(), nilUuid());
        thisUuid = replaceNull(this.getUuid(), nilUuid());

        // Compare this object to the object.
        lastCompare = thisUuid.compareTo(thatUuid);

        return lastCompare;
    }

    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public UserPreference copy() {
        
        // Declare.
        UserPreference copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setComicGrade(this.getComicGrade());
        copy.setComicType(this.getComicType());
        copy.setDistribution(this.getDistribution());
        copy.setImageType(this.getImageType());
        copy.setMinimumPublishDate(this.getMinimumPublishDate());
        copy.setPublisher(this.getPublisher());
        copy.setTitleType(this.getTitleType());
        copy.setUuid(this.getUuid());

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
        if (object instanceof UserPreference &&
            this.compareTo((UserPreference)object) == 0) {
            equals = true;
        }

        return equals;
    }

    /**
     * Get the comic grade.
     *
     * @return  the comic grade.
     */
    public ComicGrade getComicGrade() {
        return this.comicGrade;
    }

    /**
     * Get the comic type.
     *
     * @return  the comic type.
     */
    public ComicType getComicType() {
        return this.comicType;
    }

    /**
     * Get the distribution.
     *
     * @return  the distribution.
     */
    public Distribution getDistribution() {
        return this.distribution;
    }

    /**
     * Get the image type.
     *
     * @return  the image type.
     */
    public ImageType getImageType() {
        return this.imageType;
    }

    /**
     * Get the minimum publish date.
     *
     * @return  the minimum publish date.
     */
    public Date getMinimumPublishDate() {
        return this.minimumPublishDate;
    }
       
    /**
     * Get the publisher.
     *
     * @return  the publisher.
     */
    public Publisher getPublisher() {
        return this.publisher;
    }
    
    /**
     * Get the title type.
     *
     * @return  the title type.
     */
    public TitleType getTitleType() {
        return this.titleType;
    }

    /**
     * Get the universally unique identifier (UUID).
     *
     * @return  the universally unique identifier (UUID).
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {

        // Declare.
        String thisUuid;

        // Initialize.
        thisUuid = replaceNull(this.getUuid(), nilUuid());

        return thisUuid.hashCode();
    }

    /**
     * Set the comic grade.
     *
     * @param  comicGrade  the comic grade.
     */
    public void setComicGrade(ComicGrade comicGrade) {
        this.comicGrade = comicGrade;
    }
  
    /**
     * Set the comic type.
     *
     * @param  comicType  the comic type.
     */
    public void setComicType(ComicType comicType) {
        this.comicType = comicType;
    }

    /**
     * Set the distribution.
     *
     * @param  distribution  the distribution.
     */
    public void setDistribution(Distribution distribution) {
        
        this.distribution = distribution;
    }

    /**
     * Set the image type.
     *
     * @param  imageType  the image type.
     */
    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }
 
    /**
     * Set the minimum publish date.
     *
     * @param  minimumPublishDate  the minimum publish date.
     */
    public void setMinimumPublishDate(Date minimumPublishDate) {
        
        if (minimumPublishDate == null) {
            this.minimumPublishDate = null;
        }
        else {
            
            // Declare.
            Calendar minimumPublishDateAsCalendar;

            // Initialize.
            minimumPublishDateAsCalendar = Calendar.getInstance();
            
            // Preserve the month and year only.
            minimumPublishDateAsCalendar.setTime(minimumPublishDate);
            minimumPublishDateAsCalendar.set(Calendar.DAY_OF_MONTH, 1);
            minimumPublishDateAsCalendar.set(Calendar.HOUR_OF_DAY, 0);
            minimumPublishDateAsCalendar.set(Calendar.MINUTE, 0);
            minimumPublishDateAsCalendar.set(Calendar.SECOND, 0);
            minimumPublishDateAsCalendar.set(Calendar.MILLISECOND, 0);
            
            this.minimumPublishDate = minimumPublishDateAsCalendar.getTime();
        }
    }
          
    /**
     * Set the publisher.
     *
     * @param  publisher  the publisher.
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    /**
     * Set the title type.
     *
     * @param  titleType  the title type.
     */
    public void setTitleType(TitleType titleType) {
        this.titleType = titleType;
    }

    /**
     * Set the universally unique identifier (UUID).
     *
     * @param  uuid  the universally unique identifier (UUID).
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
        
        toString.append("UserPreference [");
        toString.append("comicGrade = ").append(this.getComicGrade());
        toString.append(", comicType = ").append(this.getComicType());
        toString.append(", distribution = ").append(this.getDistribution());
        toString.append(", imageType = ").append(this.getImageType());
        toString.append(", minimumPublishDate = ").append(this.getMinimumPublishDate());
        toString.append(", publisher = ").append(this.getPublisher());
        toString.append(", titleType = ").append(this.getTitleType());
        toString.append(", uuid = ").append(this.getUuid());
        toString.append("]");
        
        return toString.toString();
    }
}
