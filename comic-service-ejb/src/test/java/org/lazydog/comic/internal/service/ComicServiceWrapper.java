package org.lazydog.comic.internal.service;

import org.lazydog.comic.ComicRepository;
import org.lazydog.comic.internal.repository.ComicRepositoryImpl;


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
        this.setComicRepository(new ComicRepositoryImpl());
    }
}
