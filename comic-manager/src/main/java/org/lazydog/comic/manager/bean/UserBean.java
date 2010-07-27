package org.lazydog.comic.manager.bean;

import org.lazydog.comic.criteria.criterion.ComparisonOperation;
import org.lazydog.comic.criteria.Criteria;
import org.lazydog.comic.criteria.CriteriaFactory;
import org.lazydog.comic.model.User;
import org.lazydog.comic.service.ComicService;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;


/**
 * User managed bean.
 * 
 * @author  Ron Rickard
 */
public class UserBean
       implements Serializable {

    private static final String SUCCESS = "success";

    private String name;
    private String password;
    private ComicService comicService;

    /**
     * Authenticate.
     *
     * @return  null.
     */
    public String authenticate() {

        try {

            // Declare.
            ExternalContext context;
            RequestDispatcher dispatcher;
            HttpServletRequest request;
            HttpServletResponse response;

            // Dispatch to loginProxy.jsp.
            context = FacesContext.getCurrentInstance().getExternalContext();
            request = (HttpServletRequest)context.getRequest();
            request.setAttribute("j_username", this.name);
            request.setAttribute("j_password", this.password);
            response = (HttpServletResponse)context.getResponse();
            dispatcher = request.getRequestDispatcher("/pages/common/loginProxy.jsp");
            dispatcher.forward(request, response);
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Invalid user name or password entered."));
        }

        return null;
    }

    /**
     * Is the user authenticated?
     *
     * @return  true if the user is authenticated, otherwise false.
     */
    public Boolean getAuthenticated() {
        return (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null) ?
                Boolean.TRUE : Boolean.FALSE;
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
     * @return  success outcome.
     */
    public String login() {
        return SUCCESS;
    }

    /**
     * Logout.
     *
     * @return  success outcome.
     */
    public String logout() {

        // Declare.
        ExternalContext context;
        HttpSession session;

        // Invalidate the session.
        context = FacesContext.getCurrentInstance().getExternalContext();
        session = (HttpSession)context.getSession(false);
        session.invalidate();

        return SUCCESS;
    }

    /**
     * Lookup the user.
     * 
     * @param  actionEvent  the action event.
     */
    public void lookup(ActionEvent actionEvent) {

        try {

            // Declare.
            Criteria<User> criteria;
            CriteriaFactory criteriaFactory;
            User user;

            // Initialize the criteria factory.
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
