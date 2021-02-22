public class Pedagogue extends Employee {
    private String area;

    public Pedagogue(String firstName, String lastName, int phoneNumber, String mail, String address, String area) {
        super(firstName, lastName, phoneNumber, mail, address);
        this.area = area;
    }

    @Override
    public String toString() {
        return "Pedagogue: " +
                "\nFirstname: " + getFirstName() +
                "\nLastname: " + getLastName() +
                "\nPhone number: " + getPhoneNumber() +
                "\nMail: " + getMail() +
                "\nAddress: " + getAddress() +
                "\nArea: " + area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
