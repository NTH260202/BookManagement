package controller;

import java.util.ArrayList;

import model.BookReaderManagement;

public class ControllerProcess {
    public ArrayList<BookReaderManagement> updateBRMInfo(ArrayList<BookReaderManagement> list, BookReaderManagement brm) {
        boolean isUpdated = false;

        for(int i = 0; i < list.size(); i ++) {
            BookReaderManagement b = list.get(i);
            if(b.getBooks().getBookID() == brm.getBooks().getBookID()
            && b.getReaders().getReaderID() == brm.getReaders().getReaderID()) {
                list.set(i, brm); //update objects in BRM
                isUpdated = true;
                break;
            }
        }
        if(!isUpdated) {
            list.add(brm);
        }
        return list;
    }

    public ArrayList<BookReaderManagement> updateTotalBorrowed(ArrayList<BookReaderManagement> list) {
        for(int i = 0; i < list.size(); i++) {
            BookReaderManagement b = list.get(i);
            int count = 0;
            for (int j = 0; i < list.size(); j++) {
                if(list.get(j).getReaders().getReaderID() == b.getReaders().getReaderID()) {
                    count = count + list.get(j).getNumOfBorrowed();
                }
            }
            b.setTotalOfBorrowed(count);//update totalBorrowed
            list.set(i, b);
        }
        return list;
    }

    public ArrayList<BookReaderManagement> sortByReaderName(ArrayList<BookReaderManagement> list) {
        for(int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                BookReaderManagement bj = list.get(j);
                BookReaderManagement bbj = list.get(j-1);
                String[] name1 = bj.getReaders().getFullName().split("\\s");
                String[] name2 = bbj.getReaders().getFullName().split("\\s");
                if(name1[name1.length-1].compareTo(name2[name2.length-1])<0) {
                    list.set(j, bbj);
                    list.set(j - 1, bj);
                }
            }
        }
        return list;
    }

    public ArrayList<BookReaderManagement> sortByNumOfBorrowed(ArrayList<BookReaderManagement> list) {
        for(int i = 0; i < list.size(); i++) {
            for (int j = list.size() - 1; j > i; j--) {
                BookReaderManagement bj = list.get(j);
                BookReaderManagement bbj = list.get(j-1);
                if(bj.getTotalOfBorrowed() > bbj.getTotalOfBorrowed()) {
                    list.set(j, bbj);
                    list.set(j-1, bj);
                }
            }
        }
        return list;
    }

    public ArrayList<BookReaderManagement> searchByReaderName(ArrayList<BookReaderManagement> list, String key) {
       ArrayList<BookReaderManagement> result = new ArrayList<>();
       String pattern = "." + key.toLowerCase() + ".";
       for(int i = 0; i< list.size(); i++) {
           BookReaderManagement b = list.get(i);
           if(b.getReaders().getFullName().toLowerCase().matches(pattern)) {
               result.add(b);
           }
       }
       return result;
    }
}
        
