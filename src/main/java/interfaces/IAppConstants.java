package interfaces;

import javafx.scene.image.Image;

/**
 * Iterface using to store constant values
 */
public interface IAppConstants {

    //Generated values
    String GENERATED_GENERATOR = "incrementor";
    String GENERATED_NAME = "incrementator";
    String GENERATED_STRATEGY = "increment";

    //Item columns names
    String ITEM_ID_COLUMN_NAME = "itemId";
    String ITEM_NAME_COLUMN_NAME = "itemName";
    String ITEM_PRICE_COLUMN_NAME = "itemPrice";
    String ITEM_STATE_COLUMN_NAME = "state";
    String ITEM_MAGAZINE_ID_COLUMN_NAME = "magazineId";

    //Magazine columns names
    String MAGAZINE_ID_COLUMN_NAME = "magazineId";
    String MAGAZINE_OWNER_SURNAME_COLUMN_NAME = "ownerSurname";
    String MAGAZINE_ADDRESS_COLUMN_NAME = "magazineAddress";
    String MAGAZINE_NAME_COLUMN_NAME = "magazineName";

    //Orders columns names
    String ORDER_ID_COLUMN_NAME = "orderId";
    String ORDER_PRICE_COLUMN_NAME = "orderPrice";
    String ORDER_STATUS_COLUMN_NAME = "status";
    String ORDER_INSTALLMENT_COLUMN_NAME = "installment";
    String ORDER_MAGAZINIER_PESEL_NUMBER_COLUMN_NAME = "magazinierPeselNumber";
    String ORDER_CLIENT_PESEL_NUMBER_COLUMN_NAME = "clientPeselNumber";

    //Clients columns names
    String CLIENT_PESEL_NUMBER_COLUMN_NAME = "clientPeselNumber";
    String CLIENT_NAME_COLUMN_NAME = "clientName";
    String CLIENT_SURNAME_COLUMN_NAME = "clientSurname";
    String CLIENT_ADDRESS_COLUMN_NAME = "clientAddress";

    //ItemOrder columns names
    String ITEMS_ORDERS_ORDER_ID_COLUMN_NAME = "orderId";
    String ITEMS_ORDERS_ITEM_TD_COLUMN_NAME = "itemId";
    String ITEMS_ORDERS_ITEMS_AMOUNT_COLUMN_NAME = "itemsAmount";

    //Magaziniers columns names
    String MAGAZINIER_PESEL_NUMBER_COLUMN_NAME = "magazinierPeselNumber";
    String MAGAZINIER_NAME_COLUMN_NAME = "magazinierName";
    String MAGAZINIER_SURNAME_COLUMN_NAME = "magazinierSurname";
    String MAGAZINIER_WORKING_HOURS_COLUMN_NAME = "workingHours";
    String MAGAZINIER_SALARY_COLUMN_NAME = "salary";
    String MAGAZINIER_ACCOUNT_NUMBER_COLUMN_NAME = "accountNumber";

    //HQL Queries
    String SELECT_ALL_ITEMS_QUERY = "from Item a";
    String SELECT_ALL_ORDERS_QUERY = "from Order a";
    String SELECT_ALL_ITEMS_ORDERS_QUERY = "from ItemOrder a";

    //FXML Items names
    String ITEMS_TABLE_NAME = "#itemsTable";
    String ADD_ITEM_BUTTON_NAME = "#addItemButton";
    String CONFIRM_FORM_BUTTON_NAME = "#confirmFormButton";
    String OK_ADDED_ITEM_BUTTON_NAME = "#okAddedItemButton";
    String OK_UPDATED_ITEM_STATUS_BUTTON_NAME = "#okUpdatedItemStatusButton";
    String OK_INCOMPLETE_FORM_ERROR_BUTTON_NAME = "#okIncompleteFormErrorButton";
    String WARN_FIX_DATA_BUTTON_NAME = "#fixDataButton";
    String WARN_INCREASE_ITEMS_BUTTON_NAME = "#increaseItemsButton";
    String NAME_TEXT_FIELD_NAME = "#nameTextField";
    String PRICE_TEXT_FIELD_NAME = "#priceTextField";
    String STATE_TEXT_FIELD_NAME = "#stateTextField";
    String OPEN_HELP_PAGE_BUTTON_NAME = "#helpButton";
    String ITEMS_LIST_BUTTON_NAME = "#itemsListButton";
    String ORDERS_LIST_BUTTON_NAME = "#ordersListButton";
    String BACK_FROM_ITEM_LIST_BUTTON_NAME = "#backFromItemListButton";

