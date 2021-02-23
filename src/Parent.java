public class Parent {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String mail;
    Children children;

    public Parent(String firstName, String lastName, int phoneNumber, String mail, Children children) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
        this.children = children;
    }
    public Parent(String firstName, String lastName, int phoneNumber, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "\nParent: " +
                "\nFirst name: " + firstName +
                "\nLast name: " + lastName +
                "\nPhone number: " + phoneNumber +
                "\nMail: " + mail +
                "\n Children: " + children;

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

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }

}
