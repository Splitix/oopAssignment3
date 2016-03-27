package edu.txstate.simpleLib4170678;

//Creates Journal Objects
public class Journals {
    String title;
    String publisherDate;
    int volume;
    int issue;
    int copies;
    String publisher;
    String articles;
    int journalID;

    public Journals () {}

    public Journals (String t, String pd, int v, int i, int c, String p, String a, int jID) {
        this.title = t;
        this.publisherDate = pd;
        this.volume = v;
        this.issue = i;
        this.copies = c;
        this.publisher = p;
        this.articles = a;
        this.journalID = jID;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public int getJournalID() {return this.journalID; }

    public int getVolume() { return this.volume;}

    public int getIssue() { return this.issue;}

    public int getCopies() { return this.copies;}

    public String getArticles() { return this.articles; }

    public void setCopies() { this.copies--;}

    public void setReturnCopies() { this.copies++;}

    public void displayInfo(){
        System.out.println("Title: " + this.title);
        System.out.println("Published Date: " + this.publisherDate);
        System.out.println("Volume: " + this.volume);
        System.out.println("Issue: " + this.issue);
        System.out.println("Publisher: " + this.publisher);
        System.out.println("Articles: " + this.articles);
        System.out.println("Journal ID: " + this.journalID);
        System.out.println("======================================");
    }
}