    //FXML Orders names
    String ORDERS_TABLE_NAME = "#itemsTable";
    String ITEMS_ORDERS_TABLE_NAME = "#itemsTable";
    String ORDER_ID_TEXT_FIELD_NAME = "#orderId";
    String ORDER_PRICE_TEXT_FIELD_NAME = "#orderPrice";
    String ORDER_STATUS_TEXT_FIELD_NAME = "#orderStatus";
    String ORDER_INSTALLMENT_TEXT_FIELD_NAME = "#orderInstallment";
    String ORDERS_BACK_FROM_DETAIL_BUTTON_NAME = "#back";
    String ALL_ORDER_ITEMS_AVAILABLE_BUTTON_NAME = "#close";
    String GENERATE_WHOLESALER_ORDER_BUTTON_NAME = "#generateWholesalerOrder";
    String WHOLESALER_ORDER_RECIEVER_TEXT_FIELD_NAME = "#reciever";
    String WHOLESALER_ORDER_SUBJECT_TEXT_FIELD_NAME = "#subject";
    String WHOLESALER_ORDER_MESSAGE_TEXT_AREA_NAME = "#message";
    String WHOLESALER_ORDER_BACK_BUTTON_NAME = "#back";
    String WHOLESALER_ORDER_SEND_BUTTON_NAME = "#send";

    //Items table columns indexes
    int ITEM_ID_COLUMN_INDEX = 0;
    int ITEM_NAME_COLUMN_INDEX = 1;
    int ITEM_PRICE_COLUMN_INDEX = 2;
    int ITEM_STATE_COLUMN_INDEX = 3;

    //Items orders columns indexes
    int ORDER_ID_COLUMN_INDEX = 0;
    int ORDER_PRICE_COLUMN_INDEX = 1;
    int ORDER_STATUS_COLUMN_INDEX = 2;
    int ORDER_INSTALLMENT_COLUMN_INDEX = 3;
    int ORDER_MAGAZINIER_PESEL_NUMBER_COLUMN_INDEX = 4;
    int ORDER_CLIENT_PESEL_NUMBER_COLUMN_INDEX = 5;

    //GUIs Names
    String APP_TITLE = "Computer Shop Manager";

    //Windows sizes
    int ITEM_LIST_WINDOW_WIDTH = 820;
    int ITEM_LIST_WINDOW_HEIGHT = 600;
    int ADD_ITEM_FORM_WINDOW_WIDTH = 450;
    int ADD_ITEM_FORM_WINDOW_HEIGHT = 350;
    int ERROR_AND_OK_WINDOWS_WIDTH = 450;
    int ERROR_AND_OK_WINDOWS_HEIGHT = 200;
    int WARN_WINDOW_WIDTH = 600;
    int WARN_WINDOW_HEIGHT = 200;
    int CLIENTS_ORDER_LIST_WINDOW_WIDTH = 900;
    int CLIENTS_ORDER_LIST_WINDOW_HEIGHT = 600;
    int CLIENTS_ORDER_DETAILS_WINDOW_WIDTH = 850;
    int CLIENTS_ORDER_DETAILS_WINDOW_HEIGHT = 700;
    int WHOLESALER_ORDER_MESSAGE_EDITOR_WINDOW_WIDTH = 800;
    int WHOLESALER_ORDER_MESSAGE_EDITOR_WINDOW_HEIGHT = 700;
    int WARN_ALL_ITEMS_AVAILABLE_WINDOW_WIDTH = 650;
    int WARN_ALL_ITEMS_AVAILABLE_WINDOW_HEIGHT = 250;

    //App icon
    Image appIcon = new Image("graphics/myIcon.png");

    //Wholesaler Message
    String WHOLESALER_MESSAGE_DEFAULT_EMAIL_ADDRESS = "hurtownia.wroclaw@gmail.com";
    String WHOLESALER_MESSAGE_DEFAULT_SUBJECT = "Zamówienie na produkty";
    String WHOLESALER_MESSAGE_DEFAULT_BEGGINING = "Dzień Dobry,\n" + "Chielibyśmy powiększyć następną dostawę do naszego magazynu o następujące produkty:";
    String WHOLESALER_MESSAGE_DEFAULT_ENDING = "\n" + "Z poważaniem,\n" + "Sklep komputerowy";
    String WHOLESALER_MESSAGE_DEFAULT_PRODUCT = "- Produkt numer ";
    String WHOLESALER_MESSAGE_DEFAULT_PRODUCT_NUMBER = " w liczbie ";
}
