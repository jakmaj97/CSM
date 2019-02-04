package executable;

import interfaces.IAppConstants;
import tables.Item;
import tables.ItemOrder;
import tables.Magazine;
import tables.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * Objects of DBController class are used to help to communicate with data base from other parts of program.
 */
public class DBController implements IAppConstants {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    /**
     * EntityManager and EntityManagerFactory are initialized and connected to proper data base.
     */
    public DBController() {

        entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.cdmanagerfinal2.jpa");
        entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * @return EntityManager object connected to proper data base (to change connection url go to persistance.xml)
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Creates Query object, which selects all items stored in table in data base, and then saves them in List object.
     * @return List object containing all items stored in data base
     */
    public List<Item> getItemList() {

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(SELECT_ALL_ITEMS_QUERY);
        final List<Item> itemList = query.getResultList();
        entityManager.getTransaction().commit();
        return itemList;
    }

    public List<Order> getOrderList() {

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(SELECT_ALL_ORDERS_QUERY);
        final List<Order> orderList = query.getResultList();
        entityManager.getTransaction().commit();
        return orderList;
    }

    public List<ItemOrder> getItemOrderList() {

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery(SELECT_ALL_ITEMS_ORDERS_QUERY);
        final List<ItemOrder> orderList = query.getResultList();
        entityManager.getTransaction().commit();
        return orderList;
    }

    public void commitTransaction() {
        entityManager.getTransaction().commit();
    }

    /**
     * Finds Magazine with id = 1 in data base (in this app we use only one Magazine).
     * @return the only one Magazine used in this program
     */
    public Magazine getMagazine() {

        entityManager.getTransaction().begin();
        final Magazine mainMagazine = entityManager.find(Magazine.class, 1);
        entityManager.getTransaction().commit();
        return mainMagazine;
    }

    /**
     * @return amount of items stored in Magazine
     */
    public int countItemsInMagazine() {

        List<Item> itemList = getItemList();

        int counter = 0;
        for(int i = 0; i < itemList.size(); i++)
            counter++;

        return counter;
    }

    /**
     * Prints names of all items stored in Magazine.
     */
    public void printItems() {

        List<Item> itemList = getItemList();

        for(Item i : itemList)
            System.out.println(i.getItemName());
    }

    /**
     * Checks if data base already contains Item with a name equal to name of Item given as parameter.
     * @param item Object given to check if Item with the same name exits in data base already
     * @return true if such Item exits, otherwise false
     */
    public boolean itemNameExistsInMagazine(Item item) {

        List<Item> items = getItemList();
        for(Item i : items)
            if (i.getItemName().equals(item.getItemName()))
                return true;
        return false;
    }

    /**
     * Adds given Item to data base.
     * @param item Object to add to data base
     */
    public void addItemToMagazine(Item item) {
        entityManager.persist(item);
    }

    /**
     * Closes EntityManagerFactory object.
     */
    public void killDBController() {
        entityManagerFactory.close();
    }

    /**
     * Increases state of item, finding it by its name.
     * @param increasor a number to increase product amount
     * @param itemName the name of an item
     */
    public void increaseItemState(int increasor, String itemName) {
        for(Item i : getItemList())
            if(i.getItemName().equals(itemName))
                i.setState(i.getState()+increasor);
    }
}
