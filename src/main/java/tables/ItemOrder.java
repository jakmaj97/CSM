package tables;

import interfaces.IAppConstants;

import javax.persistence.*;

import java.io.Serializable;

import static interfaces.IAppConstants.*;

@Entity
@Table(name = "itemsorders")
public class ItemOrder implements IAppConstants, Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = ORDER_ID_COLUMN_NAME)
    private Order orderId;

    @Id
    @ManyToOne
    @JoinColumn(name = ITEM_ID_COLUMN_NAME)
    private Item itemId;

    @Column(name = ITEMS_ORDERS_ITEMS_AMOUNT_COLUMN_NAME)
    private int itemsAmount;

    public ItemOrder() {

    }

    /**
     * @param orderId set by user as a reference to a specific order
     * @param itemId set by user as a reference to a specific item
     * @param itemsAmount set by user as an amount of items with specific id in order
     */
    public ItemOrder(Order orderId, Item itemId, int itemsAmount) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.itemsAmount = itemsAmount;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public int getItemsAmount() {
        return itemsAmount;
    }

    public void setItemsAmount(int itemsAmount) {
        this.itemsAmount = itemsAmount;
    }
}
