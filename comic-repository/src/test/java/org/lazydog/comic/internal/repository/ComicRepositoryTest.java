package org.lazydog.comic.internal.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.Category;
import org.lazydog.comic.model.Character;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.ComicGrade;
import org.lazydog.comic.model.ComicType;
import org.lazydog.comic.model.Creator;
import org.lazydog.comic.model.Distribution;
import org.lazydog.comic.model.Have;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;
import org.lazydog.comic.model.Imprint;
import org.lazydog.comic.model.Location;
import org.lazydog.comic.model.Person;
import org.lazydog.comic.model.Profession;
import org.lazydog.comic.model.Publisher;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.TitleType;
import org.lazydog.comic.model.Trait;
import org.lazydog.comic.model.User;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.model.Want;
import org.lazydog.comic.spi.repository.ComicRepository;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit tests for ComicRepositoryImpl class.
 *
 * @author  Ron Rickard
 */
public class ComicRepositoryTest {

    private Criteria<Category> categoryCriteria;
    private Criteria<Character> characterCriteria;
    private Criteria<Comic> comicCriteria;
    private Criteria<ComicGrade> comicGradeCriteria;
    private Criteria<ComicType> comicTypeCriteria;
    private Criteria<Creator> creatorCriteria;
    private Criteria<Distribution> distributionCriteria;
    private Criteria<Have> haveCriteria;
    private Criteria<Image> imageCriteria;
    private Criteria<ImageType> imageTypeCriteria;
    private Criteria<Imprint> imprintCriteria;
    private Criteria<Location> locationCriteria;
    private Criteria<Person> personCriteria;
    private Criteria<Profession> professionCriteria;
    private Criteria<Publisher> publisherCriteria;
    private Criteria<Title> titleCriteria;
    private Criteria<TitleType> titleTypeCriteria;
    private Criteria<Trait> traitCriteria;
    private Criteria<User> userCriteria;
    private Criteria<Want> wantCriteria;
    private Criteria<UserPreference> userPreferenceCriteria;
    private ComicRepository comicRepository;
    private SimpleDateFormat sdf;

    @Before
    public void initialize() throws Exception {

        // Declare.
        CriteriaFactory criteriaFactory;

        try {

            // Declare.
            Context context;

            // Initialize the context.
            context = new InitialContext();

            // Get the comic repository.
            comicRepository = (ComicRepository)context.lookup("ejb/ComicRepository");
        }
        catch(NamingException e) {
            // Ignore.
        }

        // Get the criteria factory.
        criteriaFactory = CriteriaFactory.instance();

        // Initialize the criteria.
        categoryCriteria = criteriaFactory.createCriteria(Category.class);
        characterCriteria = criteriaFactory.createCriteria(Character.class);
        comicCriteria = criteriaFactory.createCriteria(Comic.class);
        comicGradeCriteria = criteriaFactory.createCriteria(ComicGrade.class);
        comicTypeCriteria = criteriaFactory.createCriteria(ComicType.class);
        creatorCriteria = criteriaFactory.createCriteria(Creator.class);
        distributionCriteria = criteriaFactory.createCriteria(Distribution.class);
        haveCriteria = criteriaFactory.createCriteria(Have.class);
        imageCriteria = criteriaFactory.createCriteria(Image.class);
        imageTypeCriteria = criteriaFactory.createCriteria(ImageType.class);
        imprintCriteria = criteriaFactory.createCriteria(Imprint.class);
        locationCriteria = criteriaFactory.createCriteria(Location.class);
        personCriteria = criteriaFactory.createCriteria(Person.class);
        professionCriteria = criteriaFactory.createCriteria(Profession.class);
        publisherCriteria = criteriaFactory.createCriteria(Publisher.class);
        titleCriteria = criteriaFactory.createCriteria(Title.class);
        titleTypeCriteria = criteriaFactory.createCriteria(TitleType.class);
        traitCriteria = criteriaFactory.createCriteria(Trait.class);
        userCriteria = criteriaFactory.createCriteria(User.class);
        userPreferenceCriteria = criteriaFactory.createCriteria(UserPreference.class);
        wantCriteria = criteriaFactory.createCriteria(Want.class);

        // Initialize the data format.
        sdf = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss");
    }

