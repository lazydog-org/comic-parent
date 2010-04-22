package comic.faces.renderer.html;

import comic.faces.component.TogglePanelTrigger;
import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;


/**
 * HTML toggle panel trigger JavaServer Faces UI component renderer.
 * 
 * @author  Ron Rickard
 */
public class HtmlTogglePanelTriggerRenderer 
       extends Renderer {

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
        String forId;
        ResponseWriter writer;
        
        // Get the response writer.
        forId = ((TogglePanelTrigger)component).getForId();
        writer = context.getResponseWriter();

        // Render a response.
        writer.startElement("div", component);
        writer.startElement("a", component);
        writer.writeAttribute("href", "javascript:// Toggle panel", null);
        writer.writeAttribute("id", component.getId(), null);
        writer.writeAttribute("onmousedown", "toggleDiv" + forId + "();", null);
        writer.writeAttribute("style", "white-space: nowrap;", null);
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
        String forId;
        StringBuffer response;
        ResponseWriter writer;
        
        // Get the for ID.
        forId = ((TogglePanelTrigger)component).getForId();
        
        // Get the response writer.
        writer = context.getResponseWriter();
        
        // Set the response.
        response = new StringBuffer();
        response.append("<script language=\"javascript\">")
                .append("function toggleDiv")
                .append(forId)
                .append("() {")
                .append("if(document.getElementById('")
                .append(forId)
                .append("').style.display == 'none') {")
                .append("document.getElementById('")
                .append(forId)
                .append("').style.display = 'block';")
                .append("document.getElementById('expanded")
                .append(forId)
                .append("').value = 'true';")
                .append("}")
                .append("else {")
                .append("document.getElementById('")
                .append(forId)
                .append("').style.display = 'none';")
                .append("document.getElementById('expanded")
                .append(forId)
                .append("').value = 'false';")
                .append("}")
                .append("}")
                .append("</script>");
                
        // Render the response.
        writer.endElement("a");
        writer.write(response.toString());
        writer.endElement("div");
    }
}
