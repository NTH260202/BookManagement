package model;

public class Reader extends Person{
    private static int baseId = 10000000;
    private int readerId;
    private int phone;

    public Reader() {
        
    }

    public Reader(int readerId) {
        this.readerId = readerId;
    }

    public Reader(String name, String address) {
        super(name, address);
    }

    public Reader(int readerId, String fullName, String address, int phone) {
        super(fullName, address);
        setReaderId();
        this.phone = phone;
    }

    public static int getBaseId() {
        return baseId;
    }

    public static void setBaseId(int baseId) {
        Reader.baseId = baseId;
    }

    public void setReaderId() {
        this.readerId = baseId++;
    }

    public void setReaderId(int id) {
        readerId = id;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Reader{" +
                " readerId: " + readerId +
                " readerName: " + getFullName() +
                " Address: " + getAddress() +
                " Phone: " + phone +
                "}";
    }
}





