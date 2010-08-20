package org.lazydog.comic.manager.helper.bean;

import org.lazydog.comic.model.ImageType;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.utility.SessionKey;
import java.io.Serializable;


/**
 * Image type filter managed bean.
 * 
 * @author  Ron Rickard
 */
public class ImageTypeFilter
       extends AbstractTypeFilter<ImageType>
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<ImageType> getEntityClass() {
        return ImageType.class;
    }

    /**
     * Get the type session key.
     *
     * @return  the type session key.
     */
    @Override
    protected SessionKey getTypeSessionKey() {
        return SessionKey.IMAGE_TYPE;
    }

    /**
     * Get the type value.
     *
     * @return  the type value.
     */
    @Override
    protected String getTypeValue(ImageType type) {
        return type.getValue();
    }

    /**
     * Get the user preference type.
     *
     * @return  the user preference type.
     */
    @Override
    protected ImageType getUserPreferenceType(UserPreference userPreference) {
        return userPreference.getImageType();
    }
}
