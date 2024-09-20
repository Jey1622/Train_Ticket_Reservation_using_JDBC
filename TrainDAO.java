package train_ticket_bookig;

import java.sql.*;
import java.util.Date;

public class TrainDAO {
	public void DisplayTrainInfo() throws SQLException {
		String query="select * from train";
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		while(rs.next()) {
			System.out.println("Train no : "+rs.getInt(1));
			System.out.println("Available lower birth : "+rs.getInt(2));
			System.out.println("Available middle birth : "+rs.getInt(3));
			System.out.println("Available upper birth : "+rs.getInt(4));
			System.out.println("Train origne : "+rs.getString(5));
			System.out.println("Train Distination : "+rs.getString(6));
			System.out.println("Train Duration Time : "+rs.getString(7));
			System.out.println();
		}
		System.out.println("--------------------------------------");
	}
	
	
	
	public int GetLowerCapacity(int train_no) throws SQLException {
		String query="Select lowercap from train where tno="+train_no;
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int capacity=rs.getInt(1);
		return capacity;
	}
	
	public int GetMiddleCapacity(int train_no) throws SQLException {
		String query="Select middelcap from train where tno="+train_no;
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int capacity=rs.getInt(1);
		return capacity;
	}
	
	public int GetUpperCapacity(int train_no) throws SQLException {
		String query="Select uppercap from train where tno="+train_no;
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int capacity=rs.getInt(1);
		return capacity;
	}
	
	public int GetRACCapacity(int train_no) throws SQLException {
		String query="Select RAC from train where tno="+train_no;
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int capacity=rs.getInt(1);
		return capacity;
	}
	
	public int GetWLCapacity(int train_no) throws SQLException {
		String query="Select WL from train where tno="+train_no;
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int capacity=rs.getInt(1);
		return capacity;
	}
	
	
	
	
}
