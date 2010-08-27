package org.lazydog.comic.internal.service;


/**
 * Comic service wrapper.
 *
 * @author  Ron Rickard
 */
public class ComicServiceWrapper extends ComicServiceImpl {

    /**
     * Constructor.
     */
    public ComicServiceWrapper () {

        // Inject the comic repository.
        this.setComicRepository(new ComicRepositoryWrapper());
    }
}
