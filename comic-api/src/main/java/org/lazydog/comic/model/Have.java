package org.lazydog.comic.model;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.Valid;


/**
 * Entity class used to represent a have.
 *
 * @author  Ron Rickard
 */
public class Have
       extends Entity<Have>
       implements Comparable<Have>,
                  Serializable {

    private static final long serialVersionUID = 1L;

    @Valid @NotNull(message="Comic is required.")
    private Comic comic;
    @Valid @NotNull(message="Comic grade is required.")
    private ComicGrade comicGrade;
    @Valid @NotNull(message="Location is required.")
    private Location location;
    private Double purchasePrice = new Double(0.0);
    @NotNull(message="Quantity is required.")
    @Min(value=1, message="Quantity must be at least 1.")
    private Integer quantity = new Integer(1);

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
    public int compareTo(Have that) {
        
        // Declare.
        int lastCompare;
        Comic thatComic;
        ComicGrade thatComicGrade;
        User thatCreateUser;
        Location thatLocation;
        Double thatPurchasePrice;
        Comic thisComic;
        ComicGrade thisComicGrade;
        User thisCreateUser;
        Location thisLocation;
        Double thisPurchasePrice;

        // Initialize.
        lastCompare = 0;
        thatComic = replaceNull(that.getComic(), new Comic());
        thatComicGrade = replaceNull(that.getComicGrade(), new ComicGrade());
        thatCreateUser = replaceNull(that.getCreateUser(), new User());
        thatLocation = replaceNull(that.getLocation(), new Location());
        thatPurchasePrice = that.getPurchasePrice();
        thisComic = replaceNull(this.getComic(), new Comic());
        thisComicGrade = replaceNull(this.getComicGrade(), new ComicGrade());
        thisCreateUser = replaceNull(this.getCreateUser(), new User());
        thisLocation = replaceNull(this.getLocation(), new Location());
        thisPurchasePrice = this.getPurchasePrice();
        
        // Compare this object to the object.
        lastCompare = thisCreateUser.compareTo(thatCreateUser);
        lastCompare = (lastCompare != 0) ? lastCompare : thisComic.compareTo(thatComic);
        lastCompare = (lastCompare != 0) ? lastCompare : thisComicGrade.compareTo(thatComicGrade);
        lastCompare = (lastCompare != 0) ? lastCompare : thisLocation.compareTo(thatLocation);
        lastCompare = (lastCompare != 0) ? lastCompare : thisPurchasePrice.compareTo(thatPurchasePrice);
        
        return lastCompare;
    }
                                            
    /**
     * Create a copy of this object.
     *
     * @return  a copy of this object.
     */
    @Override
    public Have copy() {
        
        // Declare.
        Have copy;
        
        // Create a copy.
        copy = super.copy();
        copy.setComic(this.getComic());
        copy.setComicGrade(this.getComicGrade());
        copy.setLocation(this.getLocation());
        copy.setPurchasePrice(this.getPurchasePrice());

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
        if (object instanceof Have &&
            this.compareTo((Have)object) == 0) {
            equals = true;
        }
        
        return equals;
    }

    /**
     * Get the comic.
     *
     * @return  the comic.
     */
    public Comic getComic() {
        return this.comic;
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
     * Get the location.
     *
     * @return  the location.
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Get the purchase price.
     *
     * @return  the purchase price.
     */
    public Double getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Get the quantity.
     *
     * @return  the quantity.
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Returns a hash code for this object.
     *
     * @return  a hash code for this object.
     */
    @Override
    public int hashCode() {
        
        // Declare.
        Comic thisComic;
        ComicGrade thisComicGrade;
        User thisCreateUser;
        Location thisLocation;
        Double thisPurchasePrice;
        
        // Initialize.
        thisComic = replaceNull(this.getComic(), new Comic());
        thisComicGrade = replaceNull(this.getComicGrade(), new ComicGrade());
        thisCreateUser = replaceNull(this.getCreateUser(), new User());
        thisLocation = replaceNull(this.getLocation(), new Location());
        thisPurchasePrice = this.getPurchasePrice();

        return thisCreateUser.hashCode()*7^4
             + thisComic.hashCode()*7^3
             + thisComicGrade.hashCode()*7^2
             + thisLocation.hashCode()*7
             + thisPurchasePrice.hashCode();
    }
  
    /**
     * Set the comic.
     *
     * @param  comic  the comic.
     */
    public void setComic(Comic comic) {
        this.comic = comic;
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
     * Set the location.
     *
     * @param  location  the location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Set the purchase price.
     *
     * @param  purchasePrice  the purchase price.
     */
    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = replaceNull(purchasePrice, new Double(0.0));
    }

    /**
     * Set the quantity.
     *
     * @param  quantity  the quantity.
     */
    public void setQuantity(Integer quantity) {

        if (quantity == null || quantity.intValue() < 1) {
            this.quantity = new Integer(1);
        }
        else {
            this.quantity = quantity;
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
        
        toString.append("Have [");
        toString.append("comic = ").append(this.getComic());
        toString.append(", comicGrade = ").append(this.getComicGrade());
        toString.append(", location = ").append(this.getLocation());
        toString.append(", purchasePrice = ").append(this.getPurchasePrice());
        toString.append(", quantity = ").append(this.getQuantity());
        toString.append("]");
        
        return toString.toString();
    }
}

