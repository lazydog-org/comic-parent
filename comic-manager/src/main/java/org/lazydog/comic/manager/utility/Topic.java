package org.lazydog.comic.manager.utility;


/**
 * Topic.
 *
 * @author  Ron Rickard
 */
public enum Topic {
    HOME            (Subtopic.HOME),
    COMIC_LIBRARY   (Subtopic.TITLE),
    IMAGE_LIBRARY   (Subtopic.IMAGE),
    REPORTS         (Subtopic.REPORTS);

    private Subtopic defaultSubtopic;

    /**
     * Constructor.
     *
     * @param  defaultSubtopic  the default subtopic.
     */
    Topic(Subtopic defaultSubtopic) {
        this.defaultSubtopic = defaultSubtopic;
    }

    /**
     * Get the default subtopic.
     *
     * @return  the default subtopic.
     */
    public Subtopic getDefaultSubtopic() {
        return this.defaultSubtopic;
    }
};
