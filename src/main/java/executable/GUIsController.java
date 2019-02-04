package executable;

import interfaces.IAppConstants;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tables.Item;
import tables.ItemOrder;
import tables.Order;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to user interface management
 */
public class GUIsController extends Application implements IAppConstants {

    DBController dbController = new DBController();
    List<ItemOrder> currentSpecificItems = null;
    boolean shouldOpenAllOrderAvailableWindow;

    /**
     * Initializes all windows. Creates Parent objects, which load paths to proper .fxml files, creates Scene object for every window and applies them stylesheer (.css file).
     * Also creates Stage object for every window and set them proper Scene objects, titles,  and icons.
     * Loads every needed controllers (buttons, textFields, etc.) from all .fxml files.
     * Configures item table showed in one of main windows and fill them with items loaded from data base through DBController object.
     * Configures and sets proper actions to buttons.
     * Finally, shows the first window.
     * @param primaryStage first scene, created by the platform itself
     * @throws Exception general protection from exceptions
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {

        final Parent startWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/startWindow.fxml"));

        final Scene startWindowScene = new Scene(startWindow, WARN_WINDOW_WIDTH, WARN_WINDOW_HEIGHT);
        startWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        //PrimaryStage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.getIcons().add(appIcon);
        primaryStage.setScene(startWindowScene);

        Button itemsListButton = (Button) startWindowScene.lookup(ITEMS_LIST_BUTTON_NAME);
        Button ordersListButton = (Button) startWindowScene.lookup(ORDERS_LIST_BUTTON_NAME);

        itemsListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    serviceItemWindows(primaryStage);
                    primaryStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        ordersListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    generateWholesalerOrder(primaryStage);
                    primaryStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        primaryStage.show();
    }

    /**
     * Called ath the end of the program, closes EntityManagerFactory object from used DBController object.
     */
    @Override
    public void stop() {
        //ALWAYS REMEMBER TO KILL DBCONTROLLER!!! IT CLOSES ENTITYMANAGERFACTORY!!!
        dbController.killDBController();
    }
    /**
     * Supports and provides every operation connected with adding item to magazine.
     * @param primaryStage needed to have connection to primary stage.
     * @throws Exception when getting access the files
     */
    public void serviceItemWindows(final Stage primaryStage) throws Exception {

        //Get paths to windows that represent User Interface Windows
        final Parent itemsListWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/itemsListGUI.fxml"));
        final Parent addItemFormWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/addItemFormGUI.fxml"));
        final Parent incompleteAddItemFormWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/incompleteAddItemFormGUI.fxml"));
        final Parent addedItemCompleteWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/addedItemCompleteGUI.fxml"));
        final Parent updatedItemStateWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/updatetItemStateGUI.fxml"));
        final Parent duplicatedItemWarnWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/duplicatedItemWarnGUI.fxml"));

        //Create SCENES for every window and set them stylesheet
        final Scene itemsListWindowScene = new Scene(itemsListWindow, ITEM_LIST_WINDOW_WIDTH, ITEM_LIST_WINDOW_HEIGHT);
        itemsListWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene addItemFormWindowScene = new Scene(addItemFormWindow, ADD_ITEM_FORM_WINDOW_WIDTH, ADD_ITEM_FORM_WINDOW_HEIGHT);
        addItemFormWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene incompleteAddItemFormWindowScene = new Scene(incompleteAddItemFormWindow, ERROR_AND_OK_WINDOWS_WIDTH, ERROR_AND_OK_WINDOWS_HEIGHT);
        incompleteAddItemFormWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene addedItemCompleteWindowScene = new Scene(addedItemCompleteWindow, ERROR_AND_OK_WINDOWS_WIDTH, ERROR_AND_OK_WINDOWS_HEIGHT);
        addedItemCompleteWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene updatedItemStateWindowScene = new Scene(updatedItemStateWindow, ERROR_AND_OK_WINDOWS_WIDTH, ERROR_AND_OK_WINDOWS_HEIGHT);
        updatedItemStateWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene duplicatedItemWindowScene = new Scene(duplicatedItemWarnWindow, WARN_WINDOW_WIDTH, WARN_WINDOW_HEIGHT);
        duplicatedItemWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        //Create STAGES for every window and add them Icon!
        final Stage itemsListWindowStage = configureStage(itemsListWindowScene);
        final Stage addItemFormWindowStage = configureStage(addItemFormWindowScene);
        final Stage incompleteAddItemFormWindowStage = configureStage(incompleteAddItemFormWindowScene);
        final Stage addedItemCompleteWindowStage = configureStage(addedItemCompleteWindowScene);
        final Stage updatedItemStateWindowStage = configureStage(updatedItemStateWindowScene);
        final Stage duplicatedItemWindowStage = configureStage(duplicatedItemWindowScene);

        //Identify necessary controls (buttons etc.)
        Button addItemButton = (Button) itemsListWindowScene.lookup(ADD_ITEM_BUTTON_NAME);
        Button confirmFormButton = (Button) addItemFormWindowScene.lookup(CONFIRM_FORM_BUTTON_NAME);
        Button okAddedItemButton = (Button) addedItemCompleteWindowScene.lookup(OK_ADDED_ITEM_BUTTON_NAME);
        Button okUpdatedItemStatusButton = (Button) updatedItemStateWindowScene.lookup(OK_UPDATED_ITEM_STATUS_BUTTON_NAME);
        Button okIncompleteFormErrorButton = (Button) incompleteAddItemFormWindowScene.lookup(OK_INCOMPLETE_FORM_ERROR_BUTTON_NAME);
        Button warnFixDataButton = (Button) duplicatedItemWindowScene.lookup(WARN_FIX_DATA_BUTTON_NAME);
        Button warnIncreaseItemsButton = (Button) duplicatedItemWindowScene.lookup(WARN_INCREASE_ITEMS_BUTTON_NAME);
        Button openHelpButton = (Button) itemsListWindowScene.lookup(OPEN_HELP_PAGE_BUTTON_NAME);
        Button backFromItemListButton = (Button) itemsListWindowScene.lookup(BACK_FROM_ITEM_LIST_BUTTON_NAME);
        final TextField nameTextField = (TextField) addItemFormWindowScene.lookup(NAME_TEXT_FIELD_NAME);
        final TextField priceTextField = (TextField) addItemFormWindowScene.lookup(PRICE_TEXT_FIELD_NAME);
        final TextField stateTextField = (TextField) addItemFormWindowScene.lookup(STATE_TEXT_FIELD_NAME);

        //Configuring columns of items table
        configureTableColumns(itemsListWindowScene);

        //Fill Table with items from data base
        fillTableWithItems(itemsListWindowScene);

        //Set action to addItemButton - show new Window with form to add item
        addItemButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Open window with form for adding an item.
             * @param event catched by listener
             */
            @Override
            public void handle(ActionEvent event) {
                //Show add Item Form Stage
                addItemFormWindowStage.show();
            }
        });

        confirmFormButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * If the form for adding item is correctly filled, closes window with form, adds item to data base through DBController object, adds item to items List View and shows window with message about adding item (Main scenario).
             * If item with the same name as the one that user tries to add already exits in magazine, shows warn window with proper message.
             * If form is incolpete, shows error window with proper message.
             * @param event catched by listener
             */
            @Override
            public void handle(ActionEvent event) {

                //Check if form is filled
                if(!nameTextField.getText().isEmpty() && !priceTextField.getText().isEmpty() && !stateTextField.getText().isEmpty()) {

                    String newItemName = nameTextField.getText();

                    double newItemPrice = Double.valueOf(priceTextField.getText());
                    int newItemState = Integer.valueOf(stateTextField.getText());

                    Item itemToAdd = new Item(0, newItemName, newItemPrice, newItemState, dbController.getMagazine());

                    if (!dbController.itemNameExistsInMagazine(itemToAdd)) {
                        dbController.addItemToMagazine(itemToAdd);
                        insertItemToTable(dbController.getItemList().get(dbController.getItemList().size() - 1), itemsListWindowScene);
                        addItemFormWindowStage.close();
                        addedItemCompleteWindowStage.show();
                    }
                    else
                        duplicatedItemWindowStage.show();
                }
                else
                    incompleteAddItemFormWindowStage.show();
                //dbController.commitTransaction();
            }
        });

        warnIncreaseItemsButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Increases amount of item in data base through DBController object and also changes state of item in items Table View.
             * @param event catched by listener
             */
            @Override
            public void handle(ActionEvent event) {

                dbController.increaseItemState(Integer.valueOf(stateTextField.getText()), nameTextField.getText());

                TableView<Item> itemsTable = (TableView) itemsListWindowScene.lookup(ITEMS_TABLE_NAME);
                refreshTableView(itemsTable, itemsListWindowScene);

                addItemFormWindowStage.close();
                duplicatedItemWindowStage.close();
                updatedItemStateWindowStage.show();
            }
        });

        openHelpButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Opens help web site.
             * @param event catched by listener
             */
            @Override
            public void handle(ActionEvent event) {
                try {
                    File htmlFile = new File("src/main/resources/help/helpPage.html");
                    Desktop.getDesktop().browse(htmlFile.toURI());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        backFromItemListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.show();
                itemsListWindowStage.close();
            }
        });

        //Every OK button and FixDataButton from Warn Window do the same - just close their window
        okAddedItemButton.setOnAction(closeOKWindowHandler(addedItemCompleteWindowStage));
        okUpdatedItemStatusButton.setOnAction(closeOKWindowHandler(updatedItemStateWindowStage));
        okIncompleteFormErrorButton.setOnAction(closeOKWindowHandler(incompleteAddItemFormWindowStage));
        warnFixDataButton.setOnAction(closeOKWindowHandler(duplicatedItemWindowStage));

        itemsListWindowStage.show();
    }

    /**
     * Supports and provides every operation connected with generating wholesaler order.
     * @param primaryStage primary stage of an app
     * @throws Exception when getting access to the files
     */
    public void generateWholesalerOrder(final Stage primaryStage) throws Exception {

        //Get paths to windows that represent User Interface Windows
        final Parent clientsOrderListWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/clientsOrderListGUI.fxml"));
        final Parent clientsOrderDetailsWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/clientsOrderDetailsGUI.fxml"));
        final Parent wholesalerOrderMessageEditorWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/wholesalerOrderMessageEditorGUI.fxml"));
        final Parent allOrderItemsAvailableWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/allOrderItemsAvailableGUI.fxml"));

        //Create SCENES for every window and set them stylesheet
        final Scene clientsOrderListWindowScene = new Scene(clientsOrderListWindow, CLIENTS_ORDER_LIST_WINDOW_WIDTH, CLIENTS_ORDER_LIST_WINDOW_HEIGHT);
        clientsOrderListWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene clientsOrderDetailsWindowScene = new Scene(clientsOrderDetailsWindow, CLIENTS_ORDER_DETAILS_WINDOW_WIDTH, CLIENTS_ORDER_DETAILS_WINDOW_HEIGHT);
        clientsOrderDetailsWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene wholesalerOrerMessageEditorWindowScene = new Scene(wholesalerOrderMessageEditorWindow, WHOLESALER_ORDER_MESSAGE_EDITOR_WINDOW_WIDTH, WHOLESALER_ORDER_MESSAGE_EDITOR_WINDOW_HEIGHT);
        wholesalerOrerMessageEditorWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());

        final Scene allOrderItemsAvailableWindowScene = new Scene(allOrderItemsAvailableWindow, WARN_ALL_ITEMS_AVAILABLE_WINDOW_WIDTH, WARN_ALL_ITEMS_AVAILABLE_WINDOW_HEIGHT);
        allOrderItemsAvailableWindowScene.getStylesheets().add(getClass().getClassLoader().getResource("styles/stylesheet.css").toExternalForm());


        //Create STAGES for every window and add them Icon!
        final Stage clientsOrderListWindowStage = configureStage(clientsOrderListWindowScene);
        final Stage clientsOrderDetailsWindowStage = configureStage(clientsOrderDetailsWindowScene);
        final Stage wholesalerOrerMessageEditorWindowStage = configureStage(wholesalerOrerMessageEditorWindowScene);
        final Stage allOrderItemsAvailableWindowStage = configureStage(allOrderItemsAvailableWindowScene);

        Button openHelpButton = (Button) clientsOrderListWindowScene.lookup(OPEN_HELP_PAGE_BUTTON_NAME);
        Button backFromItemListButton = (Button) clientsOrderListWindow.lookup(BACK_FROM_ITEM_LIST_BUTTON_NAME);
        Button backFromOrderDetailsButton = (Button) clientsOrderDetailsWindow.lookup(ORDERS_BACK_FROM_DETAIL_BUTTON_NAME);
        Button closeAllOrderAvaiableButton = (Button) allOrderItemsAvailableWindow.lookup(ALL_ORDER_ITEMS_AVAILABLE_BUTTON_NAME);
        Button generateWholesalerOrderButton = (Button) clientsOrderDetailsWindow.lookup(GENERATE_WHOLESALER_ORDER_BUTTON_NAME);
        Button wholesalerOrderMessageEditorBackButton = (Button) wholesalerOrderMessageEditorWindow.lookup(WHOLESALER_ORDER_BACK_BUTTON_NAME);
        Button wholesalerOrderMessageEditorSendButton = (Button) wholesalerOrderMessageEditorWindow.lookup(WHOLESALER_ORDER_SEND_BUTTON_NAME);

        final TextField orderIdTextField = (TextField) clientsOrderDetailsWindowScene.lookup(ORDER_ID_TEXT_FIELD_NAME);
        final TextField orderPriceTextField = (TextField) clientsOrderDetailsWindowScene.lookup(ORDER_PRICE_TEXT_FIELD_NAME);
        final TextField orderStatusTextField = (TextField) clientsOrderDetailsWindowScene.lookup(ORDER_STATUS_TEXT_FIELD_NAME);
        final TextField orderInstallmentTextField = (TextField) clientsOrderDetailsWindowScene.lookup(ORDER_INSTALLMENT_TEXT_FIELD_NAME);
        final TextField wholesalerOrderRecieverTextField = (TextField) wholesalerOrerMessageEditorWindowScene.lookup(WHOLESALER_ORDER_RECIEVER_TEXT_FIELD_NAME);
        final TextField wholesalerOrderSubjectTextField = (TextField) wholesalerOrerMessageEditorWindowScene.lookup(WHOLESALER_ORDER_SUBJECT_TEXT_FIELD_NAME);
        final TextArea wholesalerOrderMessageTextField = (TextArea) wholesalerOrerMessageEditorWindowScene.lookup(WHOLESALER_ORDER_MESSAGE_TEXT_AREA_NAME);

        final TableView ordersTable = (TableView) clientsOrderListWindow.lookup(ITEMS_TABLE_NAME);
        final TableView orderItemsTable = (TableView) clientsOrderDetailsWindow.lookup(ITEMS_TABLE_NAME);


        openHelpButton.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * Opens help web site.
             * @param event catched by listener
             */
            @Override
            public void handle(ActionEvent event) {
                try {
                    File htmlFile = new File("src/main/resources/help/helpPage.html");
                    Desktop.getDesktop().browse(htmlFile.toURI());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        backFromItemListButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.show();
                clientsOrderListWindowStage.close();
            }
        });

        backFromOrderDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clientsOrderDetailsWindowStage.close();
                clientsOrderListWindowStage.show();
            }
        });

        closeAllOrderAvaiableButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                allOrderItemsAvailableWindowStage.close();
            }
        });

        wholesalerOrderMessageEditorBackButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wholesalerOrerMessageEditorWindowStage.close();
                clientsOrderDetailsWindowStage.show();
            }
        });

        wholesalerOrderMessageEditorSendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wholesalerOrerMessageEditorWindowStage.close();
                primaryStage.show();
            }
        });

        generateWholesalerOrderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                wholesalerOrderRecieverTextField.setText(WHOLESALER_MESSAGE_DEFAULT_EMAIL_ADDRESS);
                wholesalerOrderSubjectTextField.setText(WHOLESALER_MESSAGE_DEFAULT_SUBJECT);
                wholesalerOrderMessageTextField.setText(generateWholesalerOrderMessage(currentSpecificItems));

                clientsOrderDetailsWindowStage.close();
                wholesalerOrerMessageEditorWindowStage.show();
                if(shouldOpenAllOrderAvailableWindow) {
                    allOrderItemsAvailableWindowStage.show();
                }
            }
        });

        ordersTable.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {

                Order selectedOrder = (Order) ordersTable.getSelectionModel().getSelectedItem();
                orderIdTextField.setText(Integer.toString(selectedOrder.getOrderId()));
                orderPriceTextField.setText(Double.toString(selectedOrder.getOrderPrice()));
                orderStatusTextField.setText(selectedOrder.getStatus());
                orderInstallmentTextField.setText(selectedOrder.getInstallment());

                List<ItemOrder> allItemsOrders = dbController.getItemOrderList();
                List<ItemOrder> specificItemsOrders = new ArrayList<ItemOrder>();
                for(ItemOrder i : allItemsOrders) {
                    if(i.getOrderId().getOrderId()== selectedOrder.getOrderId()) {
                        specificItemsOrders.add(i);
                    }
                }
                currentSpecificItems = specificItemsOrders;

                //Configuring columns of items table
                configureItemOrderTableColumns(clientsOrderDetailsWindowScene);

                //Fill Table with specific items from data base
                orderItemsTable.getItems().clear();
                fillTableWithSpecificItems(clientsOrderDetailsWindowScene, specificItemsOrders);

                shouldOpenAllOrderAvailableWindow = areAllItemsAvailable(dbController.getItemList(), specificItemsOrders);

                clientsOrderListWindowStage.close();
                clientsOrderDetailsWindowStage.show();
            }
        });
        //Configuring columns of orders table
        configureOrdersTableColumns(clientsOrderListWindowScene);

        //Fill Table with orders from data base
        fillTableWithOrders(clientsOrderListWindowScene);

        clientsOrderListWindowStage.show();
    }

    /**
     * Something like Stage object constructor, but adapted to our requirements (sets title and icon).
     * @param sceneToStage Scene object based on which the Stage is created
     * @return properly configured Stage object
     */
    public Stage configureStage(Scene sceneToStage) {
        Stage resultStage = new Stage();
        resultStage.setTitle(APP_TITLE);
        resultStage.setScene(sceneToStage);
        resultStage.getIcons().add(appIcon);
        return resultStage;
    }

    /**
     * Properly configures amount and order of columns and rows in items Table View.
     * @param scene contains some TableView object which needs to be configured
     */
    public void configureTableColumns(Scene scene) {

        TableView<Item> itemsTable = (TableView) scene.lookup(ITEMS_TABLE_NAME);

        TableColumn itemIdColumn = itemsTable.getColumns().get(ITEM_ID_COLUMN_INDEX);
        TableColumn nameColumn = itemsTable.getColumns().get(ITEM_NAME_COLUMN_INDEX);
        TableColumn priceColumn = itemsTable.getColumns().get(ITEM_PRICE_COLUMN_INDEX);
        TableColumn stateColumn = itemsTable.getColumns().get(ITEM_STATE_COLUMN_INDEX);

        itemsTable.getColumns().clear();

        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_ID_COLUMN_NAME));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_NAME_COLUMN_NAME));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_PRICE_COLUMN_NAME));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_STATE_COLUMN_NAME));

        itemsTable.getColumns().addAll(itemIdColumn, nameColumn, priceColumn, stateColumn);
    }

    /**
     * Properly configures amount and order of columns and rows in itemsorders Table View.
     * @param scene contains some TableView object which needs to be configured
     */
    public void configureItemOrderTableColumns(Scene scene) {

        TableView<Item> itemsTable = (TableView) scene.lookup(ITEMS_TABLE_NAME);

        TableColumn nameColumn = itemsTable.getColumns().get(ITEM_NAME_COLUMN_INDEX);
        TableColumn itemIdColumn = itemsTable.getColumns().get(ITEM_ID_COLUMN_INDEX);
        TableColumn priceColumn = itemsTable.getColumns().get(ITEM_PRICE_COLUMN_INDEX);
        TableColumn stateColumn = itemsTable.getColumns().get(ITEM_STATE_COLUMN_INDEX);

        itemsTable.getColumns().clear();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_ID_COLUMN_NAME));
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_NAME_COLUMN_NAME));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_PRICE_COLUMN_NAME));
        stateColumn.setCellValueFactory(new PropertyValueFactory<>(ITEM_STATE_COLUMN_NAME));

        itemsTable.getColumns().addAll(itemIdColumn, nameColumn, priceColumn, stateColumn);
    }

    /**
     * Properly configures amount and order of columns and rows in orders Table View.
     * @param scene contains some TableView object which needs to be configured
     */
    public void configureOrdersTableColumns(Scene scene) {

        TableView<Order> ordersTable = (TableView) scene.lookup(ORDERS_TABLE_NAME);

        TableColumn orderIdColumn = ordersTable.getColumns().get(ORDER_ID_COLUMN_INDEX);
        TableColumn orderPriceColumn = ordersTable.getColumns().get(ORDER_PRICE_COLUMN_INDEX);
        TableColumn orderStatusColumn = ordersTable.getColumns().get(ORDER_STATUS_COLUMN_INDEX);
        TableColumn orderInstallementColumn = ordersTable.getColumns().get(ORDER_INSTALLMENT_COLUMN_INDEX);

        ordersTable.getColumns().clear();

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>(ORDER_ID_COLUMN_NAME));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<>(ORDER_PRICE_COLUMN_NAME));
        orderStatusColumn.setCellValueFactory(new PropertyValueFactory<>(ORDER_STATUS_COLUMN_NAME));
        orderInstallementColumn.setCellValueFactory(new PropertyValueFactory<>(ORDER_INSTALLMENT_COLUMN_NAME));

        ordersTable.getColumns().addAll(orderIdColumn, orderPriceColumn, orderStatusColumn, orderInstallementColumn);
    }

    /**
     * Inserts given Item object to items Table View in given scene WARNING Just set the item into Table View, NOT into data base!
     * @param item inserted to Table View item
     * @param scene contains proper TableView object
     */
    public void insertItemToTable(Item item, Scene scene) {

        TableView<Item> itemsTable = (TableView) scene.lookup(ITEMS_TABLE_NAME);
        itemsTable.getItems().add(item);
    }

    /**
     * Using by Buttons objects which the only task is to close the Stage object they belong to (OK Buttons etc.).
     * @param stageToClose Stage object which is going to disappear after click on button
     * @return event, that is closing the stage
     */
    public EventHandler closeOKWindowHandler (final Stage stageToClose) {

        EventHandler windowCloser = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageToClose.close();
            }
        };
        return windowCloser;
    }

    /**
     * Uses method insertItemToTable to fill whole TableView with items downloaded from data base through DBController object.
     * @param scene contains some TableView object
     */
    public void fillTableWithItems(Scene scene) {

        final List<Item> itemList = dbController.getItemList();
        for(Item i : itemList)
            insertItemToTable(i, scene);
    }

    /**
     * Clears given TableView object, downloads items from data base through DBController object again and insert them into item Table View.
     * @param tableView Table View object needed to be refreshed
     * @param scene Scene object containing some TableView object
     */
    public void refreshTableView(TableView<Item> tableView, Scene scene) {
        tableView.getItems().clear();
        List<Item>items = dbController.getItemList();

        for(Item i : items)
            insertItemToTable(i, scene);
    }

    /**
     * Uses method insertItemToTable to fill whole TableView with items downloaded from data base through DBController object.
     * @param scene contains some TableView object
     */
    public void fillTableWithOrders(Scene scene) {

        final List<Order> itemList = dbController.getOrderList();
        for(Order i : itemList)
            insertOrderToTable(i, scene);
    }

    /**
     * Insertes all orders into the table
     * @param order an order to be inserted into a table
     * @param scene a scene containing a table
     */
    public void insertOrderToTable(Order order, Scene scene) {

        TableView<Order> orderTable = (TableView) scene.lookup(ORDERS_TABLE_NAME);
        orderTable.getItems().add(order);
    }

    /**
     * Inserts specific items into the table
     * @param scene a scene containing a table
     * @param itemOrderList a list of specific items
     */
    public void fillTableWithSpecificItems(Scene scene, List<ItemOrder> itemOrderList) {

        final List<Item> itemList = dbController.getItemList();
        for(Item i : itemList) {
            boolean found = false;
            for (int j = 0; j < itemOrderList.size() && !found; j++) {
                if (itemOrderList.get(j).getItemId().getItemId() == i.getItemId()) {
                    insertItemToTable(new Item(i.getItemId(), i.getItemName(), i.getItemPrice(), itemOrderList.get(j).getItemsAmount(), dbController.getMagazine()), scene);
                    found = true;
                }
            }
        }
    }

    /**
     * Checks if magazine has enough items to take care of the order
     * @param allItems a list of all items in database
     * @param itemsOrders a list of all itemsorders
     * @return false if there are not enough items in magazine or true if there are enough items in magazine
     */
    public boolean areAllItemsAvailable(List<Item> allItems, List<ItemOrder> itemsOrders) {
        if(allItems == null || itemsOrders == null) {
            return false;
        }
        for(int i = 0; i < itemsOrders.size(); i++) {
            for(int j = 0; j < allItems.size(); j++) {
                if(itemsOrders.get(i).getItemId().getItemId() == allItems.get(j).getItemId() &&
                itemsOrders.get(i).getItemsAmount() > allItems.get(j).getState()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Generates message to be sent to wholesaler
     * @param items list of all items in the order
     * @return a message to be sent to wholesaler
     */
    private String generateWholesalerOrderMessage(List<ItemOrder> items) {
        String result = WHOLESALER_MESSAGE_DEFAULT_BEGGINING;
        for(int i = 0; i < items.size(); i++) {
            result += "\n";
            result += WHOLESALER_MESSAGE_DEFAULT_PRODUCT;
            result += items.get(i).getItemId().getItemId();
            result += WHOLESALER_MESSAGE_DEFAULT_PRODUCT_NUMBER;
            result += items.get(i).getItemsAmount();
        }
        return result;
    }
}