import java.time.LocalDate;

public class User {

    private int id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private int numberOfDays;

    public User(String name, String surname, String dateOfBirth, int numberOfDays) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.numberOfDays = numberOfDays;
    }

    public User(int id, String name, String surname, String dateOfBirth, int numberOfDays) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.numberOfDays = numberOfDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public java.lang.String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", surname=" + surname +
                ", dateOfBirth=" + dateOfBirth +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
