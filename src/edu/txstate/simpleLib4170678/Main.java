package edu.txstate.simpleLib4170678;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException{

        //Create arraylist to hold info from files
        ArrayList<User> UserList = new ArrayList<>();
        ArrayList<Books> BookList = new ArrayList<>();
        ArrayList<Journals> JournalList = new ArrayList<>();

        //User Objects
        User currentStu = new Student();
        User currentFac = new Faculty();
        User currentUser = new User();

        Scanner reader = new Scanner(System.in);

        readBooksFromFile(BookList);
        readJournalsFromFile(JournalList);
        int totalUsers = readUsersFromFile(UserList);

        int choice = 0;
        int userCounter = 0;
        boolean inputChecker = true;
        boolean continueLib = true;
        int idNumber;
        String search;


        //Loops until user make a correct choice
        while(inputChecker) {
            //Welcome Screen to sign in or sign up
            choice = displayMenu(0);

            if (choice == 1) {
                System.out.print("Please enter your ID number: ");
                idNumber = reader.nextInt();
                //Cycle through list of users
                for (User key : UserList) {
                    userCounter++;
                    if (key.getUserID() == idNumber) {
                        System.out.println("Welcome " + key.getName());
                        currentUser = key;
                        inputChecker = false;
                    }
                }
                if (userCounter > totalUsers || userCounter < 0) {
                    System.out.println("Invalid ID number.");
                }
            }
            //Allows the User to sign up
            else if (choice == 2) {

                String name;

                while (inputChecker) {
                    System.out.print("What is your title(Student or Faculty): ");
                    name = reader.nextLine();
                    name = name.toLowerCase();
                    if (name.equals("student")) {
                        currentStu.setUserType(name);
                        System.out.print("What is your name: ");
                        name = reader.nextLine();
                        currentStu.setName(name);
                        inputChecker = false;
                        currentUser = currentStu;
                    } else if(name.equals("faculty")){
                        currentFac.setUserType(name);
                        System.out.print("What is your name: ");
                        name = reader.nextLine();
                        currentFac.setName(name);
                        inputChecker = false;
                        currentUser = currentFac;
                    }else {
                        System.out.println("You didn't enter a correct type. Please Try again");
                    }

                    System.out.println(currentUser.getBookLimit());
                }
                currentUser.setUserID(totalUsers + 1);
                currentUser.setCheckedOut(0);
                currentUser.saveNewUser();
            } else if (choice == 3) {
                System.out.println("Program is Ending");
                return;
            } else {
                System.out.println("You enter an invalid option. Please try again");
            }
        }

        //Librarian mode allows the the user to add documents.
        if (currentUser.getUserType().equals("librarian")) {
            boolean libmode = true;
            while(libmode) {
                choice = displayMenu(6);
                if (choice == 1) {
                    choice = displayMenu(7);
                    if (choice == 1) {
                        //Enter information for books
                        Scanner data = new Scanner(System.in);
                        System.out.print("Enter Title: ");
                        String title = data.nextLine();
                        System.out.print("Enter Publisher: ");
                        String publisher = data.nextLine();
                        System.out.print("Enter Date: ");
                        String date = data.nextLine();
                        System.out.print("Enter ISBN: ");
                        String ISBN = data.nextLine();
                        System.out.print("Enter Copies: ");
                        int copies = data.nextInt();
                        System.out.print("Enter Author: ");
                        String author = data.nextLine();
                        int bookID = BookList.size() + 1;

                        BookList.add(new Books(title, publisher, date, ISBN, copies, author, bookID));

                        int counter = 0;
                        for (Books key : BookList) {
                            updateBookCount(key, counter);
                            counter++;
                        }

                    } else if (choice == 2) {

                        //Enter information for Journals
                        Scanner data = new Scanner(System.in);
                        System.out.print("Enter Title: ");
                        String title = data.nextLine();
                        System.out.print("Enter Published Date: ");
                        String publishedDate = data.nextLine();
                        System.out.print("Enter Volume Number: ");
                        int volume = data.nextInt();
                        System.out.print("Enter Issue Number: ");
                        int issue = data.nextInt();
                        System.out.print("Enter Copies: ");
                        int copies = data.nextInt();
                        System.out.print("Enter Publisher: ");
                        String publisher = data.nextLine();
                        System.out.print("Enter Articles: ");
                        String articles = data.nextLine();
                        int journalID = JournalList.size() + 1;

                        JournalList.add(new Journals(title, publishedDate, volume, issue, copies, publisher, articles, journalID));

                        int counter = 0;
                        for (Journals key : JournalList) {
                            updateJournalCount(key, counter);
                            counter++;
                        }

                    }
                } else if (choice == 2) {
                    System.out.println("Saving.....Exiting Program");
                    return;
                }
            }

        }

        //Main Loop for Students and Faculty
        while(continueLib){
            //Display Main Menu
            choice = displayMenu(1);


            System.out.println(choice);
            if (choice == 1) {
                //Search for a Document
                choice = displayMenu(2);
                //User choose Books
                if (choice == 1) {
                    //Search by what field
                    choice = displayMenu(3);
                    //Search by Title
                    if (choice == 1) {
                        System.out.print("Enter Search Criteria: ");
                        search = reader.next();
                        //Cycles through the ArrayList.
                        for (Books key : BookList) {
                            if (key.getTitle() != null && key.getTitle().contains(search)) {
                                System.out.println("For loop is working");
                                key.displayInfo();
                            }
                        }
                    }
                    //Search by Publisher
                    else if (choice == 2) {
                        System.out.print("Enter Search Criteria: ");
                        search = reader.next();
                        for (Books key : BookList) {
                            if (key.getPublisher() != null && key.getPublisher().contains(search)) {
                                key.displayInfo();
                            }
                        }
                    }
                    //Search by Author
                    else if (choice == 3) {
                        System.out.print("Enter Search Criteria: ");
                        search = reader.next();
                        for (Books key : BookList) {
                            if (key.getAuthor() != null && key.getAuthor().contains(search)) {
                                key.displayInfo();
                            }
                        }
                    }
                }
                //User choose Journals
                else if (choice == 2) {
                    //Search by what field
                    choice = displayMenu(4);
                    //Search by Title
                    if (choice == 1) {
                        System.out.print("Enter Search Criteria: ");
                        search = reader.next();
                        //Cycles through the ArrayList.
                        for (Journals key : JournalList) {
                            if (key.getTitle() != null && key.getTitle().contains(search)) {
                                key.displayInfo();
                            }
                        }
                    }
                    //Search by Publisher
                    else if (choice == 2) {
                        System.out.print("Enter Search Criteria: ");
                        search = reader.next();
                        for (Journals key : JournalList) {
                            if (key.getPublisher() != null && key.getPublisher().contains(search)) {
                                key.displayInfo();
                            }
                        }
                    }

                }
            } else if (choice == 2) {
                //Rent a Book
                choice = displayMenu(5);
                if (choice == 1) {
                    System.out.println("What is the book ID you would like to check out?: ");
                    Scanner userInput = new Scanner(System.in);
                    int bookId = userInput.nextInt();
                    for (Books key : BookList) {
                        if (key.getBookID() == bookId) {
                            key.displayInfo();
                            if (currentUser.getBookLimit() == 0) {
                                System.out.println("You don't have any more rentals available.");
                            } else {
                                key.setCopies();
                                int counters = 0;
                                for(Books keys : BookList){
                                    updateBookCount(keys, counters);
                                    counters++;
                                }
                                //Removes 1 from user checkout limit
                                currentUser.setBookLimit();
                                int index = currentUser.getBookArrayLength();
                                String bId = Integer.toString(bookId);
                                currentUser.setBookArray(bId);
                                int counter = 0;
                                for (User k : UserList) {
                                    updateAllUsersToFile(k, counter);
                                    counter++;
                                }
                            }

                        }
                    }

                }
                //Rent a Journal
                else if (choice == 2) {

                    System.out.println("What is the Journal ID you would like to check out?: ");

                    int bookId = reader.nextInt();
                    for (Journals key : JournalList) {
                        if (key.getJournalID() == bookId) {
                            key.displayInfo();
                            if (currentUser.getBookLimit() == 0) {
                                System.out.println("You don't have any more rentals available.");
                            } else {
                                //Removes 1 from user checkout limit
                                currentUser.setBookLimit();

                                String bId = Integer.toString(bookId);
                                currentUser.setBookArray(bId);
                                int counter = 0;
                                for (User k : UserList) {
                                    updateAllUsersToFile(k, counter);
                                    counter++;
                                }
                            }

                        }
                    }

                } else if (choice == 3) {
                    choice = displayMenu(1);
                }

            } else if (choice == 3) {
                //Return a book

                System.out.println("Which Document ID would you like to return?: ");
                System.out.println(currentUser.getBookArray());
                int size = currentUser.getBookArray().size();
                System.out.println(size);
                Scanner readerUser = new Scanner(System.in);
                int answer = readerUser.nextInt();

                //Finds what books the user currently has with them
                for(int i = 0; i < size; i++){
                    System.out.println(currentUser.getBookArray().get(i).toString());
                    String temp = currentUser.getBookArray().get(i).toString();
                    int num = Integer.parseInt(temp);
                    System.out.println(answer + "  " + num);
                    if(answer == num){
                        currentUser.getBookArray().remove(i);
                        currentUser.setReturnBook();

                        System.out.println("Book was Successfully returned");
                        if(num > 0){
                            for (Books key : BookList) {
                                if (key.getBookID() == num) {
                                    key.setReturnCopies();
                                }
                            }
                        }
                        else if(num < 0){
                            for (Journals key : JournalList) {
                                if (key.getJournalID() == num) {
                                    key.setReturnCopies();
                                }
                            }
                        }
                    }
                }

                //Updates the files.
                int newCounter = 0;
                for(User key: UserList){
                    updateAllUsersToFile(key, newCounter);
                    newCounter++;
                }
                int counter = 0;
                for (Books key : BookList) {
                    updateBookCount(key, counter);
                    counter++;
                }
                int otherCounter = 0;
                for (Journals key : JournalList) {
                    updateJournalCount(key, otherCounter);
                    otherCounter++;
                }




            }
            else if(choice == 4){
                continueLib = false;

                System.out.println("Files saved. Exiting system now.");
            }
            else{
                System.out.println("You entered an invalid choice.");
            }
        }

    }

    //Displays most of the differnet screen types (Menus)
    public static int displayMenu (int menuChoice) {

        boolean inputCheck = true;
        Scanner userInput = new Scanner(System.in);
        int choice = 0;

        //Sign in Screen
        if(menuChoice == 0){
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("Welcome to the Library:");
                System.out.println("   1. Sign in with ID");
                System.out.println("   2. Sign up");
                System.out.println("   3. Exit");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 4) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        //Main Menu
        else if(menuChoice == 1) {
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("Please select a choice:");
                System.out.println("   1. Search for a Document");
                System.out.println("   2. Rent a Document");
                System.out.println("   3. Return a Document");
                System.out.println("   4. Exit and Save");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 5) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        //Search for Document
        else if(menuChoice == 2){
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("What type of Document would you like to search for:");
                System.out.println("   1. Book");
                System.out.println("   2. Journal");
                System.out.println("   3. Back to Main Menu");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 4) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        //Search for Book
        else if(menuChoice == 3){
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("What would you like to search by:");
                System.out.println("   1. Title");
                System.out.println("   2. Publisher");
                System.out.println("   3. Author");
                System.out.println("   4. Back to Main Menu");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 5) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }

        }
        //Search for Journal
        else if(menuChoice == 4) {
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("What would you like to search by:");
                System.out.println("   1. Title");
                System.out.println("   2. Publisher");
                System.out.println("   3. Back to Main Menu");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 4) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        else if(menuChoice == 5) {
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("What type of document would you like to rent:");
                System.out.println("   1. Book");
                System.out.println("   2. Journal");
                System.out.println("   3. Back to Main Menu");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 4) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        else if(menuChoice == 6) {
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("Welcome librarian. What would you like to do? :");
                System.out.println("   1. Add new Document");
                System.out.println("   2. Exit and Save");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 3) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        else if(menuChoice == 7) {
            while (inputCheck) {
                System.out.println("======================================");
                System.out.println("Would you like to add a: ");
                System.out.println("   1. Book");
                System.out.println("   2. Journal");
                System.out.println("   3. Back to main menu");
                System.out.println("======================================");
                System.out.print("Enter choice: ");
                choice = userInput.nextInt();

                if (choice > 0 && choice < 4) {
                    return choice;
                } else {
                    System.out.println("You enter an invalid option. Please try again");
                }
            }
        }
        return -1;
    }

    //Reads from book file
    public static void readBooksFromFile (ArrayList BookList) {
        //Location of the files:
        String bookLoc = "src/bookfile.txt";

        String line = null;
        String wordArray[];


        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(bookLoc);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                wordArray = line.split(",");
                if (wordArray != null) {

                    int copies = Integer.parseInt(wordArray[4]);
                    int bID = Integer.parseInt(wordArray[6]);
                    BookList.add(new Books(wordArray[0], wordArray[1], wordArray[2], wordArray[3], copies, wordArray[5], bID));
                }

            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            bookLoc + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + bookLoc + "'");

        }
    }
    //Updates book file
    public static void updateBookCount(Books key, int count){
        String fileLoc = "src/bookfile.txt";

        try {


            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + fileLoc ;

            if(count > 0){
                FileWriter fstream = new FileWriter(loc, true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(key.getTitle() + "," + key.getPublisher() + "," + key.getDate() + "," + key.getISBN() + "," + key.getCopies() + "," + key.getAuthor() + "," + key.getBookID());
                out.newLine();

                //close buffer writer
                out.close();
            }else{
                FileWriter fstream = new FileWriter(loc, false);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(key.getTitle() + "," + key.getPublisher() + "," + key.getDate() + "," + key.getISBN() + "," + key.getCopies() + "," + key.getAuthor() + "," + key.getBookID());
                out.newLine();

                //close buffer writer
                out.close();
            }


        }
        catch(IOException ex){
            System.out.println(
                    "Error reading file '"
                            + fileLoc + "'");

        }
    }
    //Reads from Journal File
    public static void readJournalsFromFile (ArrayList JournalList) {
        //Location of the files:
        String journalLoc = "src/journalfile.txt";

        String line = null;
        String wordArray[];
        int counter = 0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(journalLoc);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                line = line.replace("[","");
                line = line.replace("]",",");

                wordArray = line.split(",");


                if (wordArray != null && counter != 0) {
                    int volume = Integer.parseInt(wordArray[2]);
                    int issue = Integer.parseInt(wordArray[3]);
                    int copies = Integer.parseInt(wordArray[4]);
                    int jID = Integer.parseInt(wordArray[7]);
                    JournalList.add(new Journals(wordArray[0], wordArray[1], volume, issue, copies, wordArray[5], wordArray[6], jID));
                }
                counter++;

            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            journalLoc + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + journalLoc + "'");

        }
    }
    //Updates the journal File
    public static void updateJournalCount(Journals key, int counter){
        String fileLoc = "src/journalfile.txt";

        try {


            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + fileLoc ;

            if(counter > 0){
                FileWriter fstream = new FileWriter(loc, true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(key.getTitle() + "," + key.getPublisher() + "," + key.getVolume() + "," + key.getIssue() + "," + key.getCopies() + "'" + key.getPublisher() + "," + key.getArticles() + "," + key.getJournalID());
                out.newLine();

                //close buffer writer
                out.close();
            }else{
                FileWriter fstream = new FileWriter(loc, false);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(key.getTitle() + "," + key.getPublisher() + "," + key.getVolume() + "," + key.getIssue() + "," + key.getCopies() + "'" + key.getPublisher() + "," + key.getArticles() + "," + key.getJournalID());
                out.newLine();

                //close buffer writer
                out.close();
            }


        }
        catch(IOException ex){
            System.out.println(
                    "Error reading file '"
                            + fileLoc + "'");

        }
    }
    //Updates the user file
    public static void updateAllUsersToFile(User key, int count) {
        String fileLoc = "src/UserFile.txt";

        try {


            File dir = new File(".");
            String loc = dir.getCanonicalPath() + File.separator + fileLoc ;

            if(count > 0){
                FileWriter fstream = new FileWriter(loc, true);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(key.getUserID() + "," + key.getName() + "," + key.getUserType() + "," + key.getBookLimit() + "," + key.getBookArray());
                out.newLine();

                //close buffer writer
                out.close();
            }else{
                FileWriter fstream = new FileWriter(loc, false);
                BufferedWriter out = new BufferedWriter(fstream);
                out.write(key.getUserID() + "," + key.getName() + "," + key.getUserType() + "," + key.getBookLimit() + "," + key.getBookArray());
                out.newLine();

                //close buffer writer
                out.close();
            }


        }
        catch(IOException ex){
            System.out.println(
                    "Error reading file '"
                            + fileLoc + "'");

        }
    }

    //Read in users from file
    public static int readUsersFromFile (ArrayList UserList) {
        //Location of the files:
        String userLoc = "src/UserFile.txt";

        String line = null;
        String wordArray[];
        int counter = 0;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(userLoc);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                line = line.replace("[","");
                line = line.replace("]",",");

                wordArray = line.split(",");

                if (wordArray != null) {
                    ArrayList<String> temp = new ArrayList<>();
                    for(int i = 4; i < wordArray.length; i++){
                        temp.add(wordArray[i]);
                    }
                    int id = Integer.parseInt(wordArray[0]);
                    int booklimit = Integer.parseInt(wordArray[3]);
                    UserList.add(new User(id, wordArray[1], wordArray[2], booklimit, temp));
                }
                counter++;
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            userLoc + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + userLoc + "'");

        }

        return counter; //Holds how many current users there are.
    }

}
