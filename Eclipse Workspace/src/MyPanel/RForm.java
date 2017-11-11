	package MyPanel;

import Entity.Student;
import Entity.Money;
import Query.InsertDB;
import Query.UpdateDB;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RForm extends JDialog implements ActionListener, ItemListener, FocusListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel []lbl;
	JLabel lerr;
	public JTextField tsid,tfnm,tpnm,tlnm,tcon,tdt;
	public JTextArea taddr;
	public Checkbox []ccs;
	public Checkbox []cgen;
	public CheckboxGroup cgcs,cggen;
	public Choice school;
	public JButton bsign,bhome;
	public Font fobj;
	public Font fobj2;
	public Student sobj;
	JFrame parent;
	InsertDB ins;
	UpdateDB updt;
	int flg1,flg2,flg3;


	public RForm(JFrame a,String title,Boolean bm){

		super(a,title,bm);
		parent=a;
		flg1=flg2=flg3=0;										//two checks are 3&7
		ins=new InsertDB();
		updt=new UpdateDB();
		sobj=new Student();
//Initialising Components		
		fobj=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,24);
		
		lerr=new JLabel("*Do not leave fields empty");lerr.setFont(new Font("Roman",Font.BOLD,30));lerr.setForeground(Color.RED);
		lbl=new JLabel[12];
		for(int i=0;i<12;i++)
			lbl[i]=new JLabel();
		lbl[0].setText("REGISTRATION FORM");lbl[0].setFont(new Font("Roman",Font.BOLD,30));
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
		
		bsign=new JButton("Register");
		bhome=new JButton("Home");
		bsign.addActionListener(this);bsign.setFont(new Font("Roman",Font.PLAIN,20));
		bhome.addActionListener(this);bhome.setFont(new Font("Roman",Font.PLAIN,20));

//Adding Components
		add(lerr);
		for(int i=0;i<12;i++)
			add(lbl[i]);
		add(tsid);add(tfnm);add(tpnm);add(tlnm);add(tcon);add(tdt);
		add(taddr);
		add(ccs[0]);add(ccs[1]);add(cgen[0]);add(cgen[1]);
		add(school);
		add(bsign);add(bhome);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try{
					updt.cClose();
					ins.cClose();
				}
				catch(Exception e1){System.out.println(""+e1);}
				parent.setVisible(true);
				dispose();
			}
		});

		setLayout(null);

//Setting layouts of the Components		
		lbl[0].setBounds(200,10,400,80);bhome.setBounds(620,20,120,60);
		lbl[1].setBounds(30,110,180,60);tsid.setBounds(210,120,100,40);
		lbl[3].setBounds(210,180,190,40);lbl[4].setBounds(410,180,190,40);	lbl[5].setBounds(610,180,190,40);
		lbl[2].setBounds(30,210,180,60);tfnm.setBounds(210,220,190,40);tpnm.setBounds(410,220,190,40);tlnm.setBounds(610,220,190,40);
		lbl[6].setBounds(30,290,180,60);tcon.setBounds(210,300,200,40); 
		lbl[7].setBounds(30,370,180,60);ccs[0].setBounds(210,380,80,40);ccs[1].setBounds(290,380,80,40); 
		lbl[8].setBounds(400,370,80,60);cgen[0].setBounds(480,380,120,40);cgen[1].setBounds(600,380,120,40);
		lbl[9].setBounds(30,450,180,60);school.setBounds(210,460,500,60);
		lbl[10].setBounds(30,530,180,60);taddr.setBounds(210,540,400,160);
		lbl[11].setBounds(30,710,180,60);tdt.setBounds(210,720,200,40);
		bsign.setBounds(250,800,300,60);
		lerr.setBounds(200,860,400,60);

		lerr.setVisible(false);
		
		setSize(850,1000);
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);		


	}

	public void itemStateChanged(ItemEvent e) {
		
		try{
			Checkbox cb=(Checkbox)e.getSource();
			CheckboxGroup cbg=cb.getCheckboxGroup();
			if(cbg==cgcs){
				flg1=1;
				if(cb==ccs[0])
					sobj.std=1;
				else
					sobj.std=2;
			}
			if(cbg==cggen){
				flg2=1;
				if(cb==cgen[0])
					sobj.gen=1;
				else
					sobj.gen=2;
			}
		}
		catch(Exception e1){}
		
		try{
			Choice ch=(Choice)e.getSource();
			if(ch==school){
				if(ch.getSelectedIndex()==0){
					flg3=0;
					return;
				}
				flg3=1;
				sobj.school=ch.getSelectedIndex();
			}
		}
		catch(Exception e1){}
		
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
	public void actionPerformed(ActionEvent e) {
		JButton b=(JButton)e.getSource();
		if(b==bsign)
		{	
			if(tsid.getText().trim().isEmpty()){
				tsid.requestFocus();lerr.setVisible(true);
			}
			else if(tfnm.getText().trim().isEmpty()){
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
			else if(flg1==0){
				System.out.println("Class Is not Selected");lerr.setVisible(true);
			}
			else if(flg2==0){
				System.out.println("Gender is not selected");lerr.setVisible(true);
			}
			else if(flg3==0){
				System.out.println("School is not Selected");lerr.setVisible(true);
			}
			else if(tcon.getText().length()<10){
				tcon.setText("");lerr.setVisible(true);
				tcon.requestFocus();
			}
			else if(tdt.getText().length()<8||tdt.getText().length()>10){
				tdt.setText("");lerr.setVisible(true);
				tdt.requestFocus();
			}
			else{
				//Inserting data into Student Object
				lerr.setVisible(false);
				try{
				sobj.sid=Integer.parseInt(tsid.getText());
				}
				catch(Exception e1){
					tsid.setText(null);lerr.setVisible(true);
					tsid.requestFocus();
					return;
				}
				sobj.fname=tfnm.getText();
				sobj.pname=tpnm.getText();
				sobj.lname=tlnm.getText();
				sobj.cont=tcon.getText();
				sobj.addr=taddr.getText();
				sobj.doa=tdt.getText();
				Money m=new Money();
				m.openFee();
				sobj.amt=m.fee;
				sobj.mth=1;

				try{ins.sAdd(sobj);}
				catch(Exception e1){JOptionPane.showMessageDialog(null,"Alert","Error in Insertion",JOptionPane.ERROR_MESSAGE);}
				clear();
			}
		}
		if(b==bhome){
			clear();
			try{	
				updt.cClose();
				ins.cClose();
				parent.setVisible(true);
			}
			catch(Exception e1){System.out.println(""+e1);}
			setVisible(false);
		}
	}
/*
	public void ini(){
		Money m=new Money();
		m.fee=900;
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;	
		try{
			fos=new FileOutputStream("money.dat");
			oos=new ObjectOutputStream(fos);

			oos.writeObject(m);
			fos.close();
			oos.close();
		}
		catch(Exception e){System.out.println(""+e);}
	}

	public void initPay(Student obj){
		Money m=new Money();
		try{
			FileInputStream fis=null;
			ObjectInputStream ois=null;
			try{
				fis=new FileInputStream("money.dat");
				ois=new ObjectInputStream(fis);

				m=(Money)ois.readObject();

				fis.close();
				ois.close();
			}
			catch(Exception e){System.out.println("Money$$"+e);}

		}catch(Exception e1){System.out.println("Money Problem"+e1);}
		System.out.println("Fees::::"+m.fee);
		try{updt.pMod(obj.sid,m.fee,1);}
		catch(Exception e1){System.out.println("Problem"+e1);}
	}
*/
	public void focusLost(FocusEvent e){
	}

	public void focusGained(FocusEvent e){
	}
}
