package Query;
import java.sql.*;
import Entity.Student;

public class UpdateDB
{
	public Connection con;
	Student sobj;
	PreparedStatement p1,p2,p3,p4;
	public UpdateDB()
	{
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Tution");
			p1=con.prepareStatement("update Student set fname=?, pname=?, lname=?, cont=?, std=?, gen=?, school=?, addr=?, doa=? where sid=?");
			p2=con.prepareStatement("update Student set mth=?, amt=? where sid=?");
			p3=con.prepareStatement("update Student set amt=? where sid=?");
			p4=con.prepareStatement("update Student set mth=?");
		}
		catch(Exception e){}
	}
	public void sMod(Student obj) throws Exception
	{
		System.out.println("Modifing 1");System.out.println(""+obj.sid);
		p1.setString(1,obj.fname);System.out.println(""+obj.fname);
		p1.setString(2,obj.pname);System.out.println(""+obj.pname);
		p1.setString(3,obj.lname);System.out.println(""+obj.lname);
		p1.setString(4,obj.cont);System.out.println(""+obj.cont);
		p1.setInt(5,obj.std);System.out.println(""+obj.std);
		p1.setInt(6,obj.gen);System.out.println(""+obj.gen);
		p1.setInt(7,obj.school);System.out.println(""+obj.school);
		p1.setString(8,obj.addr);System.out.println(""+obj.addr);
		p1.setString(9,obj.doa);System.out.println(""+obj.doa);
		p1.setInt(10,obj.sid);
		System.out.println("Modifing 2");
		p1.executeUpdate();
		System.out.println("Modifing 3");
	}
	public void pMod(int sid,int pay,int mth) throws Exception
	{
		System.out.println(""+sid+" "+pay+" "+mth);
		p2.setInt(1,mth);
		p2.setInt(2,pay);
		p2.setInt(3,sid);
		p2.executeUpdate();
	}
	public void payment(Student sobj) throws Exception
	{
		p3.setInt(1,sobj.amt);
		p3.setInt(2,sobj.sid);
		p3.executeUpdate();
	}
	public void cClose() throws Exception{
		con.close();
	}
}