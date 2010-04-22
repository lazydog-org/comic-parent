package comic.manager.helper.bean;

import comic.api.model.TitleType;
import comic.api.model.UserPreference;
import comic.manager.utility.SessionKey;
import java.io.Serializable;


/**
 * Title type filter managed bean.
 * 
 * @author  Ron Rickard
 */
public class TitleTypeFilter
       extends AbstractTypeFilter<TitleType>
       implements Serializable {

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<TitleType> getEntityClass() {
        return TitleType.class;
    }

    /**
     * Get the type session key.
     *
     * @return  the type session key.
     */
    @Override
    protected SessionKey getTypeSessionKey() {
        return SessionKey.TITLE_TYPE;
    }

    /**
     * Get the type value.
     *
     * @return  the type value.
     */
    @Override
    protected String getTypeValue(TitleType type) {
        return type.getValue();
    }

    /**
     * Get the user preference type.
     *
     * @return  the user preference type.
     */
    @Override
    protected TitleType getUserPreferenceType(UserPreference userPreference) {
        return userPreference.getTitleType();
    }
}
