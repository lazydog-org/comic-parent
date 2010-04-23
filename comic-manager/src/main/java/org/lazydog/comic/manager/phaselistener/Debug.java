package org.lazydog.comic.manager.phaselistener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;


/**
 * Debug phase listener.
 * 
 * @author  Ron Rickard
 */
public class Debug
       implements PhaseListener {

    /**
     * Process after the phase.
     * 
     * @param  phaseEvent  the phase event.
     */
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        
        System.out.println("--- after - " + phaseEvent.getPhaseId().toString());
        
        // Check if this is the render response phase.
        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            System.out.println("=== Done processing new request ===");
        }
    }
    
    /**
     * Process before the phase.
     * 
     * @param  phaseEvent  the phase event.
     */
    @Override
    public void beforePhase(PhaseEvent phaseEvent) {
        
        // Check if this is the restore view phase.
        if (phaseEvent.getPhaseId() == PhaseId.RESTORE_VIEW) {
            System.out.println("=== Processing new request ===");
        }
        
        System.out.println("--- before - " + phaseEvent.getPhaseId().toString());
    }
    
    /**
     * Get the phase ID.
     * 
     * @return  the phase ID (any phase.)
     */
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
