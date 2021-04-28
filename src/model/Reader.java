package model;

public class Reader extends Person{
    private String address;
    private int phone;

    public Reader() {
        
    }

    public Reader(String name, int id, String address, int phone) {
        super(name, id);
        this.address = address;
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }
}





