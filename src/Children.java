public class Children {
    private String firstName;
    private String lastName;
    private int age;
    private String area;

    public Children(String firstName, String lastName, int age, String area) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.area = area;
    }
    public Children(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    public Children(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String toString() {
        return "Children: " + getFirstName() + " " +  getLastName() + " " + getAge() + " " + getArea();
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
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
