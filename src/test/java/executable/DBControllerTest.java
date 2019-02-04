package executable;


import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import tables.Item;
import tables.ItemOrder;
import tables.Magazine;
import tables.Order;

import java.util.ArrayList;

public class DBControllerTest {

    DBController db = null;

    @Before
    public void initializeTest() {
        if(db == null) {
            db = new DBController();
        }
    }

    @Test
    public void shouldReturnItemList() {
        assertThat(db.getItemList().get(0), instanceOf(Item.class));
    }

    @Test
    public void shouldReturnOrderList() {
        assertThat(db.getOrderList().get(0), instanceOf(Order.class));
    }

    @Test
    public void shouldReturnItemOrderList() {
        assertThat(db.getItemOrderList().get(0), instanceOf(ItemOrder.class));
    }

    @Test
    public void shouldReturnMagazine() {
        assertThat(db.getMagazine(), instanceOf(Magazine.class));
    }

    @Test
    public void shouldCountItemsInMagazine() {
        assertEquals(db.getItemList().size(), db.countItemsInMagazine());
    }

    @Test
    public void shouldNotFindItemInMagazine() {
        assertFalse(db.itemNameExistsInMagazine((new Item(1, "There is no name", 50.9, 4, db.getMagazine()))));
    }

    @Test
    public void shouldFindItemInMagazine() {
        assertTrue(db.itemNameExistsInMagazine(new Item(1, db.getItemList().get(0).getItemName(), 50.9, 4, db.getMagazine())));
    }

}
