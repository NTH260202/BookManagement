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

    public ArrayList<Management> updateTotalBorrowed(ArrayList<Management> managements) {
        for (int i = 0; i < managements.size(); i++) {
            Management management = managements.get(i);
            int count = 0;
            for (int j = 0; j < managements.size(); j++) {
                if (managements.get(j).getReaders().getReaderId() == management.getReaders().getReaderId()) {
                    count += managements.get(j).getNumOfBorrowed();
                }
            }
            management.setNumOfTotalBorrowed(count);
            managements.set(i, management);
        }
        return managements;
    }

    public ArrayList<Management> sortByReaderName(ArrayList<Management> managements) {
        for (int i = 0; i < managements.size(); i++) {
            for (int j = managements.size() - 1; j > i; j--) {
                Management management_1 = managements.get(j);
                Management management_2 = managements.get(j-1);
                String[] name_1 = management_1.getReaders().getFullName().split("\\s");
                String[] name_2 = management_2.getReaders().getFullName().split("\\s");
                if (name_1[name_1.length - 1].compareTo(name_2[name_2.length - 1]) < 0) {
                    managements.set(j, management_2);
                    managements.set(j-1, management_1);
                }
            }
        }
        return managements;
    }

    public ArrayList<Management> sortByNumOfBorrowed(ArrayList<Management> managements) {
        for (int i = 0; i < managements.size(); i++) {
            for (int j = managements.size() - 1; j > i; j--) {
                Management management_1 = managements.get(j);
                Management management_2 = managements.get(j-1);
                if(management_1.getNumOfTotalBorrowed() > management_2.getNumOfTotalBorrowed()) {
                    managements.set(j, management_2);
                    managements.set(j-1, management_1);
                }
            }
        }
        return managements;
    }
}
