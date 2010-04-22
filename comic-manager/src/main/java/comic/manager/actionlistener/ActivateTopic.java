package comic.manager.actionlistener;

import comic.manager.utility.SessionKey;
import comic.manager.utility.SessionUtility;
import comic.manager.utility.Topic;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;


/**
 * Activate topic action listener.
 *
 * @author  Ron Rickard
 */
public class ActivateTopic
       implements ActionListener {

    /**
     * Process the action.
     *
     * @param  actionEvent  the action event.
     */
    @Override
    public void processAction(ActionEvent actionEvent) {

        // Declare.
        Topic topic;
        String topicAsString;

        // Get the topic as a String.
        topicAsString = (String)actionEvent.getComponent().getAttributes().get("topic");

        // Get the topic.
        topic = Topic.valueOf(topicAsString);

        // Put the topic on the session.
        SessionUtility.putValue(SessionKey.TOPIC, topic);

        // Put the default subtopic on the session.
        SessionUtility.putValue(SessionKey.SUBTOPIC, topic.getDefaultSubtopic());
    }
}
