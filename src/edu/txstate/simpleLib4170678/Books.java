package edu.txstate.simpleLib4170678;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Read all books and store into arraylist
public class Books {
    String title;
    String publisher;
    String date;
    String ISBN;
    int copies;
    String author;
    int bookID;

    public Books () {}

    public Books (String t, String p, String d, String i, int c, String a, int bID) {
        this.title = t;
        this.publisher = p;
        this.date = d;
        this.ISBN = i;
        this.copies = c;
        this.author = a;
        this.bookID = bID;

    }

    public String getTitle() {
        return this.title;
    }

    public int getCopies() { return this.copies; }

    public String getISBN() { return this.ISBN; }

    public String getDate() { return this.date; }

    public String getPublisher() {
        return this.publisher;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getBookID() { return this.bookID;}

    public void setCopies() { this.copies--; }

    public void setReturnCopies() {this.copies++; }

    public void displayInfo(){
        System.out.println("Title: " + this.title);
        System.out.println("Publisher: " + this.publisher);
        System.out.println("Date: " + this.date);
        System.out.println("ISBN: " + this.ISBN);
        System.out.println("Copies: " + this.copies);
        System.out.println("Author: " + this.author);
        System.out.println("Book ID: " + this.bookID);
        System.out.println("======================================");
    }

    public void displayAll(){
        System.out.print(this.title + "   ");
        System.out.print(this.author + "   ");
        System.out.print(this.copies + "   ");
        System.out.print(this.bookID + "   ");
        System.out.println();
    }
}
