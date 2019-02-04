package tables;

import javax.persistence.*;

import static interfaces.IAppConstants.*;
import static interfaces.IAppConstants.ORDER_INSTALLMENT_COLUMN_NAME;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = CLIENT_PESEL_NUMBER_COLUMN_NAME)
    private int clientPeselNumber;

    @Column(name = CLIENT_NAME_COLUMN_NAME)
    private String clientName;

    @Column(name = CLIENT_SURNAME_COLUMN_NAME)
    private String clientSurname;

    @Column(name = CLIENT_ADDRESS_COLUMN_NAME)
    private String clientAddress;

    public Client() {

    }

    /**
     * @param clientPeselNumber Set automatically by Hibernate framework and incremented with every new Object
     * @param clientName set by user as any String
     * @param clientSurname set by user as any String
     * @param clientAddress set by user as any String
     */
    public Client(int clientPeselNumber, String clientName, String clientSurname, String clientAddress) {
        this.clientPeselNumber = clientPeselNumber;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.clientAddress = clientAddress;
    }

    /**
     * @return pesel number of a client
     */
    public int getClientPeselNumber() {
        return clientPeselNumber;
    }

    /**
     * @param clientPeselNumber new value of client pesel name
     */
    public void setClientPeselNumber(int clientPeselNumber) {
        this.clientPeselNumber = clientPeselNumber;
    }

    /**
     * @return client name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName new client name
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return client surname
     */
    public String getClientSurname() {
        return clientSurname;
    }

    /**
     * @param clientSurname new client surname
     */
    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    /**
     * @return client address
     */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * @param clientAddress new client address
     */
    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }
}
