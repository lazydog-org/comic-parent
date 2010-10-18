package org.lazydog.comic.manager.bean;

import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.utility.ImageSearchBy;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.TitleSearchBy;
import org.lazydog.entry.EntryService;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.Criteria;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;


/**
 * User managed bean.
 * 
 * @author  Ron Rickard
 */
public class UserBean
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Is the user authenticated?
     *
     * @return  true if the user is authenticated, otherwise false.
     */
    public Boolean getAuthenticated() {
        return (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null);
    }
}
