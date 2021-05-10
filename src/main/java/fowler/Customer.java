package fowler;

import java.util.Enumeration;
import java.util.Vector;
import static fowler.PriceCode.*;

class Customer {

    private String name;
    private Vector rentals = new Vector();

    public Customer (String name){
        this.name = name;
    };

    public void addRental(Rental rental) {
        rentals.addElement(rental);
    };

    public String getName (){
        return name;
    };

    public String statement() {
        TotalAmount totalAmount = new TotalAmount();
        RenterPoints renterPoints = new RenterPoints();
        Enumeration enum_rentals = rentals.elements();
        String result = "Rental Record for " + this.getName() + "\n";
        result += "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) enum_rentals.nextElement();
            //determine amounts for each line
            thisAmount = amountFor(each);
            // add frequent renter points
            renterPoints.increase();
            // add bonus for a two day new release rental
            if((each.getMovie().getPriceCode() == NEW_RELEASE) && each.getDaysRented() > 1)
                renterPoints.increase();
            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + "\t" + each.getDaysRented() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount.increase(thisAmount);
        }
        //add footer lines
        result += totalAmount;
        result += renterPoints;
        return result;
    }

    private double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }
}