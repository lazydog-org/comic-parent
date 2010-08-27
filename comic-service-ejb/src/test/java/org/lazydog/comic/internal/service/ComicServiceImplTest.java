package org.lazydog.comic.internal.service;

import java.util.Date;
import java.util.List;
import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.Category;
import org.lazydog.comic.model.Character;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.ComicGrade;
import org.lazydog.comic.model.ComicType;
import org.lazydog.comic.model.Creator;
import org.lazydog.comic.model.Distribution;
import org.lazydog.comic.model.Entity;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


/**
 * Unit tests for ComicServiceImpl class.
 *
 * @author  Ron Rickard
 */
public class ComicServiceImplTest {

    private static ComicService service;

    @BeforeClass
    public static void initialize() throws Exception {

        // Get the comic service.
        service = new ComicServiceWrapper();
    }

    @Before
    public void beforeTest() throws Exception {
        service = null;
        System.gc();
        Thread.sleep(1000);
        service = new ComicServiceWrapper();
    }

    private static double duration(Date startTime, Date endTime) {
        return (endTime.getTime() - startTime.getTime()) / 1000d;
    }

    private static <T extends Entity<T>> void findList(Class<T> entityClass) {

        List<T> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        entities = service.findList(entityClass);
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
    public void findListCharacter() throws Exception {
        findList(Character.class);
    }

    @Test
    public void findListComic() throws Exception {
        findList(Comic.class);
    }

    @Test
    public void findListComicGrade() throws Exception {
        findList(ComicGrade.class);
    }

    @Test
    public void findListComicType() throws Exception {
        findList(ComicType.class);
    }

    @Test
    public void findListCreator() throws Exception {
        findList(Creator.class);
    }

    @Test
    public void findListDistribution() throws Exception {
        findList(Distribution.class);
    }

    @Test
    public void findListHave() throws Exception {
        findList(Have.class);
    }

    @Test
    public void findListImage() throws Exception {
        findList(Image.class);
    }

    @Test
    public void findListImageType() throws Exception {
        findList(ImageType.class);
    }

    @Test
    public void findListImprint() throws Exception {
        findList(Imprint.class);
    }

    @Test
    public void findListLocation() throws Exception {
        findList(Location.class);
    }

    @Test
    public void findListPerson() throws Exception {
        findList(Person.class);
    }

    @Test
    public void findListProfession() throws Exception {
        findList(Profession.class);
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
    public void findListTitleType() throws Exception {
        findList(TitleType.class);
    }

    @Test
    public void findListTrait() throws Exception {
        findList(Trait.class);
    }

    @Test
    public void findListUser() throws Exception {
        findList(User.class);
    }

    @Test
    public void findListUserPreference() throws Exception {
        findList(UserPreference.class);
    }

    @Test
    public void findListWant() throws Exception {
        findList(Want.class);
    }
}
