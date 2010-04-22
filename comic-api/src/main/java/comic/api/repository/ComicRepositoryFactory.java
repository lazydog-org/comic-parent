package comic.api.repository;

import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;


/**
 * Comic repository factory.
 *
 * @author  Ronald Rickard
 */
public abstract class ComicRepositoryFactory {

    private static ServiceLoader<ComicRepositoryFactory> comicRepositoryFactoryLoader
            = ServiceLoader.load(ComicRepositoryFactory.class);

    public abstract ComicRepository createComicRepository()
           throws ComicRepositoryFactoryException;
    
    /**
     * Get an implementation of the comic repository factory.
     *
     * @return  an implementation of the comic repository factory.
     *  
     * @throws  ComicRepositoryFactoryException  if unable to get an
     *                                           implementation of the
     *                                           comic repository factory.
     */
    public static ComicRepositoryFactory instance()
           throws ComicRepositoryFactoryException {
        
        // Declare.
        ComicRepositoryFactory comicRepositoryFactory;
        
        // Initialize.
        comicRepositoryFactory = null;
        
        try {
            
            // Loop through the comic repository factory implementations.
            for (ComicRepositoryFactory foundComicRepositoryFactory : comicRepositoryFactoryLoader) {

                // Check if a comic repository factory implementation has not been found.
                if (comicRepositoryFactory == null) {

                    // Set the comic repository factory.
                    comicRepositoryFactory = foundComicRepositoryFactory;
                }
                else {
                    throw new ComicRepositoryFactoryException(
                        "More than one ComicRepositoryFactory implementation found.");
                }
            }

            // Check if a comic repository factory implementation has not been found.
            if (comicRepositoryFactory == null) {
                throw new ComicRepositoryFactoryException(
                    "No ComicRepositoryFactory implementation found.");
            }
        }
        catch(ServiceConfigurationError e) {
            throw new ComicRepositoryFactoryException(
                "Unable to determine ComicRepositoryFactory implementation.", e);
        }

        return comicRepositoryFactory;
    }
}
