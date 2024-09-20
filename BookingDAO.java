package train_ticket_bookig;

import java.sql.*;  
import java.util.Date;



public class BookingDAO {
	public int getBookedCount(int train_no,Date date) throws SQLException {
		int count;
		String query="Select count(pname) from passenger where train_no=? and travel_date=?";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1,train_no);
		pst.setDate(2, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		count=rs.getInt(1);
		return count;
	}
	
	public int GetRACBookedCount(int train_no,Date date) throws SQLException{
		int count;
		String query="Select count(birthpreferance) from passenger where train_no=? and birthpreferance=? and travel_date=?";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1,train_no);
		pst.setString(2,"RAC");
		pst.setDate(3, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		count=rs.getInt(1);
		return count;
	}
	
	public int GetWLBookedCount(int train_no,Date date) throws SQLException{
		int count;
		String query="Select count(birthpreferance) from passenger where train_no=? and birthpreferance=? and travel_date=?";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1,train_no);
		pst.setString(2,"WL");
		pst.setDate(3, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		count=rs.getInt(1);
		return count;
	}
	
	public int getBerthBookedCount(int train_no,Date date,String berthpre) throws SQLException {
		int count;
		String query="Select count(pname) from passenger where train_no=? and travel_date=? and birthpreferance=?";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1,train_no);
		pst.setDate(2, sqldate);
		pst.setString(3, berthpre);
		ResultSet rs=pst.executeQuery();
		rs.next();
		count=rs.getInt(1);
		return count;
	}
	
	public int addBooking(Passenger p,int seatno,String allocated) throws SQLException {
		String query="Insert into passenger (pname,age,gender,birthpreferance,seatno,travel_date,train_no) values (?,?,?,?,?,?,?)";
		
		Connection con =DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(p.date.getTime());
		pst.setString(1,p.name);
		pst.setString(3, p.gender);
		pst.setString(4, allocated);
		pst.setInt(5,seatno);
		pst.setDate(6, sqldate);
		pst.setInt(7, p.train_no);
		pst.setInt(2, p.age);
		pst.executeUpdate();
		int id=GetID(p.name);
		return id;
		
	}
	
	public int GetID(String name) throws SQLException {
		String query="select id from passenger where pname='"+name+"'";
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		int id=rs.getInt(1);
		return id;
	}
	
	public int Getseatno(int train_no,Date date) throws SQLException {
		String query="select MAX(seatno) from passenger where train_no=? and travel_date=? and birthpreferance IN(\"l\",\"m\",\"u\",\"RAC\")";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setInt(1,train_no);
		pst.setDate(2, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int seatno=rs.getInt(1);
		return seatno+1;
	}
	
	public void deleteBooking(int id)throws SQLException{
		
		String query="delete from passenger where id="+id;
		Connection con=DBConnection.getconnection();
		Statement st=con.createStatement();
		int rows=st.executeUpdate(query);
		
		System.out.println("Your Booking is Cancelled.....");
		
	}
	
	public ResultSet Getdetails(int id) throws SQLException{
		String query="select * from passenger where id="+id;
		Connection con= DBConnection.getconnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);

		return rs;
	}
	
	public void allocateBerthforRAC(int id) throws SQLException {
		ResultSet rs=Getdetails(id);
		rs.next();
		String berth=rs.getString(5);
		Date travel_date=rs.getDate(6);
		int racid=GetRACDetails(travel_date);
		String query="update passenger set birthpreferance='"+berth+"' where id="+racid;
		Connection con= DBConnection.getconnection();
		Statement st=con.createStatement();
		int rows =st.executeUpdate(query);
		
	}
	
	
	public void allocateRACforWL(int id) throws SQLException {
		ResultSet rs=Getdetails(id);
		rs.next();
		Date travel_date=rs.getDate(6);
		int seatno=rs.getInt(8);
		int WLid=GetWLDetails(travel_date);
		String query="update passenger set birthpreferance='RAC' , seatno="+seatno+" where id="+WLid;
		Connection con= DBConnection.getconnection();
		Statement st=con.createStatement();
		int rows =st.executeUpdate(query);
		
	}
	
	public int GetRACDetails(Date date) throws SQLException {
		String query="select * from passenger where travel_date=? and birthpreferance='RAC'";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setDate(1, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int id=rs.getInt(1);
		return id;
	}
	
	public int GetWLDetails(Date date) throws SQLException {
		String query="select * from passenger where travel_date=? and birthpreferance='WL'";
		Connection con=DBConnection.getconnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());
		pst.setDate(1, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		int id=rs.getInt(1);
		return id;
	}
	
}
