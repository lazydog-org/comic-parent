package org.lazydog.comic.internal.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.ApplicationUser;
import org.lazydog.comic.model.ApplicationUserPreference;
import org.lazydog.comic.model.Category;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.ComicCharacter;
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
import org.lazydog.comic.model.Want;
import org.lazydog.comic.spi.repository.ComicRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Unit test for ComicRepository class.
 *
 * @author  Ron Rickard
 */
@Ignore
public class ComicRepositoryTest {

    private static Criteria<ApplicationUser> applicationUserCriteria;
    private static Criteria<ApplicationUserPreference> applicationUserPreferenceCriteria;
    private static Criteria<Category> categoryCriteria;
    private static Criteria<Comic> comicCriteria;
    private static Criteria<ComicCharacter> comicCharacterCriteria;
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
    private static Criteria<Want> wantCriteria;
    private static ComicRepository comicRepository;
    private static EJBContainer ejbContainer;
    private static SimpleDateFormat sdf;

    @BeforeClass
    public static void initialize() throws Exception {

        // Declare.
        CriteriaFactory criteriaFactory;

        // Create the EJB container.
        ejbContainer = EJBContainer.createEJBContainer();

        // Get the comic repository.
        comicRepository = (ComicRepository)ejbContainer.getContext().lookup("ejb/ComicRepository");

        // Get the criteria factory.
        criteriaFactory = CriteriaFactory.instance();

        // Initialize the criteria.
        applicationUserCriteria = criteriaFactory.createCriteria(ApplicationUser.class);
        applicationUserPreferenceCriteria = criteriaFactory.createCriteria(ApplicationUserPreference.class);
        categoryCriteria = criteriaFactory.createCriteria(Category.class);
        comicCriteria = criteriaFactory.createCriteria(Comic.class);
        comicCharacterCriteria = criteriaFactory.createCriteria(ComicCharacter.class);
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
        wantCriteria = criteriaFactory.createCriteria(Want.class);

        // Initialize the data format.
        sdf = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss");
    }

    @AfterClass
    public static void destroy() {
        ejbContainer.close();
    }

    @Ignore
    @Test
    public void findList() throws Exception {

        System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

        List<ApplicationUser> applicationUsers = comicRepository.findList(ApplicationUser.class);
        List<ApplicationUserPreference> applicationUserPreferences = comicRepository.findList(ApplicationUserPreference.class);
        List<Category> categories = comicRepository.findList(Category.class);
        List<Comic> comics = comicRepository.findList(Comic.class);
        List<ComicCharacter> comicCharacters = comicRepository.findList(ComicCharacter.class);
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
        List<Want> wants = comicRepository.findList(Want.class);

        System.out.println("number of applicationUsers = " + applicationUsers.size());
        System.out.println("number of applicationUserPreferences = " + applicationUserPreferences.size());
        System.out.println("number of categories = " + categories.size());
        System.out.println("number of comics = " + comics.size());
        System.out.println("number of comicCharacters = " + comicCharacters.size());
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
        System.out.println("number of wants = " + wants.size());

        System.out.println(sdf.format(new Date()) + " Finished.");
    }

    @Test
    public void findListByCriteria() throws Exception {

        System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

        List<ApplicationUser> applicationUsers = comicRepository.findList(applicationUserCriteria);
        List<ApplicationUserPreference> applicationUserPreferences = comicRepository.findList(applicationUserPreferenceCriteria);
        List<Category> categories = comicRepository.findList(categoryCriteria);
        List<Comic> comics = comicRepository.findList(comicCriteria);
        List<ComicCharacter> comicCharacters = comicRepository.findList(comicCharacterCriteria);
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
        List<Want> wants = comicRepository.findList(wantCriteria);

        System.out.println("number of applicationUsers = " + applicationUsers.size());
        System.out.println("number of applicationUserPreferences = " + applicationUserPreferences.size());
        System.out.println("number of categories = " + categories.size());
        System.out.println("number of comics = " + comics.size());
        System.out.println("number of comicCharacters = " + comicCharacters.size());
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
        System.out.println("number of wants = " + wants.size());

        System.out.println(sdf.format(new Date()) + " Finished.");
    }
}
