package org.lazydog.comic.manager.actionlistener;

import org.lazydog.comic.manager.utility.ImageSearchBy;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;


/**
 * Activate image searcher action listener.
 *
 * @author  Ron Rickard
 */
public class ActivateImageSearcher
       implements ActionListener {

    /**
     * Process the action.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processAction(ActionEvent actionEvent) {

        // Put the default search by and search for on the session.
        SessionUtility.putValue(SessionKey.IMAGE_SEARCH_BY,
                ImageSearchBy.IMAGE_FILE_NAME);
        SessionUtility.putValue(SessionKey.IMAGE_SEARCH_FOR, "");
    }
}
