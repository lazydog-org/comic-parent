package comic.manager.bean;

import comic.api.criteria.criterion.ComparisonOperation;
import comic.api.criteria.Criteria;
import comic.api.criteria.CriteriaFactory;
import comic.api.model.User;
import comic.api.service.ComicService;
import comic.manager.utility.SessionKey;
import comic.manager.utility.SessionUtility;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * User managed bean.
 * 
 * @author  Ron Rickard
 */
public class UserBean
       implements Serializable {

    private static final String FAILURE = "failure";
    private static final String SUCCESS = "success";

    private boolean authenticated;
    private String name;
    private String password;
    private ComicService comicService;

    /**
     * Authenticate.
     *
     * @param  actionEvent  action event.
     *
     * @return  success or failure outcome.
     */
    public void authenticate(ActionEvent actionEvent) {

        try {

            // Declare.
            Criteria<User> criteria;
            CriteriaFactory criteriaFactory;
            User user;

            // Initialize criteria factory.
            criteriaFactory = CriteriaFactory.instance();

            // Create a new criteria.
            criteria = criteriaFactory.createCriteria(User.class);

            // Modify the criteria.
            criteria.add(ComparisonOperation.eq("name", this.name));

            // Get the user.
            user = this.comicService.find(criteria);

            // Check if the user exists.
            if (user != null) {

                // Put the user on the session.
                SessionUtility.putValue(SessionKey.USER, user);

                // Put the user preference on the session.
                SessionUtility.putValue(
                        SessionKey.USER_PREFERENCE, user.getUserPreference());

                // Flag that the user is authenticated.
                this.authenticated = true;
            }
            else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Invalid user name or password entered."));
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Invalid user name or password entered."));
        }
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
     * Get the password.
     *
     * @return  the password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Login.
     *
     * @return  success or failure outcome.
     */
    public String login() {

        // Declare.
        String outcome;

        // Set the outcome to failure.
        outcome = FAILURE;

        if (this.authenticated) {

            // Set the outcome to success.
            outcome = SUCCESS;
        }
        else {

            // Set the outcome to failure.
            outcome = FAILURE;
        }

        return outcome;
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
     * Set the password.
     *
     * @param  password  the password.
     */
    public void setPassword(String password) {
        this.password = password;
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
