package Query;
import java.sql.*;
import Entity.Student;
import Entity.Pay;

public class InsertDB{
	public  Connection con;
	Student sobj;
	PreparedStatement ptas,ptap;
	public InsertDB(){
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Tution");
			ptas=con.prepareStatement("Insert into Student values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ptap=con.prepareStatement("Insert into Pay values(?,?,?,?)");
		}
		catch(Exception e){System.out.println(""+e);}
	}
	public void sAdd(Student obj) throws Exception{

		ptas.setInt(1,obj.sid);
		ptas.setString(2,obj.fname);
		ptas.setString(3,obj.pname);
		ptas.setString(4,obj.lname);
		ptas.setString(5,obj.cont);
		ptas.setInt(6,obj.std);
		ptas.setInt(7,obj.gen);
		ptas.setInt(8,obj.school);
		ptas.setString(9,obj.addr);
		ptas.setString(10,obj.doa);
		ptas.setInt(11,obj.mth);
		ptas.setInt(12,obj.amt);
		ptas.executeUpdate();
	}
	public void pAdd(Student sobj,Pay pobj) throws Exception{

		System.out.println(""+sobj.sid+pobj.pid+pobj.dop+pobj.pamt);
		ptap.setInt(1,pobj.pid);
		ptap.setInt(2,sobj.sid);
		ptap.setString(3,pobj.dop);
		ptap.setInt(4,pobj.pamt);
		ptap.executeUpdate();
	}
	public void cClose() throws Exception{
		con.close();
	}
}