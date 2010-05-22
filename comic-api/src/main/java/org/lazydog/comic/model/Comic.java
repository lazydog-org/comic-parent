package org.lazydog.comic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.Valid;


/**
 * Entity class used to represent a comic.
 *
 * @author  Ron Rickard
 */
public class Comic 
       extends Entity<Comic>
       implements Comparable<Comic>,
                  Serializable {

    // Declare.
    private List<ComicCharacter> characters = new ArrayList<ComicCharacter>();
    private Double coverPrice;
    private List<Creator> creators = new ArrayList<Creator>();
    private String description;
    @Valid @NotNull(message="Distribution is required.")
    private Distribution distribution;
    private Image image;
    private Integer number;
    @NotNull(message="Print is required.") 
    private Integer print;
    private Date publishDate;
    @Valid @NotNull(message="Title is required.")
    private Title title;
    private List<Trait> traits = new ArrayList<Trait>();
    @Valid @NotNull(message="Comic type is required.")
    private ComicType type;
    @NotNull(message="Variant is required.") 
    @Size(max=1, message="Variant cannot contain more than 1 character.")
    private String variant;
    
    /**
     * Constructor.
     */
    public Comic() {
        
        super();
        this.setCharacters(null);
        this.setCoverPrice(null);
        this.setCreators(null);
        this.setDescription(null);
        this.setDistribution(null);
        this.setImage(null);
        this.setNumber(null);
        this.setPrint(null);
        this.setPublishDate(null);
        this.setTitle(null);
        this.setTraits(null);
        this.setType(null);
        this.setVariant(null);
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
    public int compareTo(Comic object) {
        
        // Declare.
        int lastCompare;
        Double thatCoverPrice;
        String thatDescription;
        Distribution thatDistribution;
        Integer thatNumber;
        Integer thatPrint;
        Date thatPublishDate;
        Title thatTitle;
        ComicType thatType;
        String thatVariant;
        Double thisCoverPrice;
        String thisDescription;
        Distribution thisDistribution;
        Integer thisNumber;
        Integer thisPrint;
        Date thisPublishDate;
        Title thisTitle;
        ComicType thisType;
        String thisVariant;
        
        // Initialize.
        lastCompare = 0;
        thatCoverPrice = object.getCoverPrice();
        thatDescription = (object.getDescription() == null) ? "" : object.getDescription();
        thatDistribution = (object.getDistribution() == null) ? new Distribution() : object.getDistribution();
        thatNumber = (object.getNumber() == null) ? new Integer(0) : object.getNumber();
        thatPrint = object.getPrint();
        thatPublishDate = (object.getPublishDate() == null) ? this.getEpoch() : object.getPublishDate();
        thatTitle = (object.getTitle() == null) ? new Title() : object.getTitle();
        thatType = (object.getType() == null) ? new ComicType() : object.getType();
        thatVariant = (object.getVariant() == null) ? "" : object.getVariant();
        thisCoverPrice = this.getCoverPrice();
        thisDescription = (this.getDescription() == null) ? "" : this.getDescription();
        thisDistribution = (this.getDistribution() == null) ? new Distribution() : this.getDistribution();
        thisNumber = (this.getNumber() == null) ? new Integer(0) : this.getNumber();
        thisPrint = this.getPrint();
        thisPublishDate = (this.getPublishDate() == null) ? this.getEpoch() : this.getPublishDate();
        thisTitle = (this.getTitle() == null) ? new Title() : this.getTitle();
        thisType = (this.getType() == null) ? new ComicType() : this.getType();
        thisVariant = (this.getVariant() == null) ? "" : this.getVariant();
        
        // Compare this object to the object.
        lastCompare = thisTitle.compareTo(thatTitle);
        lastCompare = (lastCompare != 0) ? lastCompare : thisType.compareTo(thatType);
        lastCompare = (lastCompare != 0) ? lastCompare : thisNumber.compareTo(thatNumber);
        lastCompare = (lastCompare != 0) ? lastCompare : thisVariant.compareTo(thatVariant);
        lastCompare = (lastCompare != 0) ? lastCompare : thisPrint.compareTo(thatPrint);
        lastCompare = (lastCompare != 0) ? lastCompare : thisPublishDate.compareTo(thatPublishDate);
        lastCompare = (lastCompare != 0) ? lastCompare : thisDistribution.compareTo(thatDistribution);
        lastCompare = (lastCompare != 0) ? lastCompare : thisCoverPrice.compareTo(thatCoverPrice);
        lastCompare = (lastCompare != 0) ? lastCompare : thisDescription.compareTo(thatDescription);

        return lastCompare;
    }
        
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Comic copy() {
        
        // Declare.
        Comic copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setCharacters(this.getCharacters());
        copy.setCoverPrice(this.getCoverPrice());
        copy.setCreators(this.getCreators());
        copy.setDescription(this.getDescription());
        copy.setDistribution(this.getDistribution());
        copy.setImage(this.getImage());
        copy.setNumber(this.getNumber());
        copy.setPrint(this.getPrint());
        copy.setPublishDate(this.getPublishDate());
        copy.setTitle(this.getTitle());
        copy.setTraits(this.getTraits());
        copy.setType(this.getType());
        copy.setVariant(this.getVariant());

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
        if (object instanceof Comic &&
            this.compareTo((Comic)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the characters.
     * 
     * @return  the characters.
     */
    public List<ComicCharacter> getCharacters() {
        return this.characters;
    }
    
    /**
     * Get the cover price.
     *
     * @return  the cover price.
     */
    public Double getCoverPrice() {
        return this.coverPrice;
    }

    /**
     * Get the creators.
     *
     * @return  the creators.
     */
    public List<Creator> getCreators() {
        return this.creators;
    }

    /**
     * Get the description.
     *
     * @return  the description.
     */
    public String getDescription() {
        return this.description;
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
     * Get the image.
     *
     * @return  the image.
     */
    public Image getImage() {
        return this.image;
    }
    
    /**
     * Get the number.
     *
     * @return  the number.
     */
    public Integer getNumber() {
        return this.number;
    }
               
    /**
     * Get the print.
     *
     * @return  the print.
     */
    public Integer getPrint() {
        return this.print;
    }
                 
    /**
     * Get the publish date.
     *
     * @return  the publish date.
     */
    public Date getPublishDate() {
        return this.publishDate;
    }
                      
    /**
     * Get the title.
     *
     * @return  the title.
     */
    public Title getTitle() {
        return this.title;
    }
     
    /**
     * Get the traits.
     * 
     * @return  the traits.
     */
    public List<Trait> getTraits() {
        return this.traits;
    }
                                
    /**
     * Get the type.
     *
     * @return  the type.
     */
    public ComicType getType() {
        return this.type;
    }
                    
    /**
     * Get the variant.
     *
     * @return  the variant.
     */
    public String getVariant() {
        return this.variant;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        Double thisCoverPrice;
        String thisDescription;
        Distribution thisDistribution;
        Integer thisNumber;
        Integer thisPrint;
        Date thisPublishDate;
        Title thisTitle;
        ComicType thisType;
        String thisVariant;
        
        // Initialize.
        thisCoverPrice = this.getCoverPrice();
        thisDescription = (this.getDescription() == null) ? "" : this.getDescription();
        thisDistribution = (this.getDistribution() == null) ? new Distribution() : this.getDistribution();
        thisNumber = (this.getNumber() == null) ? new Integer(0) : this.getNumber();
        thisPrint = this.getPrint();
        thisPublishDate = (this.getPublishDate() == null) ? this.getEpoch() : this.getPublishDate();
        thisTitle = (this.getTitle() == null) ? new Title() : this.getTitle();
        thisType = (this.getType() == null) ? new ComicType() : this.getType();
        thisVariant = (this.getVariant() == null) ? "" : this.getVariant();
        
        return thisTitle.hashCode()*7^8
             + thisType.hashCode()*7^7
             + thisNumber.hashCode()*7^6
             + thisVariant.hashCode()*7^5
             + thisPrint.hashCode()*7^4
             + thisPublishDate.hashCode()*7^3
             + thisDistribution.hashCode()*7^2
             + thisCoverPrice.hashCode()*7
             + thisDescription.hashCode();
    }

    /**
     * Set the characters.
     * 
     * @param  characters  the characters.
     */
    public void setCharacters(List<ComicCharacter> characters) {
        
        if (characters == null) {
            this.characters = new ArrayList<ComicCharacter>();
        }
        else {
            this.characters = characters;
        }
    }
    
    /**
     * Set the cover price.
     *
     * @param  coverPrice  the cover price.
     */
    public void setCoverPrice(Double coverPrice) {
        
        if (coverPrice == null) {
            this.coverPrice = new Double(0.0);
        }
        else {
            this.coverPrice = coverPrice;
        }
    }

    /**
     * Set the creators.
     *
     * @param  creators  the creators.
     */
    public void setCreators(List<Creator> creators) {

        if (creators == null) {
            this.creators = new ArrayList<Creator>();
        }
        else {
            this.creators = creators;
        }
    }

    /**
     * Set the description.
     *
     * @param  description  the description.
     */
    public void setDescription(String description) {
        this.description = this.trimmed(description);
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
     * Set the image.
     *
     * @param  image  the image.
     */
    public void setImage(Image image) {
        this.image = image;
    }
      
    /**
     * Set the number.
     *
     * @param  number  the number.
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
               
    /**
     * Set the print.
     *
     * @param  print  the print.
     */
    public void setPrint(Integer print) {
        
        if (print == null || print.intValue() < 1) {
            this.print = new Integer(1);
        }
        else {
            this.print = print;
        }
    }
                        
    /**
     * Set the publish date.
     *
     * @param  publishDate  the publish date.
     */
    public void setPublishDate(Date publishDate) {
        
        if (publishDate == null) {
            this.publishDate = null;
        }
        else {
            
            // Declare.
            Calendar publishDateAsCalendar;

            // Initialize.
            publishDateAsCalendar = Calendar.getInstance();
            
            // Preserve the month and year only.
            publishDateAsCalendar.setTime(publishDate);
            publishDateAsCalendar.set(Calendar.DAY_OF_MONTH, 1);
            publishDateAsCalendar.set(Calendar.HOUR_OF_DAY, 0);
            publishDateAsCalendar.set(Calendar.MINUTE, 0);
            publishDateAsCalendar.set(Calendar.SECOND, 0);
            publishDateAsCalendar.set(Calendar.MILLISECOND, 0);
            
            this.publishDate = publishDateAsCalendar.getTime();
        }
    }
                         
    /**
     * Set the title.
     *
     * @param  title  the title.
     */
    public void setTitle(Title title) {
        this.title = title;
    }
     
    /**
     * Set the traits.
     * 
     * @param  traits  the traits.
     */
    public void setTraits(List<Trait> traits) {
        
        if (traits == null) {
            this.traits = new ArrayList<Trait>();
        }
        else {
            this.traits = traits;
        }
    }
                                
    /**
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(ComicType type) {
        this.type = type;
    }
                   
    /**
     * Set the variant.
     *
     * @param  variant  the variant.
     */
    public void setVariant(String variant) {
        this.variant = this.trimmed(variant);
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
        
        toString.append("Comic [");
        toString.append("characters = ").append(this.getCharacters());
        toString.append(", coverPrice = ").append(this.getCoverPrice());
        toString.append(", creators = ").append(this.getCreators());
        toString.append(", description = ").append(this.getDescription());
        toString.append(", distribution = ").append(this.getDistribution());
        toString.append(", image = ").append(this.getImage());
        toString.append(", number = ").append(this.getNumber());
        toString.append(", print = ").append(this.getPrint());
        toString.append(", publishDate = ").append(this.getPublishDate());
        toString.append(", title = ").append(this.getTitle());
        toString.append(", traits = ").append(this.getTraits());
        toString.append(", type = ").append(this.getType());
        toString.append(", variant = ").append(this.getVariant());
        toString.append("]");
        
        return toString.toString();
    }
}