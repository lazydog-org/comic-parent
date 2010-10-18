package org.lazydog.comic.manager.phaselistener;

import org.lazydog.comic.ComicService;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.utility.ImageSearchBy;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.comic.manager.utility.TitleSearchBy;
import org.lazydog.entry.EntryService;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.Criteria;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 * Authenticate phase listener.
 * 
 * @author  Ron Rickard
 */
public class Authenticate
       implements PhaseListener {

    private static final long serialVersionUID = 1L;
    
    /**
     * Process after the phase.
     * 
     * @param  phaseEvent  the phase event.
     */
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        
        try {

            // Check if the user is logged in and not stored on the session.
            if (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() != null &&
                SessionUtility.getValue(SessionKey.UUID, String.class) == null) {
                
                // Declare.
                ComicService comicService;
                Context context;
                Criteria<UserPreference> criteria;
                EntryService entryService;
                String username;
                UserPreference userPreference;
                String uuid;

                // Get the comic service and entry service.
                context = new InitialContext();
                comicService = (ComicService)context.lookup("ejb/ComicService");
                entryService = (EntryService)context.lookup("ejb/EntryService");

                // Get the username.
                username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

                // Get the UUID.
                uuid = entryService.getUserProfile(username).getUuid();

                // Put the UUID on the session.
                SessionUtility.putValue(SessionKey.UUID, uuid);

                // Get the user preference.
                criteria = comicService.getCriteria(UserPreference.class);
                criteria.add(ComparisonOperation.eq("uuid", uuid));
                userPreference = comicService.find(UserPreference.class, criteria);

                // Put the user preference on the session.
                SessionUtility.putValue(SessionKey.USER_PREFERENCE, userPreference);

                // Put the default image search by and image search for on the session.
                SessionUtility.putValue(SessionKey.IMAGE_SEARCH_BY,
                        ImageSearchBy.IMAGE_FILE_NAME);
                SessionUtility.putValue(SessionKey.IMAGE_SEARCH_FOR, "");

                // Put the default title search by and title search for on the session.
                SessionUtility.putValue(
                        SessionKey.TITLE_SEARCH_BY, TitleSearchBy.PUBLISHER_NAME);
                SessionUtility.putValue(
                        SessionKey.TITLE_SEARCH_FOR, userPreference.getPublisher());
            }
        }
        catch(Exception e) {
e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Unable to authenticate user."));
        }
    }
    
    /**
     * Process before the phase.
     * 
     * @param  phaseEvent  the phase event.
     */
    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
    }
    
    /**
     * Get the phase ID.
     * 
     * @return  the phase ID (restore view.)
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
