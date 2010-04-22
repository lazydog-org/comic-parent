package comic.manager.utility;


/**
 * Image search by.
 *
 * @author  Ron Rickard
 */
public enum ImageSearchBy {
    IMAGE_FILE_NAME ("Image File Name");

    private String name;

    /**
     * Constructor.
     *
     * @param  name  the name.
     */
    ImageSearchBy(String name) {
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
}
