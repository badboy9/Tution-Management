package MyPanel;
import Query.UpdateDB;
import Query.SearchDB;
import Entity.Money;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.io.*;
import java.sql.*;


public class Admin extends JDialog implements ActionListener,FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField tmth,tcurr,tnew;
	JLabel lbl1,lbl2,lmth,lfee;
	JButton bok1,bok2,binc,bdec;
	JTextField tp1,tp2;
	JButton bok;
	JLabel lp1,lp2,lbl,lerr1,lerr2;
	Font fobj1,fobj2;
	JFrame parent;
	SearchDB srch;
	UpdateDB updt;
	Money mobj;

	public Admin(JFrame prnt,String title, Boolean bm){

		super(prnt,title,bm);
		
		parent=prnt;

		mobj=loadMoney();
		srch=new SearchDB();
		updt=new UpdateDB();

		fobj1=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,20);

		lbl=new JLabel("Change Password");lbl.setFont(new Font("Roman",Font.BOLD,30));
		lp1=new JLabel("New Password");lp1.setFont(fobj1);
		lp2=new JLabel("Confirm Password");lp2.setFont(fobj1);
		lerr1=new JLabel("Passoword are not Same");lerr1.setFont(fobj2);lerr1.setVisible(false);lerr1.setForeground(Color.RED);
		lerr2=new JLabel("Check Password length");lerr2.setFont(fobj2);lerr2.setVisible(false);lerr2.setForeground(Color.RED);
		
		tp1=new JPasswordField();tp1.addFocusListener(this);tp1.setFont(fobj2);
		tp2=new JPasswordField();tp2.addFocusListener(this);tp2.setFont(fobj2);

		bok=new JButton("Okay");bok.addActionListener(this);

		fobj1=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,18);
		tmth=new JTextField();tmth.addFocusListener(this);tmth.setText("0");tmth.setEnabled(false);tmth.setFont(fobj2);
		tcurr=new JTextField();tcurr.addFocusListener(this);tcurr.setText(""+mobj.fee);tcurr.setEnabled(false);tcurr.setFont(fobj2);
		tnew=new JTextField();tnew.addFocusListener(this);tnew.setFont(fobj2);

		lbl1=new JLabel("Change Month");lbl1.setFont(new Font("Roman",Font.PLAIN,20));
		lbl2=new JLabel("Change Fees");lbl2.setFont(new Font("Roman",Font.PLAIN,20));
		lmth=new JLabel("Month");lmth.setFont(fobj1);lmth.setFont(fobj2);
		lfee=new JLabel("New Fees");lfee.setFont(fobj1);lfee.setFont(fobj2);

		bok1=new JButton("Apply");bok1.addActionListener(this);bok1.setFont(fobj2);
		bok2=new JButton("Apply");bok2.addActionListener(this);bok2.setFont(fobj2);
		binc=new JButton("Increase");binc.addActionListener(this);binc.setFont(fobj2);
		bdec=new JButton("Decrease");bdec.addActionListener(this);bdec.setFont(fobj2);
		
		add(lbl);add(lp1);add(lp2);
		add(tp1);add(tp2);
		add(bok);add(lerr1);add(lerr2);
		add(lbl1);add(lbl2);
		add(tmth);add(tcurr);add(tnew);
		add(lmth);add(lfee);
		add(bok1);add(bok2);add(binc);add(bdec);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try{
					srch.cClose();
					updt.cClose();
				}
				catch(Exception e1){System.out.println(""+e1);}
				parent.setVisible(true);
				setVisible(false);	
			}
		});
		setLayout(null);

		lbl.setBounds(300,10,400,80);
		lp1.setBounds(120,100,180,40);tp1.setBounds(300,100,180,40);
		lp2.setBounds(120,160,180,40);tp2.setBounds(300,160,180,40);
		bok.setBounds(350,220,100,40);
		lerr1.setBounds(300,260,250,40);
		lerr2.setBounds(300,260,250,40);

		lbl1.setBounds(300,350,200,80);
		lmth.setBounds(170,440,80,40);tmth.setBounds(300,440,100,40);binc.setBounds(425,440,100,40);bdec.setBounds(550,440,100,40);
		bok1.setBounds(350,520,100,40);		
		lbl2.setBounds(300,600,200,80);
		lfee.setBounds(170,690,100,40);tcurr.setBounds(300,690,100,40);tnew.setBounds(425,690,100,40);
		bok2.setBounds(350,770,100,40);

		setSize(800,1000);
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==bok){
			if(tp1.getText().length()<6){
				lerr2.setVisible(true);
			}
			else if(tp1.getText().equals(tp2.getText())){
				commitChange(tp1.getText());
				System.out.println(""+showChanges());
			}
			else{
				lerr1.setVisible(true);
			}
		}
		if(b==bok1){
			int id=0,m=0,pay=0,mth=0;
			try{
				m=Integer.parseInt(tmth.getText());
			}
			catch(Exception e1){System.out.println(""+e1);return;}
			try{
				ResultSet rs=srch.retriveAll();
				while(rs.next())
				{

					id=rs.getInt(1);
					pay=m*mobj.fee+rs.getInt(12);
					mth=m+rs.getInt(11);
					System.out.println("qweqw::"+id+" "+pay+" "+mth);
					updt.pMod(id,pay,mth);
				}
				tmth.setText("0");
			}
			catch(Exception e1){System.out.println(""+e1);return;}
		}
		if(b==bok2){
			try{
				mobj.fee=Integer.parseInt(tnew.getText());
				saveMoney(mobj);
				mobj=loadMoney();
				tcurr.setText(""+mobj.fee);tnew.setText("0");
			}
			catch(Exception e1){System.out.println(""+e1);return;}
		}
		if(b==binc){
			try{
				int i=Integer.parseInt(tmth.getText());
				i++;
				tmth.setText(""+i);
			}
			catch(Exception e1){System.out.println(""+e1);}
		}
		if(b==bdec){
			try{
				int i=Integer.parseInt(tmth.getText());
					i--;
				tmth.setText(""+i);
			}
			catch(Exception e1){System.out.println(""+e1);}

		}
	}


	public void focusGained(FocusEvent e){
		lerr1.setVisible(false);
		lerr2.setVisible(false);
	}

	public void focusLost(FocusEvent e){

	}


	public void commitChange(String s){
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		try{
			fos=new FileOutputStream("pass.dat");
			oos=new ObjectOutputStream(fos);

			oos.writeObject(s);
			fos.close();
			oos.close();
		}
		catch(Exception e){System.out.println(""+e);}
	}


	public String showChanges(){
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


	public void saveMoney(Money obj){
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;	
		try{
			fos=new FileOutputStream("money.dat");
			oos=new ObjectOutputStream(fos);

			oos.writeObject(obj);
			fos.close();
			oos.close();
		}
		catch(Exception e){System.out.println(""+e);}
	}


	public Money loadMoney(){
		Money obj=null;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		try{
			fis=new FileInputStream("money.dat");
			ois=new ObjectInputStream(fis);

			obj=(Money)ois.readObject();

			fis.close();
			ois.close();
		}
		catch(Exception e){System.out.println("Money$$"+e);}
		return obj;
	}
}