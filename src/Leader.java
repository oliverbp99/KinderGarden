public class Leader extends Employee{

    public Leader(String firstName, String lastName, int phoneNumber, String mail, String address) {
        super(firstName, lastName, phoneNumber, mail, address);
    }

    @Override
    public String toString() {
        return "Leader: " +
                "\nFirstName: " + getFirstName() +
                "\nLastName='" + getLastName() +
                "\nPhoneNumber: " + getPhoneNumber() +
                "\nMail: " + getMail() +
                "\nPassword: " + getPassword();
    }
}