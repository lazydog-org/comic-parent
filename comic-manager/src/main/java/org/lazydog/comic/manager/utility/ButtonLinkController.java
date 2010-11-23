package org.lazydog.comic.manager.utility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;
import org.lazydog.comic.model.Title;


/**
 * Button/link controller.
 * 
 * @author  Ron Rickard
 */
public class ButtonLinkController
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Enumeration for comic links.
     */
    public static enum ComicLink {
        ADD_TO_WISHLIST           ("Add to Wishlist"),
        MANAGE_COLLECTION         ("Manage Collection"),
        REMOVE_FROM_WISHLIST      ("Remove from Wishlist"),
        RETURN_TO_TITLE_SELECTION ("Return to Title Selection");

        private String label;

        /**
         * Constructor.
         *
         * @param  label  the label.
         */
        ComicLink(String label) {
            this.label = label;
        }

        /**
         * Get the label.
         *
         * @return  the label.
         */
        public String getLabel() {
            return this.label;
        }
    }

    /**
     * Enumeration for filter buttons.
     */
    public static enum FilterButton {
        APPLY ("Apply Filter"),
        RESET ("Reset Filter");

        private String label;

        /**
         * Constructor.
         *
         * @param  label  the label.
         */
        FilterButton(String label) {
            this.label = label;
        }

        /**
         * Get the label.
         *
         * @return  the label.
         */
        public String getLabel() {
            return this.label;
        }
    }

    /**
     * Enumeration for form buttons.
     */
    public static enum FormButton {
        CANCEL  ("Cancel"),
        OK      ("OK"),
        RESET   ("Reset");

        private String label;

        /**
         * Constructor.
         *
         * @param  label  the label.
         */
        FormButton(String label) {
            this.label = label;
        }

        /**
         * Get the label.
         *
         * @return  the label.
         */
        public String getLabel() {
            return this.label;
        }
    }

    /**
     * Enumeration for perspective buttons.
     */
    public static enum PerspectiveButton {
        ADD         ("add"),
        ADD_MANY    ("add range"),
        DUPLICATE   ("duplicate"),
        DELETE      ("delete"),
        FIRST       ("<<"),
        LAST        (">>"),
        NEXT        (">"),
        PREVIOUS    ("<"),
        EDIT        ("edit");

        private String label;

        /**
         * Constructor.
         *
         * @param  label  the label.
         */
        PerspectiveButton(String label) {
            this.label = label;
        }

        /**
         * Get the label.
         *
         * @return  the label.
         */
        public String getLabel() {
            return this.label;
        }
    }

    /**
     * Enumeration for title links.
     */
    public static enum TitleLink {
        MANAGE_ISSUES ("Manage Issues");

        private String label;

        /**
         * Constructor.
         *
         * @param  label  the label.
         */
        TitleLink(String label) {
            this.label = label;
        }

        /**
         * Get the label.
         *
         * @return  the label.
         */
        public String getLabel() {
            return this.label;
        }
    }

    private static final List<String> comicLinks = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add(ComicLink.MANAGE_COLLECTION.getLabel());
            add(ComicLink.ADD_TO_WISHLIST.getLabel());
            add(ComicLink.REMOVE_FROM_WISHLIST.getLabel());
            add(ComicLink.RETURN_TO_TITLE_SELECTION.getLabel());
        }
    };

    private static final List<String> filterButtons = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add(FilterButton.RESET.getLabel());
            add(FilterButton.APPLY.getLabel());
        }
    };

    private static final List<String> formButtons = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add(FormButton.RESET.getLabel());
            add(FormButton.CANCEL.getLabel());
            add(FormButton.OK.getLabel());
        }
    };

    private static final List<String> perspectiveButtons = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add(PerspectiveButton.FIRST.getLabel());
            add(PerspectiveButton.PREVIOUS.getLabel());
            add(PerspectiveButton.EDIT.getLabel());
            add(PerspectiveButton.DUPLICATE.getLabel());
            add(PerspectiveButton.DELETE.getLabel());
            add(PerspectiveButton.ADD.getLabel());
            add(PerspectiveButton.ADD_MANY.getLabel());
            add(PerspectiveButton.NEXT.getLabel());
            add(PerspectiveButton.LAST.getLabel());
        }
    };

    private static final List<String> titleLinks = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add(TitleLink.MANAGE_ISSUES.getLabel());
        }
    };

    private Boolean formDisabled;
    private Map<String, Boolean> buttonDisabledMap;
    private Map<String, Boolean> buttonRenderedMap;
    private Map<String, Boolean> linkDisabledMap;
    private Map<String, Boolean> linkRenderedMap;

    /**
     * Private constructor.
     *
     * @param  entityClass  the entity class.
     * @param  perspective  the perspective.
     */
    private ButtonLinkController(Class entityClass, Perspective perspective) {

        // Initialize button disabled map.
        this.buttonDisabledMap = new HashMap<String, Boolean>() {

            private static final long serialVersionUID = 1L;

            {
                // Loop through the filter buttons.
                for (String actionButton : ButtonLinkController.filterButtons) {

                    // Put the button on the map, disabled.
                    put(actionButton, true);
                }

                // Loop through the form buttons.
                for (String actionButton : ButtonLinkController.formButtons) {

                    // Put the button on the map, disabled.
                    put(actionButton, true);
                }

                // Loop through the perspective buttons.
                for (String actionButton : ButtonLinkController.perspectiveButtons) {

                    // Put the button on the map, disabled.
                    put(actionButton, true);
                }
            }
        };

        // Initialize button rendered map.
        this.buttonRenderedMap = new HashMap<String, Boolean>() {

            private static final long serialVersionUID = 1L;
            
            {
                // Loop through the filter buttons.
                for (String actionButton : ButtonLinkController.filterButtons) {

                    // Put the button on the map, rendered.
                    put(actionButton, true);
                }

                // Loop through the form buttons.
                for (String actionButton : ButtonLinkController.formButtons) {

                    // Put the button on the map, rendered.
                    put(actionButton, true);
                }

                // Loop through the perspective buttons.
                for (String actionButton : ButtonLinkController.perspectiveButtons) {

                    // Put the button on the map, rendered.
                    put(actionButton, true);
                }
            }
        };

        // Initialize link disabled map.
        this.linkDisabledMap = new HashMap<String, Boolean>() {

            private static final long serialVersionUID = 1L;

            {
                // Loop through the comic links.
                for (String actionLink : ButtonLinkController.comicLinks) {

                    // Put the link on the map, disabled.
                    put(actionLink, true);
                }

                // Loop through the title links.
                for (String actionLink : ButtonLinkController.titleLinks) {

                    // Put the link on the map, disabled.
                    put(actionLink, true);
                }
            }
        };

        // Initialize link rendered map.
        this.linkRenderedMap = new HashMap<String, Boolean>() {

            private static final long serialVersionUID = 1L;

            {
                // Loop through the comic links.
                for (String actionLink : ButtonLinkController.comicLinks) {

                    // Put the link on the map, rendered.
                    put(actionLink, true);
                }

                // Loop through the title links.
                for (String actionLink : ButtonLinkController.titleLinks) {

                    // Put the link on the map, rendered.
                    put(actionLink, true);
                }
            }
        };

        // Initialize the form as disabled.
        this.formDisabled = true;

        // Enable and render buttons specified by the perspective and entity class.
        this.enableButtons(perspective);
        this.unrenderButtons(entityClass);

        // Enable and render links specified by the perspective and entity class.
        this.enableLinks(perspective);
        this.unrenderLinks(entityClass);

    }

    /**
     * Enable the buttons specified by the perspective.
     *
     * @param  perspective  the perspective.
     */
    private void enableButtons(Perspective perspective) {

        // Check if the perspective is ADD, ADD_MANY, DUPLICATE, or EDIT.
        if (perspective == Perspective.ADD ||
            perspective == Perspective.ADD_MANY ||
            perspective == Perspective.DUPLICATE ||
            perspective == Perspective.EDIT) {

            // Put buttons on the map, enabled.
            this.buttonDisabledMap.put(
                    FormButton.CANCEL.getLabel(), false);
            this.buttonDisabledMap.put(
                    FormButton.OK.getLabel(), false);
            this.buttonDisabledMap.put(
                    FormButton.RESET.getLabel(), false);

            // Enable the form.
            this.formDisabled = false;
        }

        // Check if the perspective is FRESH.
        else if (perspective == Perspective.FRESH) {

            // Put buttons on the map, enabled.
            this.buttonDisabledMap.put(
                    FilterButton.APPLY.getLabel(), false);
            this.buttonDisabledMap.put(
                    FilterButton.RESET.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.ADD.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
        }

        // Check if the perspective is VIEW.
        else if (perspective == Perspective.VIEW) {

            // Put the buttons on the map, enabled.
            this.buttonDisabledMap.put(
                    FilterButton.APPLY.getLabel(), false);
            this.buttonDisabledMap.put(
                    FilterButton.RESET.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.ADD.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.DELETE.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.DUPLICATE.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.EDIT.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.FIRST.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.LAST.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.NEXT.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.PREVIOUS.getLabel(), false);
        }
    }

    /**
     * Enable the links specified by the perspective.
     *
     * @param  perspective  the perspective.
     */
    private void enableLinks(Perspective perspective) {

        // Check if the perspective is FRESH.
        if (perspective == Perspective.FRESH) {

            // Put the links on the map, enabled.
            this.linkDisabledMap.put(
                    ComicLink.RETURN_TO_TITLE_SELECTION.getLabel(), false);
        }

        // Check if the perspective is VIEW.
        else if (perspective == Perspective.VIEW) {

            // Put the links on the map, enabled.
            this.linkDisabledMap.put(
                    ComicLink.ADD_TO_WISHLIST.getLabel(), false);
            this.linkDisabledMap.put(
                    ComicLink.MANAGE_COLLECTION.getLabel(), false);
            this.linkDisabledMap.put(
                    ComicLink.REMOVE_FROM_WISHLIST.getLabel(), false);
            this.linkDisabledMap.put(
                    ComicLink.RETURN_TO_TITLE_SELECTION.getLabel(), false);
            this.linkDisabledMap.put(
                    TitleLink.MANAGE_ISSUES.getLabel(), false);
        }
    }

    /**
     * Get the button disabled map.
     *
     * @return  the button disabled map.
     */
    public Map<String, Boolean> getButtonDisabledMap() {
        return this.buttonDisabledMap;
    }

    /**
     * Get the button rendered map.
     *
     * @return  the button rendered map.
     */
    public Map<String, Boolean> getButtonRenderedMap() {
        return this.buttonRenderedMap;
    }

    /**
     * Get the comic links.
     *
     * @return  the comic links.
     */
    public List<String> getComicLinks() {
        return ButtonLinkController.comicLinks;
    }

    /**
     * Get the filter buttons.
     * 
     * @return  the filter buttons.
     */
    public List<String> getFilterButtons() {
        return ButtonLinkController.filterButtons;
    }

    /**
     * Get the form buttons.
     *
     * @return  the form buttons.
     */
    public List<String> getFormButtons() {
        return ButtonLinkController.formButtons;
    }

    /**
     * Get the form disabled.
     * 
     * @return  the form disabled.
     */
    public Boolean getFormDisabled() {
        return this.formDisabled;
    }

    /**
     * Get the link disabled map.
     *
     * @return  the link disabled map.
     */
    public Map<String, Boolean> getLinkDisabledMap() {
        return this.linkDisabledMap;
    }

    /**
     * Get the link rendered map.
     *
     * @return  the link rendered map.
     */
    public Map<String, Boolean> getLinkRenderedMap() {
        return this.linkRenderedMap;
    }

    /**
     * Get the perspective buttons.
     *
     * @return  the perspective buttons.
     */
    public List<String> getPerspectiveButtons() {
        return ButtonLinkController.perspectiveButtons;
    }

    /**
     * Get the title links.
     *
     * @return  the title links.
     */
    public List<String> getTitleLinks() {
        return ButtonLinkController.titleLinks;
    }

    /**
     * Get a new instance of this class.
     * 
     * @param  entityClass  the entity class.
     * @param  perspective  the perspective.
     * 
     * @return  a new instance of this class.
     */
    public static ButtonLinkController newInstance(Class entityClass, Perspective perspective) {
        return new ButtonLinkController(entityClass, perspective);
    }

    /**
     * Set the button.
     * 
     * @param  button    the button.
     * @param  disabled  true, false, or null (leave alone).
     * @param  rendered  true, false, or null (leave alone).
     */
    public void setButton(String button, Boolean disabled, Boolean rendered) {

        // Check if the disabled flag exists.
        if (disabled != null) {
            this.buttonDisabledMap.put(button, disabled);
        }

        // Check if the rendered flag exists.
        if (rendered != null) {
            this.buttonRenderedMap.put(button, rendered);
        }
    }

    /**
     * Set the link.
     *
     * @param  link      the link.
     * @param  disabled  true, false, or null (leave alone).
     * @param  rendered  true, false, or null (leave alone).
     */
    public void setLink(String link, Boolean disabled, Boolean rendered) {

        // Check if the disabled flag exists.
        if (disabled != null) {
            this.linkDisabledMap.put(link, disabled);
        }

        // Check if the rendered flag exists.
        if (rendered != null) {
            this.linkRenderedMap.put(link, rendered);
        }
    }

    /**
     * Unrender the buttons specified by the entity class.
     *
     * @param  entityClass  the entity class.
     */
    private void unrenderButtons(Class entityClass) {

        // Check if the entity class is a Image class.
        if (entityClass == Image.class) {

            // Put buttons on the map, unrendered.
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DELETE.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DUPLICATE.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.EDIT.getLabel(), false);
        }

        // Check if the entity class is a ImageType class.
        else if (entityClass == ImageType.class) {

            // Put buttons on the map, unrendered.
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DELETE.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DUPLICATE.getLabel(), false);
        }

        // Check if the entity class is not a Comic class.
        else if (entityClass != Comic.class) {

            // Put buttons on the map, unrendered.
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DUPLICATE.getLabel(), false);
        }
    }

    /**
     * Unrender the links specified by the entity class.
     *
     * @param  entityClass  the entity class.
     */
    private void unrenderLinks(Class entityClass) {

        // Check if the entity class is not a Title class.
        if (entityClass != Title.class) {

            // Put links on the map, unrendered.
            this.linkRenderedMap.put(
                    TitleLink.MANAGE_ISSUES.getLabel(), false);
        }

        // Check if the entity class is not a Comic class.
        else if (entityClass != Comic.class) {

            // Put links on the map, unrendered.
            this.linkRenderedMap.put(
                    ComicLink.ADD_TO_WISHLIST.getLabel(), false);
            this.linkRenderedMap.put(
                    ComicLink.MANAGE_COLLECTION.getLabel(), false);
            this.linkRenderedMap.put(
                    ComicLink.REMOVE_FROM_WISHLIST.getLabel(), false);
            this.linkRenderedMap.put(
                    ComicLink.RETURN_TO_TITLE_SELECTION.getLabel(), false);
        }
    }
}
