package model;

public class Reader {
    private static  int id = 10000000;
    private int readerID;
    private String fullName;
    private String address;
    private int phoneNumber;

    public Reader() {

    }
    
    public Reader(int readerID) {
        this.readerID = readerID;
    }

    public Reader(int readerID, String fullName, String address, int phoneNumber) {
        setReaderID();
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static int getID() {
        return id;
    }

    public static void setID(int id) {
        Reader.id = id;
    }

    public void setReaderID() {
        this.readerID = id ++;
    }

    public void setReaderID(int id) {
        readerID = id;
    }

    public int getReaderID() {
        return readerID;
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

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" + 
                " readerID: " + readerID +
                " fullName: " + fullName +
                " address: " + address +
                " phoneNumber: " + phoneNumber + 
                "}";
    }
}
