package model;

public class Reader extends Person {
    private String address;
    private int phone;

    public Reader(String name, int id, String address, int phone) {
        super(name, id);
        this.phone = phone;
        this.address = address;
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
