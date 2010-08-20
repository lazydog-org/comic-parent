package org.lazydog.comic.manager.helper.bean;

import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.Subtopic;
import org.lazydog.comic.manager.utility.Topic;
import java.io.Serializable;


/**
 * Navigator managed bean.
 * 
 * @author  Ron Rickard
 */
public class Navigator
       implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Topic DEFAULT_TOPIC = Topic.HOME;

    /**
     * Get the subtopic.
     *
     * @return  the subtopic.
     */
    public String getSubtopic() {
        return ((SessionUtility.valueExists(SessionKey.SUBTOPIC)) ?
            SessionUtility.getValue(SessionKey.SUBTOPIC, Subtopic.class) :
            Topic.valueOf(this.getTopic()).getDefaultSubtopic()).toString();
    }

    /**
     * Get the subtopic content.
     *
     * @return  the subtopic content.
     */
    public String getSubtopicContent() {

        // Declare.
        Subtopic subtopic;

        // Convert the subtopic.
        subtopic = Subtopic.valueOf(this.getSubtopic());

        switch (subtopic) {
            case COMIC:

                // Check if the title does not exist on the session.
                if (!SessionUtility.valueExists(SessionKey.TITLE)) {

                    // Set the subtopic so the content is for the title.
                    subtopic = Subtopic.TITLE;
                }
                break;
        }

        return subtopic.getContent();
    }

    /**
     * Get the topic.
     *
     * @return  the topic.
     */
    public String getTopic() {
        return ((SessionUtility.valueExists(SessionKey.TOPIC)) ?
            SessionUtility.getValue(SessionKey.TOPIC, Topic.class) :
            Navigator.DEFAULT_TOPIC).toString();
    }

    /**
     * Set the topic.
     *
     * @param  topic  the topic.
     */
    public void setTopic(String topic) {

        // Put the topic on the session.
        SessionUtility.putValue(SessionKey.TOPIC, Topic.valueOf(topic));

        // Put the default subtopic on the session.
        SessionUtility.putValue(SessionKey.SUBTOPIC,
                Topic.valueOf(topic).getDefaultSubtopic());
    }
}