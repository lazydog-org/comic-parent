package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.faces.context.FacesContext;


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
