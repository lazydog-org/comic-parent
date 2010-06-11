package org.lazydog.comic.internal.service;

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
import org.lazydog.comic.service.ComicService;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;



/**
 * Unit test for ComicService class.
 * 
 * @author  Ron Rickard
 */
public class ComicServiceTest {

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
    private static ComicService comicService;
    private static SimpleDateFormat sdf;

    @BeforeClass
    public static void initialize() throws Exception {

        // Declare.
        Context context;
        CriteriaFactory criteriaFactory;

        // Initialize the context.
        context = new InitialContext();

        // Initialize the services.
        comicService = (ComicService)context.lookup("ejb/ComicService");

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

        // Initialize the date format.
        sdf = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss");
    }

    @Ignore
    @Test
    public void testFindList() throws Exception {

        System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

        List<ApplicationUser> applicationUsers = comicService.findList(ApplicationUser.class);
        List<ApplicationUserPreference> applicationUserPreferences = comicService.findList(ApplicationUserPreference.class);
        List<Category> categories = comicService.findList(Category.class);
        List<Comic> comics = comicService.findList(Comic.class);
        List<ComicCharacter> comicCharacters = comicService.findList(ComicCharacter.class);
        List<ComicGrade> comicGrades = comicService.findList(ComicGrade.class);
        List<ComicType> comicTypes = comicService.findList(ComicType.class);
        List<Creator> creators = comicService.findList(Creator.class);
        List<Distribution> distributions = comicService.findList(Distribution.class);
        List<Have> haves = comicService.findList(Have.class);
        List<Image> images = comicService.findList(Image.class);
        List<ImageType> imageTypes = comicService.findList(ImageType.class);
        List<Imprint> imprints = comicService.findList(Imprint.class);
        List<Location> locations = comicService.findList(Location.class);
        List<Person> persons = comicService.findList(Person.class);
        List<Profession> professions = comicService.findList(Profession.class);
        List<Publisher> publishers = comicService.findList(Publisher.class);
        List<Title> titles = comicService.findList(Title.class);
        List<TitleType> titleTypes = comicService.findList(TitleType.class);
        List<Trait> traits = comicService.findList(Trait.class);
        List<Want> wants = comicService.findList(Want.class);

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

    @Test
    public void findListByCriteria() throws Exception {

        System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

        List<ApplicationUser> applicationUsers = comicService.findList(applicationUserCriteria);
        List<ApplicationUserPreference> applicationUserPreferences = comicService.findList(applicationUserPreferenceCriteria);
        List<Category> categories = comicService.findList(categoryCriteria);
        List<Comic> comics = comicService.findList(comicCriteria);
        List<ComicCharacter> comicCharacters = comicService.findList(comicCharacterCriteria);
        List<ComicGrade> comicGrades = comicService.findList(comicGradeCriteria);
        List<ComicType> comicTypes = comicService.findList(comicTypeCriteria);
        List<Creator> creators = comicService.findList(creatorCriteria);
        List<Distribution> distributions = comicService.findList(distributionCriteria);
        List<Have> haves = comicService.findList(haveCriteria);
        List<Image> images = comicService.findList(imageCriteria);
        List<ImageType> imageTypes = comicService.findList(imageTypeCriteria);
        List<Imprint> imprints = comicService.findList(imprintCriteria);
        List<Location> locations = comicService.findList(locationCriteria);
        List<Person> persons = comicService.findList(personCriteria);
        List<Profession> professions = comicService.findList(professionCriteria);
        List<Publisher> publishers = comicService.findList(publisherCriteria);
        List<Title> titles = comicService.findList(titleCriteria);
        List<TitleType> titleTypes = comicService.findList(titleTypeCriteria);
        List<Trait> traits = comicService.findList(traitCriteria);
        List<Want> wants = comicService.findList(wantCriteria);

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
