package org.lazydog.comic.manager.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.lazydog.comic.model.Comic;
import org.lazydog.comic.model.Have;
import org.lazydog.comic.model.UserPreference;
import org.lazydog.comic.manager.utility.SessionKey;
import org.lazydog.comic.manager.utility.SessionUtility;
import org.lazydog.repository.criterion.ComparisonOperation;
import org.lazydog.repository.criterion.LogicalOperation;
import org.lazydog.repository.criterion.Order;
import org.lazydog.repository.Criteria;


/**
 * Have managed bean.
 * 
 * @author  Ron Rickard
 */
public class HaveBean
       extends AbstractDataAccessBean<Have>
       implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Get the criteria.
     *
     * @return  the criteria.
     */
    @Override
    protected Criteria<Have> getCriteria() {

        // Declare.
        Criteria<Have> criteria;

        // Initialize.
        criteria = null;

        try {

            // Check if the comic and UUID session values exist.
            if (SessionUtility.valueExists(SessionKey.COMIC) &&
                SessionUtility.valueExists(SessionKey.UUID)) {

                // Create a new criteria.
                criteria = this.comicService.getCriteria(Have.class);

                // Modify the criteria.
                criteria.add(ComparisonOperation.eq(
                        "comic",
                        SessionUtility.getValue(SessionKey.COMIC, Comic.class)));
                criteria.add(LogicalOperation.and(ComparisonOperation.eq(
                        "uuid",
                        SessionUtility.getValue(SessionKey.UUID, String.class))));
                criteria.addOrder(Order.desc("comicGrade.scale"));
                criteria.addOrder(Order.asc("location.name"));
                criteria.addOrder(Order.desc("purchasePrice"));
            }
        }
        catch(Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cannot get criteria."));
        }

        return criteria;
    }

    /**
     * Get the entities as select items.
     * 
     * @return  the entities as select items.
     */
    @Override
    public List<SelectItem> getEntitiesAsSelectItems() {
        return null;
    }

    /**
     * Get the entity class.
     *
     * @return  the entity class.
     */
    @Override
    protected Class<Have> getEntityClass() {
        return Have.class;
    }

    /**
     * Get a new entity.
     * 
     * @return  a new entity.
     */
    @Override
    protected Have getNewEntity() {

        // Declare.
        Have newEntity;

        // Create a new entity.
        newEntity = new Have();

        // Set the comic in the new entity.
        newEntity.setComic(SessionUtility.getValue(SessionKey.COMIC, Comic.class));

        // Check if the user preference exits.
        if (SessionUtility.valueExists(SessionKey.USER_PREFERENCE)) {

            // Set the comic grade in the new entity.
            newEntity.setComicGrade(SessionUtility
                    .getValue(SessionKey.USER_PREFERENCE, UserPreference.class)
                    .getComicGrade());
        }

        // Set the UUID in the new entity.
        newEntity.setUuid(SessionUtility.getValue(SessionKey.UUID, String.class));

        return newEntity;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {

        // Create a new entity.
        this.entity = new Have();
    }
}
