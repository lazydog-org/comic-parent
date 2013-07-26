package org.lazydog.comic.manager.filter;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.lazydog.comic.ComicService;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.entry.EntryService;
import org.lazydog.repository.Criteria;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Authentication filter.
 * 
 * @author  Ron Rickard
 */
@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
    private ComicService comicService;
    private EntryService entryService;
    
    /**
     * Not implemented.
     */
    @Override
    public void destroy() {    
    }
    
    /**
     * Process the filter.
     * 
     * @param  request   the servlet request.
     * @param  response  the servlet response.
     * @param  chain     the filter chain.
     * 
     * @param  phaseEvent  the phase event.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpSession httpSession = httpServletRequest.getSession(true);
        String username = httpServletRequest.getRemoteUser();
        String uuid = (String)httpSession.getAttribute(SessionKey.UUID.toString());
        logger.debug("filter - username: {}", username);
        logger.debug("filter - uuid: {}", uuid);
        
        try {

            // Check if the user is logged in and the UUID is not stored on the session.
            if (username != null && uuid == null) {
                
                // Declare.
                Criteria<UserPreference> criteria;
                UserPreference userPreference;

                // Get the UUID.
                //uuid = this.entryService.getUserProfile(username).getUuid();
                uuid = "aa8a27ff-5fdd-400e-b51f-4d634df74bff";

                // Put the UUID on the session.
                httpSession.setAttribute(SessionKey.UUID.toString(), uuid);
                logger.debug("filter - add uuid {} to the session", uuid);

                // Get the user preference.
                criteria = comicService.getCriteria(UserPreference.class);
                criteria.add(ComparisonOperation.eq("uuid", uuid));
                userPreference = this.comicService.find(UserPreference.class, criteria);

                // Put the user preference on the session.
                httpSession.setAttribute(SessionKey.USER_PREFERENCE.toString(), userPreference);
                logger.debug("filter - add userPreference {} to the session", userPreference);
            }
            
            
        } catch (Exception e) {
            logger.error("Unable to authenticate the user.");
        } finally {
            
            // Continue processing filters.
            chain.doFilter(request, response);
        }
    }
    
    /**
     * Not implemented.
     */
    @Override
    public void init(FilterConfig config) throws ServletException {    
    }

    /**
     * Set the comic service.
     *
     * @param  comicService  the comic service.
     */
    @EJB
    protected void setComicService(ComicService comicService) {
        this.comicService = comicService;
    }

    /**
     * Set the entry service.
     *
     * @param  entryService  the entry service.
     */
    protected void setEntryService(EntryService entryService) {
        this.entryService = entryService;
    }
}
