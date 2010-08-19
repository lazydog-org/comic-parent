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

    // Declare.
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
    
    /**
     * Constructor.
     */
    public UserPreference() {
        
        super();
        this.setComicGrade(null);
        this.setComicType(null);
        this.setDistribution(null);
        this.setImageType(null);
        this.setMinimumPublishDate(null);
        this.setPublisher(null);
        this.setTitleType(null);
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
    public int compareTo(UserPreference object) {

        // Declare.
        int lastCompare;
        ComicGrade thatComicGrade;
        ComicType thatComicType;
        User thatCreateUser;
        Distribution thatDistribution;
        ImageType thatImageType;
        Date thatMinimumPublishDate;
        Publisher thatPublisher;
        TitleType thatTitleType;
        ComicGrade thisComicGrade;
        ComicType thisComicType;
        User thisCreateUser;
        Distribution thisDistribution;
        ImageType thisImageType;
        Date thisMinimumPublishDate;
        Publisher thisPublisher;
        TitleType thisTitleType;

        // Initialize.
        lastCompare = 0;
        thatComicGrade = (object.getComicGrade() == null) ? new ComicGrade() : object.getComicGrade();
        thatComicType = (object.getComicType() == null) ? new ComicType() : object.getComicType();
        thatCreateUser = (object.getCreateUser() == null) ? new User() : object.getCreateUser();
        thatDistribution = (object.getDistribution() == null) ? new Distribution() : object.getDistribution();
        thatImageType = (object.getImageType() == null) ? new ImageType() : object.getImageType();
        thatMinimumPublishDate = (object.getMinimumPublishDate() == null) ? getEpoch() : object.getMinimumPublishDate();
        thatPublisher = (object.getPublisher() == null) ? new Publisher() : object.getPublisher();
        thatTitleType = (object.getTitleType() == null) ? new TitleType() : object.getTitleType();
        thisComicGrade = (this.getComicGrade() == null) ? new ComicGrade() : this.getComicGrade();
        thisComicType = (this.getComicType() == null) ? new ComicType() : this.getComicType();
        thisCreateUser = (this.getCreateUser() == null) ? new User() : this.getCreateUser();
        thisDistribution = (this.getDistribution() == null) ? new Distribution() : this.getDistribution();
        thisImageType = (this.getImageType() == null) ? new ImageType() : this.getImageType();
        thisMinimumPublishDate = (this.getMinimumPublishDate() == null) ? getEpoch() : this.getMinimumPublishDate();
        thisPublisher = (this.getPublisher() == null) ? new Publisher() : this.getPublisher();
        thisTitleType = (this.getTitleType() == null) ? new TitleType() : this.getTitleType();
        

        // Compare this object to the object.
        lastCompare = thisCreateUser.compareTo(thatCreateUser);
        lastCompare = (lastCompare != 0) ? lastCompare : thisComicGrade.compareTo(thatComicGrade);
        lastCompare = (lastCompare != 0) ? lastCompare : thisComicType.compareTo(thatComicType);
        lastCompare = (lastCompare != 0) ? lastCompare : thisDistribution.compareTo(thatDistribution);
        lastCompare = (lastCompare != 0) ? lastCompare : thisImageType.compareTo(thatImageType);
        lastCompare = (lastCompare != 0) ? lastCompare : thisMinimumPublishDate.compareTo(thatMinimumPublishDate);
        lastCompare = (lastCompare != 0) ? lastCompare : thisPublisher.compareTo(thatPublisher);
        lastCompare = (lastCompare != 0) ? lastCompare : thisTitleType.compareTo(thatTitleType);
        

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
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {

        // Declare.
        ComicGrade thisComicGrade;
        ComicType  thisComicType;
        User thisCreateUser;
        Distribution thisDistribution;
        ImageType thisImageType;
        Date thisMinimumPublishDate;
        Publisher thisPublisher;
        TitleType thisTitleType;

        // Initialize.
        thisComicGrade = (this.getComicGrade() == null) ? new ComicGrade() : this.getComicGrade();
        thisComicType = (this.getComicType() == null) ? new ComicType() : this.getComicType();
        thisCreateUser = (this.getCreateUser() == null) ? new User() : this.getCreateUser();
        thisDistribution = (this.getDistribution() == null) ? new Distribution() : this.getDistribution();
        thisImageType = (this.getImageType() == null) ? new ImageType() : this.getImageType();
        thisMinimumPublishDate = (this.getMinimumPublishDate() == null) ? getEpoch() : this.getMinimumPublishDate();
        thisPublisher = (this.getPublisher() == null) ? new Publisher() : this.getPublisher();
        thisTitleType = (this.getTitleType() == null) ? new TitleType() : this.getTitleType();

        return thisCreateUser.hashCode()*7^7
             + thisComicGrade.hashCode()*7^6
             + thisComicType.hashCode()*7^5
             + thisDistribution.hashCode()*7^4
             + thisImageType.hashCode()*7^3
             + thisMinimumPublishDate.hashCode()*7^2
             + thisPublisher.hashCode()*7
             + thisTitleType.hashCode();
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
        toString.append("]");
        
        return toString.toString();
    }
}
