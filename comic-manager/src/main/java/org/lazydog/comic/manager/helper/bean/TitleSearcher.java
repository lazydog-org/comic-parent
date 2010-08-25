package org.lazydog.comic.manager.helper.bean;

import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.Subtopic;
import org.lazydog.comic.manager.utility.TitleSearchBy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;


/**
 * Title searcher managed bean.
 * 
 * @author  Ron Rickard
 */
public class TitleSearcher 
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the search by.
     * 
     * @return  the search by.
     */
    public String getSearchBy() {
        return SessionUtility.getValue(
                SessionKey.TITLE_SEARCH_BY, TitleSearchBy.class).toString();
    }

    /**
     * Get the search bys as select items.
     * 
     * @return  the search bys as select items.
     */
    public List<SelectItem> getSearchBysAsSelectItems() {
        
        
        // Declare.
        List<SelectItem> searchBysAsSelectItems;
        
        // Initialize.
        searchBysAsSelectItems = new ArrayList<SelectItem>();
        
        // Loop through the search bys.
        for (TitleSearchBy searchBy : TitleSearchBy.values()) {
            
            // Add the search by to the select items.
            searchBysAsSelectItems.add(new SelectItem(
                    searchBy, searchBy.getName()));
        }
        
        return searchBysAsSelectItems;
    }

    /**
     * Get the search disabled.
     * 
     * @return  the search disabled.
     */
    public Boolean getSearchDisabled() {
        return (SessionUtility.getValue(
                SessionKey.SUBTOPIC, Subtopic.class) == Subtopic.TITLE) ?
                false : true;
    }

    /**
     * Get the search for.
     *
     * @return  the search for.
     */
    public Object getSearchFor() {

        // Declare.
        TitleSearchBy searchBy;
        Object searchFor;

        // Get the search by from the session.
        searchBy = SessionUtility.getValue(
                SessionKey.TITLE_SEARCH_BY, TitleSearchBy.class);

        // Get the search for from the session.
        searchFor = SessionUtility.getValue(
                SessionKey.TITLE_SEARCH_FOR, Object.class);

        // Check if the search by is title name and
        // the search for is not a string.
        if (searchBy.equals(TitleSearchBy.TITLE_NAME) &&
            !(searchFor instanceof String)) {

            // Clear the search for to be displayed.
            searchFor = "";
        }

        return searchFor;
    }

    /**
     * Set the search by.
     * 
     * @param  searchBy  the search by.
     */
    public void setSearchBy(String searchBy) {

        // Put the search by on the session.
        SessionUtility.putValue(
                SessionKey.TITLE_SEARCH_BY, TitleSearchBy.valueOf(searchBy));
    }
        
    /**
     * Set the search for.
     * 
     * @param  searchFor  the search for.
     */
    public void setSearchFor(Object searchFor) {
        
        // Put the search for on the session.
        SessionUtility.putValue(SessionKey.TITLE_SEARCH_FOR, searchFor);

        // Remove the title from the session.
        SessionUtility.removeValue(SessionKey.TITLE);
    }
}
