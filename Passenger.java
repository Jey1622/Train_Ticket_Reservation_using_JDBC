package train_ticket_bookig;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Passenger {
	int id;
	String name;
	int age;
	String gender;
	int train_no;
	String berthPreferance;
	Date date;
	String alloted;
	int seatno;

	public static BookingDAO booking = new BookingDAO();
	public static Train train = new Train();
	public static TrainDAO traindao = new TrainDAO();

	Passenger() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter train no : ");
		train_no = scan.nextInt();

		System.out.println("Enter your Name : ");
		name = scan.next();

		System.out.println("Enter your Age : ");
		age = scan.nextInt();

		System.out.println("Enter your gender(M/F/O) : ");
		gender = scan.next();

		System.out.println("Enter your birth Preferance(L/M/U) : ");
		berthPreferance = scan.next();

		System.out.println("Enter Journey Date(dd-mm-yyyy) : ");
		String dateIP = scan.next();

		SimpleDateFormat dateFormate = new SimpleDateFormat("dd-MM-yyyy");

		try {
			date = dateFormate.parse(dateIP);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		alloted = "";
		seatno = 0;

	}

	public boolean isAvailable() throws SQLException {

		int booked = booking.getBookedCount(train_no, date);
		int totalcapacity = train.GetTotalBerthCapacity(traindao, train_no);
		boolean available = totalcapacity > booked;

		return available;
	}

	public String GetBerthPreferance() throws SQLException {
		String[] berths = { "l", "m", "u" };
		int capacity = train.GetCapacity(traindao, train_no, berthPreferance);
		int berthbooked = booking.getBerthBookedCount(train_no, date, berthPreferance);
		boolean available = capacity > berthbooked;
		if (!available) {
			for (int i = 0; i < berths.length; i++) {
				if (berthPreferance.equals(berths[i]) && !berthPreferance.equals("u")) {
					berthPreferance = berths[++i];
					capacity = train.GetCapacity(traindao, train_no, berthPreferance);
					berthbooked = booking.getBerthBookedCount(train_no, date, berthPreferance);
					available = capacity > berthbooked;
					if (available) {
						return berthPreferance;
					}
					else {
						return berthPreferance=berths[0];
					}
				} else if (berthPreferance.equals("u")) {
					berthPreferance = berths[0];
					capacity = train.GetCapacity(traindao, train_no, berthPreferance);
					berthbooked = booking.getBerthBookedCount(train_no, date, berthPreferance);
					available = capacity > berthbooked;
					if (available) {
						return berthPreferance;
					}
				}

			}

		}
		return berthPreferance;
	}

	public boolean isRacWlAvailable() throws SQLException {
		int booked = booking.GetRACBookedCount(train_no, date);
		int RACWLcapacity = train.GetRACWLCapacity(traindao, train_no);
		boolean available = RACWLcapacity > booked;
		return available;
	}

	public String AllocatedRACWL() throws SQLException {
		int RACcapacity = traindao.GetRACCapacity(train_no);
		int RACbooked = booking.GetRACBookedCount(train_no, date);
		if (RACcapacity > RACbooked) {
			return "RAC";
		}
		return "WL";
	}
	
	
}