    @Test
    public void findList() throws Exception {

        // Check if the comic repository is accessible.
        if (comicRepository != null) {

            System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

            List<Category> categories = comicRepository.findList(Category.class);
            List<Character> characters = comicRepository.findList(Character.class);
            List<Comic> comics = comicRepository.findList(Comic.class);
            List<ComicGrade> comicGrades = comicRepository.findList(ComicGrade.class);
            List<ComicType> comicTypes = comicRepository.findList(ComicType.class);
            List<Creator> creators = comicRepository.findList(Creator.class);
            List<Distribution> distributions = comicRepository.findList(Distribution.class);
            List<Have> haves = comicRepository.findList(Have.class);
            List<Image> images = comicRepository.findList(Image.class);
            List<ImageType> imageTypes = comicRepository.findList(ImageType.class);
            List<Imprint> imprints = comicRepository.findList(Imprint.class);
            List<Location> locations = comicRepository.findList(Location.class);
            List<Person> persons = comicRepository.findList(Person.class);
            List<Profession> professions = comicRepository.findList(Profession.class);
            List<Publisher> publishers = comicRepository.findList(Publisher.class);
            List<Title> titles = comicRepository.findList(Title.class);
            List<TitleType> titleTypes = comicRepository.findList(TitleType.class);
            List<Trait> traits = comicRepository.findList(Trait.class);
            List<User> users = comicRepository.findList(User.class);
            List<UserPreference> userPreferences = comicRepository.findList(UserPreference.class);
            List<Want> wants = comicRepository.findList(Want.class);

            System.out.println("number of categories = " + categories.size());
            System.out.println("number of characters = " + characters.size());
            System.out.println("number of comics = " + comics.size());
            System.out.println("number of comicGrades = " + comicGrades.size());
            System.out.println("number of comicTypes = " + comicTypes.size());
            System.out.println("number of creators = " + creators.size());
            System.out.println("number of distributions = " + distributions.size());
            System.out.println("number of haves = " + haves.size());
            System.out.println("number of images = " + images.size());
            System.out.println("number of imageTypes = " + imageTypes.size());
            System.out.println("number of imprint = " + imprints.size());
            System.out.println("number of locations = " + locations.size());
            System.out.println("number of persons = " + persons.size());
            System.out.println("number of professions = " + professions.size());
            System.out.println("number of publishers = " + publishers.size());
            System.out.println("number of titles = " + titles.size());
            System.out.println("number of titleTypes = " + titleTypes.size());
            System.out.println("number of traits = " + traits.size());
            System.out.println("number of users = " + users.size());
            System.out.println("number of userPreferences = " + userPreferences.size());
            System.out.println("number of wants = " + wants.size());

            System.out.println(sdf.format(new Date()) + " Finished.");
        }
    }

    @Test
    public void findListByCriteria() throws Exception {

        // Check if the comic repository is accessible.
        if (comicRepository != null) {

            System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

            List<Category> categories = comicRepository.findList(categoryCriteria);
            List<Character> characters = comicRepository.findList(characterCriteria);
            List<Comic> comics = comicRepository.findList(comicCriteria);
            List<ComicGrade> comicGrades = comicRepository.findList(comicGradeCriteria);
            List<ComicType> comicTypes = comicRepository.findList(comicTypeCriteria);
            List<Creator> creators = comicRepository.findList(creatorCriteria);
            List<Distribution> distributions = comicRepository.findList(distributionCriteria);
            List<Have> haves = comicRepository.findList(haveCriteria);
            List<Image> images = comicRepository.findList(imageCriteria);
            List<ImageType> imageTypes = comicRepository.findList(imageTypeCriteria);
            List<Imprint> imprints = comicRepository.findList(imprintCriteria);
            List<Location> locations = comicRepository.findList(locationCriteria);
            List<Person> persons = comicRepository.findList(personCriteria);
            List<Profession> professions = comicRepository.findList(professionCriteria);
            List<Publisher> publishers = comicRepository.findList(publisherCriteria);
            List<Title> titles = comicRepository.findList(titleCriteria);
            List<TitleType> titleTypes = comicRepository.findList(titleTypeCriteria);
            List<Trait> traits = comicRepository.findList(traitCriteria);
            List<User> users = comicRepository.findList(userCriteria);
            List<UserPreference> userPreferences = comicRepository.findList(userPreferenceCriteria);
            List<Want> wants = comicRepository.findList(wantCriteria);

            System.out.println("number of categories = " + categories.size());
            System.out.println("number of characters = " + characters.size());
            System.out.println("number of comics = " + comics.size());
            System.out.println("number of comicGrades = " + comicGrades.size());
            System.out.println("number of comicTypes = " + comicTypes.size());
            System.out.println("number of creators = " + creators.size());
            System.out.println("number of distributions = " + distributions.size());
            System.out.println("number of haves = " + haves.size());
            System.out.println("number of images = " + images.size());
            System.out.println("number of imageTypes = " + imageTypes.size());
            System.out.println("number of imprints = " + imprints.size());
            System.out.println("number of locations = " + locations.size());
            System.out.println("number of persons = " + persons.size());
            System.out.println("number of professions = " + professions.size());
            System.out.println("number of publishers = " + publishers.size());
            System.out.println("number of titles = " + titles.size());
            System.out.println("number of titleTypes = " + titleTypes.size());
            System.out.println("number of traits = " + traits.size());
            System.out.println("number of users = " + users.size());
            System.out.println("number of userPreferences = " + userPreferences.size());
            System.out.println("number of wants = " + wants.size());

            System.out.println(sdf.format(new Date()) + " Finished.");
        }
    }
}
