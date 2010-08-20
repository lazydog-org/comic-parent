package org.lazydog.comic.manager.report.bean;

import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.Have;
import org.lazydog.comic.model.Title;
import org.lazydog.comic.model.User;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.data.access.criterion.ComparisonOperation;
import org.lazydog.data.access.criterion.LogicalOperation;
import org.lazydog.data.access.criterion.Order;
import org.lazydog.data.access.Criteria;
import org.lazydog.data.access.CriteriaFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;


/**
 * Title report managed bean.
 *
 * @author  Ron Rickard
 */
public class TitleReportBean
       implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ComicService comicService;
    private List<Have> haves;
    private Title title;
    private List<Title> titles;

    /**
     * Get the haves.
     *
     * @return  the haves.
     */
    public List<Have> getHaves() {
        return this.haves;
    }

    /**
     * Get the report rendered.
     *
     * @return  the report rendered.
     */
    public Boolean getReportRendered() {
        return (this.haves != null) ? true : false;
    }

    /**
     * Get the title.
     *
     * @return  the title.
     */
    public Title getTitle() {
        return this.title;
    }

    /**
     * Get the titles.
     *
     * @return  the titles.
     */
    private List<Title> getTitles() {

        // Check if the titles exist.
        if (this.titles == null) {

            try {

                // Declare.
                Criteria<Have> criteria;
                CriteriaFactory criteriaFactory;
                List<Have> haves;

                // Initialize criteria factory.
                criteriaFactory = CriteriaFactory.instance();

                // Create the criteria.
                criteria = criteriaFactory.createCriteria(Have.class);
                criteria.add(ComparisonOperation.eq("createUser",
                        SessionUtility.getValue(SessionKey.USER, User.class)));
                criteria.addOrder(Order.asc("comic.title.name"));

                // Get the haves.
                haves = this.comicService.findList(criteria);

                // Check if there are haves.
                if (haves != null) {

                    // Initialize the titles.
                    this.titles = new ArrayList<Title>();

                    // Loop through the haves.
                    for (Have have : haves) {

                        // Check if the have comic title does not exist on the title list.
                        if (!this.titles.contains(have.getComic().getTitle())) {

                            // Add the have comic title to the title list.
                            this.titles.add(have.getComic().getTitle());
                        }
                    }
                }
            }
            catch(Exception e) {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Cannot get the titles."));
            }
        }

        return titles;
    }

    /**
     * Get the titles as select items.
     *
     * @return  the titles as select items.
     */
    public List<SelectItem> getTitlesAsSelectItems() {

        // Declare.
        List<SelectItem> titlesAsSelectItems;

        // Initialize.
        titlesAsSelectItems = new ArrayList<SelectItem>();

        // Loop through the entities.
        for(Title title : this.getTitles()) {

            // Add the entity to the select items.
            titlesAsSelectItems.add(new SelectItem(
                title, title.getName() + " (Vol. " + title.getVolume() + ")"));
        }

        return titlesAsSelectItems;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.title = new Title();
    }

    /**
     * Process the report button.
     *
     * @param  actionEvent  the action event.
     */
    public void processReportButton(ActionEvent actionEvent) {

        try {

            // Declare.
            Criteria<Have> criteria;
            CriteriaFactory criteriaFactory;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create the criteria.
            criteria = criteriaFactory.createCriteria(Have.class);
            criteria.add(ComparisonOperation.eq("comic.title", this.title));
            criteria.add(LogicalOperation.and(ComparisonOperation.eq("createUser",
                    SessionUtility.getValue(SessionKey.USER, User.class))));
            criteria.addOrder(Order.asc("comic.number"));
            criteria.addOrder(Order.asc("comic.variant"));
            criteria.addOrder(Order.asc("comic.type.value"));
            criteria.addOrder(Order.asc("comic.distribution.value"));
            criteria.addOrder(Order.asc("location.name"));
            criteria.addOrder(Order.desc("comicGrade.scale"));

            // Get the haves.
            this.haves = this.comicService.findList(criteria);
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot generate the title report."));
        }
    }

    /**
     * Set the comic service.
     *
     * @param  comicService  the comic service.
     */
    @EJB(mappedName="ejb/ComicService", beanInterface=ComicService.class)
    protected void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    /**
     * Set the title.
     *
     * @param  title  the title.
     */
    public void setTitle(Title title) {
        this.title = title;
    }
}
