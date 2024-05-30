import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Theatre {
    //Declaring variables, arrays and an arraylist
    static int[] row1 = new int[12]; //Creating an array for row 1 with 12 elements
    static int[] row2 = new int[16]; //Creating an array for row 2 with 16 elements
    static int[] row3 = new int[20]; //Creating an array for row 3 with 20 elements
    static String firstName;
    static String lastName;
    static String email;
    static int rowInput;
    static int seatInput;
    static double price;
    static int[] selectedRow;
    static int seatsPerRow;

    public static ArrayList<Ticket> ticketList = new ArrayList<>();

    public static void main(String[] args) {
        int option;
        initializingRows(row1); //To assign all seats to Unbooked state
        initializingRows(row2);
        initializingRows(row3);

        System.out.println("Welcome to the New Theatre.");
        while (true) {
            System.out.println("-------------------------------------------------\nPlease select an Option:\n1)Buy a ticket\n2)Print seating area\n3)Cancel ticket\n4)List available seats\n5)Save to file\n6)Load from file\n7)Print ticket information and total price\n8)Sort tickets by price\n0)Quit\n-------------------------------------------------\nEnter the option:");
            try {
                Scanner input = new Scanner(System.in);
                option = input.nextInt();
            } catch (Exception exception) {
                System.out.println("Integer required. Please enter an integer.");
                continue;
            }
            switch (option) { //Switch, case is used to select the correct option
                case 1 -> buy_ticket();
                case 2 -> print_seating_area();
                case 3 -> cancel_ticket();
                case 4 -> show_available();
                case 5 -> save();
                case 6 -> load();
                case 7 -> show_tickets_info();
                case 8 -> sort_tickets();
                case 0 -> {
                    System.out.println("Thank you for visiting.\nExiting program.");
                    System.exit(0); //To exit the program
                }
                default -> System.out.println("Option is out of range. Please enter a valid option. ");
            }
        }
    }

    public static void initializingRows(int[] row) {
        /* This method sets all the seats inside a row as unbooked, it assigns them to 0 value */

        for (int element: row) {   //Use of an enhanced for loop to iterate through each element in the row
            row[element] = 0;
        }
    }

    public static void validation_of_firstName() {
        /* This method gets the user's first name and converts the first letter to uppercase */

        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter the first name: ");
            firstName = input.nextLine(); //Gets the input as firstname from the user
            if (firstName.matches("[a-zA-Z]+")) { //Only allows a-z and A-Z characters to be entered
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase(); //Converts the first letter of the name to an Upper case value
            } else {
                System.out.println("Enter a valid name.");
                validation_of_firstName();
            }
        } catch (Exception exception) {
            System.out.println("Enter a valid name.");
            validation_of_firstName();
        }
    }

    public static void validation_of_lastName() {
        /* This method gets the user's last name and converts the first letter to uppercase */

        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter the last name: ");
            lastName = input.nextLine(); //Gets the input as lastname from the user
            if (lastName.matches("[a-zA-Z]+")) { //Only allows a-z and A-Z characters to be entered
                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase(); //Converts the first letter of the name to an Upper case value
            } else {
                System.out.println("Enter a valid name.");
                validation_of_lastName();
            }
        } catch (Exception exception) {
            System.out.println("Enter a valid name.");
            validation_of_lastName();
        }
    }
    public static void validation_of_email() {
        /* This method gets the user's email address, checks whether it is in the correct format - It has to contain "@" and ".com" */

        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter the email address: ");
            email = input.nextLine(); //Gets the input as email from the user

            while (!email.contains("@") || !email.contains(".com")) { //Checks whether entered email address contains "@" and ".com"
                System.out.println("Invalid format of email address. Try again");
                validation_of_email();
            }
        } catch (Exception exception) {
            System.out.println("Invalid format of email address. Try again.");
            validation_of_email();
        }
    }
    public static void validation_of_rows() {
        /* This method gets the row number that the user enters and checks if it is a valid input and in the correct range */

        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter row number (1-3): ");
            rowInput = input.nextInt(); //Gets the row as input from the user

            if (rowInput < 1 || rowInput > 3) {
                System.out.println("Invalid row number.");
                validation_of_rows();
            } else {
                System.out.println();
            }
        } catch (Exception exception) {
            System.out.println("Integer required for row number. Please try again.");
            validation_of_rows();
        }
    }

    public static void validation_of_seats() {
        /* This method gets the seat number that the user enters and checks if it is a valid input and in the correct range */

        Scanner input = new Scanner(System.in);
        try {
            if (rowInput == 1) {
                System.out.println("Enter seat number (1-12): ");
                seatInput = input.nextInt(); //Gets the seat as input from the user

                selectedRow = row1; //Assigns the selected row to row 1
                seatsPerRow = 12;
                if (seatInput < 1 || seatInput > seatsPerRow) {
                    System.out.println("Invalid seat number entered. Please try again.");
                    validation_of_seats();
                } else {
                    System.out.println();
                }
            } else if (rowInput == 2) {
                System.out.println("Enter seat number (1-16): ");
                seatInput = input.nextInt();

                selectedRow = row2; //Assigns the selected row to row 2
                seatsPerRow = 16;
                if (seatInput < 1 || seatInput > seatsPerRow) {
                    System.out.println("Invalid seat number entered. Please try again.");
                    validation_of_seats();
                } else {
                    System.out.println();
                }
            } else if (rowInput == 3) {
                System.out.println("Enter seat number (1-20): ");
                seatInput = input.nextInt();

                selectedRow = row3; //Assigns the selected row to row 3
                seatsPerRow = 20;
                if (seatInput < 1 || seatInput > seatsPerRow) {
                    System.out.println("Invalid seat number entered. Please try again.");
                    validation_of_seats();
                } else {
                    System.out.println();
                }
            }
        } catch (Exception exception) {
            System.out.println("Integer required for seat number. Please try again.");
            validation_of_seats();
        }
    }

    public static void validation_of_ticket_price() {
        /* This method gets the ticket price from the user and checks if it is an acceptable value */

        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Enter the price: ");
            price = input.nextDouble();
            if (price <= 0) {
                System.out.println("Please enter the correct value.");
                validation_of_ticket_price();
            }
        }
        catch (Exception exception) {
            System.out.println("Enter price in the valid format.");
            validation_of_ticket_price();
        }

    }

    public static void buy_ticket() {
        /* This method calls all validating methods to get all user inputs, books the tickets and adds them to the arraylist*/

        //Calling validating methods of row and seat information needed to book a ticket
        validation_of_rows();
        validation_of_seats();

        if (selectedRow[seatInput - 1] == 1) { //Checking if seat has already been booked
            System.out.println("This seat is already occupied.");
            buy_ticket();
        } else {
            selectedRow[seatInput - 1] = 1; //Marking seat as booked by assigning value 1 to it
            System.out.println("Seat is available. Please enter your personal details to continue.");

            //Calling validating methods of other information needed to book a ticket
            validation_of_firstName();
            validation_of_lastName();
            validation_of_email();
            validation_of_ticket_price();

            System.out.println("Seat booked successfully.");
            System.out.println("Seat " + seatInput + " of row " + rowInput + " has been booked.");
        }

        Person person = new Person(firstName, lastName, email); //Creates an object from the Person class
        Ticket ticket = new Ticket(rowInput, seatInput, price, person); //Creates an object from the Ticket class

        ticketList.add(ticket); //Adds the ticket information to the arraylist
    }

    public static void print_row(int[] rowToBePrinted) {
        /* This method is used to format the printing of the seating area
         * It takes the row number as a parameter */

        if (rowToBePrinted == row1) {
            System.out.print("    "); //Spaces added for the formatting of seating area in row 1
        } else if (rowToBePrinted == row2) {
            System.out.print("  "); //Spaces added for the formatting of seating area in row 2
        } else {
            System.out.print("");
        }

        for (int i = 0; i < rowToBePrinted.length; i++) {
            if (rowToBePrinted[i] == 0) {
                System.out.print("O"); //Prints "O" for empty seats
            } else if (rowToBePrinted[i] == 1) {
                System.out.print("X"); //Prints "X" for booked seats
            }

            if ((i + 1) == (rowToBePrinted.length / 2)) {
                System.out.print(" "); //Prints a space in the middle of each row
            }
        }
        System.out.println();
    }

    public static void print_seating_area() {
        /* This method is used to format the printing of the stage and calls the print_row method to print the rows */

        System.out.println("     ***********\n     *  STAGE  *\n     ***********");

        print_row(row1);
        print_row(row2);
        print_row(row3);
    }

    public static void cancel_ticket(){
        /* This method gets the users input of rows and seats and then cancels the ticket and removes it from the arraylist*/

        //Checking if any seat has been already booked in all 3 rows
        if (ticketList.size() != 0) { //If seats have been booked, it is able to cancel a ticket

            validation_of_rows();
            validation_of_seats();

            if (selectedRow[seatInput - 1] == 0) { //If a particular seat has not been booked, it cannot be cancelled
                System.out.println("This seat has not been booked.");
                cancel_ticket();
            } else {
                selectedRow[seatInput - 1] = 0; //If a particular seat has been booked, it will be assigned back to 0 and be cancelled
                System.out.println("Seat cancelled successfully.");
                System.out.println("Seat " + seatInput + " of row " + rowInput + " has been cancelled.");
            }
            for(int i = 0; i < ticketList.size();i++){
                if(ticketList.get(i).getRow() == rowInput && ticketList.get(i).getSeat() == seatInput){
                    ticketList.remove(i); //Cancelled tickets are removed from the arraylist
                    break;
                }
            }
        } else {
            System.out.println("You have not booked any seats yet."); //If no seats have been booked
        }
    }

    public static void show_available(){
        /* This method displays all the seats that are available to be booked (Excludes the seats that are already booked) */

        //Printing seats of row 1
        System.out.print("Seats available in row 1: ");
        for (int i = 0; i < row1.length; i++) {
            if (row1[i] == 0) {
                System.out.print(i+1);
                if (i < row1.length - 1) {
                    System.out.print(", "); // Prints a comma after each element
                } else {
                    System.out.print("."); // Prints a "." after the last element
                }
            }
        }
        System.out.println();

        //Printing seats of row 2
        System.out.print("Seats available in row 2: ");
        for (int i = 0; i < row2.length; i++) {
            if (row2[i] == 0) {
                System.out.print(i+1);
                if (i < row2.length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.print(".");
                }
            }
        }
        System.out.println();

        //Printing seats of row 3
        System.out.print("Seats available in row 3: ");
        for (int i = 0; i < row3.length; i++) {
            if (row3[i] == 0) {
                System.out.print(i+1);
                if (i < row3.length - 1) {
                    System.out.print(", ");
                } else {
                    System.out.print(".");
                }
            }
        }
        System.out.println();
    }

    public static void save(){
        /* This method creates a new text file and writes the theatre row information in it - all the seats that are available to be booked (Excludes the seats that are already booked) */

        try {
            File fileName = new File("Theatre Row Information.txt"); //Creates a file to store row information

            FileWriter myFileWriter = new FileWriter("Theatre Row Information.txt"); //Creates and object of the FileWriter class to be used for file-writing

            myFileWriter.write("Information about Theatre Rows.\n\n");

            //File-writing information of row 1
            myFileWriter.write("Seats available in row 1: ");
            for (int i = 0; i < row1.length; i++) {
                if (row1[i] == 0) {
                    int value = i + 1;
                    myFileWriter.write(String.valueOf(value)); //Converts the integer value to a String
                    if (i < row1.length - 1) {
                        myFileWriter.write(", ");
                    } else {
                        myFileWriter.write(".");
                    }
                }
            }
            myFileWriter.write("\n");

            //File-writing information of row 2
            myFileWriter.write("Seats available in row 2: ");
            for (int i = 0; i < row2.length; i++) {
                if (row2[i] == 0) {
                    int value = i + 1;
                    myFileWriter.write(String.valueOf(value));
                    if (i < row2.length - 1) {
                        myFileWriter.write(", ");
                    } else {
                        myFileWriter.write(".");
                    }
                }
            }
            myFileWriter.write("\n");

            //File-writing information of row 3
            myFileWriter.write("Seats available in row 3: ");
            for (int i = 0; i < row3.length; i++) {
                if (row3[i] == 0) {
                    int value = i + 1;
                    myFileWriter.write(String.valueOf(value));
                    if (i < row3.length - 1) {
                        myFileWriter.write(", ");
                    } else {
                        myFileWriter.write(".");
                    }
                }
            }
            myFileWriter.close(); //Closing the file-writing object
            if (fileName.exists()) {
                System.out.println(fileName + " has been created successfully and row information has been stored in it.");
            }
        } catch (IOException exception){
            System.out.println();
        }
    }

    public static void load(){
        /* This method reads the information stored in the text file and prints it again */

        try{
            File filename = new File("Theatre Row Information.txt");
            Scanner input = new Scanner(filename);
            String fileLine;
            while(input.hasNextLine()){
                fileLine = input.nextLine();
                System.out.println(fileLine);
            }
        } catch (IOException exception){
            System.out.println();
        }
    }

    public static void show_tickets_info() {
        /* This method prints all the tickets booked and personal information related to it, and finally prints the total price of all tickets */

        double totalPrice = 0;

        if (ticketList.size() == 0) { //Checking if any seats have been booked
            System.out.println("Tickets have not been booked yet.");
            System.out.println("Please book a ticket.");
        } else {
            for(Ticket element : ticketList){
                System.out.println("-------------------------------------------------");
                element.print();
                totalPrice += element.price;
            }
            System.out.println();
            System.out.printf("The Total price of the tickets are: £%.2f" , totalPrice);
            System.out.println();
        }
        System.out.println();
    }

    public static void sort_tickets() {
        /* This method sorts all the tickets booked in ascending order of their ticket price, and prints the information related to it */

        Ticket tempTicket;

        if (ticketList.size() == 0) { //Checking if any seats have been booked
            System.out.println("Tickets have not been booked yet.");
            System.out.println("Please book a ticket.");
        } else {
            ArrayList<Ticket> ticketListCopy = new ArrayList<>(ticketList); //Assigning all tickets in ticketList to ticketListCopy
            for (int i = 0; i < ticketListCopy.size() - 1; i++) { //Iterates through each ticket to select a ticket to be compared
                for (int j = 1; j < (ticketListCopy.size() - i); j++) { //Iterates through each ticket to select a ticket with which "i" is compared with
                    if (ticketListCopy.get(j).price < ticketListCopy.get(j - 1).price) { //Compares the ticket prices

                        //Swapping the order of tickets
                        tempTicket = ticketListCopy.get(j - 1);
                        ticketListCopy.set((j - 1), ticketListCopy.get(j));
                        ticketListCopy.set(j, tempTicket);
                    }
                }
            }
            System.out.println("Tickets sorted according to price in ascending order: ");
            for (Ticket iteration : ticketListCopy) {
                System.out.println("-------------------------------------------------");
                iteration.print();
            }
        }
    }
}

