package tables;

import interfaces.IAppConstants;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

/**
 * Objects of Magazine class represent some places where we store products (objects of Item class). In this app we are using only one instance of Magazine class.
 */
@Entity
@Table(name = "magazines")
public class Magazine implements IAppConstants {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column (name = MAGAZINE_ID_COLUMN_NAME)
    private int magazineId;

    @Column (name = "itemsAmount")
    private int itemsAmount;

    @Column (name = MAGAZINE_OWNER_SURNAME_COLUMN_NAME)
    private String ownerSurname;

    @Column (name = MAGAZINE_ADDRESS_COLUMN_NAME)
    private String magazineAddress;

    @Column (name = MAGAZINE_NAME_COLUMN_NAME)
    private String magazineName;

    /**
     * @return name of Magazine object
     */
    public String getName() {
        return magazineName;
    }

    /**
     * @param name given by User to change name of Magazine object
     */
    public void setName(String name) {
        this.magazineName = name;
    }
}
