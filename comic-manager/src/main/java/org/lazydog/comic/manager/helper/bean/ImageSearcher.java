package org.lazydog.comic.manager.helper.bean;

import java.io.Serializable;
import org.lazydog.comic.manager.utility.ImageSearchBy;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.Subtopic;


/**
 * Title searcher managed bean.
 * 
 * @author  Ron Rickard
 */
public class ImageSearcher 
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the search by.
     *
     * @return  the search by.
     */
    public String getSearchBy() {
        return SessionUtility.getValue(
                SessionKey.IMAGE_SEARCH_BY, ImageSearchBy.class).getName();
    }

    /**
     * Get the search disabled.
     *
     * @return  the search disabled.
     */
    public Boolean getSearchDisabled() {
        return (SessionUtility.getValue(
                SessionKey.SUBTOPIC, Subtopic.class) == Subtopic.IMAGE) ?
                false : true;
    }

    /**
     * Get the search for.
     * 
     * @return  the search for.
     */
    public Object getSearchFor() {
        return SessionUtility.getValue(
                SessionKey.IMAGE_SEARCH_FOR, Object.class);
    }

    /**
     * Set the search for.
     * 
     * @param  searchFor  the search for.
     */
    public void setSearchFor(Object searchFor) {

        // Put the search for on the session.
        SessionUtility.putValue(SessionKey.IMAGE_SEARCH_FOR, searchFor);
    }
}
