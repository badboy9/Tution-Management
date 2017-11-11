import MyPanel.RForm;
import MyPanel.PForm;
import MyPanel.SSearch;
import MyPanel.ESearch;
import MyPanel.VSearch;
import MyPanel.PSearch;
import MyPanel.Admin;
import MyPanel.PDelete;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;

public class HomeScreen extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btn[];
	JLabel lbl;
	JPanel bg;
	Font fobj;

	public HomeScreen(){

		super();
		
		while(true)
		{
			if(login()==true)
				break;
		}

		bg=new JPanel();
//		bg.setBackground(new Color(35,35,35));
		bg.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red));
		
		fobj=new Font("Roman",Font.BOLD,20);
		lbl=new JLabel("HOME");lbl.setFont(new Font("Roman",Font.PLAIN,50));
		btn=new JButton[9];
	
 		//add(bg);
		for(int i=0;i<9;i++){
			btn[i]=new JButton(new ImageIcon("icon\\"+(i+1)+".ico"));
			btn[i].setFont(fobj);
			btn[i].addActionListener(this);
			add(btn[i]);
		}
		add(lbl);
		
		setLayout(null);
		bg.setLayout(null);

		bg.setBounds(5,120,880,1000);
		lbl.setBounds(420,10,160,180);
		btn[0].setBounds(160,220,200,180);
		btn[1].setBounds(400,220,200,180);
		btn[2].setBounds(640,220,200,180);
		btn[3].setBounds(160,430,200,180);
		btn[4].setBounds(400,430,200,180);
		btn[5].setBounds(640,430,200,180);
		btn[6].setBounds(160,640,200,180);
		btn[7].setBounds(400,640,200,180);
		btn[8].setBounds(640,640,200,180);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		setSize(1000,1000);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e){	
		JButton b=(JButton)e.getSource();
		if(b==btn[0]){
			@SuppressWarnings("unused")
			RForm obj1=new RForm(this,"Registration",true);
		}
		if(b==btn[1]){
			@SuppressWarnings("unused")
			VSearch obj2=new VSearch(this,"View",true);
		}
		if(b==btn[2]){
			@SuppressWarnings("unused")
			ESearch obj3=new ESearch(this,"Edit",true);
		}
		if(b==btn[3]){
			@SuppressWarnings("unused")
			SSearch obj4=new SSearch(this,"Search",true);
		}
		if(b==btn[4]){
			@SuppressWarnings("unused")
			PForm obj5=new PForm(this,"Payment",true);
		}
		if(b==btn[5]){
			@SuppressWarnings("unused")
			PSearch obj6=new PSearch(this,"View Payment",true);
		}
		if(b==btn[6]){
			@SuppressWarnings("unused")
			PDelete obj7=new PDelete(this,"Delete Payment",true);
		}
		if(b==btn[8]){
			while(login()==false);
			@SuppressWarnings("unused")
			Admin obj8=new Admin(this,"Adminstrator",true);
		}
	}


	public Boolean login(){
		JTextField username = new JTextField();
		JTextField password = new JPasswordField();
		Object[] message = {"Username:", username,"Password:", password};
		String s=ReadPassword();
		int option = JOptionPane.showConfirmDialog(null, message, "login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
    		if (username.getText().equals("admin") && password.getText().equals(s)) {
        		System.out.println("login successful");
    			return true;
    		}
    		else {
        		System.out.println("login failed");
        		return false;
    		}
		}
		else {
	    	System.out.println("login canceled");
			System.exit(0);
			return false;
		}
	}


	public String ReadPassword(){
		String s=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try{
			fis=new FileInputStream("pass.dat");
			ois=new ObjectInputStream(fis);
			s=(String)ois.readObject();
			ois.close();
			fis.close();
		}
		catch(Exception e){System.out.println(""+e);}
		return s;
	}


	public static void main(String []args){
		HomeScreen obj=new HomeScreen();
	}
}



