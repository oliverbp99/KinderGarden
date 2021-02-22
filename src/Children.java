public class Children {
    private String firstName;
    private String lastName;
    private String area;

    public Children(String firstName, String lastName, String area) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.area = area;
    }
    public String toString() {
        return "Children: " +
                "\nFirstname: " +
                "\nLastname: " +
                "\nArea: ";
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName() {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName() {
        this.lastName = lastName;
    }
    public String getArea() {
        return area;
    }
    public void setArea() {
        this.area = area;
    }


}
