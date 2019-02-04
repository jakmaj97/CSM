package executable;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import tables.Item;
import tables.ItemOrder;
import tables.Magazine;
import tables.Order;

import java.util.ArrayList;

public class GUIsControllerTest {

    GUIsController gui;
    Magazine magazine = new Magazine();

    @Before
    public void initializeTest() {
        if(gui == null)
            gui = new GUIsController();
    }

    @Test
    public void shouldAnswerAllItemsAvailable() {
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<ItemOrder> itemsOrders = new ArrayList<ItemOrder>();
        for(int i = 0; i < 7; i++) {
            items.add(new Item(i, "item", 50.9, 4, magazine));
        }
        Order order = new Order();
        for(int i = 0; i < 7; i++) {
            itemsOrders.add(new ItemOrder(order, items.get(i), 3));
        }
        assertTrue( gui.areAllItemsAvailable(items, itemsOrders) );
    }

    @Test
    public void shouldAnswerNotAllItemsAvailable() {
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<ItemOrder> itemsOrders = new ArrayList<ItemOrder>();
        for(int i = 0; i < 7; i++) {
            items.add(new Item(i, "item", 50.9, 4, magazine));
        }
        Order order = new Order();
        for(int i = 0; i < 7; i++) {
            itemsOrders.add(new ItemOrder(order, items.get(i), 3));
        }
        itemsOrders.set(3, new ItemOrder(order, items.get(3), 5));
        assertFalse( gui.areAllItemsAvailable(items, itemsOrders) );
    }

    @Test
    public void shouldAnswerItemsNotAvailableInEmptyLists() {
        ArrayList<Item> items = null;
        ArrayList<ItemOrder> itemsOrders = null;
        assertFalse( gui.areAllItemsAvailable(items, itemsOrders) );
    }
}
