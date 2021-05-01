package controller;

import java.util.ArrayList;

import model.Management;

public class Utility {
    public ArrayList<Management> updateManagementInfo(ArrayList<Management> managements, Management management) {
        boolean isUpdated = false;

        for (int i = 0; i < managements.size(); i++) {
            Management check = managements.get(i);
            if (check.getBooks().getBookId() == management.getBooks().getBookId() && 
                check.getReaders().getReaderId() == management.getReaders().getReaderId()) {
                managements.set(i, management);
                isUpdated = true;
                break;
            }
        }
        if (!isUpdated) {
            managements.add(management);
        }
        return managements;
    }
}
