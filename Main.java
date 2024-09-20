package train_ticket_bookig;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

	public static void main(String[] args) throws SQLException {

		TrainDAO traindao = new TrainDAO();
		Train train=new Train();

		try {
			traindao.DisplayTrainInfo();
			boolean loop = true;
			Scanner scan = new Scanner(System.in);
			while (loop) {
				System.out.println("1.Booking");
				System.out.println("2.Cancelling");
				System.out.println("3.Available ticket");
				System.out.println("4.Booked Ticket");
				System.out.println("5.Exit");
				System.out.println();

				System.out.println("Enter your Choise : ");
				int userip = scan.nextInt();

				switch (userip) {

				case 1:
					Passenger passenger = new Passenger();
					BookingDAO bookingdao = new BookingDAO();
					boolean available = passenger.isAvailable();
					if (available) {
						String allocated = passenger.GetBerthPreferance();
						int seatno = bookingdao.Getseatno(passenger.train_no, passenger.date);
						int id = bookingdao.addBooking(passenger, seatno, allocated);

						System.out.println("Your Booking is conformed....\n Your id is " + id
								+ "\n your seat number is " + seatno + "\n Allocaten berth is " + allocated);

					} else if (passenger.isRacWlAvailable()) {

						String allocated = passenger.AllocatedRACWL();
						int seatno = bookingdao.Getseatno(passenger.train_no, passenger.date);
						if (allocated == "WL")
							seatno = 0;
						int id = bookingdao.addBooking(passenger, seatno, allocated);
						if (allocated == "WL")
							System.out.println("\n Your id is " + id + "\n Allocated " + allocated);
						else {
							System.out.println("Your Booking is conformed....\n Your id is " + id
									+ "\n Your seat number is " + seatno + "\n Allocated " + allocated);
						}
					} else {
						System.out.println("Soory, train is Full. try another train or date...");
					}
					break;
				case 2:
					BookingDAO bookingdao2 = new BookingDAO();
					System.out.println("Enter your booking id : ");
					int id = scan.nextInt();
					bookingdao2.allocateRACforWL(id);
					bookingdao2.allocateBerthforRAC(id);
					bookingdao2.deleteBooking(id);

					break;
				case 3:
					
					System.out.println("Enter a train no : ");
					int train_no=scan.nextInt();
					System.out.println("Enter a travel date : ");
					String dateIP = scan.next();

					SimpleDateFormat dateFormate = new SimpleDateFormat("dd-MM-yyyy");

					try {
						Date date = dateFormate.parse(dateIP);
						train.AvailableTicket(train_no,date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					break;
				case 4:
					
					System.out.println("Enter a train no : ");
					int train_no2=scan.nextInt();
					System.out.println("Enter a travel date : ");
					String dateIP2 = scan.next();

					SimpleDateFormat dateFormate2= new SimpleDateFormat("dd-MM-yyyy");

					try {
						Date date = dateFormate2.parse(dateIP2);
						train.BookedTicket(train_no2,date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case 5:
					loop = false;
					break;
				default:
					break;
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
