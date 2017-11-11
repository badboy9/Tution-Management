package MyPanel;

import Entity.Pay;
import Entity.Student;
import Query.InsertDB;
import Query.SearchDB;
import Query.UpdateDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PForm extends JDialog implements ActionListener, FocusListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6433084871460054889L;
	public JLabel	lpid,lsid,lamt,ldt,lerr,lbl;
	public JTextField tpid,tsid,tamt,tdop;
	public JButton bpay,bback;
	public JFrame parent;
	public Font fobj1,fobj2;
	Student sobj;
	Pay pobj;
	InsertDB ins;
	SearchDB srch;
	UpdateDB updt;

	public PForm(JFrame prnt,String title, Boolean bm){
		
		super(prnt,title,bm);

		fobj1=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,24);
		parent=prnt;
		ins=new InsertDB();
		srch=new SearchDB();
		updt=new UpdateDB();
		sobj=new Student();
		pobj=new Pay();

		lpid=new JLabel("Payment ID");lpid.setFont(fobj1);
		lsid=new JLabel("Student ID");lsid.setFont(fobj1);
		lamt=new JLabel("Amount");lamt.setFont(fobj1);
		ldt=new JLabel("Date");ldt.setFont(fobj1);
		lerr=new JLabel("*Do Not leave Fields Empty");lerr.setFont(fobj1);lerr.setForeground(Color.RED);
		lbl=new JLabel("PAYMENT");lbl.setFont(new Font("Roman",Font.BOLD,30));

		tpid=new JTextField();tpid.addFocusListener(this);tpid.setFont(fobj2);
		tsid=new JTextField();tsid.addFocusListener(this);tsid.setFont(fobj2);
		tamt=new JTextField();tamt.addFocusListener(this);tamt.setFont(fobj2);
		tdop=new JTextField();tdop.addFocusListener(this);tdop.setFont(fobj2);

		bpay=new JButton("Pay");bpay.addActionListener(this);
		bback=new JButton("Back");bback.addActionListener(this);


		add(lpid);add(lsid);add(lamt);add(ldt);add(lerr);add(lbl);
		add(tpid);add(tsid);add(tamt);add(tdop);
		add(lerr);	
		add(bpay);add(bback);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try{
					updt.cClose();
					ins.cClose();
					srch.cClose();
				}
				catch(Exception e1){System.out.println(""+e1);}
				parent.setVisible(true);
				dispose();
			}
		});

		setLayout(null);

		lbl.setBounds(200,10,400,80);bback.setBounds(620,20,120,60);
		lpid.setBounds(60,90,180,80);tpid.setBounds(210,110,100,40);
		lsid.setBounds(60,150,180,80);tsid.setBounds(210,170,100,40);
		lamt.setBounds(60,210,180,80);tamt.setBounds(210,230,100,40);
		ldt.setBounds(60,270,180,80);tdop.setBounds(210,290,200,40);
		lerr.setBounds(260,310,300,80);
		bpay.setBounds(275,380,250,60);

		lerr.setVisible(false);
		setSize(800,500);
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);		
	}


	public void focusGained(FocusEvent e){

	}
	public void focusLost(FocusEvent e){

	}
	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==bback){
			try{
				updt.cClose();
				ins.cClose();
				srch.cClose();
			}
			catch(Exception e1){System.out.println(""+e1);}
			parent.setVisible(true);
			setVisible(false);
		}
		if(b==bpay){
			 if(tpid.getText().trim().isEmpty()){
			 	tpid.requestFocus();lerr.setVisible(true);	
			 }
			 else if(tsid.getText().trim().isEmpty()){
			 	tsid.requestFocus();lerr.setVisible(true);
			 }
			 else if(tamt.getText().trim().isEmpty()){
			 	tamt.requestFocus();lerr.setVisible(true);
			 }
			 else if(tdop.getText().trim().isEmpty()){
			 	tdop.requestFocus();lerr.setVisible(true);
			 }
			 else{
			 	lerr.setVisible(false);
			 	try{
				pobj.pid=Integer.parseInt(tpid.getText());
				}
				catch(Exception e1){
					tpid.setText(null);lerr.setVisible(true);
					tpid.requestFocus();
					return;
				}
			 	try{
				sobj.sid=Integer.parseInt(tsid.getText());
				}
				catch(Exception e1){
					tsid.setText(null);lerr.setVisible(true);
					tsid.requestFocus();
					return;
				}
				try{
					ResultSet rs=srch.sidSearch(sobj.sid);
					if(rs.next()==false){
						System.out.println("Student Not Found");
						return;
					}

			 	}
			 	catch(Exception e1){}
			 	try{
				pobj.pamt=Integer.parseInt(tamt.getText());
				}
				catch(Exception e1){
					tamt.setText(null);lerr.setVisible(true);
					tamt.requestFocus();
					return;
				}
				pobj.dop=tdop.getText();
				System.out.println(""+sobj.sid+pobj.pid+pobj.dop+pobj.pamt);
				
				try{ins.pAdd(sobj,pobj);}
				catch(Exception e1){
					System.out.println(""+e1);
					JOptionPane.showMessageDialog(null,"Alert","Error in Insertion",JOptionPane.ERROR_MESSAGE);
					return;
				}
				payment(sobj,pobj);
				clear();
			 }

		}
	}

	public void payment(Student sobj,Pay pobj){

		try{
			ResultSet rs=srch.sidSearch(sobj.sid);
			rs.next();
			sobj.amt=rs.getInt(12);
			sobj.amt-=pobj.pamt;
		}
		catch(Exception e1){
			System.out.println(""+e1);
			JOptionPane.showMessageDialog(null,"Alert","Error in Insertion",JOptionPane.ERROR_MESSAGE);
		}
		try{
			updt.payment(sobj);
		}
		catch(Exception e1){
			System.out.println(""+e1);
			JOptionPane.showMessageDialog(null,"Alert","Error in Insertion3",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void clear(){
		tpid.setText(null);
		tsid.setText(null);
		tdop.setText(null);
		tamt.setText(null);
	}

}
