package Entity;

public class Pay {

	public int pid;
	public String dop;
	public int pamt;
	
	public Pay(){
		pid=0;
		dop="";
		pamt=0;
	}
	public void setData(int a,String b,int c){
		pid=a;
		dop=b;
		pamt=c;
	}
}
