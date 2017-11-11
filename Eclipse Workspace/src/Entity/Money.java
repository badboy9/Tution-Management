package Entity;
import java.io.*;


public class Money implements Serializable {
	
	public int fee;
	
	public Money()
	{
		fee=0;
	}


	public void setFee(int n){
		fee=n;
	}

	public void saveFee(){
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;	
		try{
			fos=new FileOutputStream("money.dat");
			oos=new ObjectOutputStream(fos);

			oos.writeObject(this);
			fos.close();
			oos.close();
		}
		catch(Exception e){System.out.println(""+e);}
	}


	public void openFee(){
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try{
			fis=new FileInputStream("money.dat");
			ois=new ObjectInputStream(fis);

			Money obj=(Money)ois.readObject();

			fis.close();
			ois.close();
			this.fee=obj.fee;
		}
		catch(Exception e){System.out.println("Money$$"+e);}
	}

	
}