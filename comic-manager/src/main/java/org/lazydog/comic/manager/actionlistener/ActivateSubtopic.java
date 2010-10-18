package org.lazydog.comic.manager.actionlistener;

import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.Subtopic;


/**
 * Activate subtopic action listener.
 *
 * @author  Ron Rickard
 */
public class ActivateSubtopic
       implements ActionListener {

    /**
     * Process the action.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processAction(ActionEvent actionEvent) {

        // Declare.
        Subtopic subtopic;
        String subtopicAsString;

        // Get the subtopic as a String.
        subtopicAsString = (String)actionEvent.getComponent().getAttributes().get("subtopic");

        // Get the subtopic.
        subtopic = Subtopic.valueOf(subtopicAsString);

        // Put the subtopic on the session.
        SessionUtility.putValue(SessionKey.SUBTOPIC, subtopic);
    }
}
