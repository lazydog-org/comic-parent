package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import org.lazydog.comic.manager.utility.ButtonLinkController;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.model.ImageType;


/**
 * Image filter managed bean.
 *
 * @author  Ron Rickard
 */
public class ImageFilterBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String fileName;
    private ImageBean imageBean;
    private ImageType type;

    /**
     * Apply the filter.
     *
     * @param  event  the action event.
     */
    public void applyFilter(ActionEvent event) {
        
        // Store the image filter values on the session.
        SessionUtility.putValue(SessionKey.IMAGE_FILTER_FILE_NAME, this.fileName);
        SessionUtility.putValue(SessionKey.IMAGE_FILTER_TYPE, this.type);

        // Process the first button.
        this.imageBean.processFirstButton(event);
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
     * Get the file name.
     *
     * @return  the file name.
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Get the image bean.
     * 
     * @return  the image bean.
     */
    public ImageBean getImageBean() {
        return this.imageBean;
    }

    /**
     * Get the type.
     *
     * @return  the type.
     */
    public ImageType getType() {
        return this.type;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Set the image filter values according to what is stored in the session.
        this.fileName = SessionUtility.getValue(SessionKey.IMAGE_FILTER_FILE_NAME, String.class);
        this.type = SessionUtility.getValue(SessionKey.IMAGE_FILTER_TYPE, ImageType.class);
    }

    /**
     * Reset the filter.
     *
     * @param  event  the action event.
     */
    public void resetFilter(ActionEvent event) {

        // Set the image filter values according to what is stored in the session.
        this.fileName = SessionUtility.getValue(SessionKey.IMAGE_FILTER_FILE_NAME, String.class);
        this.type = SessionUtility.getValue(SessionKey.IMAGE_FILTER_TYPE, ImageType.class);
    }

    /**
     * Set the file name.
     *
     * @param  fileName  the file name.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Set the image bean.
     * 
     * @param  imageBean  the image bean.
     */
    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    /**
     * Set the type.
     *
     * @param  type  the type.
     */
    public void setType(ImageType type) {
        this.type = type;
    }
}
