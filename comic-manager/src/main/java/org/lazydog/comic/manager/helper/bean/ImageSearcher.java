package org.lazydog.comic.manager.helper.bean;

import org.lazydog.comic.criteria.criterion.ComparisonOperation;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.criteria.CriteriaFactoryException;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.manager.utility.ImageSearchBy;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.Subtopic;
import java.io.Serializable;


/**
 * Title searcher managed bean.
 * 
 * @author  Ron Rickard
 */
public class ImageSearcher 
       implements Serializable {

    /**
     * Get the criteria.
     *
     * @param  searchBy   the search by.
     * @param  searchFor  the search for.
     *
     * @return  the criteria.
     */
    public static Criteria<Image> getCriteria(ImageSearchBy searchBy,
                                              Object searchFor) {

        // Declare.
        Criteria<Image> criteria;

        // Initialize.
        criteria = null;

        try {

            // Declare.
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Get a new criteria.
            criteria = criteriaFactory.createCriteria(Image.class);

            switch(searchBy) {

                case IMAGE_FILE_NAME:

                    // Modify the criteria.
                    criteria.add(ComparisonOperation.like(
                            "fileName", "%" + (String)searchFor + "%"));
                    break;
            }
        }
        catch(CriteriaFactoryException e) {
            // Ignore.
        }

        return criteria;
    }

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
