import Model.Entidade.CarRental;
import Model.Entidade.Invoice;
import Model.Entidade.Vehicle;
import Model.Services.BrazilTaxService;
import Model.Services.RentalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Programa {

    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:ss");

        System.out.println("Enter rental date: ");
        System.out.print("Car Model");
        String carModel = sc.nextLine();
        System.out.print("Pickup (dd/MM/yyyy): ");
        Date start = (sdf.parse(sc.nextLine()));
        System.out.print("Return (dd/MM/yyyy): ");
        Date finish = (sdf.parse(sc.nextLine()));

        CarRental cr = new CarRental( start,  finish, new Vehicle(carModel));

        System.out.print("Enter price per hour: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());

        rentalService.processInvoice(cr);

        System.out.println("Invoice: ");
        System.out.println("Basic Payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Tax: " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Total Payment: "  + String.format("%.2f", cr.getInvoice().getTotalPayment()));

        sc.close();
    }
}
