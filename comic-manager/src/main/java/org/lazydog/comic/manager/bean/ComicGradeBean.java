package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.lazydog.comic.model.ComicGrade;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Comic grade managed bean.
 * 
 * @author  Ron Rickard
 */
public class ComicGradeBean
       extends AbstractDataAccessBean<ComicGrade>
       implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<ComicGrade> getCriteria() {

        // Declare.
        Criteria<ComicGrade> criteria;

        // Initialize.
        criteria = null;

        try {

            // Create a new criteria.
            criteria = this.comicService.getCriteria(ComicGrade.class);

            // Modify the criteria.
            criteria.addOrder(Order.desc("scale"));
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get criteria."));
        }

        return criteria;
    }

    /**
     * Get the current entity.
     *
     * @return  the current entity.
     */
    @Override
    public ComicGrade getCurrentEntity() {
        return null;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<ComicGrade> getEntityClass() {
        return ComicGrade.class;
    }

    /**
     * Get the entity select property.
     *
     * @param  entity  the entity.
     *
     * @return  the entity select property.
     */
    @Override
    protected String getEntitySelectProperty(ComicGrade entity) {
        return entity.getCode();
    }

    /**
     * Get a new entity.
     *
     * @return  a new entity.
     */
    @Override
    protected ComicGrade getNewEntity() {
        return null;
    }

    /**
     * Initialize.
     */
    @Override
    @PostConstruct
    protected void initialize() {
        super.initialize();
    }
    
    /**
     * Store the entity.
     */
    @Override
    public void storeEntity() {
        // Ignore.
    }
}
