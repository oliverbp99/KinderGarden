public class Leader extends Employee{
    String password;

    public Leader(String firstName, String lastName, int phoneNumber, String mail, String password) {
        super(firstName, lastName, phoneNumber, mail);
        this.password = password;
    }

    public String toString() {
        return "Leader: " +
                "\nFirstName: " + getFirstName() +
                "\nLastName='" + getLastName() +
                "\nPhoneNumber: " + getPhoneNumber() +
                "\nMail: " + getMail() +
                "\nPassword: " + getPassword();
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}