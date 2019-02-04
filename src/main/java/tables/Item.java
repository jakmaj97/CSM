package tables;

import interfaces.IAppConstants;
import javax.persistence.*;


/**
 * Objects of Item class represent some single products that are being stored in magazine.
 */
@Entity
@Table(name = "items")
public class Item implements IAppConstants {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = ITEM_ID_COLUMN_NAME)
    private int itemId;

    @Column(name = ITEM_NAME_COLUMN_NAME)
    private String itemName;

    @Column(name = ITEM_PRICE_COLUMN_NAME)
    private double itemPrice;

    @Column(name = ITEM_STATE_COLUMN_NAME)
    private int state;

    @ManyToOne
    @JoinColumn (name = ITEM_MAGAZINE_ID_COLUMN_NAME)
    private Magazine magazine;

    /**
     * Demanded by Hibernate, but practically never used.
     */
    public Item() {

    }

    /**
     * @param itemId Set automatically by Hibernate framework and incremented with every new Object
     * @param name set by user as any String
     * @param price set by user as any numeric value
     * @param state set by user as any Integer value
     * @param magazine in this app we use only one Magazine, so to this field we always set the same one
     */
    public Item(int itemId, String name, double price, int state, Magazine magazine) {

        this.itemId = itemId;
        this.itemName = name;
        this.itemPrice = price;
        this.state = state;
        this.magazine = magazine;
    }

    /**
     * @return name of Item object
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param name given by User to change name of Item object
     */
    public void setItemName(String name) {
        this.itemName = name;
    }

    /**
     * @return Magazine object that Item object is stored in
     */
    public Magazine getMagazine() {
        return magazine;
    }

    /**
     * @param magazine in our case its always the same one Magazine
     */
    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    /**
     * @return id of Item object
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @return price of Item object
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * @param price given by User to change price of Item object
     */
    public void setItemPrice(double price) {
        this.itemPrice = price;
    }

    /**
     * @return current amount of this Item object stored in Magazine
     */
    public int getState() {
        return state;
    }

    /**
     * @param state given by User to change state of Item object
     */
    public void setState(int state) {
        this.state = state;
    }
}
