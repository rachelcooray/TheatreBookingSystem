public class Ticket { //Creation of a class named Ticket

    private int row;
    private int seat;
    double price;
    Person person; // Creation of an object named person of the Person class

    public Ticket(int row, int seat, double price, Person person) { //Overloaded Constructor of the Ticket class
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public int getSeat() {
        return seat;
    }
    public void setSeat(int seat) {
        this.seat = seat;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public void print() {
        /* This method is used to print all the information of the user */

        System.out.println("Person first name: " + person.getFirstName());
        System.out.println("Person last name: " + person.getLastName());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Row: " + row);
        System.out.println("Seat: " + seat);
        System.out.printf("Price: £%.2f", price);
        System.out.println();
    }
}
