package org.lazydog.comic.manager.utility;


/**
 * Title search by.
 *
 * @author  Ron Rickard
 */
public enum TitleSearchBy {
    CATEGORY_NAME  ("Category Name"),
    PUBLISHER_NAME ("Publisher Name"),
    TITLE_NAME     ("Title Name");

    private String name;

    /**
     * Constructor.
     *
     * @param  name  the name.
     */
    TitleSearchBy(String name) {
        this.name = name;
    }

    /**
     * Get the name.
     *
     * @return  the name.
     */
    public String getName() {
        return this.name;
    }
};