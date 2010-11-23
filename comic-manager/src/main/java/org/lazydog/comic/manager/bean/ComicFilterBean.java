package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.lazydog.comic.manager.utility.ButtonLinkController;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.model.ComicType;
import org.lazydog.comic.model.Distribution;


/**
 * Comic filter managed bean.
 *
 * @author  Ron Rickard
 */
public class ComicFilterBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private ComicBean comicBean;
    private Distribution distribution;
    private ComicType type;

    /**
     * Apply the filter.
     *
     * @param  event  the action event.
     */
    public void applyFilter(ActionEvent event) {

        // Store the comic filter values on the session.
        SessionUtility.putValue(SessionKey.COMIC_FILTER_DISTRIBUTION, this.distribution);
        SessionUtility.putValue(SessionKey.COMIC_FILTER_TYPE, this.type);

        // Process the first button.
        this.comicBean.processFirstButton(event);
    }

    /**
     * Get the button/link controller.
     *
     * @return  the button/link controller.
     */
    public ButtonLinkController getButtonLinkController() {
        return SessionUtility.getValue(
                SessionKey.BUTTON_LINK_CONTROLLER, ButtonLinkController.class);
    }

    /**
     * Get the comic bean.
     * 
     * @return  the comic bean.
     */
    public ComicBean getComicBean() {
        return this.comicBean;
    }

    /**
     * Get the distribution.
     *
     * @return  the distribution.
     */
    public Distribution getDistribution() {
        return this.distribution;
    }

    /**
     * Get the type.
     *
     * @return  the type.
     */
    public ComicType getType() {
        return this.type;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Set the comic filter values according to what is stored in the session.
        this.distribution = SessionUtility.getValue(SessionKey.COMIC_FILTER_DISTRIBUTION, Distribution.class);
        this.type = SessionUtility.getValue(SessionKey.COMIC_FILTER_TYPE, ComicType.class);
    }

    /**
     * Reset the filter.
     *
     * @param  event  the action event.
     */
    public void resetFilter(ActionEvent event) {

        // Set the comic filter values according to what is stored in the session.
        this.distribution = SessionUtility.getValue(SessionKey.COMIC_FILTER_DISTRIBUTION, Distribution.class);
        this.type = SessionUtility.getValue(SessionKey.COMIC_FILTER_TYPE, ComicType.class);
    }

    /**
     * Set the comic bean.
     *
     * @param  comicBean  the comic bean.
     */
    public void setComicBean(ComicBean comicBean) {
        this.comicBean = comicBean;
    }

    /**
     * Set the distribution.
     *
     * @param  distribution  the distribution.
     */
    public void setDistribution(Distribution distribution) {
        this.distribution = distribution;
    }

    /**
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(ComicType type) {
        this.type = type;
    }
}
