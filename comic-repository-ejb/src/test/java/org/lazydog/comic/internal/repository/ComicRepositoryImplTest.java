package org.lazydog.comic.internal.repository;

import java.util.Date;
import java.util.List;
import org.lazydog.comic.ComicRepository;
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
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.model.Want;
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.JoinOperation;
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

    private static ComicRepository repository;

    @BeforeClass
    public static void initialize() throws Exception {

        // Get the comic repository.
        repository = new ComicRepositoryWrapper();
    }

    @Before
    public void beforeTest() throws Exception {
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
    public void findListComicEager() throws Exception {

        Criteria<Comic> criteria;
        List<Comic> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        criteria = repository.getCriteria(Comic.class);
        criteria.addJoin(JoinOperation.leftJoinFetch("characters"));
        criteria.addJoin(JoinOperation.leftJoinFetch("creators"));
        criteria.addJoin(JoinOperation.leftJoinFetch("traits"));
        entities = repository.findList(Comic.class, criteria);
        endTime = new Date();
        System.out.println(entities.size() + " " + Comic.class.getSimpleName()
                + "s retrieved in " + duration(startTime, endTime)
                + " seconds");
    }

    @Test
    public void findListComicCharacter() throws Exception {
        findList(ComicCharacter.class);
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
    public void findListTitleEager() throws Exception {

        Criteria<Title> criteria;
        List<Title> entities;
        Date endTime;
        Date startTime;

        startTime = new Date();
        criteria = repository.getCriteria(Title.class);
        criteria.addJoin(JoinOperation.leftJoinFetch("categories"));
        criteria.addJoin(JoinOperation.leftJoinFetch("publishers"));
        entities = repository.findList(Title.class, criteria);
        endTime = new Date();
        System.out.println(entities.size() + " " + Title.class.getSimpleName()
                + "s retrieved in " + duration(startTime, endTime)
                + " seconds");
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
    public void findListUserPreference() throws Exception {
        findList(UserPreference.class);
    }

    @Test
    public void findListWant() throws Exception {
        findList(Want.class);
    }
}
