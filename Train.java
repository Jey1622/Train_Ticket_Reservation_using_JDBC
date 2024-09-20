package train_ticket_bookig;

import java.sql.SQLException;
import java.util.Date;

public class Train {

	public int GetCapacity(TrainDAO traindao, int train_no, String berthPre) throws SQLException {

		if (berthPre.equals("l") || berthPre.equals("L")) {
			int capacity = traindao.GetLowerCapacity(train_no);
			return capacity;
		}

		else if (berthPre.equals("m") || berthPre.equals("M")) {
			int capacity = traindao.GetMiddleCapacity(train_no);
			return capacity;
		}
		else if (berthPre.equals("u") || berthPre.equals("U")) {
			int capacity = traindao.GetUpperCapacity(train_no);
			return capacity;
		} 
		else if (berthPre.equals("RAC")) {
			int capacity = traindao.GetRACCapacity(train_no);
			return capacity;
		}
		return -1;
	}

	public int GetTotalBerthCapacity(TrainDAO traindao, int train_no) throws SQLException {

		int lowcapacity = traindao.GetLowerCapacity(train_no);
		int midcapacity = traindao.GetMiddleCapacity(train_no);
		int upcapacity = traindao.GetUpperCapacity(train_no);
		int capacity = lowcapacity + midcapacity + upcapacity;
		return capacity;
	}

	public int GetRACWLCapacity(TrainDAO traindao, int train_no) throws SQLException {
		int RACcapacity = traindao.GetRACCapacity(train_no);
		int WLcapacity = traindao.GetWLCapacity(train_no);
		int capacity = RACcapacity + WLcapacity;
		return capacity;
	}
	
	public void AvailableTicket(int train_no,Date date) throws SQLException {
		String[] berths = { "l", "m", "u" ,"RAC"};
		TrainDAO traindao=new TrainDAO();
		BookingDAO booking=new BookingDAO();
		for (int i=0;i<berths.length;i++) {
			int capacity = GetCapacity(traindao, train_no, berths[i]);
			int booked = booking.getBerthBookedCount(train_no, date, berths[i]);
			int available=capacity-booked;
			if(berths[i]=="l") {
				System.out.println("Available Lower berth Ticket :"+available);
			} else if(berths[i]=="m") {
				System.out.println("Available Middle berth Ticket :"+available);
			} else if(berths[i]=="u") {
				System.out.println("Available Upper berth Ticket :"+available);
			} else if(berths[i]=="RAC") {
				System.out.println("Available RAC Ticket :"+available);
			}
		}
	}
	
	public void BookedTicket(int train_no,Date date) throws SQLException {
		String[] berths = { "l", "m", "u" ,"RAC","WL"};
		BookingDAO booking=new BookingDAO();
		for (int i=0;i<berths.length;i++) {
			int booked = booking.getBerthBookedCount(train_no, date, berths[i]);
			if(berths[i]=="l") {
				System.out.println("Available Lower berth Ticket :"+booked);
			} else if(berths[i]=="m") {
				System.out.println("Available Middle berth Ticket :"+booked);
			} else if(berths[i]=="u") {
				System.out.println("Available Upper berth Ticket :"+booked);
			} else if(berths[i]=="RAC") {
				System.out.println("Available RAC Ticket :"+booked);
			} else if(berths[i]=="WL") {
				System.out.println("Available WL Ticket :"+booked);
			}
		}
	}
}
