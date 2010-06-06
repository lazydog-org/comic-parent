package org.lazydog.comic.manager.actionlistener;

import org.lazydog.comic.model.ApplicationUserPreference;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.TitleSearchBy;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;


/**
 * Activate title searcher action listener.
 *
 * @author  Ron Rickard
 */
public class ActivateTitleSearcher
       implements ActionListener {

    /**
     * Process the action.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processAction(ActionEvent actionEvent) {

        // Declare.
        ApplicationUserPreference userPreference;

        // Get the user preference from the session.
        userPreference = SessionUtility
                .getValue(SessionKey.USER_PREFERENCE, ApplicationUserPreference.class);

        // Check if there is a user preference.
        if (userPreference != null) {

            // Put the default search by and search for on the session.
            SessionUtility.putValue(
                    SessionKey.TITLE_SEARCH_BY, TitleSearchBy.PUBLISHER_NAME);
            SessionUtility.putValue(
                    SessionKey.TITLE_SEARCH_FOR, userPreference.getPublisher());
        }
    }
}
