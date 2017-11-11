package MyPanel;
import Entity.Student;
import Query.SearchDB;
import Query.UpdateDB;
import Query.DeleteDB;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MForm extends JDialog implements ActionListener, ItemListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9143620336420380642L;
	JLabel []lbl;
	JLabel lerr;
	public JTextField tsid,tfnm,tpnm,tlnm,tcon,tdt;
	public JTextArea taddr;
	public Checkbox []ccs;
	public Checkbox []cgen;
	public CheckboxGroup cgcs,cggen;
	public Choice school;
	public JButton bmod,bback,bdel;
	public Font fobj;
	public Font fobj2;
	public Student sobj;
	UpdateDB updt;
	SearchDB srch;
	DeleteDB del;
	JDialog parent;
	
	public MForm(JDialog prnt,String title,Boolean bm,int id){

		super(prnt,title,bm);
		
		parent=prnt;	
					
		updt=new UpdateDB();
		srch=new SearchDB();
		sobj=new Student();
		del=new DeleteDB();

		fobj=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,20);
		
		lerr=new JLabel("*Do not leave fields empty");lerr.setFont(new Font("Roman",Font.BOLD,30));lerr.setForeground(Color.RED);
		lbl=new JLabel[12];
		for(int i=0;i<12;i++)
			lbl[i]=new JLabel();
		lbl[0].setText("MODIFICATION FORM");lbl[0].setFont(new Font("Roman",Font.BOLD,30));
		lbl[1].setText("Student ID");lbl[1].setFont(fobj);
		lbl[2].setText("Name");lbl[2].setFont(fobj);
		lbl[3].setText("First");lbl[3].setFont(fobj);
		lbl[4].setText("Middle");lbl[4].setFont(fobj);
		lbl[5].setText("Last");lbl[5].setFont(fobj);
		lbl[6].setText("Contact No");lbl[6].setFont(fobj);
		lbl[7].setText("Class");lbl[7].setFont(fobj);
		lbl[8].setText("Gender");lbl[8].setFont(fobj);
		lbl[9].setText("School");lbl[9].setFont(fobj);
		lbl[10].setText("Address");lbl[10].setFont(fobj);
		lbl[11].setText("Date");lbl[11].setFont(fobj);

		tsid=new JTextField();tsid.setFont(fobj2);tsid.addFocusListener(this);
		tfnm=new JTextField();tfnm.setFont(fobj2);tfnm.addFocusListener(this); 
		tpnm=new JTextField();tpnm.setFont(fobj2);tpnm.addFocusListener(this);
		tlnm=new JTextField();tlnm.setFont(fobj2);tlnm.addFocusListener(this);
		tcon=new JTextField();tcon.setFont(fobj2);tcon.addFocusListener(this);
		tdt=new JTextField();tdt.setFont(fobj2);tdt.addFocusListener(this);

		taddr=new JTextArea();taddr.setFont(fobj2);

		cgcs=new CheckboxGroup();
		cggen=new CheckboxGroup();

		ccs=new Checkbox[2];
		ccs[0]=new Checkbox();
		ccs[1]=new Checkbox();
		ccs[0].setLabel("IX");
		ccs[0].setFont(new Font("Roman",Font.PLAIN,18));
		ccs[0].setState(false);
		ccs[0].setCheckboxGroup(cgcs);
		ccs[0].addItemListener(this);
		ccs[1].setLabel("X");
		ccs[1].setFont(new Font("Roman",Font.PLAIN,18));
		ccs[1].setState(false);
		ccs[1].setCheckboxGroup(cgcs);
		ccs[1].addItemListener(this);
		
		cgen=new Checkbox[2];
		cgen[0]=new Checkbox();
		cgen[1]=new Checkbox();
		cgen[0].setLabel("Male");
		cgen[0].setFont(new Font("Roman",Font.PLAIN,18));
		cgen[0].setCheckboxGroup(cggen);
		cgen[0].setState(false);
		cgen[0].addItemListener(this);
		cgen[1].setLabel("Female");
		cgen[1].setFont(new Font("Roman",Font.PLAIN,18));
		cgen[1].setCheckboxGroup(cggen);
		cgen[1].setState(false);
		cgen[1].addItemListener(this);

		school=new Choice();school.setFont(fobj2);
		school.addItemListener(this);
		school.add("Select school");
		school.add("1. Mount Carmel");
		school.add("2. Carmel Academy");
		school.add("3. Vidya Niketan");
		school.add("4. St. Michael School");
		school.add("Other");
		
		bmod=new JButton("Modify");
		bback=new JButton("Back");
		bdel=new JButton("Delete");
		bmod.addActionListener(this);bmod.setFont(fobj2);
		bback.addActionListener(this);bback.setFont(fobj2);
		bdel.addActionListener(this);bdel.setFont(fobj2);

		add(lerr);
		for(int i=0;i<12;i++)
			add(lbl[i]);
		add(tsid);add(tfnm);add(tpnm);add(tlnm);add(tcon);add(tdt);
		add(taddr);
		add(ccs[0]);add(ccs[1]);add(cgen[0]);add(cgen[1]);
		add(school);
		add(bmod);add(bback);add(bdel);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try{
					updt.cClose();
					del.cClose();
					srch.cClose();
				}
				catch(Exception e1){System.out.println(""+e1);}
				parent.setVisible(true);
				dispose();
			}
		});

		setLayout(null);

		lbl[0].setBounds(200,10,400,80);bback.setBounds(620,20,120,60);
		lbl[1].setBounds(30,110,180,60);tsid.setBounds(210,120,100,40);
		lbl[3].setBounds(210,180,190,40);lbl[4].setBounds(410,180,190,40);lbl[5].setBounds(610,180,190,40);
		lbl[2].setBounds(30,210,180,60);tfnm.setBounds(210,220,190,40);tpnm.setBounds(410,220,190,40);tlnm.setBounds(610,220,190,40);
		lbl[6].setBounds(30,290,180,60);tcon.setBounds(210,300,200,40); 
		lbl[7].setBounds(30,370,180,60);ccs[0].setBounds(210,380,80,40);ccs[1].setBounds(290,380,80,40); 
		lbl[8].setBounds(400,370,80,60);cgen[0].setBounds(480,380,120,40);cgen[1].setBounds(600,380,120,40);
		lbl[9].setBounds(30,450,180,60);school.setBounds(210,460,500,60);
		lbl[10].setBounds(30,530,180,60);taddr.setBounds(210,540,400,160);
		lbl[11].setBounds(30,710,180,60);tdt.setBounds(210,720,200,40);
		bmod.setBounds(150,800,250,60);bdel.setBounds(450,800,250,60);
		lerr.setBounds(200,860,400,60);

		lerr.setVisible(false);
		setSize(850,1000);
		try{
			ResultSet rs=srch.sidSearch(id);
			initForm(rs);}
		catch(Exception e1){System.out.println(""+e1);}
		
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}


	public void itemStateChanged(ItemEvent e) {}

	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==bmod)
		{
			if(tfnm.getText().trim().isEmpty()){
				tfnm.requestFocus();lerr.setVisible(true);
			}
			else if(tpnm.getText().trim().isEmpty()){
				tpnm.requestFocus();lerr.setVisible(true);
			}
			else if(tlnm.getText().trim().isEmpty()){
				tlnm.requestFocus();lerr.setVisible(true);
			}
			else if(tcon.getText().trim().isEmpty()){
				tcon.requestFocus();lerr.setVisible(true);
			}
			else if(tdt.getText().trim().isEmpty()){
				tdt.requestFocus();lerr.setVisible(true);
			}
			else if(tsid.getText().trim().isEmpty()){
				tsid.requestFocus();lerr.setVisible(true);
			}
			else if(taddr.getText().trim().isEmpty()){
				taddr.requestFocus();lerr.setVisible(true);
			}
			else{
																			//now update data of DB
				lerr.setVisible(false);
				try{
					System.out.println(""+tsid.getText());
					sobj.sid=Integer.parseInt(tsid.getText());
				}
				catch(Exception e1){
					tsid.setText(null);lerr.setVisible(true);
					tsid.requestFocus();
					return;
				}
				try{
					if(school.getSelectedIndex()==0)
						return;
					sobj.school=school.getSelectedIndex();
				}
				catch(Exception e1){}
				sobj.fname=tfnm.getText();
				sobj.pname=tpnm.getText();
				sobj.lname=tlnm.getText();
				sobj.cont=tcon.getText();
				sobj.addr=taddr.getText();
				sobj.doa=tdt.getText();

				if(cgcs.getSelectedCheckbox()==ccs[0])
					sobj.std=1;
				else
					sobj.std=2;
				if(cggen.getSelectedCheckbox()==cgen[0])
					sobj.gen=1;
				else
					sobj.gen=2;
				
				try{updt.sMod(sobj);}
				catch(Exception e1){System.out.println("Problem in Updating::"+e1);return;}
				clear();
			}
		}

		if(b==bdel)
		{
			if(tsid.getText().trim().isEmpty()){
				tsid.requestFocus();lerr.setVisible(true);
			}
			else{
																			//now update data of DB
				lerr.setVisible(false);
				try{
					System.out.println(""+tsid.getText());
					sobj.sid=Integer.parseInt(tsid.getText());
				}
				catch(Exception e1){
					tsid.setText(null);lerr.setVisible(true);
					tsid.requestFocus();
					return;
				}
				
				try{
					del.spDel(sobj);
					del.sDel(sobj);
				}
				catch(Exception e1){System.out.println("Problem in Deleting::"+e1);return;}
				clear();
			}
		}
		if(b==bback){
			clear();
			try{
				updt.cClose();
				del.cClose();
				srch.cClose();
			}
			catch(Exception e1){System.out.println(""+e1);}
			setVisible(false);
			parent.setVisible(true);
		}
	}


	public void focusLost(FocusEvent e){}
	public void focusGained(FocusEvent e){}


	public void initForm(ResultSet rs)throws Exception{
		while(rs.next()){
				tsid.setText(""+rs.getInt(1));
				tfnm.setText(rs.getString(2));
				tpnm.setText(rs.getString(3));
				tlnm.setText(rs.getString(4));
				tcon.setText(rs.getString(5));
				taddr.setText(rs.getString(9));
				tdt.setText(rs.getString(10));
				
				school.select(rs.getInt(8));
				if(rs.getInt(6)==1)
					cggen.setSelectedCheckbox(cgen[0]);
				else
					cggen.setSelectedCheckbox(cgen[1]);
				if(rs.getInt(7)==1)
					cgcs.setSelectedCheckbox(ccs[0]);
				else
					cgcs.setSelectedCheckbox(ccs[1]);
			}
	}


	public void clear(){
		tsid.setText(null);
		tfnm.setText(null);
		tpnm.setText(null);
		tlnm.setText(null);
		taddr.setText(null);
		tdt.setText(null);
		tcon.setText(null);

		ccs[0].setState(false);
		ccs[1].setState(false);
		cgen[0].setState(false);
		cgen[1].setState(false);
	}
}
