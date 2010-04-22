package comic.repository.jpa;

import comic.api.criteria.Criteria;
import comic.api.criteria.CriteriaFactory;
import comic.api.criteria.criterion.ComparisonOperation;
import comic.api.criteria.criterion.ComparisonOperator;
import comic.api.criteria.criterion.LogicalOperation;
import comic.api.criteria.criterion.LogicalOperator;
import comic.api.criteria.criterion.Order;
import comic.api.criteria.criterion.OrderDirection;
import comic.api.repository.ComicRepository;
import comic.api.repository.ComicRepositoryFactory;
import comic.api.model.Category;
import comic.api.model.Character;
import comic.api.model.Comic;
import comic.api.model.ComicGrade;
import comic.api.model.ComicType;
import comic.api.model.Creator;
import comic.api.model.Distribution;
import comic.api.model.Have;
import comic.api.model.Image;
import comic.api.model.ImageType;
import comic.api.model.Imprint;
import comic.api.model.Location;
import comic.api.model.Person;
import comic.api.model.Profession;
import comic.api.model.Publisher;
import comic.api.model.Title;
import comic.api.model.TitleType;
import comic.api.model.Trait;
import comic.api.model.User;
import comic.api.model.UserPreference;
import comic.api.model.Want;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class JPAComicRepositoryTester {

    Criteria<Category> categoryCriteria;
    Criteria<Character> characterCriteria;
    Criteria<Comic> comicCriteria;
    Criteria<ComicGrade> comicGradeCriteria;
    Criteria<ComicType> comicTypeCriteria;
    Criteria<Creator> creatorCriteria;
    Criteria<Distribution> distributionCriteria;
    Criteria<Have> haveCriteria;
    Criteria<Image> imageCriteria;
    Criteria<ImageType> imageTypeCriteria;
    Criteria<Imprint> imprintCriteria;
    Criteria<Location> locationCriteria;
    Criteria<Person> personCriteria;
    Criteria<Profession> professionCriteria;
    Criteria<Publisher> publisherCriteria;
    Criteria<Title> titleCriteria;
    Criteria<TitleType> titleTypeCriteria;
    Criteria<Trait> traitCriteria;
    Criteria<User> userCriteria;
    Criteria<Want> wantCriteria;
    Criteria<UserPreference> userPreferenceCriteria;
    ComicRepository comicRepository;
    SimpleDateFormat sdf;

    public JPAComicRepositoryTester() {
        
        try {
            
            // Declare.
            CriteriaFactory criteriaFactory;
            ComicRepositoryFactory comicRepositoryFactory;

            // Get the factories.
            criteriaFactory = CriteriaFactory.instance();
            comicRepositoryFactory = ComicRepositoryFactory.instance();

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

            // Create the comic repository.
            comicRepository = comicRepositoryFactory.createComicRepository();

            sdf = new SimpleDateFormat("MM/DD/yyyy HH:mm:ss");
        }
        catch(Exception e) {
            System.err.println("Unable to get the comic repository.");
            System.err.println(e);
        }
    }

    public static void main(String[] args) {

        JPAComicRepositoryTester tester = new JPAComicRepositoryTester();

        //tester.testFindList();
        //tester.testFindListByCriteria();
        tester.testFindTitleListByCriteria();
        
    }

    public void testFindList() {

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
    
    public void testFindListByCriteria() {

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

    public void testFindTitleListByCriteria() {

        try {

            System.out.println(sdf.format(new Date()) + " Fetching titles ...");

            Publisher publisher = comicRepository.find(Publisher.class, 5);
            titleCriteria.add(ComparisonOperation.memberOf("publishers", publisher));
            TitleType type = comicRepository.find(TitleType.class, 1);
            System.out.println(type);
            titleCriteria.add(LogicalOperation.and(ComparisonOperation.eq("type", type)));
            titleCriteria.addOrder(Order.asc("name"));
            System.out.println(titleCriteria.getQlString());
            
            List<Title> titles = comicRepository.findList(titleCriteria);

            System.out.println("number of titles = " + titles.size());
            for (Title title: titles) {
                System.out.println(title);
            }
            
            System.out.println(sdf.format(new Date()) + " Finished.");
        }
        catch(Exception e) {
            System.err.println("Unable to find title list by query criteria.");
            System.err.println(e);
        }
    }

    public void testComicPersist() {

        Distribution distribution = comicRepository.find(Distribution.class, 1);
        Title title = comicRepository.find(Title.class, 1);
        ComicType type = comicRepository.find(ComicType.class, 1);

        // Create comic.
        Comic comic = new Comic();
        comic.setDistribution(distribution);
        comic.setNumber(9999);
        comic.setPrint(1);
        comic.setTitle(title);
        comic.setType(type);
        comic.setVariant("Z");

        // Persist the comic.
        comicRepository.persist(comic);
    }

    public void testImagePersist() {

        // Create image.
        Image image = new Image();
        image.setFileName("test2.jpg");
        image.setLabel("Test");
        ImageType type = comicRepository.find(ImageType.class, 6);
        image.setType(type);
        image.setCreateTime(new Date());
        image.setModifyTime(new Date());

        // Persist image.
        comicRepository.persist(image);
    }
}
