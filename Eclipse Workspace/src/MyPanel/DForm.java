package MyPanel;
import Entity.Student;
import Query.SearchDB;
import Query.UpdateDB;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class DForm extends JDialog implements ActionListener {
	JLabel []lbl;
	JLabel lerr;
	public JLabel tsid,tfnm,tcon,tdt;
	public JLabel taddr;
	public JLabel tstd,tgen,tschool;
	public JButton bmod,bback;
	public Font fobj;
	public Font fobj2;
	public Student sobj;
	UpdateDB updt;
	SearchDB srch;
	JDialog parent;
	HashMap<Integer,String> hgen;
	HashMap<Integer,String> hstd;
	HashMap<Integer,String> hscl;


	public DForm(JDialog prnt,String title,Boolean bm,int id){

		super(prnt,title,bm);
		
		parent=prnt;				
								
		hgen=new HashMap<Integer,String>();
		hstd=new HashMap<Integer,String>();
		hscl=new HashMap<Integer,String>();

		hgen.put(1,"Male");
		hgen.put(2,"Female");

		hstd.put(1,"IX");
		hstd.put(2,"X");

		hscl.put(1,"Mount Carmel");
		hscl.put(2,"Carmel Academy");
		hscl.put(3,"Vidya Niketan");
		hscl.put(4,"St Michael School");
		hscl.put(5,"Other");

		updt=new UpdateDB();
		srch=new SearchDB();
		sobj=new Student();
				
		fobj=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,20);
		
		lerr=new JLabel("*Do not leave fields empty");lerr.setFont(fobj);lerr.setForeground(Color.RED);
		lbl=new JLabel[9];
		for(int i=0;i<9;i++)
			lbl[i]=new JLabel();
		
		lbl[0].setText("STUDENT PROFILE");lbl[0].setFont(new Font("Roman",Font.BOLD,30));
		lbl[1].setText("Student ID");lbl[1].setFont(fobj);
		lbl[2].setText("Name");lbl[2].setFont(fobj);
		lbl[3].setText("Contact No");lbl[3].setFont(fobj);
		lbl[4].setText("Class");lbl[4].setFont(fobj);
		lbl[5].setText("Gender");lbl[5].setFont(fobj);
		lbl[6].setText("School");lbl[6].setFont(fobj);
		lbl[7].setText("Address");lbl[7].setFont(fobj);
		lbl[8].setText("Date");lbl[8].setFont(fobj);

		tsid=new JLabel();tsid.setFont(fobj2);
		tfnm=new JLabel();tfnm.setFont(fobj2);
		tcon=new JLabel();tcon.setFont(fobj2);
		tdt=new JLabel();tdt.setFont(fobj2);

		taddr=new JLabel();taddr.setFont(fobj2);
		
		tstd=new JLabel();tstd.setFont(fobj2);
		tgen=new JLabel();tgen.setFont(fobj2);
		tschool=new JLabel();tschool.setFont(fobj2);

		bmod=new JButton("Modify");
		bback=new JButton("Back");
		bmod.addActionListener(this);bmod.setFont(fobj2);
		bback.addActionListener(this);bback.setFont(fobj2);

		add(lerr);
		for(int i=0;i<9;i++)
			add(lbl[i]);
		add(tsid);add(tfnm);add(tcon);add(tdt);
		add(taddr);
		add(tgen);add(tstd);
		add(tschool);
		add(bmod);add(bback);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try{
					updt.cClose();
					srch.cClose();
				}
				catch(Exception e1){System.out.println(""+e1);}
				parent.setVisible(true);
				dispose();
			}
		});

		setLayout(null);

		lbl[0].setBounds(200,10,400,80);bback.setBounds(620,20,120,60);
		lbl[1].setBounds(30,110,180,40);tsid.setBounds(210,110,100,40);
		lbl[2].setBounds(30,150,180,40);tfnm.setBounds(210,150,570,40);
		lbl[3].setBounds(30,190,180,40);tcon.setBounds(210,190,200,40); 
		lbl[4].setBounds(30,230,180,40);tstd.setBounds(210,220,80,40); 
		lbl[5].setBounds(400,230,80,40);tgen.setBounds(480,220,120,40);
		lbl[6].setBounds(30,270,180,40);tschool.setBounds(210,270,500,40);
		lbl[7].setBounds(30,310,180,40);taddr.setBounds(210,310,400,150);
		lbl[8].setBounds(30,460,180,40);tdt.setBounds(210,460,200,40);
		bmod.setBounds(250,500,300,40);  
		lerr.setBounds(200,540,400,40);

		lerr.setVisible(false);
		setSize(850,1000);

		initForm(id);
		
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==bback){
			try{
				updt.cClose();
				srch.cClose();
			}
			catch(Exception e1){System.out.println(""+e1);}
			setVisible(false);
			parent.setVisible(true);
		}
	}


	public void focusLost(FocusEvent e){}
	public void focusGained(FocusEvent e){}


	public void initForm(int id){
		try{
			ResultSet rs=srch.sidSearch(id);
			while(rs.next()){
				int a=rs.getInt(6),b=rs.getInt(7),c=rs.getInt(8);
				tsid.setText(""+rs.getInt(1));
				tfnm.setText(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
				tcon.setText(rs.getString(5));
				tstd.setText(hstd.get(a));
				tgen.setText(hgen.get(b));
				tschool.setText(hscl.get(c));
				taddr.setText(rs.getString(9));
				tdt.setText(rs.getString(10));			
			}
		}
		catch(Exception e1){System.out.println(""+e1);}
	}
}
