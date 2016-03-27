package edu.txstate.simpleLib4170678;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Creates a user object
public class User {
    String name;
    int userID;
    String userType;
    int bookLimit;     //How many documents can they have out
    ArrayList<String> bookArr = new ArrayList<>();



    public User () {
        this.name = "";
        this.userID = 0;
        this.userType = "";
        this.bookLimit = 0;

    }

    public User ( int userID, String name, String userType, int limit, ArrayList books) {
        this.name = name;
        this.userID = userID;
        this.userType = userType;
        this.bookLimit = limit;
        this.bookArr = books;

    }

    public String getName() {
        return this.name;
    }

    public int getUserID() {
        return this.userID;
    }

    public String getUserType() {
        return this.userType;
    }

    public int getBookLimit() {return this.bookLimit;}

    public int getBookArrayLength () { return this.bookArr.size(); }

    public ArrayList getBookArray() {
        return this.bookArr;
    }
    public void setBookArray (String newBook) {
        this.bookArr.add(newBook);
    }

    public void setBookLimit() { this.bookLimit--;}

    public void setReturnBook() { this.bookLimit++; }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserID(int id) {
        this.userID = id;
    }

    public void setUserType(String type) {
        this.userType = type;
    }

    public void setCheckedOut(int out) {
        this.bookLimit = out;
    }

    public void saveNewUser() throws IOException{

        File dir = new File(".");
        String loc = dir.getCanonicalPath() + File.separator + "src/UserFile.txt";

        FileWriter fstream = new FileWriter(loc, true);
        BufferedWriter out = new BufferedWriter(fstream);

        out.write(this.userID+","+this.name+","+this.userID+","+this.userType+","+this.getBookLimit()+","+this.bookArr);
        out.newLine();

        //close buffer writer
        out.close();
    }





}
