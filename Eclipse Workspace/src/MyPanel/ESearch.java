package MyPanel;

import Query.SearchDB;
import MyPanel.MForm;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.sql.*;

public class ESearch extends JDialog implements ActionListener, ItemListener,FocusListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public CheckboxGroup cgs,cgcls,cgres;
	public Checkbox csid,cfnm,ccls,cscl,cuf,cpf,ccs1,ccs2;
	public Checkbox []cres;
	public JTextField tfnm,tsid;
	JLabel ls;
	JLabel []lsid;
	JLabel []lnm;
	JLabel []lcon;
	public JButton bsrch,bsel,bhome;
	public Choice scl;
	SearchDB srch;
	Font fobj1,fobj2;
	int flg,flg2;
	JFrame parent;

	public ESearch(JFrame a,String title,Boolean bm){

		super(a,title,bm);

		parent=a;
		flg=flg2=0;

		srch=new SearchDB();
		fobj1=new Font("Roman",Font.BOLD,15);
		fobj2=new Font("Roman",Font.PLAIN,20);
		cgs=new CheckboxGroup();
		cgcls=new CheckboxGroup();
		cgres=new CheckboxGroup();

		csid=new Checkbox("Student ID");csid.addItemListener(this);csid.setCheckboxGroup(cgs);csid.setFont(fobj1);
		cfnm=new Checkbox("First Name");cfnm.addItemListener(this);cfnm.setCheckboxGroup(cgs);cfnm.setFont(fobj1);
		ccls=new Checkbox("Class");ccls.addItemListener(this);ccls.setCheckboxGroup(cgs);ccls.setFont(fobj1);
		cscl=new Checkbox("School");cscl.addItemListener(this);cscl.setCheckboxGroup(cgs);cscl.setFont(fobj1);
		cuf=new Checkbox("Unpaid Fees");cuf.addItemListener(this);cuf.setCheckboxGroup(cgs);cuf.setFont(fobj1);
		cpf=new Checkbox("Paid Fees");cpf.addItemListener(this);cpf.setCheckboxGroup(cgs);cpf.setFont(fobj1);

		ccs1=new Checkbox("IX");ccs1.addItemListener(this);
		ccs1.setCheckboxGroup(cgcls);ccs1.setFont(fobj2);
		ccs2=new Checkbox("X");ccs2.addItemListener(this);
		ccs2.setCheckboxGroup(cgcls);ccs2.setFont(fobj2);

		scl=new Choice();scl.addItemListener(this);
		scl.setFont(fobj2);
		scl.add("Select school");
		scl.add("1. Mount Carmel");
		scl.add("2. Carmel Academy");
		scl.add("3. Vidya Niketan");
		scl.add("4. St. Michael School");
		scl.add("Other");


		ls=new JLabel("SEARCH");ls.setFont(new Font("Roman",Font.BOLD,30));

		tsid=new JTextField();tsid.setEditable(false);tsid.setFont(fobj2);tsid.addFocusListener(this);
		tfnm=new JTextField();tfnm.setEditable(false);tfnm.setFont(fobj2);tfnm.addFocusListener(this);

		bhome=new JButton("Home");bhome.addActionListener(this);bhome.setFont(fobj2);
		bsrch=new JButton("Search");bsrch.addActionListener(this);bsrch.setFont(fobj2);
		bsel=new JButton("Select");bsel.addActionListener(this);bsel.setFont(fobj2);

		lsid=new JLabel[12];
		lnm=new JLabel[12];
		lcon=new JLabel[12];

		cres=new Checkbox[12];

		lsid[0]=new JLabel("Student ID");lsid[0].setFont(fobj1);
		lnm[0]=new JLabel("Name");lnm[0].setFont(fobj1);
		lcon[0]=new JLabel("Contact");lcon[0].setFont(fobj1);

		for(int i=1;i<12;i++){
			lsid[i]=new JLabel();add(lsid[i]);lsid[i].setFont(fobj2);
			lnm[i]=new JLabel();add(lnm[i]);lnm[i].setFont(fobj2);
			lcon[i]=new JLabel();add(lcon[i]);lcon[i].setFont(fobj2);
			
			cres[i]=new Checkbox(""+i,cgres,false);cres[i].setFont(fobj2);
			cres[i].addItemListener(this);
		}

		add(lsid[0]);add(lnm[0]);add(lcon[0]);
		add(ls);
		add(csid);add(cfnm);add(ccls);add(cscl);add(cuf);add(cpf);
		add(ccs1);add(ccs2);
		add(tsid);add(tfnm);
		add(scl);
		add(bhome);add(bsel);add(bsrch);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				try{
					srch.cClose();
				}
				catch(Exception e1){System.out.println(""+e1);}
				parent.setVisible(true);
				dispose();	
			}
		});

		setLayout(null);

		ls.setBounds(200,10,400,80);bhome.setBounds(620,20,120,60);
		csid.setBounds(20,100,180,40);tsid.setBounds(200,100,100,40);
		cfnm.setBounds(20,150,180,40);tfnm.setBounds(200,150,200,40);
		ccls.setBounds(20,200,180,40);ccs1.setBounds(200,200,80,40);ccs2.setBounds(280,200,80,40);
		cscl.setBounds(20,250,180,40);scl.setBounds(200,250,500,40);
		cuf.setBounds(20,300,180,40);
		cpf.setBounds(20,350,180,40);
		bsrch.setBounds(300,390,200,40);
		lsid[0].setBounds(100,430,100,40);lnm[0].setBounds(200,430,450,40);lcon[0].setBounds(650,430,200,40);
		bsel.setBounds(300,910,200,40);

		for(int i=1;i<12;i++){
			int j=430+(40*i);
			cres[i].setBounds(50,j,50,40);lsid[i].setBounds(100,j,100,40);lnm[i].setBounds(200,j,450,40);lcon[i].setBounds(650,j,200,40);
		}
		setSize(850,1000);
		parent.setVisible(false);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		if(b==bsrch){
			clearLabel();
			Checkbox cb=cgs.getSelectedCheckbox();
			if(cb==csid){
				if(tsid.getText().trim().isEmpty()){
					tsid.requestFocus();
				}
				try{
					int a=Integer.parseInt(tsid.getText());
					ResultSet rs=srch.sidSearch(a);
					fillLabel(rs);
				}
				catch(Exception e1){}
				
			}
			else if(cb==cfnm){
				if(tfnm.getText().trim().isEmpty()){
					tfnm.requestFocus();
				}
				try{
				ResultSet rs=srch.fnmSearch(tfnm.getText());
				fillLabel(rs);
				}
				catch(Exception e1){}
				
			}
			else if(cb==ccls){
				int k=0;
				if(cgcls.getSelectedCheckbox()==ccs1)
					k=1;
				else
					k=2;
				try{
				ResultSet rs=srch.clsSearch(k);
				fillLabel(rs);
				}
				catch(Exception e1){}
			}
			else if(cb==cscl){
				int a=scl.getSelectedIndex();
				try{
				ResultSet rs=srch.sclSearch(a);
				fillLabel(rs);
				}
				catch(Exception e1){}
			}
			else if(cb==cuf){
				try{
				ResultSet rs=srch.ufSearch();
				fillLabel(rs);
				}
				catch(Exception e1){}
			}
			else if(cb==cpf){
				try{
				ResultSet rs=srch.pfSearch();
				fillLabel(rs);
				}
				catch(Exception e1){}
			}
		}
		if(b==bsel){
			if(flg==0||flg2==0)
				return;
			Checkbox cb=cgres.getSelectedCheckbox();
			int a=Integer.parseInt(cb.getLabel());
			int k=Integer.parseInt(lsid[a].getText());
			System.out.println(""+a+" "+k);
			@SuppressWarnings("unused")
			MForm mod=new MForm(this,"Modify",true,k);
			flg2=0;
		}
		if(b==bhome){
			try{
					srch.cClose();
			}
			catch(Exception e1){System.out.println(""+e1);}
			parent.setVisible(true);
			setVisible(false);
		}
	}


	public void itemStateChanged(ItemEvent e){
		tsid.setEditable(false);
		tfnm.setEditable(false);
		try{
			Checkbox cb=(Checkbox)e.getSource();
			if(cb==csid)
			tsid.setEditable(true);
			if(cb==cfnm)
				tfnm.setEditable(true);
			if(cb.getCheckboxGroup()==cgres){
				flg=1;
			}
		}
		catch(Exception e1){}
	}


	public void focusLost(FocusEvent e){}
	public void focusGained(FocusEvent e){}


	void fillLabel(ResultSet rs)throws Exception{	
		int i=1;
		while(rs.next()){
			flg2=1;																	//indicates that result found
			lsid[i].setText(""+rs.getInt(1));
			lnm[i].setText(""+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			lcon[i].setText(""+rs.getString(5));
			add(cres[i]);
			i++;
		}
	}


	void clearLabel(){
		for(int i=1;i<12;i++){
			lsid[i].setText(null);
			lnm[i].setText(null);
			lcon[i].setText(null);
			remove(cres[i]);
		}
	}
}