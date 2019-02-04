package tables;

import javax.persistence.*;

import static interfaces.IAppConstants.*;

/**
 * Objects of Magazinier class represent workers of magazine.
 */

@Entity
@Table(name = "magaziniers")
public class Magazinier {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = MAGAZINIER_PESEL_NUMBER_COLUMN_NAME)
    private int peselNumber;

    @Column(name = MAGAZINIER_NAME_COLUMN_NAME)
    private String name;

    @Column(name = MAGAZINIER_SURNAME_COLUMN_NAME)
    private String surname;

    @Column(name = MAGAZINIER_WORKING_HOURS_COLUMN_NAME)
    private String workHours;

    @Column(name = MAGAZINIER_SALARY_COLUMN_NAME)
    private double salary;

    @Column(name = MAGAZINIER_ACCOUNT_NUMBER_COLUMN_NAME)
    private int acountNumber;

    public Magazinier(int peselNumber, String name, String surname, String workHours, double salary, int acountNumber) {
        this.peselNumber = peselNumber;
        this.name = name;
        this.surname = surname;
        this.workHours = workHours;
        this.salary = salary;
        this.acountNumber = acountNumber;
    }

    public int getPeselNumber() {
        return peselNumber;
    }

    public void setPeselNumber(int peselNumber) {
        this.peselNumber = peselNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAcountNumber() {
        return acountNumber;
    }

    public void setAcountNumber(int acountNumber) {
        this.acountNumber = acountNumber;
    }
}
