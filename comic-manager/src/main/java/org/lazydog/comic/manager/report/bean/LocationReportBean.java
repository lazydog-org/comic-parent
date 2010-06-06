package org.lazydog.comic.manager.report.bean;

import org.lazydog.comic.criteria.criterion.ComparisonOperation;
import org.lazydog.comic.criteria.criterion.LogicalOperation;
import org.lazydog.comic.criteria.criterion.Order;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.ApplicationUser;
import org.lazydog.comic.model.Have;
import org.lazydog.comic.model.Location;
import org.lazydog.comic.service.ComicService;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * Location report managed bean.
 *
 * @author  Ron Rickard
 */
public class LocationReportBean
       implements Serializable {

    protected ComicService comicService;
    private List<Have> haves;
    private Location location;

    /**
     * Get the haves.
     *
     * @return  the haves.
     */
    public List<Have> getHaves() {
        return this.haves;
    }

    /**
     * Get the location.
     *
     * @return  the location.
     */
    public Location getLocation() {
        return this.location;
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
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.location = new Location();
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
            criteria.add(ComparisonOperation.eq("location", this.location));
            criteria.add(LogicalOperation.and(ComparisonOperation.eq("createUser",
                    SessionUtility.getValue(SessionKey.USER, ApplicationUser.class))));
            criteria.addOrder(Order.asc("comic.title.name"));
            criteria.addOrder(Order.asc("comic.number"));
            criteria.addOrder(Order.asc("comic.variant"));
            criteria.addOrder(Order.asc("comic.type.value"));
            criteria.addOrder(Order.asc("comic.distribution.value"));
            criteria.addOrder(Order.desc("comicGrade.scale"));

            // Get the haves.
            this.haves = this.comicService.findList(criteria);
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot generate the location report."));
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
     * Set the location.
     *
     * @param  location  the location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
