package org.lazydog.comic.manager.utility;

import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.Image;
import org.lazydog.comic.model.ImageType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Form button controller.
 * 
 * @author  Ron Rickard
 */
public class FormButtonController
       implements Serializable {

    /**
     * Enumeration for perspective buttons.
     */
    private static enum PerspectiveButton {
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
     * Enumeration for form buttons.
     */
    private static enum FormButton {
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

    private static final List<String> perspectiveButtons = new ArrayList<String>() {
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

    private static final List<String> formButtons = new ArrayList<String>() {
        {
            add(FormButton.RESET.getLabel());
            add(FormButton.CANCEL.getLabel());
            add(FormButton.OK.getLabel());
        }
    };

    private Boolean formDisabled;
    private Map<String, Boolean> buttonDisabledMap;
    private Map<String, Boolean> buttonRenderedMap;

    /**
     * Constructor.
     *
     * @param  entityClass  the entity class.
     * @param  perspective  the perspective.
     */
    public FormButtonController(Class entityClass, Perspective perspective) {

        // Initialize button disabled map.
        this.buttonDisabledMap = new HashMap<String, Boolean>() {
            {
                // Loop through the perspective buttons.
                for (String actionButton : FormButtonController.perspectiveButtons) {

                    // Put the button on the map, disabled.
                    put(actionButton, true);
                }

                // Loop through the form buttons.
                for (String actionButton : FormButtonController.formButtons) {

                    // Put the button on the map, disabled.
                    put(actionButton, true);
                }
            }
        };

        // Initialize button rendered map.
        this.buttonRenderedMap = new HashMap<String, Boolean>() {
            {
                // Loop through the perspective buttons.
                for (String actionButton : FormButtonController.perspectiveButtons) {

                    // Put the button on the map, rendered.
                    put(actionButton, true);
                }

                // Loop through the form buttons.
                for (String actionButton : FormButtonController.formButtons) {

                    // Put the button on the map, rendered.
                    put(actionButton, true);
                }
            }
        };

        // Initialize the form as disabled.
        this.formDisabled = true;

        // Enable and render buttons specified by the perspective and entity class.
        this.enableButtons(perspective);
        this.unrenderButtons(entityClass);

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

            // Put buttons on map, enabled.
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

            // Put buttons on map, enabled.
            this.buttonDisabledMap.put(
                    PerspectiveButton.ADD.getLabel(), false);
            this.buttonDisabledMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
        }

        // Check if the perspective is VIEW.
        else if (perspective == Perspective.VIEW) {

            // Put the buttons on map, enabled.
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
     * Get the form buttons.
     *
     * @return  the form buttons.
     */
    public List<String> getFormButtons() {
        return FormButtonController.formButtons;
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
     * Get the perspective buttons.
     *
     * @return  the perspective buttons.
     */
    public List<String> getPerspectiveButtons() {
        return FormButtonController.perspectiveButtons;
    }

    /**
     * Unrender the buttons specified by the entity class.
     *
     * @param  entityClass  the entity class.
     */
    private void unrenderButtons(Class entityClass) {

        // Check if the entity class is a Image class.
        if (entityClass == Image.class) {

            // Put buttons on map, unrendered.
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DUPLICATE.getLabel(), false);
        }

        // Check if the entity class is a ImageType class.
        else if (entityClass == ImageType.class) {

            // Put buttons on map, unrendered.
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

            // Put buttons on map, unrendered.
            this.buttonRenderedMap.put(
                    PerspectiveButton.ADD_MANY.getLabel(), false);
            this.buttonRenderedMap.put(
                    PerspectiveButton.DUPLICATE.getLabel(), false);
        }
    }
}
