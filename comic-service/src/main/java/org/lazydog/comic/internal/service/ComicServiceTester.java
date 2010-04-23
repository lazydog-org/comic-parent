package org.lazydog.comic.internal.service;

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
import org.lazydog.comic.service.ComicService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 *
 * @author  Ron Rickard
 */
public class ComicServiceTester {

    ComicService comicService;
    SimpleDateFormat sdf;

    public ComicServiceTester() {

        try {

            // Declare.
            Context context;

            // Initialize the context.
            context = new InitialContext();

            // Initialize the services.
            comicService = (ComicService)context.lookup("ejb/ComicService");

            sdf = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss");
        }
        catch(Exception e) {
            System.err.println("Unable to initialize services.");
            System.err.println(e);
        }
    }

    public static void main(String[] args) {

        ComicServiceTester tester = new ComicServiceTester();

        tester.testFindList();
    }

    public void testFindList() {

        try {

            System.out.println(sdf.format(new Date()) + " Fetching all objects ...");

            List<Category> categories = comicService.findList(Category.class);
            List<Character> characters = comicService.findList(Character.class);
            List<Comic> comics = comicService.findList(Comic.class);
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
            List<User> users = comicService.findList(User.class);
            List<UserPreference> userPreference = comicService.findList(UserPreference.class);
            List<Want> wants = comicService.findList(Want.class);

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
            System.out.println("number of userPreferences = " + userPreference.size());
            System.out.println("number of wants = " + wants.size());

            System.out.println(sdf.format(new Date()) + " Finished.");
        }
        catch(Exception e) {
            System.err.println("Unable to get all.");
            System.err.println(e);
        }
    }

    public void testCategorySave() {

        try {

            // Change category.
            Category category = comicService.find(Category.class, 1);
            category.setName("another test");
            User user = comicService.find(User.class, 1);

            // Save the category.
            comicService.save(category, user);
        }
        catch(Exception e) {
            System.err.println("Unable to save category.");
            System.err.println(e);
        }
    }

    public void testComicSave() {

        try {

            // Create comic.
            Comic comic = comicService.find(Comic.class, 9815);
            comic.setId(null);
            comic.setNumber(999);
            User user = comicService.find(User.class, 1);

            // Save the comic.
            comicService.save(comic, user);
        }
        catch(Exception e) {
            System.err.println("Unable to save comic.");
            System.err.println(e);
        }
    }
}
