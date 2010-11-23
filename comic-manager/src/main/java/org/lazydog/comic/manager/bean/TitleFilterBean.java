package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.lazydog.comic.manager.utility.ButtonLinkController;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.model.Category;
import org.lazydog.comic.model.Publisher;
import org.lazydog.comic.model.TitleType;


/**
 * Title filter managed bean.
 *
 * @author  Ron Rickard
 */
public class TitleFilterBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Category category;
    private String name;
    private Publisher publisher;
    private TitleBean titleBean;
    private TitleType type;

    /**
     * Apply the filter.
     *
     * @param  event  the action event.
     */
    public void applyFilter(ActionEvent event) {

        // Store the title filter values on the session.
        SessionUtility.putValue(SessionKey.TITLE_FILTER_CATEGORY, this.category);
        SessionUtility.putValue(SessionKey.TITLE_FILTER_NAME, this.name);
        SessionUtility.putValue(SessionKey.TITLE_FILTER_PUBLISHER, this.publisher);
        SessionUtility.putValue(SessionKey.TITLE_FILTER_TYPE, this.type);

        // Process the first button.
        this.titleBean.processFirstButton(event);
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
     * Get the category.
     *
     * @return  the category.
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * Get the name.
     *
     * @return  the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the publisher.
     *
     * @return  the publisher.
     */
    public Publisher getPublisher() {
        return this.publisher;
    }

    /**
     * Get the title bean.
     * 
     * @return  the title bean.
     */
    public TitleBean getTitleBean() {
        return this.titleBean;
    }

    /**
     * Get the type.
     *
     * @return  the type.
     */
    public TitleType getType() {
        return this.type;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Set the title filter values according to what is stored in the session.
        this.category = SessionUtility.getValue(SessionKey.TITLE_FILTER_CATEGORY, Category.class);
        this.name = SessionUtility.getValue(SessionKey.TITLE_FILTER_NAME, String.class);
        this.publisher = SessionUtility.getValue(SessionKey.TITLE_FILTER_PUBLISHER, Publisher.class);
        this.type = SessionUtility.getValue(SessionKey.TITLE_FILTER_TYPE, TitleType.class);
    }

    /**
     * Reset the filter.
     *
     * @param  event  the action event.
     */
    public void resetFilter(ActionEvent event) {

        // Set the title filter values according to what is stored in the session.
        this.category = SessionUtility.getValue(SessionKey.TITLE_FILTER_CATEGORY, Category.class);
        this.name = SessionUtility.getValue(SessionKey.TITLE_FILTER_NAME, String.class);
        this.publisher = SessionUtility.getValue(SessionKey.TITLE_FILTER_PUBLISHER, Publisher.class);
        this.type = SessionUtility.getValue(SessionKey.TITLE_FILTER_TYPE, TitleType.class);
    }

    /**
     * Set the category.
     *
     * @param  category  the category.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Set the name.
     *
     * @param  name  the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the publisher.
     *
     * @param  publisher  the publisher.
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


    /**
     * Set the title bean.
     *
     * @param  titleBean  the title bean.
     */
    public void setTitleBean(TitleBean titleBean) {
        this.titleBean = titleBean;
    }

    /**
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(TitleType type) {
        this.type = type;
    }
}
