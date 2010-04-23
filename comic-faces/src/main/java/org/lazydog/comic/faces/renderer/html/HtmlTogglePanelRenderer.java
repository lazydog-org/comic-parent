package org.lazydog.comic.faces.renderer.html;

import org.lazydog.comic.faces.component.TogglePanel;
import java.io.IOException;
import java.util.Map;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;


/**
 * HTML toggle panel JavaServer Faces UI component renderer.
 * 
 * @author  Ron Rickard
 */
public class HtmlTogglePanelRenderer 
       extends Renderer {

    /**
     * Decode the request from the client.
     * 
     * @param  context    the faces context.
     * @param  component  the UI component.
     */
    @Override
    public void decode(FacesContext context,
                       UIComponent component) {
        
        // Declare.
        String expanded;
        Map requestMap;

        // Get the request parameter map.
        requestMap = context.getExternalContext().getRequestParameterMap();
        
        // Get the expanded hidden field value.
        expanded = (String)requestMap.get("expanded" + component.getId());
     
        // Set the expanded hidden field value in the component.
        ((TogglePanel)component).setExpanded(Boolean.valueOf(expanded));
    }
                       
    /**
     * Encode a response for the client.
     * 
     * @param  context    the faces context.
     * @param  component  the UI component.
     * 
     * @throws  IOException  if unable to encode a response.
     */
    @Override
    public void encodeBegin(FacesContext context,
                            UIComponent component) 
           throws IOException {
        
        // Declare.
        ResponseWriter writer;        

        // Get the response writer.
        writer = context.getResponseWriter();

        // Render a response.
        writer.startElement("div", component);
        writer.startElement("div", component);
        writer.writeAttribute("id", component.getId(), null);
        if (isExpanded(component)) {
            writer.writeAttribute("style", "display: block;", null);
        }
        else {
            writer.writeAttribute("style", "display: none;", null);
        }
    }
    
    /**
     * Encode a response for the client.
     * 
     * @param  context    the faces context.
     * @param  component  the UI component.
     * 
     * @throws  IOException  if unable to encode a response.
     */
    @Override
    public void encodeEnd(FacesContext context,
                          UIComponent component) 
           throws IOException {
        
        // Declare.
        ResponseWriter writer;
        
        // Get the response writer.
        writer = context.getResponseWriter();
        
        // Render a response.
        writer.endElement("div");
        writer.startElement("input", component);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("id", "expanded" + component.getId(), null);
        writer.writeAttribute("name", "expanded" + component.getId(), null);
        if (isExpanded(component)) {
            writer.writeAttribute("value", "true", null);
        }
        else {
            writer.writeAttribute("value", "false", null);
        }
        writer.endElement("input");
        writer.endElement("div");
    }
    
    /**
     * Is the toggle panel expanded?
     * 
     * @param  component  the UI component.
     * 
     * @return  true if the toggle panel is expanded, otherwise false.
     */
    private boolean isExpanded(UIComponent component) {
        
        // Declare.
        Boolean expanded;
        Boolean opened;
        
        // Get the component properties.
        expanded = ((TogglePanel)component).getExpanded();
        opened = ((TogglePanel)component).getOpened();

        // Check if expanded exists.
        if (expanded == null) {
            
            // Set expanded to opened.
            expanded = opened;
        }
        
        return expanded.booleanValue();
    }
}
