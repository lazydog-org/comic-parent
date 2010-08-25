package org.lazydog.comic.internal.repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.lazydog.comic.ComicRepository;
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
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Unit tests for ComicRepositoryImpl class.
 *
 * @author  Ron Rickard
 */
public class ComicRepositoryImplTest {

    private static final DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private static Criteria<Category> categoryCriteria;
    private static Criteria<Character> characterCriteria;
    private static Criteria<Comic> comicCriteria;
    private static Criteria<ComicGrade> comicGradeCriteria;
    private static Criteria<ComicType> comicTypeCriteria;
    private static Criteria<Creator> creatorCriteria;
    private static Criteria<Distribution> distributionCriteria;
    private static Criteria<Have> haveCriteria;
    private static Criteria<Image> imageCriteria;
    private static Criteria<ImageType> imageTypeCriteria;
    private static Criteria<Imprint> imprintCriteria;
    private static Criteria<Location> locationCriteria;
    private static Criteria<Person> personCriteria;
    private static Criteria<Profession> professionCriteria;
    private static Criteria<Publisher> publisherCriteria;
    private static Criteria<Title> titleCriteria;
    private static Criteria<TitleType> titleTypeCriteria;
    private static Criteria<Trait> traitCriteria;
    private static Criteria<User> userCriteria;
    private static Criteria<Want> wantCriteria;
    private static Criteria<UserPreference> userPreferenceCriteria;
    private static ComicRepository repository;

    @BeforeClass
    public static void initialize() throws Exception {

        // Get the comic repository.
        repository = new ComicRepositoryWrapper();

        // Initialize the criteria.
        categoryCriteria = repository.getCriteria(Category.class);
        characterCriteria = repository.getCriteria(Character.class);
        comicCriteria = repository.getCriteria(Comic.class);
        comicGradeCriteria = repository.getCriteria(ComicGrade.class);
        comicTypeCriteria = repository.getCriteria(ComicType.class);
        creatorCriteria = repository.getCriteria(Creator.class);
        distributionCriteria = repository.getCriteria(Distribution.class);
        haveCriteria = repository.getCriteria(Have.class);
        imageCriteria = repository.getCriteria(Image.class);
        imageTypeCriteria = repository.getCriteria(ImageType.class);
        imprintCriteria = repository.getCriteria(Imprint.class);
        locationCriteria = repository.getCriteria(Location.class);
        personCriteria = repository.getCriteria(Person.class);
        professionCriteria = repository.getCriteria(Profession.class);
        publisherCriteria = repository.getCriteria(Publisher.class);
        titleCriteria = repository.getCriteria(Title.class);
        titleTypeCriteria = repository.getCriteria(TitleType.class);
        traitCriteria = repository.getCriteria(Trait.class);
        userCriteria = repository.getCriteria(User.class);
        userPreferenceCriteria = repository.getCriteria(UserPreference.class);
        wantCriteria = repository.getCriteria(Want.class);
    }

    @Before
    public void beforeTest() throws Exception {
System.out.println("new test");
        repository = null;
        System.gc();
        Thread.sleep(1000);
        repository = new ComicRepositoryWrapper();
    }

    private static double duration(Date startTime, Date endTime) {
        return (endTime.getTime() - startTime.getTime()) / 1000d;
    }

    private static <T> void findList(Class<T> entityClass) {

        List<T> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        entities = repository.findList(entityClass);
        endTime = new Date();
        System.out.println(entities.size() + " " + entityClass.getSimpleName()
                + "s retrieved in " + duration(startTime, endTime)
                + " seconds");
    }

    @Test
    public void findListCategory() throws Exception {
        findList(Category.class);
    }

    @Test
    public void findListComic() throws Exception {
        findList(Comic.class);
    }

    @Test
    public void findListHave() throws Exception {
        findList(Have.class);
    }

    @Test
    public void findListImprint() throws Exception {
        findList(Imprint.class);
    }

    @Test
    public void findListPublisher() throws Exception {
        findList(Publisher.class);
    }

    @Test
    public void findListTitle() throws Exception {
        findList(Title.class);
    }

    @Test
    public void findListTrait() throws Exception {
        findList(Trait.class);
    }

    @Ignore
    @Test
    public void findListHaveBySpecificCriteria() throws Exception {

        List<Have> haves;
        List<Title> titles;
        User user;
        Date endTime;
        Date startTime;

        for (int x = 0; x < 10; x++) {

            titles = new ArrayList<Title>();

            startTime = new Date();
            userCriteria.add(ComparisonOperation.eq("name", "rjrjr"));
            user = repository.find(User.class, userCriteria);

            haveCriteria.add(ComparisonOperation.eq("createUser", user));

            haves = repository.findList(Have.class, haveCriteria);

            endTime = new Date();
            System.out.println(haves.size() + " retrieved in "
                    + duration(startTime, endTime) + " seconds");
            startTime = new Date();

            if (haves != null) {

                for (Have have : haves) {

                    if (!titles.contains(have.getComic().getTitle())) {

                        titles.add(have.getComic().getTitle());
                    }
                }
            }

            endTime = new Date();
            System.out.println(titles.size() + " retrieved in "
                    + duration(startTime, endTime) + " seconds");
        }
    }

    @Ignore
    @Test
    public void findLists() throws Exception {

        System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

        List<Category> categories = repository.findList(Category.class);
        List<Character> characters = repository.findList(Character.class);
        List<Comic> comics = repository.findList(Comic.class);
        List<ComicGrade> comicGrades = repository.findList(ComicGrade.class);
        List<ComicType> comicTypes = repository.findList(ComicType.class);
        List<Creator> creators = repository.findList(Creator.class);
        List<Distribution> distributions = repository.findList(Distribution.class);
        List<Have> haves = repository.findList(Have.class);
        List<Image> images = repository.findList(Image.class);
        List<ImageType> imageTypes = repository.findList(ImageType.class);
        List<Imprint> imprints = repository.findList(Imprint.class);
        List<Location> locations = repository.findList(Location.class);
        List<Person> persons = repository.findList(Person.class);
        List<Profession> professions = repository.findList(Profession.class);
        List<Publisher> publishers = repository.findList(Publisher.class);
        List<Title> titles = repository.findList(Title.class);
        List<TitleType> titleTypes = repository.findList(TitleType.class);
        List<Trait> traits = repository.findList(Trait.class);
        List<User> users = repository.findList(User.class);
        List<UserPreference> userPreferences = repository.findList(UserPreference.class);
        List<Want> wants = repository.findList(Want.class);

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
