package org.lazydog.comic.model;

import java.io.Serializable;
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

    // Declare.
    @Valid @NotNull(message="Comic is required.")
    private Comic comic;
    @Valid @NotNull(message="Comic grade is required.")
    private ComicGrade comicGrade;
    @Valid @NotNull(message="Location is required.")
    private Location location;
    private Double purchasePrice;
    @NotNull(message="Quantity is required.")
    private Integer quantity;
    
    /**
     * Constructor.
     */
    public Have() {
        
        super();
        this.setComic(null);
        this.setComicGrade(null);
        this.setLocation(null);
        this.setPurchasePrice(null);
        this.setQuantity(null);
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
    public int compareTo(Have object) {
        
        // Declare.
        int lastCompare;
        Comic thatComic;
        ComicGrade thatComicGrade;
        ApplicationUser thatCreateUser;
        Location thatLocation;
        Double thatPurchasePrice;
        Comic thisComic;
        ComicGrade thisComicGrade;
        ApplicationUser thisCreateUser;
        Location thisLocation;
        Double thisPurchasePrice;

        // Initialize.
        lastCompare = 0;
        thatComic = (object.getComic() == null) ? new Comic() : object.getComic();
        thatComicGrade = (object.getComicGrade() == null) ? new ComicGrade() : object.getComicGrade();
        thatCreateUser = (object.getCreateUser() == null) ? new ApplicationUser() : object.getCreateUser();
        thatLocation = (object.getLocation() == null) ? new Location() : object.getLocation();
        thatPurchasePrice = object.getPurchasePrice();
        thisComic = (this.getComic() == null) ? new Comic() : this.getComic();
        thisComicGrade = (this.getComicGrade() == null) ? new ComicGrade() : this.getComicGrade();
        thisCreateUser = (this.getCreateUser() == null) ? new ApplicationUser() : this.getCreateUser();
        thisLocation = (this.getLocation() == null) ? new Location() : this.getLocation();
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
        ApplicationUser thisCreateUser;
        Location thisLocation;
        Double thisPurchasePrice;
        Integer thisQuantity;
        
        // Initialize.
        thisComic = (this.getComic() == null) ? new Comic() : this.getComic();
        thisComicGrade = (this.getComicGrade() == null) ? new ComicGrade() : this.getComicGrade();
        thisCreateUser = (this.getCreateUser() == null) ? new ApplicationUser() : this.getCreateUser();
        thisLocation = (this.getLocation() == null) ? new Location() : this.getLocation();
        thisPurchasePrice = this.getPurchasePrice();
        thisQuantity = this.getQuantity();

        return thisCreateUser.hashCode()*7^5
             + thisComic.hashCode()*7^4
             + thisComicGrade.hashCode()*7^3
             + thisLocation.hashCode()*7^2
             + thisPurchasePrice.hashCode()*7
             + thisQuantity.hashCode();
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

        if (purchasePrice == null) {
            this.purchasePrice = new Double(0.0);
        }
        else {
            this.purchasePrice = purchasePrice;
        }
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

