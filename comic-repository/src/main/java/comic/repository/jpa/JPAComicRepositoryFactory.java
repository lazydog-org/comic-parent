package comic.repository.jpa;

import comic.api.repository.ComicRepository;
import comic.api.repository.ComicRepositoryFactory;
import comic.api.repository.ComicRepositoryFactoryException;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 * Comic repository factory implemented using the Java Persistence API.
 * 
 * @author  Ron Rickard
 */
public class JPAComicRepositoryFactory
       extends ComicRepositoryFactory {

    /**
     * Create the comic repository.
     * 
     * @return  the comic repository.
     *
     * @throws  ComicRepositoryFactoryException  if unable to create the
     *                                           comic repository.
     */
    @Override
    public ComicRepository createComicRepository()
           throws ComicRepositoryFactoryException {

        // Declare.
        ComicRepository comicRepository;

        try {

            // Declare.
            Context context;

            // Initialize the context.
            context = new InitialContext();

            // Create the comic repository.
            comicRepository = (ComicRepository)context.lookup("ejb/ComicRepository");

        }
        catch(Exception e) {
            throw new ComicRepositoryFactoryException(
                "Unable to create comic repository.", e);
        }

        return comicRepository;
    }
}
