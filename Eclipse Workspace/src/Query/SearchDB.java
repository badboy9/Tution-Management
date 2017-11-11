package Query;
import java.sql.*;

public class SearchDB{
	public Connection con;
	PreparedStatement p0,p1,p2,p3,p4,p5,p6,p7,p8,p9;
	public SearchDB(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Tution");
			p0=con.prepareStatement("select * from Student");
			p1=con.prepareStatement("select * from Student where sid=?");
			p2=con.prepareStatement("select * from Student where fname=?");
			p3=con.prepareStatement("select * from Student where std=?");
			p4=con.prepareStatement("select * from Student where school=?");
			p5=con.prepareStatement("select * from Student where amt>0");
			p6=con.prepareStatement("select * from Student where amt<=0");
			p7=con.prepareStatement("select * from Pay where pid=?");
			p8=con.prepareStatement("select * from Pay where sid=?");
			p9=con.prepareStatement("select fname,pname,lname from Student where sid=?");	
		}
		catch(Exception e){}
	}
	public ResultSet retriveAll() throws Exception{
		return p0.executeQuery();
	}
	public ResultSet sidSearch(int a) throws Exception{
		p1.setInt(1,a);
		return p1.executeQuery();
	}
	public ResultSet fnmSearch(String a) throws Exception{
		p2.setString(1,a);
		return p2.executeQuery();
	}
	public ResultSet clsSearch(int a) throws Exception{
		p3.setInt(1,a);
		return p3.executeQuery();
	}
	public ResultSet sclSearch(int a) throws Exception{
		p4.setInt(1,a);
		return p4.executeQuery();
	}
	public ResultSet ufSearch() throws Exception{
		return p5.executeQuery();
	}
	public ResultSet pfSearch() throws Exception{
		return p6.executeQuery();
	}
	public ResultSet pdSearch(int a) throws Exception{
		p7.setInt(1,a);
		return p7.executeQuery();
	}
	public ResultSet sdSearch(int a) throws Exception{
		p8.setInt(1,a);
		return p8.executeQuery();
	}
	public String nmSearch(int a) throws Exception{
		p9.setInt(1,a);
		ResultSet rs=p9.executeQuery();
		rs.next();
		String str=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3);
		return str;
	} 

	public void cClose() throws Exception{
		con.close();
	}
}