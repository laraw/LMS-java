//package lms.test;
//import lms.*;
//
///**
// * <b>Programming 2, Assignment 1, SP1 - 2014</b><br/>
// * 
// * This class will test the AMS system.
// * 
// * USAGE -----
// * 
// * Run it for an interactive test menu.
// * 
// * Test #11 ("Test all") will dump any debugging output made by the test
// * program, your code, and the JRE, into a text file named "tests.txt".
// * 
// * It will also estimate the completeness of the "functionality" section of the Assignment.
// * 
// * The test #11 can be invoked from the command line with the "all" argument.
// * 
// */
//
//import java.io.*;
//import java.util.*;
//
//import lms.model.*;
//import lms.model.exception.*;
//import lms.model.facade.*;
//
///**
// * @author Mikhail Perepletchikov
// */
//
//public class TestHarness {
//    
//    /**
//     ************************************************************************* 
//     ************************************************************************* 
//     * BE VERY CAREFUL CHANGING ANYTHING PAST HERE! You may add your own lines
//     * of code while debugging your work, but DO NOT change the existing code.
//     * YOUR SYSTEM MUST BE ABLE TO WORK WITH ANYTHING AFTER THIS POINT, LEFT
//     * UNMODIFIED !!!
//     ************************************************************************* 
//     ************************************************************************* 
//     **/
//    
//    private static final String COLLECTION_CODE = "LIB_IT";
//    private static final String COLLECTION_NAME = "Information Technology";
//
//    private static final String STANDARD_MEMBER_ID = "m00001";
//    private static final String STANDARD_MEMBER_NAME = "Joe Bloggs";
//    private static final String PREMIUM_MEMBER_ID = "m10001";
//    private static final String PREMIUM_MEMBER_NAME = "Fred Bloggs";
//
//    private static final String CURRENT_DATE = "01-01-2014";
//    private static final String BOOK_NORMAL_DATE = "29-01-2014";
//    private static final String BOOK_LATE_DATE = "03-02-2014";
//    private static final String VIDEO_NORMAL_DATE = "08-01-2014";
//    private static final String VIDEO_LATE_DATE = "13-01-2014";
//
//    private static LMSModel model;
//
//    private static BufferedReader stdin = new BufferedReader(
//            new InputStreamReader(System.in));
//
//    private static Holding book1, book2, book3;
//    private static Holding video1, video2, video3, video4;
//
//    // use LMS engine (model) to initialise the Library with default
//    // LibraryCollection
//    
//    public static void initialiseEngine() {
//        model = new LMSFacade();
//        model.addCollection(new LibraryCollection(COLLECTION_CODE,
//                COLLECTION_NAME));
//        
//        // IMPORTANT: we need to set artificial date in order to test borrow and
//        // return functions (check the "Assignments" page on Blackboard for further explanation)
//        model.setDate(CURRENT_DATE);
//    }
//
//    public static void initialiseHoldings() {
//        
//        // create sample books
//        book1 = new Book(1000001, "Introduction to Java Programming");
//        book2 = new Book(1000002, "Learning UML");
//        book3 = new Book(1000003,
//                "Design Patterns - Elements of Reusable Object-Oriented Software");
//
//        video1 = new Video(2000001, "Java 1", 4);
//        video2 = new Video(2000002, "Java 2", 4);
//        video3 = new Video(2000003, "UML 1", 6);
//        video4 = new Video(2000004, "UML 2", 6);
//    }
//
//    
//    // --- test1 ----------------------------------------------------------
//    // Check that Library Collection and Members are represented correctly
//    public static boolean test1() throws Exception {
//        initialiseEngine();
//
//        System.out.println("Adding Standard Member ...");
//        model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                STANDARD_MEMBER_NAME));
//        // check member string representation
//        String member = model.getMember().toString();
//        System.out.print("Member string is: " + member);
//        if (member.indexOf(STANDARD_MEMBER_ID + ":" + STANDARD_MEMBER_NAME
//                + ":30:STANDARD") != 0) {
//            System.out.println(" ... not correct");
//            return false;
//        } else {
//            System.out.println(" ... correct");
//        }
//
//        initialiseEngine();
//        System.out.println("Replacing Standard member with Premium member ...");
//        model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                PREMIUM_MEMBER_NAME));
//        // check member string representation
//        member = model.getMember().toString();
//        System.out.print("Member string is: " + member);
//        if (member.indexOf(PREMIUM_MEMBER_ID + ":" + PREMIUM_MEMBER_NAME
//                + ":45:PREMIUM") != 0) {
//            System.out.println(" ... not correct");
//            return false;
//        } else {
//            System.out.println(" ... correct");
//        }
//
//        // check Library Collection string representation (no holdings)
//        String collection = model.getCollection().toString();
//        System.out.print("Library Collection string is: " + collection);
//        if (collection.indexOf(COLLECTION_CODE + ":" + COLLECTION_NAME) != 0) {
//            System.out.println(" ... not correct");
//            return false;
//        } else {
//            System.out.println(" ... correct");
//            return true;
//        }
//    }
//
//    // --- test2 ----------------------------------------------------------
//    // Test holding creation, addition and lookup
//    public static boolean test2() throws Exception {
//
//        String[] expected = {
//                "1000001:Introduction to Java Programming:10:28:BOOK",
//                "1000002:Learning UML:10:28:BOOK",
//                "1000003:Design Patterns - Elements of Reusable Object-Oriented Software:10:28:BOOK",
//                "2000001:Java 1:4:7:VIDEO",
//                "2000003:UML 1:6:7:VIDEO" };
//        String collectionString = "LIB_IT:Information Technology:1000001,1000002,1000003,2000001,2000003";
//        int numOfBooks = 3;
//        int numOfVideos = 2;
//        
//        initialiseEngine();
//        initialiseHoldings();
//
//        try {
//            System.out.println("Adding sample holdings ... ");
//            // adding holdings
//            System.out.print("Adding first book ...");
//            model.addHolding(book1);
//            System.out.print("Adding second book ...");
//            model.addHolding(book2);
//            System.out.print("Adding third book ...");
//            model.addHolding(book3);
//            System.out.print("Adding first video ...");
//            model.addHolding(video1);
//            System.out.println("Adding second video ...");
//            model.addHolding(video3);
//
//            // retrieving and checking holdings
//            System.out.print("Finding first book with getHolding()...");
//            if (failureCheck(model.getHolding(1000001).toString()
//                    .equalsIgnoreCase(expected[0]), true))
//                return false;
//
//            System.out.print("Finding second book ...");
//            if (failureCheck(model.getHolding(1000002).toString()
//                    .equalsIgnoreCase(expected[1]), true))
//                return false;
//
//            System.out.print("Finding first video ...");
//            if (failureCheck(model.getHolding(2000001).toString()
//                    .equalsIgnoreCase(expected[3]), true))
//                return false; 
//
//            System.out.print("Finding second video ...");
//            if (failureCheck(model.getHolding(2000003).toString()
//                    .equalsIgnoreCase(expected[4]), true))
//                return false;
//
//            // find all holdings included in the collection
//            System.out.print("Checking holdings with getAllHoldings()...");
//            if (failureCheck(
//                    checkForHoldings(model.getAllHoldings(), expected), true))
//                return false;
//            
//            // holding counting functions
//            System.out.print("Checking the books counting function ...");
//            if (failureCheck
//                     (model.countBooks() == numOfBooks, true))
//                 return false;            
//            System.out.print("Checking the videos counting function ...");
//            if (failureCheck
//                     (model.countVideos() == numOfVideos, true))
//                 return false;
//            
//           // check Library Collection string representation (with holding present)
//            LibraryCollection collection = model.getCollection();
//            System.out.print("Library Collection string is: " + collection.toString());    
//            if (failureCheck(collection.toString()
//                    .equalsIgnoreCase(collectionString), true))
//                return false;
//            
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test3 ----------------------------------------------------------
//    // Test holding removal
//    public static boolean test3() throws Exception {
//        String[] expected = { "2000001:Java 1:4:7:VIDEO",
//                "2000003:UML 1:6:7:VIDEO" };
//        initialiseEngine();
//        initialiseHoldings();
//
//        try {
//            System.out.println("Adding sample holdings ... ");
//            // adding holdings
//            System.out.print("Adding book ...");
//            model.addHolding(book1);
//            System.out.print("Adding first video ...");
//            model.addHolding(video1);
//            System.out.println("Adding second video ...");
//            model.addHolding(video3);
//
//            System.out.println("Removing book with removeHolding() ...");
//            model.removeHolding(1000001);
//
//            // NOTE: model.getHolding() MUST return NULL when the holding cannot be found
//            System.out
//                    .print("Checking book (expecting NULL) with getHolding() ...");
//            if (failureCheck(model.getHolding(1000001) == null, true))
//                return false;
//
//            System.out.print("Checking holdings with getAllHoldings() ...");
//            if (failureCheck(
//                    checkForHoldings(model.getAllHoldings(), expected), true))
//                return false;
//
//            System.out.print("Removing first video with removeHolding() ... ");
//            model.removeHolding(2000001);
//
//            System.out.println("Removing second video ...");
//            model.removeHolding(2000003);
//
//            // NOTE: model.getAllHoldings() MUST return NULL if there are no holdings in the collection
//            System.out
//                    .print("Checking holdings (expecting NULL) with getAllHoldings()...");
//            if (failureCheck(model.getAllHoldings() == null, true))
//                return false;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test4 ----------------------------------------------------------
//    // Test basic borrow holdings functionality including calculating member's
//    // remaining credit
//    public static boolean test4() throws Exception {
//        String[] standard_expected = {
//                "1000001:Introduction to Java Programming:10:28:BOOK",
//                "2000003:UML 1:6:7:VIDEO" };
//        String[] premium_expected = { "1000002:Learning UML:10:28:BOOK",
//                "2000001:Java 1:4:7:VIDEO" };
//        int standard_credit_expected = 14;
//        int premium_credit_expected = 31;
//
//        initialiseEngine();
//        initialiseHoldings();
//
//        // checking Standard member borrowing
//        System.out.println("Adding Standard Member ...");
//        model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                STANDARD_MEMBER_NAME));
//
//        try {
//            System.out.print("Adding a book ...");
//            model.addHolding(book1);
//            System.out.println("Adding a video ...");
//            model.addHolding(video3);
//
//            System.out.println("Borrowing a book - Standard member...");
//            model.borrowHolding(1000001);
//            System.out.println("Borrowing a video - Standard member...");
//            model.borrowHolding(2000003);
//
//            System.out
//                    .print("Checking borrowed holdings with getBorrowedHoldings() ...");
//            if (failureCheck(checkForHoldings(model.getBorrowedHoldings(),
//                    standard_expected), true))
//                return false;
//
//            System.out
//                    .print("Checking Standard member's credit with calculateRemainingCredit() ...");
//            if (failureCheck(
//                    model.calculateRemainingCredit() == standard_credit_expected, true))
//                return false;
//
//            // checking Premium member borrowing
//            initialiseEngine();
//            System.out.println("Adding Premium Member ...");
//            model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                    PREMIUM_MEMBER_NAME));
//
//            System.out.print("Adding a book ...");
//            model.addHolding(book2);
//            System.out.println("Adding a video ...");
//            model.addHolding(video1);
//
//            System.out.println("Borrowing a book - Premium member...");
//            model.borrowHolding(1000002);
//            System.out.println("Borrowing a video - Premium member...");
//            model.borrowHolding(2000001);
//
//            System.out
//                    .print("Checking borrowed holdings with getBorrowedHoldings() ...");
//            if (failureCheck(checkForHoldings(model.getBorrowedHoldings(),
//                    premium_expected), true))
//                return false;
//
//            System.out
//                    .print("Checking Premium member's credit with calculateRemainingCredit() ...");
//            if (failureCheck(
//                    model.calculateRemainingCredit() == premium_credit_expected, true))
//                return false;
//        } catch (LMSException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test5 ----------------------------------------------------------
//    // Test basic return holdings functionality
//    public static boolean test5() throws Exception {
//
//        initialiseEngine();
//        initialiseHoldings();
//
//        // checking Standard member holding return
//        System.out.println("Adding Standard Member ...");
//        model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                STANDARD_MEMBER_NAME));
//        try {
//            System.out.println("Adding two books ...");
//            model.addHolding(book1);
//            model.addHolding(book2);
//
//            System.out.println("Borrowing two books ...");
//            model.borrowHolding(1000001);
//            model.borrowHolding(1000002);
//
//            // Setting return date - normal (i.e. 28 days - no late penalty)
//            model.setDate(BOOK_NORMAL_DATE);
//
//            System.out.println("Returning two books ...");
//            model.returnHolding(1000001);
//            model.returnHolding(1000002);
//
//            // NOTE: model.getBorrowedHoldings() MUST return NULL if there are
//            // no currently borrowed holdings
//            System.out
//                    .print("Checking borrowed holdings (expecting NULL) with getBorrowedHoldings() ...");
//            if (failureCheck(model.getBorrowedHoldings() == null, true))
//                return false;
//
//            // checking Premium member holding return
//            initialiseEngine();
//            System.out.println("Adding Premium Member ...");
//            model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                    PREMIUM_MEMBER_NAME));
//
//            System.out.println("Adding two videos ...");
//            model.addHolding(video1);
//            model.addHolding(video3);
//
//            System.out.println("Borrowing two videos ...");
//            model.borrowHolding(2000001);
//            model.borrowHolding(2000003);
//
//            // Setting return date - normal (i.e. 7 days - no late penalty)
//            model.setDate(VIDEO_NORMAL_DATE);
//
//            System.out.println("Returning two videos ...");
//            model.returnHolding(2000001);
//            model.returnHolding(2000003);
//
//            // NOTE: model.getBorrowedHoldings() MUST return NULL if there are
//            // no currently borrowed holdings
//            System.out
//                    .print("Checking borrowed holdings (expecting NULL) with getBorrowedHoldings() ...");
//            if (failureCheck(model.getBorrowedHoldings() == null, true))
//                return false;
//
//        } catch (LMSException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test6 ----------------------------------------------------------
//    // Test borrowing history functions
//    public static boolean test6() throws Exception {
//        String[] expected = { "1000001:10", "1000002:10" };
//
//        initialiseEngine();
//        initialiseHoldings();
//
//        System.out.println("Adding Standard Member ...");
//        model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                STANDARD_MEMBER_NAME));
//        try {
//            System.out.println("Adding two books ...");
//            model.addHolding(book1);
//            model.addHolding(book2);
//            System.out.println("Borrowing two books ...");
//            model.borrowHolding(1000001);
//            model.borrowHolding(1000002);
//
//            // NOTE: model.getBorrowingHistory() MUST return null if the history
//            // has no records
//            System.out
//                    .print("Checking empty history (expecting null) with getBorrowingHistory() ...");
//            if (failureCheck(model.getBorrowingHistory() == null, true))
//                return false;
//
//            // Setting return date - normal (i.e. 28 days - no late penalty)
//            model.setDate(BOOK_NORMAL_DATE);
//
//            System.out.println("Returning two books ...");
//            model.returnHolding(1000001);
//            model.returnHolding(1000002);
//
//            // NOTE: model.getHistoryRecord() MUST return null if this history
//            // record does not exist
//            System.out
//                    .print("Checking non-existent history record (expecting null) with getHistoryRecord() ...");
//            if (failureCheck(model.getHistoryRecord(1000003) == null, true))
//                return false;
//
//            System.out
//                    .print("Checking valid history record with getHistoryRecord() ...");
//            if (failureCheck(model.getHistoryRecord(1000001) == null, false))
//                return false;
//
//            System.out
//                    .print("Checking borrowing history with getBorrowingHistory() ...");
//            if (failureCheck(checkForRecords(model.getBorrowingHistory(),
//                    expected), true))
//                return false;
//
//        } catch (LMSException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test7 ----------------------------------------------------------
//    // Test library collection constraints when adding/removing holdings
//    public static boolean test7() throws Exception {
//        initialiseEngine();
//        initialiseHoldings();
//
//        System.out
//                .println("Testing addition of the duplicate holdings - Only one copy of a given holding can be added to the collection ...");
//        System.out.println("Adding a book ...");
//        model.addHolding(book1);
//        System.out
//                .print("Adding a duplicate book - expecting false return ...");
//        if (failureCheck(model.addHolding(book1), false))
//            return false;
//
//        System.out.println("Adding a video ...");
//        model.addHolding(video1);
//        System.out
//                .print("Adding a duplicate video - expecting false return ...");
//        if (failureCheck(model.addHolding(video1), false))
//            return false;
//
//        System.out
//                .println("Testing removal of borrowed holdings - You cannot remove a holding if it is currently on loan ...");
//        System.out.println("Adding a Member ...");
//        model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                STANDARD_MEMBER_NAME));
//        System.out.println("Borrowing a book...");
//        model.borrowHolding(1000001);
//        System.out.println("Borrowing a video...");
//        model.borrowHolding(2000001);
//
//        System.out.print("Removing borrowed book - expecting false return ...");
//        if (failureCheck(model.removeHolding(1000001), false))
//            return false;
//        System.out
//                .print("Removing borrowed video - expecting false return ...");
//        if (failureCheck(model.removeHolding(2000001), false))
//            return false;
//
//        return true;
//    }
//
//    // --- test8 ----------------------------------------------------------
//    // Test advanced borrow holding functionality (multiple borrowing and
//    // insufficient credit) including reseting member's credit
//    public static boolean test8() throws Exception {
//
//        initialiseEngine();
//        initialiseHoldings();
//
//        try {
//
//            System.out.println("Checking multiple borrowing rule");
//
//            System.out.println("Adding a Member ...");
//            model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                    STANDARD_MEMBER_NAME));
//
//            System.out.println("Adding two holdings ...");
//            model.addHolding(book1);
//            model.addHolding(video1);
//
//            System.out.println("Borrowing two holdings ...");
//            model.borrowHolding(1000001);
//            model.borrowHolding(2000001);
//
//            System.out.println("Returning two holdings ...");
//            model.returnHolding(1000001);
//            model.returnHolding(2000001);
//
//            // exception should be thrown due to multiple borrowing
//            try {
//                System.out
//                        .println("Borrowing the same book again - Exception should be thrown - The member can borrow each holding only once...");
//                model.borrowHolding(1000001);
//                return false;
//            } catch (MultipleBorrowingException e) {
//                System.out.println(e.getMessage() + " ... correct");
//            }
//            // exception should be thrown due to multiple borrowing
//            try {
//                System.out
//                        .println("Borrowing the same video again - Exception should be thrown - The member can borrow each holding only once...");
//                model.borrowHolding(2000001);
//                return false;
//            } catch (MultipleBorrowingException e) {
//                System.out.println(e.getMessage() + " ... correct");
//            }
//
//            System.out.println();
//            System.out
//                    .println("Checking insufficient credit rule - STANDARD Member");
//            initialiseEngine();
//            initialiseHoldings();
//            System.out.println("Adding a Member ...");
//            model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                    STANDARD_MEMBER_NAME));
//
//            System.out.println("Adding holdings ...");
//            model.addHolding(book1);
//            model.addHolding(book2);
//            model.addHolding(book3);
//            model.addHolding(video1);
//            model.addHolding(video2);
//            model.addHolding(video3);
//            model.addHolding(video4);
//            System.out.println("Borrowing holdings ...");
//            model.borrowHolding(1000001);
//            model.borrowHolding(1000002);
//            model.borrowHolding(2000001);
//            model.borrowHolding(2000002);
//            // exception should be thrown due to insufficient credit
//            try {
//                System.out
//                        .println("Checking for insufficient credit exception ...");
//                model.borrowHolding(2000003);
//                return false;
//            } catch (InsufficientCreditException e) {
//                System.out.println(e.getMessage() + " ... correct");
//            }
//
//            // reset credit to MAX
//            System.out.println("Resetting credit ...");
//            model.resetMemberCredit();
//
//            // exception should NOT be thrown
//            try {
//                System.out
//                        .print("This holding can now be borrowed because credit was reset ...");
//                model.borrowHolding(2000003);
//                System.out.println(" correct");
//            } catch (InsufficientCreditException e) {
//                System.out.println(e.getMessage() + " incorrect");
//                return false;
//            }
//
//            System.out
//                    .println("Checking insufficient credit rule - PREMIUM Member");
//            initialiseEngine();
//            initialiseHoldings();
//            System.out.println("Adding a Member ...");
//            model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                    PREMIUM_MEMBER_NAME));
//
//            System.out.println("Adding holdings ...");
//            model.addHolding(book1);
//            model.addHolding(book2);
//            model.addHolding(book3);
//            model.addHolding(video1);
//            model.addHolding(video2);
//            model.addHolding(video3);
//            model.addHolding(video4);
//            System.out.println("Borrowing holdings ...");
//            model.borrowHolding(1000001);
//            model.borrowHolding(1000002);
//            model.borrowHolding(1000003);
//            model.borrowHolding(2000001);
//            model.borrowHolding(2000002);
//            model.borrowHolding(2000003);
//            // exception should be thrown due to insufficient credit
//            try {
//                System.out
//                        .println("Checking for insufficient credit exception ...");
//                model.borrowHolding(2000004);
//                return false;
//            } catch (InsufficientCreditException e) {
//                System.out.println(e.getMessage() + " ... correct");
//            }
//
//            // reset credit to MAX
//            System.out.println("Resetting credit ...");
//            model.resetMemberCredit();
//
//            // exception should NOT be thrown
//            try {
//                System.out
//                        .print("This holding can now be borrowed because credit was reset ...");
//                model.borrowHolding(2000004);
//                System.out.println(" correct");
//            } catch (InsufficientCreditException e) {
//                System.out.println(e.getMessage() + " incorrect");
//                return false;
//            }
//
//        } catch (LMSException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test9 ----------------------------------------------------------
//    // Test advanced return holding functionality (overdrawn credit limit)
//    // including calculating late fee and reseting member's credit
//    public static boolean test9() throws Exception {
//        int remaining_credit = 10;
//
//        initialiseEngine();
//        initialiseHoldings();
//
//        // checking Book late fee calculations
//        System.out.println("--- Book late fee calculations check --- ");
//        System.out.print("Adding Member ...");
//        model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                STANDARD_MEMBER_NAME));
//        try {
//            System.out.print("Adding a book ...");
//            model.addHolding(book1);
//            System.out.print("Borrowing a book ...");
//            model.borrowHolding(1000001);
//
//            // Setting late return date (5 days late)
//            model.setDate(BOOK_LATE_DATE);
//
//            System.out.println("Returning late book ...");
//            model.returnHolding(1000001);
//            System.out
//                    .print("Checking member's credit with calculateRemainingCredit() ...");
//            if (failureCheck(
//                    model.calculateRemainingCredit() == remaining_credit, true))
//                return false;
//
//            // checking Video late fee calculations
//            System.out.println("--- Video late fee calculations check --- ");
//            initialiseEngine();
//            initialiseHoldings();
//            System.out.print("Adding Member ...");
//            model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                    PREMIUM_MEMBER_NAME));
//
//            System.out.print("Adding videos ...");
//            model.addHolding(video1);
//            model.addHolding(video3);
//            System.out.print("Borrowing videos ...");
//            model.borrowHolding(2000001);
//            model.borrowHolding(2000003);
//
//            // Setting late return date (5 days late)
//            model.setDate(VIDEO_LATE_DATE);
//
//            System.out.println("Returning late videos ...");
//            model.returnHolding(2000001);
//            model.returnHolding(2000003);
//            System.out
//                    .print("Checking member's credit with calculateRemainingCredit() ...");
//            if (failureCheck(
//                    model.calculateRemainingCredit() == remaining_credit, true))
//                return false;
//
//            System.out.println();
//            // checking Standard member overdrawn credit limit
//            System.out
//                    .println("--- STANDARD Member overdrawn credit limit check --- ");
//            initialiseEngine();
//            initialiseHoldings();
//            System.out.print("Adding Member ...");
//            model.addMember(new StandardMember(STANDARD_MEMBER_ID,
//                    STANDARD_MEMBER_NAME));
//
//            System.out.print("Adding holdings ...");
//            model.addHolding(video1);
//            model.addHolding(video3);
//            System.out.print("Borrowing holdings ...");
//            model.borrowHolding(2000001);
//            model.borrowHolding(2000003);
//
//            // Setting late return date (5 days late)
//            model.setDate(VIDEO_LATE_DATE);
//
//            System.out.println("Returning late holdings ...");
//            model.returnHolding(2000001);
//
//            // exception must be thrown due to overdrawn credit
//            try {
//                System.out
//                        .println("Checking for overdrawn credit exception ...");
//                model.returnHolding(2000003);
//                return false;
//            } catch (OverdrawnCreditException e) {
//                System.out.println(e.getMessage() + " ... correct");
//            }
//
//            // reset credit to MAX
//            System.out.println("Resetting credit ...");
//            model.resetMemberCredit();
//
//            // exception should NOT be thrown
//            try {
//                System.out
//                        .print("This holding can now be returned because credit was reset ...");
//                model.returnHolding(2000003);
//                System.out.println(" correct");
//            } catch (OverdrawnCreditException e) {
//                System.out.println(e.getMessage() + " incorrect");
//                return false;
//            }
//
//            // checking Premium member overdrawn credit limit
//            System.out
//                    .println("--- PREMIUM Member overdrawn credit limit check --- ");
//            initialiseEngine();
//            initialiseHoldings();
//            System.out.print("Adding Member ...");
//            model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                    PREMIUM_MEMBER_NAME));
//
//            System.out.print("Adding holdings ...");
//            model.addHolding(book1);
//            model.addHolding(book2);
//            model.addHolding(book3);
//            System.out.print("Borrowing holdings ...");
//            model.borrowHolding(1000001);
//            model.borrowHolding(1000002);
//            model.borrowHolding(1000003);
//
//            // Setting late return date (5 days late)
//            model.setDate(BOOK_LATE_DATE);
//            
//            System.out.println("Returning late holdings ...");
//            model.returnHolding(1000001);
//
//            // exception should NOT be thrown because Premium members allowed to
//            // have negative credit
//            try {
//                System.out.print("Checking for overdrawn credit exception ...");
//                model.returnHolding(1000002);
//                System.out.println(" correct");
//            } catch (OverdrawnCreditException e) {
//                System.out.println(e.getMessage() + " incorrect");
//                return false;
//            }
//        } catch (LMSException e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//        return true;
//    }
//
//    // --- test10 ----------------------------------------------------------
//    // Test calculating total late fees function
//    public static boolean test10() throws Exception {
//
//        int totalLateFee = 35;
//
//        initialiseEngine();
//        initialiseHoldings();
//        System.out.print("Adding Member ...");
//        model.addMember(new PremiumMember(PREMIUM_MEMBER_ID,
//                PREMIUM_MEMBER_NAME));
//
//        System.out.print("Adding holdings ...");
//        model.addHolding(book1);
//        model.addHolding(video1);
//        model.addHolding(video3);
//        System.out.print("Borrowing holdings ...");
//        model.borrowHolding(1000001);
//        model.borrowHolding(2000001);
//        model.borrowHolding(2000003);
//
//        System.out.println("Returning late holdings ...");
//        // Setting late return date for book (5 days late)
//        model.setDate(BOOK_LATE_DATE);
//        model.returnHolding(1000001);
//        // Setting late return date for videos (5 days late)
//        model.setDate(VIDEO_LATE_DATE);
//        model.returnHolding(2000001);
//        model.returnHolding(2000003);
//
//        System.out
//                .print("Checking total late fees paid with calculateTotalLateFees() ...");
//        if (failureCheck(model.calculateTotalLateFees() == totalLateFee, true))
//            return false;
//
//        return true;
//    }
//
//    // --- test11 ----------------------------------------------------------
//    // TEST ALL
//    public static void test11() {
//
//        int failedTests = 0;
//        int completedFunctionality = 100;
//
//        // TBA (these are not final !!! and will be changed for final marking)
//        int[] testMarks = { 5, 5, 5, 10, 10, 10, 15, 15, 15, 10 };
//
//        System.out.println("These tests failed: ");
//        PrintStream realErr, realOut;
//
//        PrintStream outFile = null;
//        try {
//            outFile = new PrintStream(new FileOutputStream(
//                    new File("tests.txt")));
//        } catch (FileNotFoundException e) {
//            System.out
//                    .println("test harness can't open a file for summarising");
//            return;
//        }
//
//        realErr = System.err;
//        realOut = System.out;
//        System.setErr(outFile);
//        System.setOut(outFile);
//
//        // remember here that System.out and System.err are now mapped to the file.
//        // the real output streams are in realOut and realErr!
//        try {
//            System.out.println("--- Test 1 --------------------------");
//            if (!test1()) {
//                realOut.println("Test 1 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[0];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 1 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[0];
//        }
//        try {
//            System.out.println("--- Test 2 --------------------------");
//            if (!test2()) {
//                realOut.println("Test 2 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[1];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 2 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[1];
//        }
//        try {
//            System.out.println("--- Test 3 --------------------------");
//            if (!test3()) {
//                realOut.println("Test 3 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[2];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 3 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[2];
//        }
//        try {
//            System.out.println("--- Test 4 --------------------------");
//            if (!test4()) {
//                realOut.println("Test 4 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[3];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 4 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[3];
//        }
//        try {
//            System.out.println("--- Test 5 --------------------------");
//            if (!test5()) {
//                realOut.println("Test 5 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[4];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 5 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[4];
//        }
//        try {
//            System.out.println("--- Test 6 --------------------------");
//            if (!test6()) {
//                realOut.println("Test 6 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[5];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 6 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[5];
//        }
//        try {
//            System.out.println("--- Test 7 --------------------------");
//            if (!test7()) {
//                realOut.println("Test 7 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[6];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 7 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[6];
//        }
//        try {
//            System.out.println("--- Test 8 --------------------------");
//            if (!test8()) {
//                realOut.println("Test 8 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[7];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 8 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[7];
//        }
//        try {
//            System.out.println("--- Test 9 --------------------------");
//            if (!test9()) {
//                realOut.println("Test 9 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[8];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 9 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[8];
//        }
//        try {
//            System.out.println("--- Test 10 --------------------------");
//            if (!test10()) {
//                realOut.println("Test 10 **FAIL** (incorrect implementation)");
//                failedTests++;
//                completedFunctionality -= testMarks[9];
//            }
//        } catch (Exception e) {
//            realOut.println("Test 10 **FAIL** (unexpected exception occurred)");
//            failedTests++;
//            completedFunctionality -= testMarks[9];
//        }
//
//        // set the output streams back to normal, and close the file
//        System.setErr(realErr);
//        System.setOut(realOut);
//        outFile.close();
//
//        if (failedTests == 0)
//            System.out.println("NONE -- Fully Working LMS system");
//        else
//            System.out.println("Some tests failed...");
//
//        System.out.println("\nTests failed:  " + failedTests);
//        System.out
//                .println("Tests passed:  " + (testMarks.length - failedTests));
//        System.out
//                .println("\nFunctionality completed (estimation only! based on the preliminary test weights): "
//                        + completedFunctionality + "%");
//    }
//
//    /***************************************************************************
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * // DO NOT TOUCH ANYTHING PAST THIS POINT //
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * //
//     * ************************************************************************
//     * //
//     **************************************************************************/
//
//    public static void main(String[] args) {
//
//        int menuOption = -1;
//        if (args.length > 0) {
//            if (args[0].equalsIgnoreCase("all")) {
//                // test16();
//                System.exit(0);
//            }
//        }
//
//        // repeat processing menu items until quit is entered
//        while (menuOption != 12) {
//            menuOption = mainMenu();
//            try {
//                switch (menuOption) {
//                case 1:
//                    if (test1())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 2:
//                    if (test2())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 3:
//                    if (test3())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 4:
//                    if (test4())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 5:
//                    if (test5())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 6:
//                    if (test6())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 7:
//                    if (test7())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 8:
//                    if (test8())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 9:
//                    if (test9())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 10:
//                    if (test10())
//                        System.out.println("**PASS**");
//                    else
//                        System.out.println("**FAIL**");
//                    break;
//                case 11:
//                    test11();
//
//                }
//            } catch (Exception e) {
//                System.out.println("Unexpected exception! " + e.getMessage());
//                e.printStackTrace();
//                System.out.println("**FAIL**");
//            }
//        }
//    }
//
//    /**
//     * Display the test menu.
//     */
//    
//    public static int mainMenu() {
//        int answer = -1;
//        boolean valid = false;
//
//        // continue prompting until valid data given
//        while (!valid) {
//            // display menu and prompt for entry
//            System.out.println();
//            System.out
//                    .println("===============================================================================");
//            System.out.println("Tests");
//            System.out
//                    .println("===============================================================================");
//            System.out.println();
//            System.out.println("LMS SYSTEM TESTS                      \n");
//            System.out
//                    .println(" 1. initialise library with collection and member     6. borrowing history");
//            System.out
//                    .println(" 2. add/retrieve holding                              7. collection constraints checks (add/remove holding)");
//            System.out
//                    .println(" 3. remove holding                                    8. borrow holding (advanced) - multiple borrowing and insufficient credit");
//            System.out
//                    .println(" 4. borrow holding (simple)                           9. return holding (advanced) - overdrawn credit limit");
//            System.out
//                    .println(" 5. return holding (simple)                           10. calculate total late fees");
//            System.out
//                    .println("-----------------------------------------------------------------------------------");
//            System.out
//                    .println(" 11. TEST ALL                                         12. QUIT");
//
//            System.out.println();
//            System.out.print("Enter an option: ");
//
//            // try to read and validate entered data
//            try {
//                answer = Integer.parseInt(stdin.readLine());
//                System.out.println();
//                if ((answer >= 1) && (answer <= 12))
//                    valid = true;
//            } catch (NumberFormatException e) {
//                System.out.println("Unparsable number entered");
//            } catch (IOException e) {
//                System.out.println("I/O Exception");
//                System.exit(1);
//            }
//            // if data was invalid, print an error
//            if (!valid) {
//                System.out.println();
//                System.out.println("Please enter a valid option (1-12).");
//            }
//        }
//        // return the entered data
//        return answer;
//    }
//
//    private static boolean checkForHoldings(Holding[] holdings,
//            String[] expected) throws Exception {
//        // sort the returned holdings (OPTIONAL)
//        Arrays.sort(expected);
//        // create comparator for sort
//        Comparator<Holding> holdingComparator = new Comparator<Holding>() {
//            public int compare(Holding holding1, Holding holding2) {
//                return Integer.valueOf(holding1.getCode()).compareTo(
//                        holding2.getCode());
//            }
//        };
//        Arrays.sort(holdings, holdingComparator);
//
//        try {
//            // if number of holdings is different from expected, return failure
//            if (holdings.length != expected.length)
//                return false;
//            // make some spots to tick
//            boolean[] tickbox = new boolean[expected.length];
//
//            // for each expected holding string...
//            for (int i = 0; i < expected.length; i++) {
//                // try to find a match
//                boolean found = false;
//                for (int j = 0; (j < holdings.length) && (!found); j++) {
//                    if (expected[i].equalsIgnoreCase(holdings[j].toString()))
//                        found = true;
//                }
//                // store result for this holding string in the tick box
//                tickbox[i] = found;
//            }
//            // check there's a tick in every box
//            for (int i = 0; i < tickbox.length; i++) {
//                if (!tickbox[i])
//                    return false;
//            }
//        } catch (Exception e) {
//            System.out
//                    .println("Parsing error: could not check Holding list successfully!");
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean checkForRecords(HistoryRecord[] records,
//            String[] expected) throws Exception {
//
//        // sort the returned results (OPTIONAL)
//        Arrays.sort(expected);
//        // create comparator for sort
//        Comparator<HistoryRecord> resultComparator = new Comparator<HistoryRecord>() {
//            public int compare(HistoryRecord record1, HistoryRecord record2) {
//                return Integer.valueOf(record1.getHolding().getCode())
//                        .compareTo(record2.getHolding().getCode());
//            }
//        };
//        Arrays.sort(records, resultComparator);
//
//        try {
//            // if number of history records is different from expected, return
//            // failure
//            if (records.length != expected.length)
//                return false;
//            // make some spots to tick
//            boolean[] tickbox = new boolean[expected.length];
//
//            // for each expected history record string...
//            for (int i = 0; i < expected.length; i++) {
//                // try to find a match
//                boolean found = false;
//                for (int j = 0; (j < records.length) && (!found); j++) {
//                    if (expected[i].equalsIgnoreCase(records[j].toString()))
//                        found = true;
//                }
//                // store check outcome for this result string in the tick box
//                tickbox[i] = found;
//            }
//            // check there's a tick in every box
//            for (int i = 0; i < tickbox.length; i++) {
//                if (!tickbox[i])
//                    return false;
//            }
//        } catch (Exception e) {
//            System.out
//                    .println("Parsing error: could not check Borrowing History successfully!");
//            return false;
//        }
//        return true;
//    }
//
//    public static boolean failureCheck(boolean check, boolean expected) {
//        if (check == expected) {
//            System.out.println(" OK");
//            return false;
//        } else {
//            System.out.println(" not OK!");
//            return true;
//        }
//    }
//}
