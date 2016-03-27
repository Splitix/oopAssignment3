package edu.txstate.simpleLib4170678;


public class Student extends User {
    int bookLimit;

    public Student () {
        this.bookLimit = 6;
    }

    public int getBookLimit () {
        return this.bookLimit;
    }

    public void setBookLimit() {
        this.bookLimit--;
    }

}
