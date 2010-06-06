package org.lazydog.comic.manager.helper.bean;

import org.lazydog.comic.model.ApplicationUserPreference;
import org.lazydog.comic.model.ComicType;
import org.lazydog.comic.manager.utility.SessionKey;
import java.io.Serializable;


/**
 * Comic type filter managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicTypeFilter
       extends AbstractTypeFilter<ComicType>
       implements Serializable {

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<ComicType> getEntityClass() {
        return ComicType.class;
    }

    /**
     * Get the type session key.
     *
     * @return  the type session key.
     */
    @Override
    protected SessionKey getTypeSessionKey() {
        return SessionKey.COMIC_TYPE;
    }

    /**
     * Get the type value.
     *
     * @return  the type value.
     */
    @Override
    protected String getTypeValue(ComicType type) {
        return type.getValue();
    }

    /**
     * Get the user preference type.
     *
     * @return  the user preference type.
     */
    @Override
    protected ComicType getUserPreferenceType(ApplicationUserPreference userPreference) {
        return userPreference.getComicType();
    }
}
