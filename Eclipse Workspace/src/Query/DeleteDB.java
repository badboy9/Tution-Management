package Query;
import java.sql.*;
import Entity.Student;

public class DeleteDB{
	public Connection con;
	Student sobj;
	PreparedStatement p1,p2,p3;

	public DeleteDB(){
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Tution");
			p1=con.prepareStatement("delete from Student where sid=?");
			p2=con.prepareStatement("delete from Pay where pid=?");
			p3=con.prepareStatement("delete from Pay where sid=?");
		}
		catch(Exception e){}
	}

	public void sDel(Student obj) throws Exception
	{
		p1.setInt(1,obj.sid);
		p1.executeUpdate();
	}
	public void pDel(int pid) throws Exception
	{
		p2.setInt(1,pid);
		p2.executeUpdate();
	}
	public void spDel(Student sobj) throws Exception
	{
		p3.setInt(1,sobj.sid);
		p3.executeUpdate();
	}
	public void cClose() throws Exception{
		con.close();
	}
}