public class Pedagogue extends Employee {
    private String area;

    public Pedagogue(String firstName, String lastName, int phoneNumber, String mail, String area) {
        super(firstName, lastName, phoneNumber, mail);
        this.area = area;
    }

    @Override
    public String toString() {
        return "\nPedagogue: " +
                "\nFirstname: " + getFirstName() +
                "\nLastname: " + getLastName() +
                "\nPhone number: " + getPhoneNumber() +
                "\nMail: " + getMail() +
                "\nArea: " + area;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
