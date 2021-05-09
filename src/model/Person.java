package model;

public class Person {
    private String fullName;
    private String address;

    public Person() {
        
    }

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(String fullName, String address) {
        this.fullName = fullName;
        this.address = address;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
