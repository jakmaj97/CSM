package tables;

import interfaces.IAppConstants;

import javax.persistence.*;

import static interfaces.IAppConstants.*;

/**
 * Objects of Order class represent orders containig items.
 */

@Entity
@Table(name = "orders")
public class Order implements IAppConstants {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = ORDER_ID_COLUMN_NAME)
    private int orderId;

    @Column(name = ORDER_PRICE_COLUMN_NAME)
    private double orderPrice;

    @Column(name = ORDER_STATUS_COLUMN_NAME)
    private String status;

    @Column(name = ORDER_INSTALLMENT_COLUMN_NAME)
    private String installment;

//    @ManyToOne
//    @JoinColumn (name = ORDER_MAGAZINIER_PESEL_NUMBER_COLUMN_NAME)
//    private Magazinier magazinierPeselNumber;
//
//    @ManyToOne
//    @JoinColumn (name = ORDER_CLIENT_PESEL_NUMBER_COLUMN_NAME)
//    private Client clientPeselNumber;

    public Order() {

    }

    /**
     * @param orderId Set automatically by Hibernate framework and incremented with every new Object
     * @param orderPrice set by user as any numeric value
     * @param status set by user as any String
     * @param installment set by user as any String
     * @param magazinierPeselNumber set by user as a reference to Magazinier who is responsible for taking care of that order
     * @param clientPeselNumber set by user as a reference to Client who placed the order
     */
    public Order(int orderId, double orderPrice, String status, String installment, Magazinier magazinierPeselNumber, Client clientPeselNumber) {
        this.orderId = orderId;
        this.orderPrice = orderPrice;
        this.status = status;
        this.installment = installment;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInstallment() {
        return installment;
    }

    public void setInstallment(String installment) {
        this.installment = installment;
    }

//    public Magazinier getMagazinierPeselNumber() {
//        return magazinierPeselNumber;
//    }
//
//    public void setMagazinierPeselNumber(Magazinier magazinierPeselNumber) {
//        this.magazinierPeselNumber = magazinierPeselNumber;
//    }
//
//    public Client getClientPeselNumber() {
//        return clientPeselNumber;
//    }
//
//    public void setClientPeselNumber(Client clientPeselNumber) {
//        this.clientPeselNumber = clientPeselNumber;
//    }
}
