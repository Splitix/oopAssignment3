package edu.txstate.simpleLib4170678;

/**
 * Created by Splitix on 2/29/16.
 */
public class Faculty extends User {
    int bookLimit;

    public Faculty () {
        this.bookLimit = 12;
    }

    public int getBookLimit() { return this.bookLimit;}
}
