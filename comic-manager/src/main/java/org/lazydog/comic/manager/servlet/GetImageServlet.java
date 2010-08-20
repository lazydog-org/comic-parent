package org.lazydog.comic.manager.servlet;

import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.ImageType;
import org.lazydog.data.access.criterion.ComparisonOperation;
import org.lazydog.data.access.Criteria;
import org.lazydog.data.access.CriteriaFactory;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Get image servlet.
 * 
 * @author  Ron Rickard
 */
public class GetImageServlet 
       extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String ADMIN_DIRECTORY_NAME = "/image/admin";
    private static final String NO_IMAGE_FILE_NAME = "noImage.jpg";
    private static final String FILE_NAME_PARAMETER = "fileName";
    private static final String TYPE_VALUE_PARAMETER = "typeValue";
    private static final String HEIGHT_PARAMETER = "height";
    
    private ComicService comicService;
    
    /**
     * Resize the image.
     * 
     * @param  image   the image.
     * @param  width   the width.
     * @param  height  the height.
     * 
     * @return  the resized image.
     */
    private BufferedImage resizeImage(BufferedImage image,
                                      int width,
                                      int height) {

        // Declare.
        Graphics2D graphics;
        BufferedImage resizedImage;

        // Resize the image.
        resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        graphics = resizedImage.createGraphics();
        graphics.setComposite(AlphaComposite.Src);
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        
        return resizedImage;
    }

    /**
     * Process get request.
     * 
     * @param  request   the HTTP servlet request.
     * @param  response  the HTTP servlet response.
     * 
     * @throws  ServletException  if unable to process get request.
     * @throws  IOException       if unable to process get request.
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
           throws ServletException, 
                  IOException {
        
        // Declare.
        String directoryPath;
        File file;
        String fileName;
        String heightAsString;
        String typeValue;

        
        // Get the file name and type value.
        fileName = request.getParameter(FILE_NAME_PARAMETER);
        typeValue = request.getParameter(TYPE_VALUE_PARAMETER);
        heightAsString = request.getParameter(HEIGHT_PARAMETER);
    
        // Get the directory path.
        directoryPath = this.getDirectoryPath(typeValue);

        // Get the file.
        file = new File(directoryPath, fileName);
            
        // Check if the file does not exist.
        if (!file.exists()) {

            // Get the "no image" file.
            file = new File(ADMIN_DIRECTORY_NAME, NO_IMAGE_FILE_NAME);
        }

        // Check if the file exists.
        if (file.exists()) {

            try {

                // Declare.
                BufferedImage image;

                // Get the image from the server.
                image = ImageIO.read(file);

                // Check if the width parameter exists.
                if (heightAsString != null) {

                    // Declare.
                    int height;
                    int width;

                    // Parse the width.
                    height = Integer.parseInt(heightAsString);

                    // Calculate the width of the new image.
                    width = height * image.getWidth() / image.getHeight();

                    // Resize the image.
                    image = this.resizeImage(image, width, height);
                }
                
                // Set the content type.
                response.setContentType("image/jpeg");
                
                // Write the image.
                ImageIO.write(image, "jpg", response.getOutputStream());
            }
            catch(IOException e) {
                // Ignore.
            }
        }
    }
        
    /**
     * Process post request.
     * 
     * @param  request   the HTTP servlet request.
     * @param  response  the HTTP servlet response.
     * 
     * @throws  ServletException  if unable to process post request.
     * @throws  IOException       if unable to process post request.
     */
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
           throws ServletException, 
                  IOException {
        this.doGet(request, response);
    }
    
    /**
     * Get the directory path.
     * 
     * @param  typeValue  the type value.
     * 
     * @return  the directory path.
     */
    private String getDirectoryPath(String typeValue) {
        
        // Declare.
        String directoryPath;
        
        // Initialize.
        directoryPath = null;
        
        try {
            
            // Check if the type value exists.
            if (typeValue != null && !"".equals(typeValue)) {

                // Declare.
                Criteria<ImageType> criteria;
                CriteriaFactory criteriaFactory;
                ImageType imageType;

                // Initialize criteria factory.
                criteriaFactory = CriteriaFactory.instance();

                // Create a new criteria.
                criteria = criteriaFactory.createCriteria(ImageType.class);

                // Modify the criteria.
                criteria.add(ComparisonOperation.eq("value", typeValue));

                // Get the image type.
                imageType = this.comicService.find(criteria);

                // Get the directory path.
                directoryPath = imageType.getDirectoryPath();
            }
        }
        catch(Exception e) {
            // Ignore.
        }
        
        return directoryPath;
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
}
