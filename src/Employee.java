public class Employee {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String mail;

    public Employee(String firstName, String lastName, int phoneNumber, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "\nEmployee: " +
                "\nFirstname: " + getFirstName() +
                "\nLastname: " + getLastName() +
                "\nPhone Number: " + getPhoneNumber() +
                "\nMail: " + getMail();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
