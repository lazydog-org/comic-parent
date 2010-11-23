package org.lazydog.comic.manager.bean;

import java.io.File;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;
import org.lazydog.comic.manager.utility.ImageUtility;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.Criteria;
import org.richfaces.event.UploadEvent;


/**
 * Comic managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicUploadBean
       implements Serializable {

    private static final long serialVersionUID = 1L;

    private ComicService comicService;

    /**
     * Get the button disabled flag.
     * 
     * @return  the button disabled flag.
     */
    public Boolean getButtonDisabled() {
        return SessionUtility.getValue(SessionKey.UPLOAD_IMAGE_BUTTON_DISABLED, Boolean.class);
    }

    /**
     * Get the image.
     * 
     * @return  the image.
     */
    public Image getImage() {
        return SessionUtility.getValue(SessionKey.UPLOAD_IMAGE, Image.class);
    }

    /**
     * Process the cancel button.
     * 
     * @param  actionEvent  the action event.
     */
    public void processCancelButton(ActionEvent actionEvent) {

        try {

            // Declare.
            Criteria<ImageType> criteria;
            Image image;
            ImageType imageType;
            File jpgFile;
            File tifFile;

            // Get the JPG file.
            image = SessionUtility.getValue(SessionKey.UPLOAD_IMAGE, Image.class);
            jpgFile = new File(image.getType().getDirectoryPath(), image.getFileName());

            // Get the TIF file.
            criteria = comicService.getCriteria(ImageType.class);
            criteria.add(ComparisonOperation.eq("value", "Upload"));
            imageType = comicService.find(ImageType.class, criteria);
            tifFile = new File(
                    imageType.getDirectoryPath(),
                    ImageUtility.getTifFileName(jpgFile.getName()));

            // Delete the JPG and TIF files.
            jpgFile.delete();
            tifFile.delete();

            // Restore the comic image to the session.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE, (SessionUtility.getValue(SessionKey.COMIC, Comic.class).getImage()));

            // Set the button disable flag to true.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE_BUTTON_DISABLED, Boolean.TRUE);
        }
        catch(Exception e) {
e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot cancel adding the image to the comic."));
        }
    }

    /**
     * Process the OK button.
     *
     * @param  actionEvent  the action event.
     */
    public void processOkButton(ActionEvent actionEvent) {

        try {

            // Declare.
            Comic comic;
            Image image;

            // Get the comic and image from the session.
            comic = SessionUtility.getValue(SessionKey.COMIC, Comic.class);
            image = SessionUtility.getValue(SessionKey.UPLOAD_IMAGE, Image.class);

            // Set the image.
            comic.setImage(image);

            // Save the comic.
            this.comicService.save(comic);

            // Set the button disable flag to true.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE_BUTTON_DISABLED, Boolean.TRUE);
        }
        catch(Exception e) {
e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot add the image to the comic."));
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
     * Upload the image.
     *
     * @param  uploadEvent  the upload event.
     */
    public void uploadImage(UploadEvent uploadEvent) {

        // Declare.
        File uploadFile;

        // Get the upload file.
        uploadFile = uploadEvent.getUploadItem().getFile();

        try {

            // Declare.
            Criteria<ImageType> criteria;
            Image image;
            ImageType imageType;
            File jpgFile;
            File tifFile;

            // Get the TIF file.
            criteria = comicService.getCriteria(ImageType.class);
            criteria.add(ComparisonOperation.eq("value", "Upload"));
            imageType = comicService.find(ImageType.class, criteria);
            tifFile = ImageUtility.getUniqueFile(imageType.getDirectoryPath(), ImageUtility.TIF_EXTENSION);

            // Copy the upload file to the TIF file.
            ImageUtility.copyFile(uploadFile, tifFile);

            // Create the JPG file from the TIF file..
            criteria = comicService.getCriteria(ImageType.class);
            criteria.add(ComparisonOperation.eq("value", "Comic"));
            imageType = comicService.find(ImageType.class, criteria);
            jpgFile = ImageUtility.createJpgFromTif(tifFile, imageType.getDirectoryPath());

            // Set the image for the JPG file.
            image = new Image();
            image.setFileName(jpgFile.getName());
            image.setType(imageType);

            // Put the uploaded image on the session.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE, image);

            // Set the button disable flag to false.
            SessionUtility.putValue(SessionKey.UPLOAD_IMAGE_BUTTON_DISABLED, Boolean.FALSE);
        }
        catch(Exception e) {
e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot upload the comic image."));
        }
        finally {

            // Delete the upload file.
            uploadFile.delete();
        }
    }
}
