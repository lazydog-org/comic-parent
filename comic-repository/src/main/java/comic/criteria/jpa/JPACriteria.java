package comic.criteria.jpa;

import comic.api.criteria.Criteria;
import comic.api.criteria.criterion.Criterion;
import comic.api.model.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * Criteria implemented using the Java Persistence API.
 * 
 * @author  Ron Rickard
 */
public class JPACriteria<T extends Entity<T>>
       implements Criteria<T>,
                  Serializable {

    private String entityAlias;
    private List<Criterion> orders;
    private StringBuffer ordersStringBuffer;
    private StringBuffer qlStringBuffer;
    private Map<String, Object> parameters;
    private List<Criterion> restrictions;

    /**
     * Constructor.
     *
     * @param  entityClass  the entity class.
     */
    public JPACriteria(Class<T> entityClass) {

        // Declare.
        String entityName;

        // Get the entity name.
        entityName = entityClass.getSimpleName();
        
        // Get the entity alias.
        this.entityAlias = entityName.toLowerCase();

        // Initialize the query language string.
        this.qlStringBuffer = new StringBuffer();
        this.qlStringBuffer.append("select ")
                           .append(this.entityAlias)
                           .append(" from ")
                           .append(entityName)
                           .append(" ")
                           .append(this.entityAlias);
        this.ordersStringBuffer = new StringBuffer();
        
        // Initialize the parameters.
        this.parameters = new LinkedHashMap<String, Object>();

        // Initialize the orders and restrictions.
        this.orders = new ArrayList<Criterion>();
        this.restrictions = new ArrayList<Criterion>();
    }

    /**
     * Add a restriction criterion.
     *
     * @param  criterion  the restriction criterion.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<T> add(Criterion criterion) {

        // Process the restriction criterion.
        if (!this.processRestriction(criterion)) {
            // Ignore.
        }

        // Add the criterion to the restrictions.
        this.restrictions.add(criterion);

        return this;
    }

    /**
     * Add restriction criterions.
     *
     * @param  criterions  the restriction criterions.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<T> add(List<Criterion> criterions) {

        // Loop through the restriction criterions.
        for (Criterion criterion : criterions) {

            // Add the restriction criterion.
            this.add(criterion);
        }

        return this;
    }

    /**
     * Add a order criterion.
     *
     * @param  criterion  the order criterion.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<T> addOrder(Criterion criterion) {

        // Process the order criterion.
        if (!this.processOrder(criterion)) {
            // Ignore.
        }

        // Add the criterion to the orders.
        this.orders.add(criterion);

        return this;
    }

    /**
     * Add order criterions.
     *
     * @param  criterions  the order criterions.
     *
     * @return  the criteria.
     */
    @Override
    public Criteria<T> addOrders(List<Criterion> criterions) {

        // Loop through the order criterions.
        for (Criterion criterion : criterions) {

            // Add the order criterion.
            this.addOrder(criterion);
        }

        return this;
    }

    /**
     * Get the parameters.
     * 
     * @return  the parameters.
     */
    @Override
    public Map<String, Object> getParameters() {
        return this.parameters;
    }
    
    /**
     * Get the query language string.
     * 
     * @return  the query language string.
     */
    @Override
    public String getQlString() {
        return this.qlStringBuffer.toString()
             + this.ordersStringBuffer.toString();
    }

    /**
     * Process a order criterion.
     *
     * @param  criterion  the order criterion.
     *
     * @return  true if the order criterion is processed, otherwise false.
     */
    private boolean processOrder(Criterion criterion) {

        // Declare.
        boolean processed;

        processed = true;

        try {

            // Check if an order has not already been processed.
            if (this.orders.isEmpty()) {

                // Add the ORDER BY-clause to the query language string.
                this.ordersStringBuffer.append(" order by ");
            }
            else {

                // Add a comma to the query language string.
                this.ordersStringBuffer.append(", ");
            }

            // Add the operand to the query language string.
            this.ordersStringBuffer.append(this.entityAlias)
                                   .append(".")
                                   .append(criterion.getOperand());

            // Add the logical operator to the query language string.
            switch (criterion.getOrderDirection()) {

                case ASC:
                    this.ordersStringBuffer.append(" asc");
                    break;
                case DESC:
                    this.ordersStringBuffer.append(" desc");
                    break;
            }
        }
        catch(Exception e) {
            processed = false;
        }

        return processed;
    }

    /**
     * Process a restriction criterion.
     *
     * @param  criterion  the restriction criterion.
     *
     * @return  true if the order criterion is processed, otherwise false.
     */
    private boolean processRestriction(Criterion criterion) {

        // Declare.
        boolean processed;

        processed = true;

        try {

            // Check if a restriction has not already been processed.
            if (this.restrictions.isEmpty()) {

                // Add the WHERE-clause to the query language string.
                this.qlStringBuffer.append(" where ");
            }
            else {

                // Add the logical operator to the query language string.
                switch (criterion.getLogicalOperator()) {

                    case AND:
                        this.qlStringBuffer.append(" and ");
                        break;
                    case OR:
                        this.qlStringBuffer.append(" or ");
                        break;
                }
            }

            // Add the operand, comparison operator, and parameter if 
            // necessary to the query language string.
            switch (criterion.getComparisonOperator()) {

                case EQUAL:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" = ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case GREATER_THAN:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" > ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case GREATER_THAN_OR_EQUAL:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" >= ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case IS_EMPTY:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" is empty");
                    break;
                case IS_NOT_EMPTY:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" is not empty");
                    break;
                case IS_NULL:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" is null");
                    break;
                case IS_NOT_NULL:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" is not null");
                    break;
                case LESS_THAN:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" < ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case LESS_THAN_OR_EQUAL:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" <= ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case LIKE:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" like ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case MEMBER_OF:
                    this.qlStringBuffer.append(":param" +
                                               (this.parameters.size() + 1))
                                       .append(" member of ")
                                       .append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand());
                    break;
                case NOT_EQUAL:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" <> ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case NOT_LIKE:
                    this.qlStringBuffer.append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand())
                                       .append(" not like ")
                                       .append(":param" +
                                               (this.parameters.size() + 1));
                    break;
                case NOT_MEMBER_OF:
                    this.qlStringBuffer.append(":param" +
                                               (this.parameters.size() + 1))
                                       .append(" not member of ")
                                       .append(this.entityAlias)
                                       .append(".")
                                       .append(criterion.getOperand());
                    break;
            }

            // Check if there is a value.
            if (criterion.getValue() != null) {

                // Add the parameter.
                this.parameters.put(
                    "param" + (this.parameters.size() + 1),
                    criterion.getValue());
            }
        }
        catch(Exception e) {
            processed = false;
        }

        return processed;
    }
}