package org.lazydog.comic.manager.utility;


/**
 * Subtopic.
 *
 * @author  Ron Rickard
 */
public enum Subtopic {
    COMIC   ("/pages/comicLibrary/comic/subtopicContent.xhtml"),
    HOME    ("/pages/home/main.xhtml"),
    IMAGE   ("/pages/imageLibrary/image/subtopicContent.xhtml"),
    TITLE   ("/pages/comicLibrary/title/subtopicContent.xhtml");

    private String content;

    /**
     * Constructor.
     *
     * @param  topic    the topic.
     * @param  content  the content.
     */
    Subtopic(String content) {
        this.content = content;
    }

    /**
     * Get the content.
     *
     * @return  the content.
     */
    public String getContent() {
        return this.content;
    }
};
