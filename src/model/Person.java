package model;

public class Person {
    private String fullName;
    private int id;

    public Person() {
        
    }

    public Person(String fullName) {
        this.fullName = fullName;
    }

    public Person(String fullName, int id) {
        this.fullName = fullName;
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
